package persistance;

import java.util.ArrayList;

import javax.swing.JTable;

import model.Nota;

public interface IDataDAO {
	
	public ArrayList<String> populaEscola() throws Exception;
	public ArrayList<String> populaJurado() throws Exception;
	public ArrayList<String> populaQuesito() throws Exception;
	public void insereNota(Nota nota) throws Exception;
	public void atualizaTotal(String escola) throws Exception;
	public void carregaTotal(JTable tableTotal) throws Exception;
	
}
