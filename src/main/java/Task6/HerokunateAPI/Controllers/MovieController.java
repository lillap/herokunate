package Task6.HerokunateAPI.Controllers;

import Task6.HerokunateAPI.Models.Movie;
import Task6.HerokunateAPI.Repositories.ActorRepository;
import Task6.HerokunateAPI.Repositories.MovieRepository;
import Task6.HerokunateAPI.Utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MovieController {

    /*Spring instantiate the object with the annotation @Autowired*/
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/movie")
    public ResponseEntity<CommonResponse> addMovie(@RequestBody Movie movie){

        movie = movieRepository.save(movie);

        CommonResponse commonResponse = new CommonResponse();

        commonResponse.data = movie;
        commonResponse.message = "Movie with id: " + movie.getId() + " has been added.";

        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);

    }

    @GetMapping("/movie/all")
    public ResponseEntity<CommonResponse> getAllMovies() {


        CommonResponse commonResponse = new CommonResponse();
        commonResponse.data = movieRepository.findAll();
        commonResponse.message = "These are the existing movies here";

        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<>(commonResponse, httpStatus);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<CommonResponse> deleteMovie(@PathVariable ("id") Integer id) {

        CommonResponse commonResponse = new CommonResponse();
        HttpStatus httpStatus;

        if(movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            commonResponse.message = "Book with id: " + id + " was deleted.";
            httpStatus = HttpStatus.OK;
        } else {
            commonResponse.message = "Book with id: " + id + " was not found.";
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(commonResponse, httpStatus);
    }



}
