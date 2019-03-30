package frames;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.HumanDataBase;
import film.Genre;

public abstract class MyCheckerFields {
	public final static String dateRE = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";
	public final static String yearRE = "\\d{4}";
	public final static String ageRE = "\\d{2}";
	public final static String fioRE = "^[а-яА-Я]+$";
	
    private final static Logger logger = LogManager.getLogger(MyCheckerFields.class);

	public MyCheckerFields() {
		
	}
	
	public static boolean isDate(String checkedString) {
		Pattern pattern = Pattern.compile(dateRE);
		if(pattern.matcher(checkedString).matches()) {
			return true;
		}	
		logger.error("введите дату в формате yyyy-mm-dd");
		return false;
	}
	
	public static boolean isYear(String checkedString) {
		Pattern pattern = Pattern.compile(yearRE);
		if(checkedString.length() == 4 && pattern.matcher(checkedString).matches()) {
			return true;
		}
		logger.error("введите год в формате yyyy");
		return false;
	}
	
	public static boolean isImageFile(String filePath) {
		try {
			Image img = ImageIO.read(new File(filePath));
			if (img == null) {
				logger.error("введён некорректный путь или фийл не картинка");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("ошибка чтения файла: " + filePath);
			return false;
		}
		return true;
	}
	
	public static boolean isAge(String checkedString) {
		Pattern pattern = Pattern.compile(ageRE);
		if (checkedString.length() == 2 && pattern.matcher(checkedString).matches()) {	
			return true;
		}
		logger.error("введите возраст в формате yy");
		return false;
	}
	
	public static boolean isFIO(String checkedString) {
		Pattern pattern = Pattern.compile(fioRE);
		if (pattern.matcher(checkedString).matches()) {
			return true;
		}
		logger.error("разрешено использовать только а-я и А-Я");
		return false;
	}
}
