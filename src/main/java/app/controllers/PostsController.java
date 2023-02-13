package app.controllers;

import app.dto.MostViewedPostsDto;
import app.orchestrators.RetrievingMostViewedPostsOrchestrator;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/posts")
public class PostsController {

    private final RetrievingMostViewedPostsOrchestrator retrievingMostViewedPostsOrchestrator;

    @GetMapping(path = "/rank/max/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MostViewedPostsDto> getTenPostsWithHighestViewCount(@PathVariable int limit) {
        MostViewedPostsDto mostViewedPostsDto = new MostViewedPostsDto(retrievingMostViewedPostsOrchestrator.getMostViedPostsLimitTo(limit));
        return ResponseEntity.ok(mostViewedPostsDto);
    }

}
