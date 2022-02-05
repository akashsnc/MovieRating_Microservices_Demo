package io.codeworld.ratingsdataservice.resources;

import io.codeworld.ratingsdataservice.models.Rating;
import io.codeworld.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @GetMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable String userId) {

        List<Rating> ratings = Arrays.asList(
                new Rating("550", 5),
                new Rating("200", 4),
                new Rating("350", 4),
                new Rating("111", 3)
        );
        return new UserRating(ratings);
    }
}
