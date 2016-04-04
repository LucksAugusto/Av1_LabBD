package model;

import javax.swing.table.DefaultTableModel;

public class TotalModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Class<?> getColumnClass(int c) {
		Class<?> tipos[] = {String.class, float.class};
		return tipos[c];
	}

	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int c) {
		String name[] = {"Escola", "Nota"};
		return name[c];
	}
	
}