package eu.chrisp.bnetflux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MovieEvent {
    private String movieId;
    private LocalDateTime movieDateTime;
}
