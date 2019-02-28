package hashcode4;

public class Principal {

	static final String INPUT_A = "a_example.txt";
    static final String INPUT_B = "b_lovely_landscapes.txt";
    static final String INPUT_C = "c_memorable_moments.txt";
    static final String INPUT_D = "d_pet_pictures.txt";
    static final String INPUT_E = "e_shiny_selfies.txt";


	public static void main(String[] args) {
		InputParser parser = new InputParser();
        Stream.of(INPUT_A, INPUT_B, INPUT_C, INPUT_D, INPUT_E)
                .forEach(input -> {
                    try {
                        List<Photo> photos = parser.parse(input);
                        List<Slide> slides = run(photos);
                        write(input, slides);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
		 

	}
	
	private static void sysout() {
		// TODO Auto-generated method stub
		System.out.println("hello world");
	}

}
