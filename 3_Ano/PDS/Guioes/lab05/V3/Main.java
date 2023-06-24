package lab05.V3;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person writer = new Person("Tarantino", 40);
        Place place = new Place("Merica", "USA");
        Movie myMovie = new Movie.Builder("Batman", 1990)
                .director(writer)
                .genres(Arrays.asList("Action", "Romance", "Drama"))
                .languages(List.of("English"))
                .writer(writer)
                .isTelevision(true)
                .locations(List.of(place))
                .build();
        System.out.println(myMovie);
    }
}
