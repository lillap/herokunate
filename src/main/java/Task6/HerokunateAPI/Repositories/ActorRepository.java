package Task6.HerokunateAPI.Repositories;

import Task6.HerokunateAPI.Models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Actor getById(Integer id);
}
