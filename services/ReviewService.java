package services;

import manager.reviewmanager.ReviewManager;
import enums.UserType;
import enums.Rating;
import exceptions.MovieHasNoReviews;
import exceptions.MovieYetNotReleasedException;
import exceptions.MultipleReviewPerMovieException;
import entity.movies.Movie;
import entity.reviews.Review;
import entity.users.User;
import entity.moviereview.MovieReview;

import java.util.*;

public class ReviewService {

    private ReviewManager reviewManager;
    private UserService userService;
    private MovieService movieService;

    public ReviewService(UserService userService, MovieService movieService, ReviewManager reviewManager) {
        this.reviewManager = reviewManager;
        this.userService = userService;
        this.movieService = movieService;
    }

    public void addReview(String userName, String title, int rating) throws MovieYetNotReleasedException, MultipleReviewPerMovieException {
        Movie movie = movieService.getMovie(title);
        if(movie == null)
            throw new IllegalArgumentException("Invalid Movie");
        User user = userService.getUser(userName);
        if(user == null)
            throw new IllegalArgumentException("Invalid user");

        // Rating should be between 1 & 10
        checkRating(rating);

        // Reviews cannot be given for a movie that is yet to be released
        if(movie.getReleaseDate() > 2023)
            throw new MovieYetNotReleasedException();

        // A person cannot review the same movie multiple times
        List<String> moviesReviewedByUser = moviesReviewedByUser(userName);
        if(moviesReviewedByUser.contains(title))
            throw new MultipleReviewPerMovieException();

        // If the amount of votes the user already gave has been atleasr 3 promote him from viewer to critic
        if(userService.isUserUpgradable(user, moviesReviewedByUser.size()))
            userService.upgradeUser(user);

        rating *= user.getRole().getReviewFactor();

        UserType userType = user.getRole().getUserType();

        Review review = new Review(userName, title, rating, 2023, userType);
        reviewManager.addReview(review);
    }

    public void checkRating(int value) {
        boolean found = false;
        Rating[] ratings = Rating.values();
        for(Rating rating : ratings) {
            if(rating.getRating() == value) {
                found = true;
                break;
            }
        }
        if(!found)
            throw new IllegalArgumentException(String.format("Rating should be in between: %s and %s", ratings[0], ratings[ratings.length-1]));
    }

    public Integer getReview(String movieTitle) throws MovieHasNoReviews {
        for(Review review : reviewManager.getReviews()) {
            if(review.getMovieName().equalsIgnoreCase(movieTitle))
                return review.getRating();
        }
        throw new MovieHasNoReviews();
    }

    public List<String> moviesReviewedByUser(String userName) {
        List<String> movies = new ArrayList<>();
        for(Review review : reviewManager.getReviews()) {
            if(review.getUserName().equals(userName))
                movies.add(review.getMovieName());
        }
        return movies;
    }

    public Double getAvgMovieScore(String movieTitle) {
        int sum = 0;
        int count = 0;
        for(Review review : reviewManager.getReviews()) {
            if(review.getMovieName().equalsIgnoreCase(movieTitle)) {
                sum += review.getRating();
                count++;
            }
        }
        Double res = (double)sum/count;
        res = Math.round(res*100.0)/100.0;
        return res;
    }

    public Double getAvgReviewScoreInParticularYear(int year) {
        int sum = 0;
        int count = 0;
        // Calendar calendar = new GregorianCalendar();
        for(Review review : reviewManager.getReviews()) {
            Movie movie = movieService.getMovie(review.getMovieName());
            // calendar.setTime(movie.getReleaseDate());
            // if(calendar.get(Calendar.YEAR) == year) {
            //     sum += review.getRating();
            //     count++;
            // }
            if(movie.getReleaseDate() == year) {
                sum += review.getRating();
                count++;
            }
        }
        Double res = (double)sum/count;
        res = Math.round(res*100.0)/100.0;
        return res;
    }

    private List<MovieReview> getTopRatedMoviesAndReviewsByCriticInAGenre(String genre, int n) {
        PriorityQueue<MovieReview> pQ = new PriorityQueue<>(n, MovieReview.getComparator());
        Map<Movie, Integer> movieReviewMap = new HashMap<>();
        List<MovieReview> res = new ArrayList<>();

        for(Review review : reviewManager.getReviews()) {
            Movie movie = movieService.getMovie(review.getMovieName());
            if(movie.getGenre() == genre && review.getUserType().equals(UserType.CRITIC)) {
                if(movieReviewMap.containsKey(movie)){
                    movieReviewMap.put(movie, movieReviewMap.get(movie)+review.getRating());
                } else {
                    movieReviewMap.put(movie, review.getRating());
                }
            }
        }

        for(Map.Entry<Movie, Integer> entry : movieReviewMap.entrySet()) {
            pQ.add(new MovieReview(entry.getKey(), entry.getValue()));
        }

        while(pQ.size() > 0 && n > 0) {
            res.add(pQ.poll());
            n--;
        }

        return res;
    }

    public List<String> getTopRatedMoviesByCriticInAGenre(String genre, int n) {
        List<String> result = new ArrayList<>();
        List<MovieReview> movieReviews = getTopRatedMoviesAndReviewsByCriticInAGenre(genre, n);
        for (MovieReview mr : movieReviews) {
            result.add(mr.getMovie().getTitle());
        } 
        return result;
    }
    
    private List<MovieReview> getTopRatedMoviesAndReviewsByViewerInAYear(int year, int n) {
        // Genre parsedGenre = ValidationUtil.parseEnum(Genre.class, genre);
        PriorityQueue<MovieReview> pQ = new PriorityQueue<>(n, MovieReview.getComparator());
        Map<Movie, Integer> movieReviewMap = new HashMap<>();
        List<MovieReview> res = new ArrayList<>();

        for(Review review : reviewManager.getReviews()) {
            Movie movie = movieService.getMovie(review.getMovieName());
            if(movie.getReleaseDate() == year && review.getUserType().equals(UserType.VIEWER)) {
                if(movieReviewMap.containsKey(movie)){
                    movieReviewMap.put(movie, movieReviewMap.get(movie)+review.getRating());
                } else {
                    movieReviewMap.put(movie, review.getRating());
                }
            }
        }

        for(Map.Entry<Movie, Integer> entry : movieReviewMap.entrySet()) {
            pQ.add(new MovieReview(entry.getKey(), entry.getValue()));
        }

        while(pQ.size() > 0 && n > 0) {
            res.add(pQ.poll());
            n--;
        }

        return res;
    }

    public List<String> getTopRatedMoviesByViewerInAYear(int year, int n) {
        List<String> result = new ArrayList<>();
        List<MovieReview> movieReviews = getTopRatedMoviesAndReviewsByViewerInAYear(year, n);
        for (MovieReview mr : movieReviews) {
            result.add(mr.getMovie().getTitle());
        } 
        return result;
    }
}