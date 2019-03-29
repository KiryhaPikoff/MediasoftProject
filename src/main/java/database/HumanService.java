package database;

import human.Human;
import human.Role;

import java.util.List;

public interface HumanService {

    Human getHumanById(Integer id);

    List<Human> getHumansByLastName(String lastName);

    List<Human> getHumansByFirstName(String firstName);

    List<Human> getHumansByMiddleName(String middleName);

    List<Human> getHumansByCountry(String country);

    List<Human> getHumansByRole(Role role);

    List<Human> getHumansByAge(Integer age);

    List<Human> getAllHumans();
    
    Integer getCountStrings();
    
    void addHumanToBase(String id, String firstName, String lastName, String middleName,
    					String country, String birthdate, String age, String role,
    					String photoPath, String description);
}
