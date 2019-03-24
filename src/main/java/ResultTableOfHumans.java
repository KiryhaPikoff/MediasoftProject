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

import human.Human;

public class ResultTableOfHumans extends JTable {

	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel tableModel;
	private final Integer countColumns = 4;
	private String[] columnNames = new String[countColumns];
	
	
	public ResultTableOfHumans(List<Human> humans) {
		this.initCloumnNames();
		
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, humans.size());
		this.setModel(tableModel);;
			
		int i = 0;
		for (Human human : humans) {
			this.setValueAt(human.getFirstName(), i, 0);
			this.setValueAt(human.getLastName(), i, 1);
			this.setValueAt(human.getMiddleName(), i, 2);
			
			this.setValueAt("\u2753", i, 3); // 2714
			i++;
		}
		
		this.setCellSelectionEnabled(false);
		this.setEnabled(false);
		this.getTableHeader().setReorderingAllowed(false);
		
		this.setMouseListener(humans);
	}
	
	private void initCloumnNames() {
		columnNames[0] = "Имя";
		columnNames[1] = "Фамилия";
		columnNames[2] = "Отчество";
		columnNames[3] = "Информация";
	}
	
	private void setMouseListener(List<Human> humans) {
		this.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mouseClicked(MouseEvent e) {
         		Integer currentRow = rowAtPoint(getMousePosition());
         		Integer currentColumn = columnAtPoint(getMousePosition());
         		if(currentColumn == 3) {         			
         			JFrame humanFrame = new HumanFrame(humans.get(currentRow));
         		}
         	}
         });
	}
}
	
