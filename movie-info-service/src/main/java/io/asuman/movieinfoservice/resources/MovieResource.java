package io.asuman.movieinfoservice.resources;

import io.asuman.movieinfoservice.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    private final RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    private final String MOVIE_DB_URL = "https://api.themoviedb.org/3/movie/";

    @Autowired
    public MovieResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId) {
        String url = MOVIE_DB_URL + movieId + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, Movie.class);
//        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }
}
