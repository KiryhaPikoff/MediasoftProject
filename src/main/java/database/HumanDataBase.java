package database;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import human.Human;
import human.Role;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HumanDataBase implements HumanService {
    private AccessDataBase dataBase = new AccessDataBase();
    private String dataBaseLogin = null;
    private String dataBasePassword = null;
    private String tableName = "Humans";
    private String idField = "id";
    private String lastNameField = "lastName";
    private String firstNameField = "firstName";
    private String middleNameField = "middleName";
    private String ageField = "age";
    private String birthdateField = "birthdate";
    private String roleField = "role";
    private String countryField = "country";
    private String imageField = "photo";
    private String descriptionFirld = "description";

    public HumanDataBase(String login, String password) {
        this.dataBaseLogin = login;
        this.dataBasePassword = password;
    }

    public HumanDataBase() {

    }

    @Override
    public Human getHumanById(Integer id) {
        Human human = null;
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sql = "select * from " + this.tableName + " where " + this.idField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet result = preparedStatement.executeQuery();
                human = this.fillHumanByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();

        return human;
    }

    @Override
    public List<Human> getHumansByLastName(String lastName) {
        List<Human> humans = new ArrayList<Human>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sql = "select * from " + this.tableName + " where " + this.lastNameField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, lastName);
                ResultSet result = preparedStatement.executeQuery();
                humans = this.fillHumansByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();

        return humans;
    }

    @Override
    public List<Human> getHumansByFirstName(String firstName) {
        List<Human> humans = new ArrayList<Human>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sql = "select * from " + this.tableName + " where " + this.firstNameField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, firstName);
                ResultSet result = preparedStatement.executeQuery();
                humans = this.fillHumansByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();

        return humans;
    }

    @Override
    public List<Human> getHumansByMiddleName(String middleName) {
        List<Human> humans = new ArrayList<Human>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sql = "select * from " + this.tableName + " where " + this.middleNameField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, middleName);
                ResultSet result = preparedStatement.executeQuery();
                humans = this.fillHumansByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();

        return humans;
    }

    @Override
    public List<Human> getHumansByCountry(String country) {
        List<Human> humans = new ArrayList<Human>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sql = "select * from " + this.tableName + " where " + this.countryField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, country);
                ResultSet result = preparedStatement.executeQuery();
                humans = this.fillHumansByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();

        return humans;
    }

    @Override
    public List<Human> getHumansByRole(Role role) {
        List<Human> humans = new ArrayList<Human>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sql = "select * from " + this.tableName + " where " + this.roleField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, role.toString());
                ResultSet result = preparedStatement.executeQuery();
                humans = this.fillHumansByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();

        return humans;
    }

    @Override
    public List<Human> getHumansByAge(Integer age) {
        List<Human> humans = new ArrayList<Human>();
        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                String sql = "select * from " + this.tableName + " where " + this.ageField + " = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, age);
                ResultSet result = preparedStatement.executeQuery();
                humans = this.fillHumansByResult(result);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();

        return humans;
    }

    public List<Human> getAllHumans() {
        List<Human> humans = null;

        try {
            this.dataBase.connect(null, null);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                humans = new ArrayList<Human>();
                Statement statement = connection.createStatement();
                String sql = "select * from " + this.tableName + " ORDER BY firstName";
                ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                    Human human = new Human();
                    human.setId(result.getInt(this.idField));
                    human.setLastName(result.getString(this.lastNameField));
                    human.setFirstName(result.getString(this.firstNameField));
                    human.setMiddleName(result.getString(this.middleNameField));
                    human.setAge(result.getInt(this.ageField));
                    human.setBirthdate(result.getDate(this.birthdateField).toLocalDate());
                    human.setRole(Role.valueOf(result.getString(this.roleField)));
                    human.setCountry(result.getString(this.countryField));
                    human.setPhoto(ImageIO.read(result.getBlob(this.imageField).getBinaryStream()));
                    human.setDescription(result.getString(this.descriptionFirld));
                    humans.add(human);
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();

        return humans;
    }

    private List<Human> fillHumansByResult(ResultSet result) {
        List<Human> humans = new ArrayList<Human>();

        try {
            while (result.next()) {
                Human human = new Human();
                human.setId(result.getInt(this.idField));
                human.setLastName(result.getString(this.lastNameField));
                human.setFirstName(result.getString(this.firstNameField));
                human.setMiddleName(result.getString(this.middleNameField));
                human.setAge(result.getInt(this.ageField));
                human.setBirthdate(result.getDate(this.birthdateField).toLocalDate());
                human.setRole(Role.valueOf(result.getString(this.roleField)));
                human.setCountry(result.getString(this.countryField));
                human.setPhoto(ImageIO.read(result.getBlob(this.imageField).getBinaryStream()));
                human.setDescription(result.getString(this.descriptionFirld));

                humans.add(human);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return humans;
    }

    private Human fillHumanByResult(ResultSet result) {
        Human human = null;

        try {
            while (result.next()) {
                human = new Human();
                human.setLastName(result.getString(this.lastNameField));
                human.setFirstName(result.getString(this.firstNameField));
                human.setMiddleName(result.getString(this.middleNameField));
                human.setAge(result.getInt(this.ageField));
                human.setBirthdate(result.getDate(this.birthdateField).toLocalDate());
                human.setRole(Role.valueOf(result.getString(this.roleField)));
                human.setCountry(result.getString(this.countryField));
                human.setPhoto(ImageIO.read(result.getBlob(this.imageField).getBinaryStream()));
                human.setDescription(result.getString(this.descriptionFirld));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return human;
    }
}
