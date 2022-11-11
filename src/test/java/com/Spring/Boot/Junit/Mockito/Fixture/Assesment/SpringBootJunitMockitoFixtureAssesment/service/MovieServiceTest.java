package com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.service;

import com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.model.Movie;
import com.Spring.Boot.Junit.Mockito.Fixture.Assesment.SpringBootJunitMockitoFixtureAssesment.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceTest {
    @Mock
    private MovieRepository repository;

    @InjectMocks
    private MovieService service;

    @Before // Before Each Test Case, ready the mocked data
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void getAllMovie(){
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf8"),"Happy Death Day","2017"));
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf7"),"Happy Death Day 2","2019"));
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf6"),"Spider-man too far from home","2022"));

        when(repository.findAll()).thenReturn(movieList);

        List<Movie> movieResult = service.getMovies();

        assertEquals(3,movieResult.size());
    }

    @Test
    public  void getMovieById(){
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf8"),"Happy Death Day","2017"));
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf7"),"Happy Death Day 2","2019"));
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf6"),"Spider-man too far from home","2022"));

        ObjectId id = new ObjectId("636dc82375b6a55ee5656cf6");

        when(repository.findById(id)).thenReturn(Optional.ofNullable(movieList.get(movieList.size() - 1)));

        Movie movieResult = service.getMovieById(id);

        assertEquals(movieResult.getId(),id);
        assertEquals("Spider-man too far from home",movieResult.getName());
        assertEquals("2022",movieResult.getReleaseDate());
    }

    @Test
    public  void saveMovie(){
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf8"),"Happy Death Day","2017"));
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf7"),"Happy Death Day 2","2019"));
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf6"),"Spider-man too far from home","2022"));

        Movie movie = new Movie(new ObjectId("636dc82375b6a55ee5656cf5"),"Amazing Spider-man","2008");


        when(repository.save(movie)).thenReturn(movie);

        Movie movieResult = service.saveMovie(movie);

        assertEquals(movieResult.getId(),new ObjectId("636dc82375b6a55ee5656cf5"));
        assertEquals("Amazing Spider-man",movieResult.getName());
        assertEquals("2008",movieResult.getReleaseDate());
    }

    @Test
    public  void updateMovie(){
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf8"),"Happy Death Day","2017"));
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf7"),"Happy Death Day 2","2019"));
        movieList.add(new Movie(new ObjectId("636dc82375b6a55ee5656cf6"),"Spider-man too far from home","2022"));

        Movie movie = new Movie(new ObjectId("636dc82375b6a55ee5656cf6"),"Spider-man too far from home","2008");

        when(repository.save(movie)).thenReturn(movie);

        Movie movieResult = service.updateMovies(movie);

        assertEquals(movieResult.getId(),new ObjectId("636dc82375b6a55ee5656cf6"));
        assertEquals("Spider-man too far from home",movieResult.getName());
        assertEquals("2008",movieResult.getReleaseDate());
    }

    @Test
    public void deleteEmployeeById_With_Verifier(){
        Movie emp_ = new Movie(new ObjectId("636dc82375b6a55ee5656cf6"),"Spider-man too far from home","2008");
        service.deleteMovies(new ObjectId("636dc82375b6a55ee5656cf6"));
        verify(repository, times(1)).deleteById(emp_.getId());

    }
}
