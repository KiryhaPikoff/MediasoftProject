package frames.components;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import frames.HumanAddFrame;

public class AddHumanButton extends JButton {

	public AddHumanButton() {
		this.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Frame humanAddFrame = new HumanAddFrame();
	    	}
		});
	}
}
