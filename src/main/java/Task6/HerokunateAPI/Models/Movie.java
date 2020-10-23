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

    @JsonGetter("actors")
    private List<String> actors() {
        return actors.stream()
                .map(actor -> {
                    return "/actor/" + actor.getId();
                }).collect(Collectors.toList());

    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Actor_In_Movie",
            joinColumns = { @JoinColumn(name = "actor_id")  },
            inverseJoinColumns = { @JoinColumn(name = "movie_id") }
    )

    private Set<Actor> actors = new HashSet<>();



}
