import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static String PATH_A = "src\\input\\a_example.txt";
	public static String PATH_B = "src\\input\\b_lovely_landscapes.txt";
	public static String PATH_C = "src\\input\\c_memorable_moments.txt";
	public static String PATH_D = "src\\input\\d_pet_pictures.txt";
	public static String PATH_E = "src\\input\\e_shiny_selfies.txt";

	public static String PATH_A_OUTPUT = "src\\output\\a_output.txt";
	public static String PATH_B_OUTPUT = "src\\output\\b_output.txt";
	public static String PATH_C_OUTPUT = "src\\output\\c_output.txt";
	public static String PATH_D_OUTPUT = "src\\output\\d_output.txt";
	public static String PATH_E_OUTPUT = "src\\output\\e_output.txt";

	public static String[] paths = new String[] { PATH_B };
	public static String[] paths_output = new String[] { PATH_B_OUTPUT };

	public static void main(String[] args) throws IOException {
		int ind = 0;

		for (String path : paths) {
			System.out.println(ind);
			Scanner scanner = createScaner(path);

			ArrayList<Photo> photos = createPhotos(scanner);

			ArrayList<Photo> horizontal = new ArrayList<>();
			ArrayList<Photo> vertical = new ArrayList<>();

			for (Photo photo : photos) {
				if (photo.type == 'H')
					horizontal.add(photo);
			}

			for (Photo photo : photos) {
				if (photo.type == 'V')
					vertical.add(photo);
			}

			// Collections.shuffle(vertical);

			horizontal.addAll(mergeVerticals(vertical));

			Collections.shuffle(horizontal);

			// = photos.removeIf(photo -> photo.type == 'V');

			ArrayList<Photo> solution = solution1(horizontal);

			// System.out.println(solution.size());
			// for (Photo photo : solution) {
			// System.out.println(photo.id);
			// }

			writeToFile(paths_output[ind], solution);

			ind++;
		}

	}

	private static ArrayList<Photo> mergeVerticals(ArrayList<Photo> vertical) {
		if (vertical.size() == 0) {
			return new ArrayList<>();
		}

		ArrayList<Photo> horizontals = new ArrayList<>();
		Photo current = vertical.get(0);
		for (int i = 1; i < vertical.size(); i += 2) {
			horizontals.add(merge(current, vertical.get(i)));

			if (i + 1 <= vertical.size() - 2) {
				current = vertical.get(i + 1);
			} else {
				break;
			}
		}

		return horizontals;
	}

	public static ArrayList<Photo> solution1(ArrayList<Photo> photos) {
		int maxDiff = 8;
		ArrayList<Photo> solution = new ArrayList<>();

		Photo current = photos.get(0);
		current.used = true;
		solution.add(current);

		while (solution.size() < photos.size()) {
			if (solution.size() % 1000 == 0)
				System.out.println(solution.size());
			boolean found = false;

			for (Photo photo : photos) {
				if (!photo.used) {
					int diff = calculateMinimun(current, photo);
					if (diff >= maxDiff) {
						solution.add(photo);
						current = photo;
						found = true;
						photo.used = true;
						maxDiff = 8;
						break;
					}
				}
			}

			if (!found) {
				maxDiff--;
			}
		}

		return solution;
	}

	public static ArrayList<Photo> createPhotos(Scanner scanner) {
		int N;

		String nphotos = scanner.nextLine();
		N = Integer.valueOf(nphotos);

		ArrayList<Photo> photos = new ArrayList<Photo>();

		for (int i = 0; i < N; i++) {
			String photo = scanner.nextLine();
			String[] split = photo.split(" ");

			char type = split[0].charAt(0);
			int nTags = Integer.valueOf(split[1]);
			ArrayList<String> tags = new ArrayList<>();
			for (int j = 0; j < nTags; j++) {
				tags.add(split[j + 2]);
			}

			photos.add(new Photo(String.valueOf(i), type, nTags, tags));
		}
		return photos;
	}

	public static Scanner createScaner(String path) throws FileNotFoundException {
		File file = new File(path);
		Scanner sc = new Scanner(file);
		return sc;
	}

	public static void writeToFile(String path, ArrayList<Photo> solution) throws IOException {
		File fout = new File(path);
		FileOutputStream fos = new FileOutputStream(fout);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		bw.write(String.valueOf(solution.size()));
		bw.newLine();

		for (int i = 0; i < solution.size(); i++) {
			bw.write(String.valueOf(solution.get(i).id));
			bw.newLine();
		}

		bw.close();
	}

	public static Photo merge(Photo a, Photo b) {

		ArrayList<String> newTags = new ArrayList<>();
		newTags.addAll(b.tags);

		for (String tag : a.tags) {
			if (!b.tags.contains(tag)) {
				newTags.add(tag);
			}
		}

		return new Photo(a.id + " " + b.id, 'H', newTags.size(), newTags);
	}

	public static int calculateMinimun(Photo pic1, Photo pic2) {
		ArrayList<String> tags1 = pic1.tags;
		ArrayList<String> tags2 = pic2.tags;
		ArrayList<String> commonTags = new ArrayList<String>();

		for (int i = 0; i < tags1.size(); i++) {
			if (tags2.contains(tags1.get(i))) {
				commonTags.add(tags1.get(i));
			}
			;
		}

		return Integer.min(Integer.min(tags1.size() - commonTags.size(), tags2.size() - commonTags.size()),
				commonTags.size());
	}

}

class Photo {
	public String id;
	public char type;
	public int nTags;
	public ArrayList<String> tags;
	public boolean used;

	public Photo(String id, char type, int nTags, ArrayList<String> tags) {
		this.id = id;
		this.type = type;
		this.nTags = nTags;
		this.tags = tags;
		this.used = false;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", type=" + type + ", nTags=" + nTags + ", tags=" + tags + ", used=" + used + "]";
	}

}