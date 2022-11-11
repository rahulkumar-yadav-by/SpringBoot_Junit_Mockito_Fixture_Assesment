package com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.service;


import com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.model.Movie;
import com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;


    public List<Movie> getMovies(){
        List<Movie> result = repository.findAll();
        return result;
    }

    public Movie saveMovie(Movie movie){
        Movie savedMovie = repository.save(movie);
//        return "{" +
//                "\"message\":"+"\"Successfully Created A Movie\","+
//                "\"data\":"+savedMovie+"}";
        return savedMovie;
    }

    public String deleteMovies(ObjectId id) {
        repository.deleteById(id);
        return "{\"message\":\"Successfully deleted.\"}";
    }

    public Movie getMovieById(ObjectId id) {
        return repository.findById(id).get();

    }

    public Movie updateMovies(Movie movie) {
        return repository.save(movie);
        //return "Successfully updated.";
    }
}
