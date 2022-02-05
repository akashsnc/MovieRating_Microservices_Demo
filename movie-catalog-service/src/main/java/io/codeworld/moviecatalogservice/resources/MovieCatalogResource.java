package io.codeworld.moviecatalogservice.resources;

import io.codeworld.moviecatalogservice.models.CatalogItem;
import io.codeworld.moviecatalogservice.models.UserRating;
import io.codeworld.moviecatalogservice.services.CatalogItemService;
import io.codeworld.moviecatalogservice.services.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    private final CatalogItemService catalogItemService;
    private final UserRatingService userRatingService;

    @Autowired
    public MovieCatalogResource(CatalogItemService catalogItemService, UserRatingService userRatingService) {
        this.catalogItemService = catalogItemService;
        this.userRatingService = userRatingService;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        UserRating userRating = userRatingService.getUserRating(userId);
        return userRating.getUserRating().stream().map(catalogItemService::getCatalogItem).collect(Collectors.toList());
    }
}

//RestTemplate alternative with Reactive webclient
/* private final WebClient.Builder webClientBuilder;
   Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://movie-info-service/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
**/