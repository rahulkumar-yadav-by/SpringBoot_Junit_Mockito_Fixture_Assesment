package com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.controller;


import com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.exceptionHandling.ErrorResponse;
import com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.exceptionHandling.MovieException;
import com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.model.Movie;
import com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping(value = "/add-Movie" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie savingAMovie(@RequestBody Movie movie) throws MovieException {
        if(movie.getName()==null){
            throw new MovieException("Movie name must not be null.");
        }
        if(movie.getReleaseDate()==null){
            throw new MovieException("Release Date must not be null.");
        }
        return service.saveMovie(movie);
    }

    @GetMapping("/get_all_Movies")
    public List<Movie> GatMovies() {
        return  service.getMovies();
    }

    @DeleteMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable("id") ObjectId id) throws MovieException {
        try
        {
            service.getMovieById(id);
            return  service.deleteMovies(id);
        }
        catch (Exception e) {
            throw new MovieException("MOVIE DOESN't EXIST WITH THIS ID");
        }
    }

    @GetMapping("/get_each_Movie_By_Id/{id}")
    public Movie getMovie(@PathVariable("id") ObjectId id) throws MovieException {
        Movie movie = null;
        try
        {
            movie = service.getMovieById(id);
            return movie;
        }
        catch (Exception e){
            if(movie==null){
                throw new MovieException("MOVIE DOESN't EXIST WITH THIS ID");
            }
        }
        throw new MovieException("Unknown Error.");

    }

    @PatchMapping(value = "/updateMovie" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie updateMovie(@RequestBody Movie movie) throws MovieException {
        Movie movie_ = null;
        try
        {
            movie_ = service.getMovieById(movie.getId());
            return service.updateMovies(movie);
        }
        catch (Exception e){
            if(movie==null){
                throw new MovieException("MOVIE DOESN't EXIST WITH THIS ID");
            }
            throw new MovieException(e.getMessage());
        }
    }


}
