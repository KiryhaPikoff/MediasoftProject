package database;

import film.Film;
import film.Genre;
import frames.RootWindow;
import human.Human;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FilmDataBase implements FilmService {

    private AccessDataBase dataBase = new AccessDataBase();
    
    private String dataBaseLogin = null;
    private String dataBasePassword = null;
    private String tableName = "Films";
    private String idField = "id";
    private String nameFilmField = "name";
    private String countryField = "country";
    private String genreField = "genre";
    private String actorsIdField = "actorsId";
    private String releaseYearField = "releaseYear";
    private String directorIdField = "directorId";
    private String descriptionField = "description";
    private String imageField = "image"; 
    private String ratingField = "rating";
    
    private final Logger logger = LogManager.getLogger(FilmDataBase.class);

    public FilmDataBase(String login, String password) {
        this.dataBaseLogin = login;
        this.dataBasePassword = password;
    }

    public FilmDataBase() {

    }

    @Override
    public Film getFilmById(Integer id) {
        Film film = null;
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sqlRequest = "select * from " + this.tableName + " where " + this.idField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
             
                while(result.next()) {
                	film = new Film();
                	
	                film.setID(result.getInt(this.idField));
	                String actorsId = result.getString(this.actorsIdField);
	                String[] strActorsId = actorsId.split(";");
	                List<Integer> intActorsId = new ArrayList<Integer>();
	                for (String actorId : strActorsId) {
	                	intActorsId.add(Integer.valueOf(actorId));
	                }
	                List<Human> actors = new ArrayList<Human>();
	                HumanDataBase humanDataBase = new HumanDataBase();
	                for (Integer actorId : intActorsId) {
	                	actors.add(humanDataBase.getHumanById(actorId));
	                }
	                film.setActors(actors);
	                film.setName(result.getString(this.nameFilmField));
	                film.setGenre(Genre.valueOf(result.getString(this.genreField)));
	                film.setCountry(result.getString(this.countryField));
	                film.setDirector(new HumanDataBase().getHumanById(result.getInt(this.directorIdField)));
	                film.setDescription(result.getString(this.descriptionField));
	                film.setReleaseYear(result.getString(this.releaseYearField));
	                film.setImage(ImageIO.read(result.getBlob(this.imageField).getBinaryStream()));
	                film.setRating(result.getInt(this.ratingField));
	                
	                logger.info("успешно взяли даннные для фильма");
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();
        return film;
    }

    @Override
    public List<Film> getFilmsByReleaseDate(String releaseDate) {
        List<Film> films = new ArrayList<Film>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sqlRequest = "select * from " + this.tableName + " where " + this.releaseYearField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
                preparedStatement.setString(1, releaseDate);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    Film film = new Film();
                    film.setID(result.getInt(this.idField));
                    film.setName(result.getString(this.nameFilmField));
                    film.setGenre(Genre.valueOf(result.getString(this.genreField)));
                    film.setCountry(result.getString(this.countryField));
                    film.setReleaseYear(result.getString(this.releaseYearField));
                    film.setRating(result.getInt(this.ratingField));

                    films.add(film);
                    logger.info("успешно взяли даннные для фильма, добавили в список");
                }            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();
        return films;
    }

    @Override
    public List<Film> getFilmsByCountry(String country) {
        List<Film> films = new ArrayList<Film>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sqlRequest = "select * from " + this.tableName + " where " + this.countryField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
                preparedStatement.setString(1, country);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    Film film = new Film();
                    film.setID(result.getInt(this.idField));
                    film.setName(result.getString(this.nameFilmField));
                    film.setGenre(Genre.valueOf(result.getString(this.genreField)));
                    film.setCountry(result.getString(this.countryField));
                    film.setReleaseYear(result.getString(this.releaseYearField));
                    film.setRating(result.getInt(this.ratingField));

                    films.add(film);
                    logger.info("успешно взяли даннные для фильма, добавили в список");
                }            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();
        return films;
    }

    @Override
    public List<Film> getFilmsByGenre(Genre genre) {
        List<Film> films = new ArrayList<Film>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sqlRequest = "select * from " + this.tableName + " where " + this.genreField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
                preparedStatement.setString(1, genre.toString());
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    Film film = new Film();
                    film.setID(result.getInt(this.idField));
                    film.setName(result.getString(this.nameFilmField));
                    film.setGenre(Genre.valueOf(result.getString(this.genreField)));
                    film.setCountry(result.getString(this.countryField));
                    film.setReleaseYear(result.getString(this.releaseYearField));
                    film.setRating(result.getInt(this.ratingField));

                    films.add(film);
                    logger.info("успешно взяли даннные для фильма, добавили в список");
                }            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();
        return films;
    }

    @Override
    public List<Film> getFilmsByRating(Integer rating) {
        List<Film> films = new ArrayList<Film>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sqlRequest = "select * from " + this.tableName + " where " + this.ratingField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
                preparedStatement.setInt(1, rating);
                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    Film film = new Film();
                    film.setID(result.getInt(this.idField));
                    film.setName(result.getString(this.nameFilmField));
                    film.setGenre(Genre.valueOf(result.getString(this.genreField)));
                    film.setCountry(result.getString(this.countryField));
                    film.setReleaseYear(result.getString(this.releaseYearField));
                    film.setRating(result.getInt(this.ratingField));

                    films.add(film);
                    logger.info("успешно взяли даннные для фильма, добавили в список");
                }            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();
        return films;
    }

    @Override
	public List<Film> getAllFilms() {
    	List<Film> films = new ArrayList<Film>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sqlRequest = "select * from " + this.tableName + " ORDER BY rating DESC";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
                ResultSet result = preparedStatement.executeQuery();
                try {
                    while (result.next()) {
                        Film film = new Film();
                        film.setID(result.getInt(this.idField));
                        film.setName(result.getString(this.nameFilmField));
                        film.setGenre(Genre.valueOf(result.getString(this.genreField)));
                        film.setCountry(result.getString(this.countryField));
                        film.setReleaseYear(result.getString(this.releaseYearField));
                        film.setRating(result.getInt(this.ratingField));

                        films.add(film);
                        logger.info("успешно взяли даннные для фильма, добавили в список");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();
        return films;
	}
}
