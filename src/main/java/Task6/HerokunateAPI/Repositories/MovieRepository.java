package Task6.HerokunateAPI.Repositories;

import Task6.HerokunateAPI.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie getById(Integer id);
}
