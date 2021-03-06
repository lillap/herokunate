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

    @GetMapping("/")
    String frontPageGreeting(){
        return "Hello There";
    }

    /*Add an actor */
    @PostMapping("/actor")
    public ResponseEntity<CommonResponse> addActor(@RequestBody Actor actor) {
        actor = actorRepository.save(actor);

        CommonResponse commonResponse = new CommonResponse();

        commonResponse.data = actor;
        commonResponse.message = "New actor created with id: " + actor.getId();

        HttpStatus httpStatus = HttpStatus.CREATED;

        return new ResponseEntity<>(commonResponse, httpStatus);

    }

    /*Get all actors with method findAll */
    @GetMapping("/actor/all")
    public ResponseEntity<CommonResponse> getAllActors() {

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.data = actorRepository.findAll();
        commonResponse.message = "All actors";

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    /*Get actor by first checking if given id exists, if not, return message with actor was not found */
    @GetMapping("/actor/{id}")
    ResponseEntity<CommonResponse> getActorById(@PathVariable("id") Integer id) {

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

    @PutMapping("/actor/{id}")
    public ResponseEntity<CommonResponse> updateActor(@RequestBody Actor updatedActor,
                                                      @PathVariable ("id") Integer id){

        CommonResponse commonResponse = new CommonResponse();

       HttpStatus httpStatus;

        if (actorRepository.existsById(id)) {
            Optional<Actor> updatedActorRepository = actorRepository.findById(id);
            Actor actor = updatedActorRepository.get();

            if(updatedActor.getFirstName() != null){
               actor.setFirstName(updatedActor.getFirstName());
            }
            if(updatedActor.getLastName() != null){
                actor.setLastName(updatedActor.getLastName());
            }
            if(updatedActor.getUrl() != null){
                actor.setUrl(updatedActor.getUrl());
            }
            actorRepository.save(actor);

            commonResponse.data = actor;
            commonResponse.message = "Actor with id: " + id + " has been updated.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Actor with id: " + id + " was not found";
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(commonResponse, httpStatus);
    }

    @GetMapping("/actor/{id}/movies")
    public ResponseEntity<CommonResponse> getMoviesWithActor(@PathVariable("id") Integer id){
        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(actorRepository.existsById(id)){
            Optional<Actor> movieActorRepository = actorRepository.findById(id);
            Actor actor = movieActorRepository.get();
            commonResponse.data = actor.movies;
            commonResponse.message = "These are the movies the actor " +
                    actor.getFirstName() + " " + actor.getLastName() +
                    " with id: " + id + " " + "stars in.";
            httpStatus = HttpStatus.OK;
        } else {
        commonResponse.data = null;
        commonResponse.message = "Actor with id " + id + " was not found.";
        httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }

    /*Delete actor by checking first if the given id exists, if not, return a message and HttpStatus not found*/
    @DeleteMapping("/actor/{id}")
    public ResponseEntity<CommonResponse> deleteActor(@PathVariable ("id") Integer id){

        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(actorRepository.existsById(id)){
            actorRepository.deleteById(id);
            commonResponse.message = "Actor with id: " + id + " has been deleted.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Actor with id " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }
}
