package frames;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import database.HumanDataBase;

import java.awt.Choice;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class HumanAddFrame extends JFrame {
	
	private String title = "Добавление человека в б/д";
	
	private JPanel content;
	
	private JTextField idTF;
	private JTextField firstNameTF;
	private JTextField lastNameTF;
	private JTextField middleNameTF;
	private JTextField birthdateTF;
	private JTextField ageTF;
	private JTextField countryTF;
	private JTextField imageTF;
	private JTextField descriptionTF;
	
	private Choice roleCH;

	private JButton addBtn;
	
	public HumanAddFrame() {
		this.initFrame();
		this.initLabels();
		this.initTextFields();
		this.initChoice();
		this.initAddBtn();
		
	}
	
	private void initAddBtn() {
		addBtn = new JButton("Добавить");
		addBtn.setBackground(SystemColor.menu);
		addBtn.setBounds(112, 278, 240, 33);
		getContentPane().add(addBtn);
		
		addBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isCorrectTextFields()) {
					new HumanDataBase().addHumanToBase(idTF.getText(),
														firstNameTF.getText(),
														lastNameTF.getText(),
														middleNameTF.getText(),
														countryTF.getText(),
														birthdateTF.getText(), 
														ageTF.getText(),
														roleCH.getSelectedItem(),
														imageTF.getText(),
														descriptionTF.getText());
				}
			}
		});
	}
	
	private boolean isCorrectTextFields() {	
		boolean isIncorrect = false;
		if(myCheckerFields.isDate(birthdateTF.getText())) {
			birthdateTF.setBackground(new Color(174, 255, 174));
		} else {
			birthdateTF.setBackground(new Color(255, 174, 174));
			isIncorrect = true;
		}
		if(myCheckerFields.isFIO(firstNameTF.getText())) {
			firstNameTF.setBackground(new Color(174, 255, 174));
		} else {
			firstNameTF.setBackground(new Color(255, 174, 174));
			isIncorrect = true;
		}
		if(myCheckerFields.isFIO(lastNameTF.getText())) {
			lastNameTF.setBackground(new Color(174, 255, 174));
		} else {
			lastNameTF.setBackground(new Color(255, 174, 174));
			isIncorrect = true;
		}
		if(myCheckerFields.isFIO(middleNameTF.getText())) {
			middleNameTF.setBackground(new Color(174, 255, 174));
		} else {
			middleNameTF.setBackground(new Color(255, 174, 174));
			isIncorrect = true;
		}
		if(myCheckerFields.isFIO(countryTF.getText())) {
			countryTF.setBackground(new Color(174, 255, 174));
		} else {
			countryTF.setBackground(new Color(255, 174, 174));
			isIncorrect = true;
		}
		if(myCheckerFields.isAge(ageTF.getText())) {
			ageTF.setBackground(new Color(174, 255, 174));
		} else {
			ageTF.setBackground(new Color(255, 174, 174));
			isIncorrect = true;
		}
		if(myCheckerFields.isImageFile(imageTF.getText())) {
			imageTF.setBackground(new Color(174, 255, 174));
		} else {
			imageTF.setBackground(new Color(255, 174, 174));
			isIncorrect = true;
		}
		if(descriptionTF.getText().isEmpty()) {
			isIncorrect = true;
		}
		
		return !isIncorrect;
	}
	
	private void initFrame() {
		this.getContentPane().setBackground(SystemColor.inactiveCaption);
		this.setBackground(SystemColor.inactiveCaption);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 470, 347);
		this.setTitle(this.title);
	}

	private void initChoice() {
		roleCH = new Choice();
		roleCH.setBackground(SystemColor.inactiveCaptionBorder);
		roleCH.setBounds(112, 194, 343, 17);
		roleCH.add("Актёр");
		roleCH.add("Режиссёр");
		getContentPane().add(roleCH);
	}
	
	private void initTextFields() {
		idTF = new JTextField();
		idTF.setBackground(SystemColor.inactiveCaptionBorder);
		idTF.setBounds(112, 11, 343, 17);
		getContentPane().add(idTF);
		idTF.setColumns(10);
		idTF.setText(String.valueOf(new HumanDataBase().getCountStrings() + 1));
		idTF.setEditable(false);

		firstNameTF = new JTextField();
		firstNameTF.setBackground(SystemColor.inactiveCaptionBorder);
		firstNameTF.setColumns(10);
		firstNameTF.setBounds(112, 34, 343, 17);
		getContentPane().add(firstNameTF);
		
		lastNameTF = new JTextField();
		lastNameTF.setBackground(SystemColor.inactiveCaptionBorder);
		lastNameTF.setColumns(10);
		lastNameTF.setBounds(112, 59, 343, 17);
		getContentPane().add(lastNameTF);
		
		middleNameTF = new JTextField();
		middleNameTF.setBackground(SystemColor.inactiveCaptionBorder);
		middleNameTF.setColumns(10);
		middleNameTF.setBounds(112, 84, 343, 17);
		getContentPane().add(middleNameTF);
		
		birthdateTF = new JTextField();
		birthdateTF.setBackground(SystemColor.inactiveCaptionBorder);
		birthdateTF.setColumns(10);
		birthdateTF.setBounds(112, 110, 343, 17);
		getContentPane().add(birthdateTF);
		
		ageTF = new JTextField();
		ageTF.setBackground(SystemColor.inactiveCaptionBorder);
		ageTF.setColumns(10);
		ageTF.setBounds(112, 137, 343, 17);
		getContentPane().add(ageTF);
		
		countryTF = new JTextField();
		countryTF.setBackground(SystemColor.inactiveCaptionBorder);
		countryTF.setColumns(10);
		countryTF.setBounds(112, 166, 343, 17);
		getContentPane().add(countryTF);
		
		imageTF = new JTextField();
		imageTF.setColumns(10);
		imageTF.setBackground(SystemColor.inactiveCaptionBorder);
		imageTF.setBounds(112, 222, 343, 17);
		getContentPane().add(imageTF);
		
		descriptionTF = new JTextField();
		descriptionTF.setColumns(10);
		descriptionTF.setBackground(SystemColor.inactiveCaptionBorder);
		descriptionTF.setBounds(112, 250, 343, 17);
		getContentPane().add(descriptionTF);
	}
	
	private void initLabels() {
		JLabel lblId = new JLabel("ID человека:");
		lblId.setBounds(10, 11, 83, 17);
		getContentPane().add(lblId);
		
		JLabel label = new JLabel("Имя:");
		label.setBounds(10, 36, 46, 17);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Фамилия:");
		label_1.setBounds(10, 61, 64, 17);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Отчество:");
		label_2.setBounds(10, 86, 64, 17);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Дата рождения:");
		label_3.setBounds(10, 111, 95, 17);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Возраст:");
		label_4.setBounds(10, 139, 95, 17);
		getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Страна:");
		label_5.setBounds(10, 167, 95, 17);
		getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Роль:");
		label_6.setBounds(10, 195, 95, 17);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("Описание\r\n");
		label_7.setBounds(10, 251, 74, 17);
		getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("Фотография:");
		label_8.setBounds(10, 223, 95, 17);
		getContentPane().add(label_8);
	}
	
}
