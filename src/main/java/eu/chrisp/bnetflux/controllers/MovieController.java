package eu.chrisp.bnetflux.controllers;

import eu.chrisp.bnetflux.domain.Movie;
import eu.chrisp.bnetflux.domain.MovieEvent;
import eu.chrisp.bnetflux.services.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
class MovieController {

    private final MovieService service;

    @GetMapping("/{id}")
    Mono<Movie> getMovie(@PathVariable String id) {
        log.debug("Get movie. Id: [{}]", id);
        return service.getMovie(id);
    }

    @GetMapping
    Flux<Movie> getAllMovies() {
        return service.getAllMovies();
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvents(@PathVariable String id) {
        return service.streamMovieEvents(id);
    }
}
