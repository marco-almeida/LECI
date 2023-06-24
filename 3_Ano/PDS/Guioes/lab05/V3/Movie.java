package lab05.V3;

import java.util.List;

public class Movie {
    private final String title;
    private final int year;
    private final Person director;
    private final Person writer;
    private final String series;
    private final List<Person> cast;
    private final List<Place> locations;
    private final List<String> languages;
    private final List<String> genres;
    private final boolean isTelevision;
    private final boolean isNetflix;
    private final boolean isIndependent;

    private Movie(Builder b) {
        title = b.title;
        year = b.year;
        director = b.director;
        writer = b.writer;
        series = b.series;
        cast = b.cast;
        locations = b.locations;
        languages = b.languages;
        genres = b.genres;
        isIndependent = b.isIndependent;
        isNetflix = b.isNetflix;
        isTelevision = b.isTelevision;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", director=" + director +
                ", writer=" + writer +
                ", series='" + series + '\'' +
                ", cast=" + cast +
                ", locations=" + locations +
                ", languages=" + languages +
                ", genres=" + genres +
                ", isTelevision=" + isTelevision +
                ", isNetflix=" + isNetflix +
                ", isIndependent=" + isIndependent +
                '}';
    }

    public static class Builder {
        // Required
        private final String title;
        private final int year;
        // optional
        private Person writer;
        private Person director;
        private String series;
        private List<Person> cast;
        private List<Place> locations;
        private List<String> languages;
        private List<String> genres;
        private boolean isTelevision;
        private boolean isNetflix;
        private boolean isIndependent;

        public Builder (String title, int year) {
            this.title = title;
            this.year = year;
        }

        public Movie build() {
            return new Movie(this);
        }

        public Builder writer(Person writer) {
            this.writer = writer;
            return this;
        }
        public Builder director(Person director) {
            this.director = director;
            return this;
        }

        public Builder series(String series) {
            this.series = series;
            return this;
        }

        public Builder cast(List<Person> cast) {
            this.cast = cast;
            return this;
        }

        public Builder locations(List<Place> locations) {
            this.locations = locations;
            return this;
        }

        public Builder languages(List<String> languages) {
            this.languages = languages;
            return this;
        }

        public Builder genres(List<String> genres) {
            this.genres = genres;
            return this;
        }

        public Builder isTelevision(boolean isTelevision) {
            this.isTelevision = isTelevision;
            return this;
        }

        public Builder isNetflix(boolean isNetflix) {
            this.isNetflix = isNetflix;
            return this;
        }

        public Builder isIndependent(boolean isIndependent) {
            this.isIndependent = isIndependent;
            return this;
        }
    }
}
