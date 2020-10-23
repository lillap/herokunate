package Task6.HerokunateAPI.Controllers;

import Task6.HerokunateAPI.Models.Movie;
import Task6.HerokunateAPI.Repositories.ActorRepository;
import Task6.HerokunateAPI.Repositories.MovieRepository;
import Task6.HerokunateAPI.Utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MovieController {

    /*Spring instantiate the object with the annotation @Autowired*/
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/movie")
    public ResponseEntity<CommonResponse> addMovie(HttpServletRequest request, @RequestBody Movie movie){

        movie = movieRepository.save(movie);

        CommonResponse commonResponse = new CommonResponse();

        commonResponse.data = movie;
        commonResponse.message = "Movie with id: " + movie.getId() + " has been added.";

        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);

    }



}
