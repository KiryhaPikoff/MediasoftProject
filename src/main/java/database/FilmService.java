package database;

import film.Film;
import film.Genre;
import human.Human;

import java.util.List;

public interface FilmService {

    Film getFilmById(Integer id);

    List<Film> getFilmsByName(String nameFilm);

    List<Film> getFilmsByReleaseDate(String releaseDate);

    List<Film> getFilmsByCountry(String country);

    List<Film> getFilmsByGenre(Genre genre);

    List<Film> getFilmsByRating(Integer rating);

    List<Human> loadActors(String actorsID);
    
    List<Film> getAllFilms();

    Human loadDirector(Integer id);

}
