package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

}
