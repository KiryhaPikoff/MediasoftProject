package human;

import java.awt.*;
import java.time.LocalDate;
import java.util.logging.LogManager;

import org.apache.commons.logging.impl.Log4JLogger;

import frames.RootWindow;

public class Human {
    private String lastName;
    private String firstName;
    private String middleName;
    private Integer age;
    private LocalDate birthdate;
    private Role role;
    private Integer Id;
    private String country;
    private String description;
    private Image photo;

    public Human() {

    }

    @Override
    public String toString() {
        String outString =  "=============================================" + "\n"
							+ "Фамилия: " + this.getLastName() + "\n" +
                            "Имя: " + this.getFirstName() + "\n" +
                            "Отчество: " + this.getMiddleName() + "\n" +
                            "Дата рождения: " + this.getBirthdate() + "\n" +
                            "Страна: " + this.getCountry() + "\n" +
                            "Возраст: " + this.getAge() + "\n" +
                            "Роль: " + this.getRole() + "\n" +
                            "\n" + this.getDescription() + "\n" +
                            "=============================================" + "\n";
        return outString;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
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

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }
}
