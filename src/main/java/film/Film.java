package film;

import human.Human;

import java.awt.*;
import java.util.List;

public class Film {

    private String name;
    private Integer ID;
    private List<Human> actors;
    private List<Integer> actorsID;
    private Human director;
    private Genre genre;
    private String releaseYear;
    private String country;
    private String description;
    private Image image;
    private Integer rating;


    public Film() {

    }

    @Override
    public String toString() {
        StringBuffer outString = new StringBuffer();
        outString.append("Название фильма: " + this.getName() + "\n");
        outString.append("Жанр: " + this.getGenre() + "\n");
        outString.append("Рейтинг: " + this.getRating() + "\n");
        outString.append("Год выпуска: " + this.getReleaseYear() + "\n");
        outString.append("Страна: " + this.getCountry() + "\n");
        outString.append("Режиссёр: " + "\n" + this.getDirector() + "\n");
        outString.append("Актёры: " + "\n");
        this.actors.forEach(outString::append);
        outString.append("\n" + "Аннотация: " + this.getDescription() + "\n");
        return outString.toString();
    }

    @Override
    public int hashCode() {
        return this.getID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public List<Human> getActors() {
        return actors;
    }

    public void setActors(List<Human> actors) {
        this.actors = actors;
    }

    public Human getDirector() {
        return director;
    }

    public void setDirector(Human director) {
        this.director = director;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<Integer> getActorsID() {
        return actorsID;
    }

    public void setActorsID(List<Integer> actorsID) {
        this.actorsID = actorsID;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
