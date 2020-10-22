package Task6.HerokunateAPI.Controllers;

import Task6.HerokunateAPI.Models.Actor;
import Task6.HerokunateAPI.Repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;


    @PostMapping("/actor")
    public ResponseEntity<> addActor(HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody Actor actor) {


    }

}
