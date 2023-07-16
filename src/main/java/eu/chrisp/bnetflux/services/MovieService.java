package eu.chrisp.bnetflux.services;

import eu.chrisp.bnetflux.domain.Movie;
import eu.chrisp.bnetflux.domain.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Mono<Movie> getMovie(String id);
    Flux<Movie> getAllMovies();

    Flux<MovieEvent> streamMovieEvents(String id);
}
