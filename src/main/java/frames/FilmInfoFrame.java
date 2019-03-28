package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import film.Film;
import human.Human;

public class FilmInfoFrame extends JFrame {

	private String titleFrame;
	private JPanel content;
	private JTextPane textPane;
	private JScrollPane scrollPane;
	private Image icon;
	
	private Film film;

	public FilmInfoFrame(Film film) {
		this.film = film;
		try {
			this.icon = ImageIO.read(new File("src/main/resources/images/icons/filmIcon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.initWindow();
		this.initTextPane();
		this.initScrollPane();
	}
	
	private void initWindow() {
		this.setBackground(SystemColor.textHighlight);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 677, 350);
		this.content = new JPanel();
		this.content.setBackground(SystemColor.inactiveCaption);
		this.setContentPane(content);
		this.content.setLayout(null);	
		this.setIconImage(this.icon);
		
		this.titleFrame = this.film.getName();
		this.setTitle(this.titleFrame);
	}

	private void initScrollPane() {
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(266, 11, 385, 289);
		this.getContentPane().add(scrollPane);
		this.scrollPane.setViewportView(textPane);
	}
	
	private void initTextPane() {
		this.textPane = new JTextPane();
		this.textPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		this.textPane.setBackground(SystemColor.info);
		this.textPane.setText(film.toString());
		this.textPane.setEditable(false);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.film.getImage(), 10, 40, 250, 289, null);
	} 
}
