package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.Nota;


public class DataDAO implements IDataDAO {
	
	public static ArrayList<String> escolaList = new ArrayList<String>();
	public static ArrayList<String> juradoList = new ArrayList<String>();
	public static ArrayList<String> quesitoList = new ArrayList<String>();
	
	@Override
	public ArrayList<String> populaEscola() throws Exception {
		String SQL = "SELECT nome FROM Escola";
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			escolaList.add(rs.getString(1));
		}
		stmt.close();
		rs.close();
		return escolaList;
	}

	@Override
	public ArrayList<String> populaJurado() throws Exception {
		String SQL = "SELECT nome FROM Jurado";
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			juradoList.add(rs.getString(1));
		}
		stmt.close();
		rs.close();
		return juradoList;
	}
	
	@Override
	public ArrayList<String> populaQuesito() throws Exception {
		String SQL = "SELECT nome FROM Quesito";
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			quesitoList.add(rs.getString(1));
		}
		stmt.close();
		rs.close();
		return quesitoList;
	}

	@Override
	public void insereNota(Nota nota) throws Exception {
		String SQL = "EXEC sp_notas_str ?,?,?,?";
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement(SQL);
		ps.setString(1, nota.getNomeEscola());
		ps.setString(2, nota.getNomeJurado());
		ps.setString(3, nota.getNomeQuesito());
		ps.setFloat(4, nota.getNota());
		ps.execute();
		ps.close();
	}

	@Override
	public void atualizaTotal(String escola) throws Exception {
		String SQL = "EXEC sp_total ?";
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement(SQL);
		ps.setString(1, escola);
		ps.execute();
		ps.close();
	}

	@Override
	public void carregaTotal(JTable tableTotal) throws Exception {
		String SQL = "SELECT nome, totalPontos FROM Escola ORDER BY totalPontos";
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet rs = stmt.executeQuery();
		while(tableTotal.getRowCount() > 0) 
        {
            ((DefaultTableModel) tableTotal.getModel()).removeRow(0);
        }
        int columns = rs.getMetaData().getColumnCount();
        while(rs.next())
        {  
            Object[] row = new Object[columns];
            for (int i = 1; i <= columns; i++)
            {  
                row[i - 1] = rs.getObject(i);
            }
            ((DefaultTableModel) tableTotal.getModel()).insertRow(rs.getRow()-1,row);
        }
		stmt.close();
		rs.close();
	}
}
