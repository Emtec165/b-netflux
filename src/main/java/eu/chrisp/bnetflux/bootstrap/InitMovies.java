package eu.chrisp.bnetflux.bootstrap;

import eu.chrisp.bnetflux.domain.Movie;
import eu.chrisp.bnetflux.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
class InitMovies implements CommandLineRunner {

    private final MovieRepository repository;

    @Override
    public void run(String... args) {
        repository.deleteAll()
                .thenMany(Flux.just("Silence of the Lambda", "AEon Flux", "Enter the Mono<Void>",
                                "The Fluxxinator", "Back to the Future", "Meet the Fluxes", "Lord of the Fluxes")
                        .map(Movie::new)
                        .flatMap(repository::save)
                ).subscribe(null, null, () -> {
                    repository.findAll().subscribe(System.out::println);
                });
    }
}
