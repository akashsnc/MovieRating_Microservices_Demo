package io.asuman.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.asuman.moviecatalogservice.models.Movie;
import io.asuman.moviecatalogservice.models.Rating;
import io.asuman.moviecatalogservice.models.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogItemService {
    private final RestTemplate restTemplate;

    @Autowired
    public CatalogItemService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
            }
    )
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        assert movie != null;
        return new CatalogItem(movie.getTitle(), movie.getOverview(), rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("n/a", "n/a", rating.getRating());
    }
}
