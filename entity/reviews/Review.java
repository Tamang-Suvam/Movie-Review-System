package entity.reviews;

import java.util.UUID;
import enums.UserType;

public class Review {
    private final String id;
    private final String movieName;
    private final String userName;
    private final int rating;
    private final int yearOfReview;
    private final UserType userType;

    public Review(String userName, String movieName, int rating, int year, UserType userType) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.movieName = movieName;
        this.rating = rating;
        this.yearOfReview = year;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }
    
    public String getMovieName() {
        return movieName;
    }

    public String getUserName() {
        return userName;
    }

    public int getRating() {
        return rating;
    }

    public int getYearOfReview() {
        return yearOfReview;
    }

    public UserType getUserType() {
        return userType;
    }
}
