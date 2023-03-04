package services;

import manager.moviemanager.MovieManager;
import entity.movies.Movie;

public class MovieService {

    private MovieManager movieManager;

    public MovieService(MovieManager movieManager) {
        this.movieManager = movieManager;
    }

    public void addMovie(String title, String genre, int releaseDate) throws IllegalArgumentException {
        if(title == null || title.isEmpty())
            throw new IllegalArgumentException("Movie title cannot be null or empty");

        Movie movie = new Movie(title, releaseDate, genre);

        movieManager.addMovie(movie);
    }

    public Movie getMovie(String title) {
        return movieManager.getMovie(title);
    }
}