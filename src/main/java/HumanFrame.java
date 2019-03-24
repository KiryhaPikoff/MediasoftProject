import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import human.Human;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JList;
import java.awt.ScrollPane;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

public class HumanFrame extends JFrame {
	
	private String titleFrame;
	private JPanel contentPane;
	private JTextPane textPane;
	private JScrollPane scrollPane;
	
	private Human human;

	public HumanFrame(Human human) {	
		this.human = human;
		
		this.initWindow();
		this.initTextPane();
		this.initScrollPane();
	}
	
	private void initWindow() {
		try {
			this.setIconImage(ImageIO.read(new File("src/main/resources/images/icons/humanIcon.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBackground(SystemColor.textHighlight);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 669, 347);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		this.titleFrame = human.getLastName() + " " + human.getFirstName() + " " + human.getMiddleName();
		this.setTitle(this.titleFrame);
	}
	
	private void initScrollPane() {
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(266, 11, 387, 296);
		this.getContentPane().add(this.scrollPane);
		this.scrollPane.setViewportView(this.textPane);
	}
	
	private void initTextPane() {
		this.textPane = new JTextPane();
		this.textPane.setFont(new Font("Consolas", Font.PLAIN, 15));
		this.textPane.setBackground(SystemColor.info);
		this.textPane.setText(this.human.toString());
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(human.getPhoto(), 15, 40, 250, 289, null);
	} 
}
