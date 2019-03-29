package frames;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import film.Genre;

public abstract class myCheckerFields {
	public final static String dateRE = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";
	public final static String yearRE = "\\d{4}";
	public final static String ageRE = "\\d{2}";
	public final static String fioRE = "^[а-яА-Я ]+$";
	
	public myCheckerFields() {
		
	}
	
	public static boolean isDate(String checkedString) {
		Pattern pattern = Pattern.compile(dateRE);
		return pattern.matcher(checkedString).matches();	
	}
	
	public static boolean isYear(String checkedString) {
		if(checkedString.length() == 4) {
			Pattern pattern = Pattern.compile(yearRE);
			return pattern.matcher(checkedString).matches();
		}
		return false;
	}
	
	public static boolean isImageFile(String filePath) {
		try {
			Image img = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean isAge(String checkedString) {
		if(checkedString.length() == 2) {
			Pattern pattern = Pattern.compile(ageRE);
			return pattern.matcher(checkedString).matches();
		}
		return false;
	}
	
	public static boolean isFIO(String checkedString) {
		Pattern pattern = Pattern.compile(fioRE);
		return pattern.matcher(checkedString).matches();
	}
}
