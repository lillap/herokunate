package Task6.HerokunateAPI.Controllers;

import Task6.HerokunateAPI.Models.Actor;
import Task6.HerokunateAPI.Repositories.ActorRepository;
import Task6.HerokunateAPI.Utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    /*Add an actor */
    @PostMapping("/actor")
    public ResponseEntity<CommonResponse> addActor(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestBody Actor actor) {
        actor = actorRepository.save(actor);

        CommonResponse commonResponse = new CommonResponse();

        commonResponse.data = actor;
        commonResponse.message = "New actor created with id: " + actor.getId();

        HttpStatus httpStatus = HttpStatus.CREATED;

        response.addHeader("Location", "/actor/" + actor.getId());

        //Ad log here before return
        return new ResponseEntity<>(commonResponse, httpStatus);

    }

    /*Get all actors with .findAll() */
    @GetMapping("/actor/all")
    public ResponseEntity<CommonResponse> getAllActors(HttpServletRequest request) {

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.data = actorRepository.findAll();
        commonResponse.message = "All actors";

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @GetMapping("/actor/{id}")
    ResponseEntity<CommonResponse> getActorById(HttpServletRequest request, @PathVariable("id") Integer id) {

        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if (actorRepository.existsById(id)) {
            commonResponse.data = actorRepository.findById(id);
            commonResponse.message = "Actor found with id: " + id;
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.data = null;
            commonResponse.message = "Actor was not found";
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(commonResponse, httpStatus);

    }

    @PatchMapping("/actor/update/{id}")
    public ResponseEntity<CommonResponse> updateActor(HttpServletRequest request,
                                                      @RequestBody Actor updatedActor,
                                                      @PathVariable Integer id){

        CommonResponse commonResponse = new CommonResponse();

       HttpStatus httpStatus;

        if (actorRepository.existsById(id)) {
            Optional<Actor> updatedActorRepository = actorRepository.findById(id);
            Actor actor = updatedActorRepository.get();

            if(updatedActor.getFirstName() != null){
               actor.getFirstName() = updatedActor.getFirstName();
            }
            if(updatedActor.getLastName() != null){
                actor.getLastName() = updatedActor.getLastName();
            }
            if(updatedActor.getUrl() != null){
                actor.getUrl() = updatedActor.getUrl();
            }

            actorRepository.save(actor);

            commonResponse.data = actor;
            commonResponse.message = "Actor with id: " + id + " have been updated.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Actor with id: " + id + " was not found";
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(commonResponse, httpStatus);
    }

}
