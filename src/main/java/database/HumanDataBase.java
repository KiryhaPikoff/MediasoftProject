package database;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import human.Human;
import human.Role;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hsqldb.types.BlobInputStream;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HumanDataBase implements HumanService {
	
    private AccessDataBase dataBase = new AccessDataBase();
    
    private List<Human> humans = null;
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
    
    private final static Logger logger = LogManager.getLogger(HumanDataBase.class);

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
  
                while (result.next()) {
                    human = new Human();
                    human.setLastName(result.getString(this.lastNameField));
                    human.setFirstName(result.getString(this.firstNameField));
                    human.setMiddleName(result.getString(this.middleNameField));
                    human.setAge(result.getInt(this.ageField));
                    human.setBirthdate(result.getDate(this.birthdateField).toLocalDate());
                    human.setRole(Role.valueOf(result.getString(this.roleField)));
                    human.setCountry(result.getString(this.countryField));
                    try {
    					human.setPhoto(ImageIO.read(result.getBlob(this.imageField).getBinaryStream()));
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
        					e.printStackTrace();
        			}
                    human.setDescription(result.getString(this.descriptionFirld));
                    logger.info("успешно взяли данные человека");
                }
                connection.close();
            }
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
                while (result.next()) {  
                    Human human = new Human();
                    human.setId(result.getInt(this.idField));
                    human.setLastName(result.getString(this.lastNameField));
                    human.setFirstName(result.getString(this.firstNameField));
                    human.setMiddleName(result.getString(this.middleNameField));
                  
                    humans.add(human);
                    human = null;
                    logger.info("успешно взяли данные человека, добавили в список");
                }
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
                while (result.next()) {
                    Human human = new Human();
                    human.setId(result.getInt(this.idField));
                    human.setLastName(result.getString(this.lastNameField));
                    human.setFirstName(result.getString(this.firstNameField));
                    human.setMiddleName(result.getString(this.middleNameField));
                  
                    humans.add(human);
                    human = null;
                    logger.info("успешно взяли данные человека, добавили в список");
                }
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
                while (result.next()) {
                    Human human = new Human();
                    human.setId(result.getInt(this.idField));
                    human.setLastName(result.getString(this.lastNameField));
                    human.setFirstName(result.getString(this.firstNameField));
                    human.setMiddleName(result.getString(this.middleNameField));
                  
                    humans.add(human);
                    human = null;
                    logger.info("успешно взяли данные человека, добавили в список");
                }
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
                while (result.next()) {
                    Human human = new Human();
                    human.setId(result.getInt(this.idField));
                    human.setLastName(result.getString(this.lastNameField));
                    human.setFirstName(result.getString(this.firstNameField));
                    human.setMiddleName(result.getString(this.middleNameField));
                  
                    humans.add(human);
                    human = null;
                    logger.info("успешно взяли данные человека, добавили в список");
                }
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
                while (result.next()) {
                    Human human = new Human();
                    human.setId(result.getInt(this.idField));
                    human.setLastName(result.getString(this.lastNameField));
                    human.setFirstName(result.getString(this.firstNameField));
                    human.setMiddleName(result.getString(this.middleNameField));
                  
                    humans.add(human);
                    human = null;
                    logger.info("успешно взяли данные человека, добавили в список");
                }
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
                while (result.next()) {
                    Human human = new Human();
                    human.setId(result.getInt(this.idField));
                    human.setLastName(result.getString(this.lastNameField));
                    human.setFirstName(result.getString(this.firstNameField));
                    human.setMiddleName(result.getString(this.middleNameField));
                  
                    humans.add(human);
                    human = null;
                    logger.info("успешно взяли данные человека, добавили в список");
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();
        return humans;
    }

    public List<Human> getAllHumans() {
        this.humans = null;

        try {
            this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
            Connection connection = this.dataBase.getConnection();
            if (connection != null) {
                humans = new ArrayList<Human>();
                Statement statement = connection.createStatement();
                String sql = "select * from " + this.tableName + " ORDER BY lastName";
                ResultSet result = statement.executeQuery(sql);
                while (result.next()) {
                    Human human = new Human();
                    human.setId(result.getInt(this.idField));
                    human.setLastName(result.getString(this.lastNameField));
                    human.setFirstName(result.getString(this.firstNameField));
                    human.setMiddleName(result.getString(this.middleNameField));
                  
                    humans.add(human);
                    human = null;
                    logger.info("успешно взяли данные человека, добавили в список");
                }
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataBase.disconnect();
        return humans;
    }

	@Override
	public Integer getCountStrings() {
		Integer count = 0;
		try {
			this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
			Connection connection = this.dataBase.getConnection();
		    if (connection != null) {
		        Statement statement = connection.createStatement();
		        String sql = "select count(*) cnt from " + this.tableName;
		        ResultSet result = statement.executeQuery(sql);
		        while (result.next()) {
		         	count = result.getInt("cnt");
		        }
		    }
		    connection.close();
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
	     dataBase.disconnect();
	     return count;
	}

	@Override
	public void addHumanToBase(String id, String firstName, String lastName, String middleName, String country,
							   String birthdate, String age, String role, String photoPath, String description) {
		
		try {
			this.dataBase.connect(this.dataBaseLogin, this.dataBasePassword);
			Connection connection = this.dataBase.getConnection();
		    if (connection != null) {
		    	String sql = "INSERT INTO " + this.tableName + " (id, firstName, lastName, middlename, country,"
		    			+ " birthdate, age, role, photo, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";	 
		    	
	        	PreparedStatement ps1 = connection.prepareStatement(sql);
	        	ps1.setInt(1, Integer.parseInt(id));
	        	ps1.setString(2, firstName);
	        	ps1.setString(3, lastName);
	        	ps1.setString(4, middleName);
	        	ps1.setString(5, country);
	        	ps1.setDate(6, java.sql.Date.valueOf(birthdate));
	        	ps1.setInt(7, Integer.parseInt(age));
	        	ps1.setString(8, role);
		        ps1.setBlob(9, new FileInputStream(new File(photoPath)));
	        	ps1.setString(10, description);
				ps1.executeUpdate();
				logger.info("человек успешно добавлен в б/д");
		    }
		    connection.close();
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
	     dataBase.disconnect();
	}
	
}
