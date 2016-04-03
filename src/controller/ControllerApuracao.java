package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.Nota;
import persistance.DataDAO;
import view.Tela;
import view.TelaTotal;

public class ControllerApuracao implements ActionListener {

	private JComboBox<String> CbBxEscola;
	private JComboBox<String> CbBxJurado;
	private JComboBox<String> CbBxQuesito;
	private JTextField txtNota;
	private int eList = 0;
	private int jList = 0;
	private int qList = 0;
	private int juradoNbr = 1;

	public ControllerApuracao(JComboBox<String> CbBxEscola, JComboBox<String> CbBxJurado, JComboBox<String> CbBxQuesito,
			JTextField txtNota) {
		this.CbBxEscola = CbBxEscola;
		this.CbBxJurado = CbBxJurado;
		this.CbBxQuesito = CbBxQuesito;
		this.txtNota = txtNota;
	}

	private Nota geraNota() {
		Nota nota = new Nota();
		nota.setNomeEscola(CbBxEscola.getSelectedItem().toString());
		nota.setNomeJurado(CbBxJurado.getSelectedItem().toString());
		nota.setNomeQuesito(CbBxQuesito.getSelectedItem().toString());
		int value = ((int) (Math.random() * 5) + 5);
		nota.setNota(((float) value));
		return nota;
	}

	private void controleCbBx() {
		System.out.println("Escola: " + (eList + 1) + " | " + "Jurado: " + (jList + 1) + " | " + "Quesito: "
				+ (qList + 1) + " | " + "Jurado de Numero: " + juradoNbr);
		if (eList < (DataDAO.escolaList.size() - 1)) {
			eList++;
			CbBxEscola.setSelectedIndex(eList);
		} else {
			eList = 0;
			CbBxEscola.setSelectedIndex(eList);
			if (jList < (DataDAO.juradoList.size() - 1)) {
				juradoNbr++;
				jList++;
				CbBxJurado.setSelectedIndex(jList);
			} else {
				jList = 0;
				juradoNbr++;
				CbBxJurado.setSelectedIndex(jList);
			}
		}
		if (qList < (DataDAO.quesitoList.size() - 1) && juradoNbr == 6) {
			juradoNbr = 1;
			qList++;
			CbBxQuesito.setSelectedIndex(qList);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("Inserir".equals(cmd)) {
			// for (int i = 0; i < 630; i++) { --Look this
			DataDAO dataDAO = new DataDAO();
			try {
				dataDAO.insereNota(geraNota());
				dataDAO.atualizaTotal(CbBxEscola.getSelectedItem().toString());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			controleCbBx();
		} else if ("Ver Total".equals(cmd)) {
			TelaTotal totalFrame;
			try {
				totalFrame = new TelaTotal();
				totalFrame.setVisible(true);
				totalFrame.setAlwaysOnTop(true);
				totalFrame.setLocationRelativeTo(null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

}
