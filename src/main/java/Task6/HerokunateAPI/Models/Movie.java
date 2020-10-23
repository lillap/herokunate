package Task6.HerokunateAPI.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column
    private String releaseYear;

    @Column
    private String genre;

    @Column
    private String url;

    @JsonGetter("actors")
    private List<String> actors() {
        return actors.stream()
                .map(actor -> {
                    return "/actor/" + actor.getId();
                }).collect(Collectors.toList());

    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Movie_With_Actor",
            joinColumns = { @JoinColumn(name = "movie_id")  },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") }
    )

    private Set<Actor> actors = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
