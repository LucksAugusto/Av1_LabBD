package view;

import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.ControllerApuracao;
import controller.ControllerLists;
import persistance.DataDAO;

public class Tela extends JFrame {
	private static final long serialVersionUID = 1L;

	private int eList = 0;
	private int jList = 0;
	private int qList = 0;

	private JPanel screenPanel;
	private JComboBox<String> CbBxEscola;
	private JComboBox<String> CbBxJurado;
	private JComboBox<String> CbBxQuesito;
	private JTextField txtNota;
	private JButton btnInserir;
	private JButton btnQuesito;
	private JButton btnTotal;
	private JLabel lblEscola;
	private JLabel lblJurado;
	private JLabel lblQuesito;
	private JLabel lblNota;

	public Tela() {
		new ControllerLists();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setTitle("Apuração... Nota... 10");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		screenPanel = new JPanel();
		screenPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		screenPanel.setLayout(null);
		setContentPane(screenPanel);

		lblEscola = new JLabel("Escola: ");
		lblEscola.setBounds(50, 20, 100, 25);
		CbBxEscola = new JComboBox<String>();
		for (String nome : DataDAO.escolaList) {
			CbBxEscola.addItem(nome);
		}
		CbBxEscola.setSelectedIndex(eList);
		CbBxEscola.setBounds(100, 20, 450, 25);
		CbBxEscola.setEnabled(false);

		lblJurado = new JLabel("Jurado: ");
		lblJurado.setBounds(50, 60, 100, 25);
		CbBxJurado = new JComboBox<String>();
		for (String nome : DataDAO.juradoList) {
			CbBxJurado.addItem(nome);
		}
		CbBxJurado.setSelectedIndex(jList);
		CbBxJurado.setBounds(100, 60, 450, 25);
		CbBxJurado.setEnabled(false);

		lblQuesito = new JLabel("Quesito: ");
		lblQuesito.setBounds(50, 100, 100, 25);
		CbBxQuesito = new JComboBox<String>();
		for (String nome : DataDAO.quesitoList) {
			CbBxQuesito.addItem(nome);
		}
		CbBxQuesito.setSelectedIndex(qList);
		CbBxQuesito.setBounds(100, 100, 350, 25);
		CbBxQuesito.setEnabled(false);

		lblNota = new JLabel("Nota: ");
		lblNota.setBounds(50, 250, 100, 25);
		MaskFormatter notaFormat;
		try {
			notaFormat = new MaskFormatter("##'.#");
			txtNota = new JFormattedTextField(notaFormat);
			txtNota.requestFocus();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtNota.setBounds(100, 250, 100, 25);
		btnInserir = new JButton("Inserir");
		btnInserir.setBounds(220, 250, 100, 25);

		btnQuesito = new JButton("Ver Quesitos");
		btnQuesito.setBounds(220, 300, 100, 25);
		btnTotal = new JButton("Ver Total");
		btnTotal.setBounds(350, 300, 100, 25);

		screenPanel.add(lblEscola);
		screenPanel.add(CbBxEscola);
		screenPanel.add(lblJurado);
		screenPanel.add(CbBxJurado);
		screenPanel.add(lblQuesito);
		screenPanel.add(CbBxQuesito);
		screenPanel.add(lblNota);
		screenPanel.add(txtNota);
		screenPanel.add(btnInserir);
		screenPanel.add(btnQuesito);
		screenPanel.add(btnTotal);

		ControllerApuracao controllerAp = new ControllerApuracao(CbBxEscola, CbBxJurado, CbBxQuesito, txtNota);
		btnInserir.addActionListener(controllerAp);
		btnTotal.addActionListener(controllerAp);
		btnQuesito.addActionListener(controllerAp);
		// btnInserir.addActionListener(this);
	}

	/*
	 * @Override public void actionPerformed(ActionEvent e) { String cmd =
	 * e.getActionCommand(); if ("Inserir".equals(cmd)) { //for (int i = 0; i <
	 * 630; i++) { --Look this Nota nota = new Nota();
	 * nota.setNomeEscola(CbBxEscola.getSelectedItem().toString());
	 * nota.setNomeJurado(CbBxJurado.getSelectedItem().toString());
	 * nota.setNomeQuesito(CbBxQuesito.getSelectedItem().toString()); int value
	 * = ((int)(Math.random()*5)+5); nota.setNota(((float) value)); DataDAO
	 * dataDAO = new DataDAO(); try { dataDAO.insereNota(nota);
	 * dataDAO.atualizaTotal(CbBxEscola.getSelectedItem().toString()); } catch
	 * (Exception e1) { e1.printStackTrace(); } System.out.println( "Escola: " +
	 * (eList + 1) + " | " + "Jurado: " + (jList + 1) + " | " + "Quesito: " +
	 * (qList + 1) + " | " + "Jurado de Numero: " + juradoNbr); if (eList <
	 * (DataDAO.escolaList.size() - 1)) { eList++;
	 * CbBxEscola.setSelectedIndex(eList); } else { eList = 0;
	 * CbBxEscola.setSelectedIndex(eList); if (jList <
	 * (DataDAO.juradoList.size() - 1)) { juradoNbr++; jList++;
	 * CbBxJurado.setSelectedIndex(jList); } else { jList = 0; juradoNbr++;
	 * CbBxJurado.setSelectedIndex(jList); } } if (qList <
	 * (DataDAO.quesitoList.size() - 1) && juradoNbr == 6) { juradoNbr = 1;
	 * qList++; CbBxQuesito.setSelectedIndex(qList); } //} } }
	 */
}
