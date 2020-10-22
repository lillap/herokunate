package Task6.HerokunateAPI.Repositories;

import Task6.HerokunateAPI.Models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor getActorById(long id);
}
