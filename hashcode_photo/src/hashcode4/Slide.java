package hashcode4;

import java.util.*;
import com.google.common.collect.Sets;

import static java.lang.Math.min;
import static java.util.*;

public class Slide {

    List<Photo> photos;

    public Slide(List<Photo> photos) {
        if (this.isOk(photos)) {
            this.photos = photos;
        } else {
            throw new RuntimeException("Slide not ok");
        }
    }

    public Slide(Photo... photos) {
        this(asList(photos));
    }

    int score(Slide o) {
        Sets.SetView<String> intersection = Sets.intersection(getAllTags(), o.getAllTags());
        Set<String> thisTags = getAllTags();
        Set<String> otherTags = o.getAllTags();
        thisTags.removeAll(intersection);
        otherTags.removeAll(intersection);
        return min(intersection.size(), min(thisTags.size(), otherTags.size()));
    }

    public String getPrintString() {
        return photos.stream()
                .map(photo -> photo.id)
                .map(String::valueOf)
                .collect(joining(" "));
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    private Set<String> getAllTags() {
        return photos.stream()
                .map(photo -> photo.tags)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    // Max 1 Horizontal or 2 Vertical
    private boolean isOk(List<Photo> photos) {
        switch (photos.size()) {
            case 1:
                return photos.get(0).getOrientation() == Orientation.HORIZONTAL;
            case 2:
                return photos.stream().allMatch(x -> x.getOrientation() == Orientation.VERTICAL);
            default:
                return false;
        }
    }

    public Set<String> getTags() {
        return photos.stream().map(p -> p.tags).flatMap(Collection::stream).collect(Collectors.toSet());
    }

}