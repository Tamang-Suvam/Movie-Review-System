package manager.moviemanager;

import entity.movies.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class MovieManager {

    private HashMap<String, Movie> movieMap;

    public MovieManager() {
        this.movieMap = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movieMap.putIfAbsent(movie.getTitle(), movie);
        // String movieTitle = movie.getTitle();
        // if(movieMap.containsKey(movieTitle)) {
        //     System.out.println("Looks like the movie with the same name exists already!");
        // } else {
        //     movieMap.put(movieTitle, movie);
        // }
    }

    public Movie getMovie(String movieId) {
        return movieMap.get(movieId);
    }

    public List<Movie> getMovies() {
        return new ArrayList<>(movieMap.values());
    }

}