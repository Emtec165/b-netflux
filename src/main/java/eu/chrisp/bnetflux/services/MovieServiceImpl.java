package eu.chrisp.bnetflux.services;

import eu.chrisp.bnetflux.domain.Movie;
import eu.chrisp.bnetflux.domain.MovieEvent;
import eu.chrisp.bnetflux.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Override
    public Mono<Movie> getMovie(String id) {
        log.debug("Get movie. Id: [{}]", id);
        return repository.findById(id);
    }

    @Override
    public Flux<Movie> getAllMovies() {
        return repository.findAll();
    }

    @Override
    public Flux<MovieEvent> streamMovieEvents(String id) {
        return Flux.<MovieEvent>generate(movieEventSynchronousSink -> {
            movieEventSynchronousSink.next(new MovieEvent(id, LocalDateTime.now()));
        }).delayElements(Duration.ofSeconds(1));
    }
}
