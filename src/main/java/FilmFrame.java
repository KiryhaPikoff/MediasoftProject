import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
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

public class FilmFrame extends JFrame {

	private String titleFrame;
	private JPanel content;
	private JTextPane textPane;
	private JScrollPane scrollPane;
	
	private Film film;

	public FilmFrame(Film film) {
		this.film = film;

		this.initWindow();
		this.initTextPane();
		this.initScrollPane();
	}
	
	private void initWindow() {
		try {
			this.setIconImage(ImageIO.read(new File("src/main/resources/images/icons/filmIcon.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setBackground(SystemColor.textHighlight);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 677, 350);
		this.content = new JPanel();
		this.content.setBackground(SystemColor.inactiveCaption);
		this.setContentPane(content);
		this.content.setLayout(null);
		
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
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.film.getImage(), 10, 40, 250, 289, null);
	} 
}
