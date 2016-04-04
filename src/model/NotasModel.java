package model;

import javax.swing.table.DefaultTableModel;

public class NotasModel extends DefaultTableModel{

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Class<?> getColumnClass(int c) {
		Class<?> tipos[] = {String.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class};
		return tipos[c];
	}

	public int getColumnCount() {
		return 9;
	}

	public String getColumnName(int c) {
		String name[] = {"Escola", "Nota 1", "Nota 2", "Nota 3", "Nota 4", "Nota 5", "Menor Nota", "Maior Nota", "Total Quesito"};
		return name[c];
	}
}
