package io.asuman.moviecatalogservice.models;

import java.util.ArrayList;
import java.util.List;

public class UserRating {
    private String userId;
    private List<Rating> userRating = new ArrayList<>();

    public UserRating() { }

    public UserRating(String userId) {
        this.userId = userId;
    }

    public UserRating(String userId, List<Rating> userRating) {
        this.userId = userId;
        this.userRating = userRating;
    }

    public List<Rating> getUserRating() {
        return userRating;
    }

    public void setUserRating(List<Rating> userRating) {
        this.userRating = userRating;
    }
}
