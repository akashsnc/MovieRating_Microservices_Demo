package io.codeworld.ratingsdataservice.models;

import java.util.ArrayList;
import java.util.List;

public class UserRating {
    private List<Rating> userRating = new ArrayList<>();

    public UserRating() { }

    public UserRating(List<Rating> userRating) {
        this.userRating = new ArrayList<>(userRating);
    }

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }
}
