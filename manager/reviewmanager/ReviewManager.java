package manager.reviewmanager;

import entity.reviews.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class ReviewManager {

    private HashMap<String, Review> reviewMap;

    public ReviewManager() {
        reviewMap = new HashMap<>();
    }

    public void addReview(Review review) {
        reviewMap.putIfAbsent(review.getId(), review);
    }

    public List<Review> getReviews() {
        return new ArrayList<>(reviewMap.values());
    }

}
