package Task6.HerokunateAPI.Repositories;

import Task6.HerokunateAPI.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie getbyId(Integer id);
}
