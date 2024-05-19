package ua.kharkiv.khpi.starwarsclient.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Film {

    private String title;
    private Integer episodeID;
    private String openingCrawl;
    private String director;
    private String producer;
    private LocalDate releaseDate;
}
