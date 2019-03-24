import database.FilmDataBase;
import database.HumanDataBase;
import film.Film;
import human.Human;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {

    private JPanel content;
    private JTable resultTable;
    private JTextField requestField;
    private JScrollPane scrollPane;
    private JButton findBtn;
    private Choice findChoice;
    
    private final String byIdHuman = "by human ID";
    private final String byIdFilm = "by film ID";
    private final String byCountryHuman = "by human country";
    private final String byCountryFilm = "by film country";
    private final String getAllHumans = "get all humans";

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
    	setBackground(SystemColor.inactiveCaption);
    	this.initWindow();
    	this.initScrollPane();
        this.initRequestField();
        this.initChoice();
        this.initTable();
        this.initFindButton();	
    }
    
    private void initWindow() {
    	try {
			this.setIconImage(ImageIO.read(new File("src/main/resources/images/icons/mainWindowIcon.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.setTitle("База данных о кинематографе");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	    setBounds(100, 100, 790, 590);
	    content = new JPanel();
	    content.setBackground(SystemColor.inactiveCaption);
	    content.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(content);
	    content.setLayout(null);        	    
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
        findChoice.add(this.getAllHumans);
        
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
	    					Frame humanFrame = new HumanFrame(human);
	    				} else {
	    					requestField.setText("Такого человека нет в базе!");
	    				}
	    				break;
	    			}
	    			case byIdFilm: {
	    				Integer id = Integer.parseInt(requestField.getText());
	    				Film film = new FilmDataBase().getFilmById(id);
	    				if(film != null) {    					
	    					Frame filmFrame = new FilmFrame(film);
	    				} else {
	    					requestField.setText("Такого фильма нет в базе!");
	    				}   				
	    				break;
	    			}
	    			case byCountryHuman: {
	    				String country = requestField.getText();
	    				List<Human> humans = new HumanDataBase().getHumansByCountry(country);
	    				if(humans != null) {   
	    					resultTable = new ResultTableOfHumans(humans);
	    					scrollPane.setViewportView(resultTable);		
	    				} else {
	    					requestField.setText("Людей из такой страны не найдено!");
	    				}
	    				break;
	    			}
	    			case byCountryFilm: {
	    				String country = requestField.getText();
	    				List<Film> films = new FilmDataBase().getFilmsByCountry(country);
	    				if(films != null) {   
	    					resultTable = new ResultTableOfFilms(films);
	    					scrollPane.setViewportView(resultTable);		
	    				} else {
	    					requestField.setText("Людей из такой страны не найдено!");
	    				}
	    				break;
	    			}
	    			case getAllHumans: {
	    				List<Human> humans = new HumanDataBase().getAllHumans();
	    				if(humans != null) {   
	    					resultTable = new ResultTableOfHumans(humans);
	    					scrollPane.setViewportView(resultTable);		
	    				} else {
	    					requestField.setText("Таблица с людьми пуста!");
	    				}
	    				break;
	    			}
	    			default: System.out.println("Что-то пошло не так!"); break;
	    		}	  		
		    }
	    });
    }

    public void paint(Graphics g) {
    	super.paint(g);
    }
}
