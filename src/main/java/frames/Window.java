package frames;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.FilmDataBase;
import database.HumanDataBase;
import film.Film;
import film.Genre;
import frames.components.AddHumanButton;
import frames.tables.ResultTableOfFilms;
import frames.tables.ResultTableOfHumans;
import human.Human;

public class Window extends JFrame {

    private JPanel content;
    private JTable resultTable;
    private JTextField requestField;
    private JScrollPane scrollPane;
    private JButton findBtn;
    private JButton addHumanBtn;
    private Choice findChoice;
    
    private JFrame humanAddFrame;
    
    private HumanDataBase humanDataBase;
    private FilmDataBase filmDataBase;
    
    private final Logger logger = LogManager.getLogger(Window.class);
    
    private final String byIdHuman = "by human ID";
    private final String byIdFilm = "by film ID";
    private final String byCountryHuman = "by human country";
    private final String byCountryFilm = "by film country";
    private final String byGenreFilm = "by film genre";
    private final String byRatingFilm = "by film rating";
    private final String getAllHumans = "get all humans";
    private final String getAllFilms = "get all films";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Window frame = new Window();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Window() {
    	this.initWindow();
    	this.initScrollPane();
        this.initRequestField();
        this.initChoice();
        this.initTable();
        this.initFindButton();	
        this.initAddHumanButton();
        this.humanDataBase = new HumanDataBase();
        this.filmDataBase = new FilmDataBase();
        
        
        
        //String dat = "Вася";
       // System.out.println(myCheckerFields.isFIO(dat));
        System.out.println(Date.valueOf("2012-09-03"));
    }
    
    private void initWindow() {
    	try {
			this.setIconImage(ImageIO.read(new File("src/main/resources/images/icons/mainWindowIcon.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.setTitle("Кинобаза");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	    setBounds(100, 100, 1000, 590);
	    content = new JPanel();
	    content.setBackground(SystemColor.inactiveCaption);
	    content.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(content);
	    content.setLayout(null); 
	}
    
    private void initAddHumanButton() {
    	this.addHumanBtn = new AddHumanButton();
        addHumanBtn.setBackground(SystemColor.menu);
        addHumanBtn.setText("Добавить человека в б/д");
        this.addHumanBtn.setBounds(784, 44, 200, 36);
        content.add(addHumanBtn);
    }
    
    private void initTable() {
        resultTable = new JTable();
        resultTable.setBackground(new Color(176, 224, 230));        
        content.add(resultTable);
    }
    
    private void initScrollPane() {
    	scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.LIGHT_GRAY);
        scrollPane.setBounds(10, 44, 764, 506);
        content.add(scrollPane);
    }
    
    private void initRequestField() {
    	requestField = new JTextField();
    	requestField.setToolTipText("");
        requestField.setBackground(Color.WHITE);
        requestField.setBounds(10, 11, 426, 22);
        content.add(requestField); 
    }
    
    private void initChoice() {
    	findChoice = new Choice();
        findChoice.setBackground(Color.WHITE);
        findChoice.setBounds(442, 11, 213, 22);
        findChoice.add(this.byIdHuman);
        findChoice.add(this.byIdFilm);  
        findChoice.add(this.byCountryHuman);
        findChoice.add(this.byCountryFilm);
        findChoice.add(this.byGenreFilm);
        findChoice.add(this.byRatingFilm);
        findChoice.add(this.getAllHumans);
        findChoice.add(this.getAllFilms);
        
        content.add(findChoice);
    }
    
    private void initFindButton() {
    	findBtn = new JButton("\u041D\u0430\u0439\u0442\u0438");
	    findBtn.setBackground(SystemColor.menu);
	    findBtn.setBounds(661, 10, 113, 23);
	    content.add(findBtn);
	    findBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
					switch(findChoice.getSelectedItem()) {
	    			case byIdHuman: {
	    				Integer id = Integer.parseInt(requestField.getText());
	    				Human human = new HumanDataBase().getHumanById(id);
	    				if(human != null) {    					
	    					Frame humanFrame = new HumanInfoFrame(human);
	    				} else {
							requestField.setText("Человек не найден!");
	    				}
	    				logger.info("Очистил humans");
	    				human = null;
	    				break;
	    			}
	    			case byIdFilm: {
	    				Integer id = Integer.parseInt(requestField.getText());
	    				Film film = new FilmDataBase().getFilmById(id);
	    				if(film != null) { 
	    					Frame filmFrame = new FilmInfoFrame(film);
	    				} else {
							requestField.setText("Фильм на найден!");
	    				}   
	    				film = null;
	    				break;
	    			}
	    			case byCountryHuman: {
	    				String country = requestField.getText();
	    				List<Human> humans = new HumanDataBase().getHumansByCountry(country);
	    				if(!humans.isEmpty()) {   
	    					resultTable = new ResultTableOfHumans(humans);
	    					scrollPane.setViewportView(resultTable);		
	    				} else {
							requestField.setText("Люди на найдены!");
	    				}
	    				humans = null;
	    				break;
	    			}
	    			case byCountryFilm: {
	    				String country = requestField.getText();
	    				List<Film> films = new FilmDataBase().getFilmsByCountry(country);
	    				if(!films.isEmpty()) {   
	    					resultTable = new ResultTableOfFilms(films);
	    					scrollPane.setViewportView(resultTable);		
	    				} else {
							requestField.setText("Фильмы на найдены!");
	    				}
	    				films = null;
	    				break;
	    			}
	    			case byGenreFilm: {
	    				Genre genre = null;
	    				try {
	    					genre = Genre.valueOf(requestField.getText());
						} catch (Exception e2) {
							e2.printStackTrace();
							break;
						}
	    				List<Film> films = new FilmDataBase().getFilmsByGenre(genre);
	    				if(!films.isEmpty()) {   
	    					resultTable = new ResultTableOfFilms(films);
	    					scrollPane.setViewportView(resultTable);		
	    				} else {
							requestField.setText("Фильмы на найдены!");
	    				}
	    				films = null;
	    				break;
	    			}
	    			case byRatingFilm: {
	    				Integer rating = Integer.parseInt(requestField.getText());
	    				List<Film> films = new FilmDataBase().getFilmsByRating(rating);
	    				if(!films.isEmpty()) {   
	    					resultTable = new ResultTableOfFilms(films);
	    					scrollPane.setViewportView(resultTable);		
	    				} else {
							requestField.setText("Фильмы на найдены!");
	    				}
	    				films = null;
	    				break;
	    			}
	    			case getAllHumans: {
	    				List<Human> humans = humanDataBase.getAllHumans();
	    				if(!humans.isEmpty()) {   
	    					resultTable = new ResultTableOfHumans(humans);
	    					scrollPane.setViewportView(resultTable);		
	    				} else {
							requestField.setText("Люди на найдены!");
	    				}
	    				humans = null;
	    				break;
	    			}
	    			case getAllFilms: {
	    				FilmDataBase filmDataBase = new FilmDataBase();
	    				List<Film> films = filmDataBase.getAllFilms();
	    				if(!films.isEmpty()) {
	    					resultTable = new ResultTableOfFilms(films);
	    					scrollPane.setViewportView(resultTable);
	    				} else {
	    					requestField.setText("Фильмы на найдены!");
	    				}
	    				films = null;
	    				break;
	    			}
	    			default: break;
	    		}	  		
			}		
	    });
    }

    public void paint(Graphics g) {
    	super.paint(g);
    }
}
