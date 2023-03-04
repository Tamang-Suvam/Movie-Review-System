// import enums.Genre;
// import java.util.Date;
package entity.movies;

public class Movie {
    private String title;
    private String genre;
    private int releaseDate;

    public Movie(String title, int releaseDate, String genre) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    // @Override
    // public boolean equals(Object o) {
    //     Movie movie = null;
    //     if(o instanceof Movie)
    //         movie = (Movie) o;
    //     else
    //         return false;
    //     return (this.getTitle().equalsIgnoreCase(movie.getTitle()));
    // }

    // @Override
    // public int hashCode() {
    //     return super.hashCode();
    // }
}
