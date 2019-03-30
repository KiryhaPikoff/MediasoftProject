package frames.tables;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.FilmDataBase;
import film.Film;
import frames.FilmInfoFrame;
	
public class ResultTableOfFilms extends JTable {
	
	private static final long serialVersionUID = 1L;
	DefaultTableModel tableModel;
	final Integer countColumns = 5;
	String[] columnNames = new String[countColumns];
		
	public ResultTableOfFilms(List<Film> films) {
		this.initColumnNames();
		tableModel = new DefaultTableModel(columnNames, films.size());
		this.setModel(tableModel);
			
		int i = 0;
		for (Film film : films) {
			this.setValueAt(film.getName(), i, 0);
			this.setValueAt(film.getCountry(), i, 1);
			this.setValueAt(film.getGenre().toString(), i, 2);
			this.setValueAt(film.getRating(), i, 3);
			this.setValueAt("\u2753", i, 4); // 2714
			i++;
		}
	
		this.setEnabled(false);
		this.getTableHeader().setReorderingAllowed(false);
		this.setCellSelectionEnabled(false);
		
		this.setMouseListener(films);
	 }
	
	private void initColumnNames() {
		columnNames[0] = "Название";
		columnNames[1] = "Страна";
		columnNames[2] = "Жанр";
		columnNames[3] = "Рейтинг";
		columnNames[4] = "Информация";
	}
	
	private void setMouseListener(List<Film> films) {
		this.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mouseClicked(MouseEvent e) {
         		createFrame(films);
         	}
         });
	}

	
	private void createFrame(List<Film> films) {
		Integer currentRow = rowAtPoint(getMousePosition());
 		Integer currentColumn = columnAtPoint(getMousePosition());
 		if(currentColumn == 4) {        
 			Film film = new FilmDataBase().getFilmById(films.get(currentRow).getID());
 			JFrame filmFrame = new FilmInfoFrame(film);
 		}
	}
}
		
