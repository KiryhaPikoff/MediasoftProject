package frames.tables;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.HumanDataBase;
import frames.HumanInfoFrame;
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
		columnNames[0] = "Фамилия";
		columnNames[1] = "Имя";
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
         			Human human = new HumanDataBase().getHumanById(humans.get(currentRow).getId());
         			JFrame humanFrame = new HumanInfoFrame(human);
         		}
         	}
         });
	}
}
	
