package work;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import frames.MyCheckerFields;

public class MyCheckerFieldsTest {
	@Test
    public void testIsFIO() {	
		String rightName = "Кирилл";
		String falseName1 = "Кирill";
		String falseName2 = "К1р1лл";
		
		assertTrue(MyCheckerFields.isFIO(rightName));
		assertFalse(MyCheckerFields.isFIO(falseName1));
		assertFalse(MyCheckerFields.isFIO(falseName2));
    }
	
	@Test
    public void testIsDate() {	
		String rightDate = "2000-09-16";
		String falseDate1 = "16-09-2000";
		String falseDate2 = "2000.09.16";
		String falseDate3 = "2000/09/16";
		
		assertTrue(MyCheckerFields.isDate(rightDate));
		assertFalse(MyCheckerFields.isDate(falseDate1));
		assertFalse(MyCheckerFields.isDate(falseDate2));
		assertFalse(MyCheckerFields.isDate(falseDate3));
    }
	
	@Test
    public void testIsYear() {	
		String rightYear = "2000";
		String falseYear1 = "fwe2";
		String falseYear2 = "59613";
		
		assertTrue(MyCheckerFields.isYear(rightYear));
		assertFalse(MyCheckerFields.isYear(falseYear1));
		assertFalse(MyCheckerFields.isYear(falseYear2));
    }
	
	@Test
    public void testIsImageFile() {	
		String rightFile = "src/main/resources/images/forTests/rightImage.jpg";
		String falseFiel1 = "src/main/resources/images/forTests/falseImage.txt";
		String falseFiel2 = "src/main/resources/images/forTests/falseImage.png";
		String falseFiel3 = "src/main/resources/images/forTests/falseImage.jpeg";

		assertTrue(MyCheckerFields.isImageFile(rightFile));
		assertFalse(MyCheckerFields.isImageFile(falseFiel1));
		assertFalse(MyCheckerFields.isImageFile(falseFiel2));
		assertFalse(MyCheckerFields.isImageFile(falseFiel3));
    }

}
