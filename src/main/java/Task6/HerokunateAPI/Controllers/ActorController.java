package Task6.HerokunateAPI.Controllers;

import Task6.HerokunateAPI.Repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;
    

}
