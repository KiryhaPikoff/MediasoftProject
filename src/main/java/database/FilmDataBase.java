package database;

import film.Film;
import film.Genre;
import human.Human;

import javax.imageio.ImageIO;
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
                film = this.fillFilmByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public List<Film> getFilmsByName(String nameFilm) {
        List<Film> films = new ArrayList<Film>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sqlRequest = "select * from " + this.tableName + " where " + this.nameFilmField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
                preparedStatement.setString(1, nameFilm);
                ResultSet result = preparedStatement.executeQuery();
                films = fillFilmsByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
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
                films = fillFilmsByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                films = fillFilmsByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                films = fillFilmsByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                films = fillFilmsByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                films = fillFilmsByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return films;
	}
    
    @Override
    public List<Human> loadActors(String actorsId) {
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
        return actors;
    }

    @Override
    public Human loadDirector(Integer id) {
        HumanDataBase humanDataBase = new HumanDataBase();
        return humanDataBase.getHumanById(id);
    }

    private List<Film> fillFilmsByResult(ResultSet result) {
        List<Film> films = new ArrayList<Film>();
        try {
            while (result.next()) {
                Film film = new Film();
                film.setID(result.getInt(this.idField));
                String actorsId = result.getString(this.actorsIdField);
                film.setActors(this.loadActors(actorsId));
                film.setName(result.getString(this.nameFilmField));
                film.setGenre(Genre.valueOf(result.getString(this.genreField)));
                film.setCountry(result.getString(this.countryField));
                film.setDirector(this.loadDirector(result.getInt(this.directorIdField)));
                film.setDescription(result.getString(this.descriptionField));
                film.setReleaseYear(result.getString(this.releaseYearField));
                film.setImage(ImageIO.read(result.getBlob(this.imageField).getBinaryStream()));
                film.setRating(result.getInt(this.ratingField));

                films.add(film);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return films;
    }

    private Film fillFilmByResult(ResultSet result) {
        Film film = new Film();
        try {
            while (result.next()) {
                film.setID(result.getInt(this.idField));
                String actorsId = result.getString(this.actorsIdField);
                film.setActors(this.loadActors(actorsId));
                film.setName(result.getString(this.nameFilmField));
                film.setGenre(Genre.valueOf(result.getString(this.genreField)));
                film.setCountry(result.getString(this.countryField));
                film.setDirector(this.loadDirector(result.getInt(this.directorIdField)));
                film.setDescription(result.getString(this.descriptionField));
                film.setReleaseYear(result.getString(this.releaseYearField));
                film.setImage(ImageIO.read(result.getBlob(this.imageField).getBinaryStream()));
                film.setRating(result.getInt(this.ratingField));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return film;
    }
}
