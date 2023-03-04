import services.MovieService;
import services.UserService;
import services.ReviewService;

import java.util.ArrayList;
import java.util.List;

import exceptions.MovieYetNotReleasedException;
import exceptions.MultipleReviewPerMovieException;
import manager.moviemanager.MovieManager;
import manager.usermanager.UserManager;
import manager.reviewmanager.ReviewManager;


public class Main {
    public static void main(String [] args) {
        List<String> result = new ArrayList<>();
        // First Adding the given movies
        final MovieManager MovieManager = new MovieManager();
        MovieService movieService = new MovieService(MovieManager);
        movieService.addMovie("Don", "Action", 2006);
        movieService.addMovie("Tiger", "Drama", 2008);
        movieService.addMovie("Padmaavat", "Comedy", 2006);
        movieService.addMovie("Lunchbox-2", "Drama", 2030);
        movieService.addMovie("Guru", "Drama", 2006);
        movieService.addMovie("Metro", "Romance", 2006);
        System.out.println("All movies have been successfully added!");

        // Now adding all the users
        final UserManager UserManager = new UserManager();
        UserService userService = new UserService(UserManager);
        userService.addUser("SRK");
        userService.addUser("Salman");
        userService.addUser("Deepika");
        System.out.println("All users have been successfully added!");

        // Now performing the required operations as have been asked in the question
        final ReviewManager ReviewManager = new ReviewManager();
        ReviewService reviewService = new ReviewService(userService, movieService, ReviewManager);
        try {
            reviewService.addReview("SRK", "Don", 2);
            reviewService.addReview("SRK", "Padmaavat", 8);
            reviewService.addReview("Salman", "Don", 5);
            reviewService.addReview("Deepika", "Don", 9);
            reviewService.addReview("Deepika", "Guru", 6);
            
            /***************************************************************************************/
            // Exception: Same user reviewing same movie twice! 
            // reviewService.addReview("SRK","Don", 10);

            // Exception: User reviewing a movie not yet released!
            // reviewService.addReview("Deepika", "Lunchbox-2", 5);
            /***************************************************************************************/

            reviewService.addReview("SRK", "Tiger", 5);
            reviewService.addReview("SRK", "Metro", 7);
        } catch (MovieYetNotReleasedException | MultipleReviewPerMovieException e) {
            e.printStackTrace();
        }
        result = reviewService.getTopRatedMoviesByViewerInAYear(2006, 1);
        System.out.println("Top 1 movie by review score in “2006” year: " + result);
    }
}
