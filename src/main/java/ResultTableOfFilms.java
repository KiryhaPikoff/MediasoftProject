import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import film.Film;
	
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
         		Integer currentRow = rowAtPoint(getMousePosition());
         		Integer currentColumn = columnAtPoint(getMousePosition());
         		if(currentColumn == 4) {         			
         			JFrame filmFrame = new FilmFrame(films.get(currentRow));
         		}
         	}
         });
	}

}
		
