package Task6.HerokunateAPI.Controllers;

import Task6.HerokunateAPI.Models.Actor;
import Task6.HerokunateAPI.Repositories.ActorRepository;
import Task6.HerokunateAPI.Utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CommonResponse> addActor(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestBody Actor actor) {
        actor = actorRepository.save(actor);

        CommonResponse commonResponse = new CommonResponse();

        commonResponse.data = actor;
        commonResponse.message = "New actor with id: " + actor.id;

        HttpStatus httpStatus = HttpStatus.CREATED;
        

    }

}
