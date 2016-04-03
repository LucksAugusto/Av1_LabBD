package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import persistance.DBConnection;

public class Tela extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	private List<String> escolaList = new ArrayList<String>();
	private List<String> juradoList = new ArrayList<String>();
	private List<String> quesitoList = new ArrayList<String>();
	
	private int eList = 0;
	private int jList = 0;
	private int qList = 0;
	private int juradoNbr = 1;
	
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
	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
					Tela frame = new Tela();
					frame.setVisible(true);
					Connection con = DBConnection.getInstance().getConnection();
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
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
		
		//Usado para Teste
		escolaList.add("Cacarai");
		escolaList.add("cacareko");
		escolaList.add("Jacarei dos fundos");
		//Final do Teste
		lblEscola = new JLabel("Escola: ");
		lblEscola.setBounds(50, 20, 100, 25);
		CbBxEscola = new JComboBox<String>();
		for (String nome : escolaList) {
			CbBxEscola.addItem(nome);
		}
		CbBxEscola.setSelectedIndex(eList);
		CbBxEscola.setBounds(100, 20, 450, 25);
		
		//Usado para Teste
		juradoList.add("1");
		juradoList.add("2");
		juradoList.add("3");
		juradoList.add("4");
		juradoList.add("5");
		//Final do Teste
		lblJurado = new JLabel("Jurado: ");
		lblJurado.setBounds(50, 60, 100, 25);
		CbBxJurado = new JComboBox<String>();
		for (String nome : juradoList) {
			CbBxJurado.addItem(nome);
		}
		CbBxJurado.setSelectedIndex(qList);
		CbBxJurado.setBounds(100, 60, 450, 25);
		
		//Usado para Teste
		quesitoList.add("bunda");
		quesitoList.add("peito");
		quesitoList.add("corpo");
		quesitoList.add("sexo");
		//Final do Teste
		lblQuesito = new JLabel("Quesito: ");
		lblQuesito.setBounds(50, 100, 100, 25);
		CbBxQuesito = new JComboBox<String>();
		for (String nome : quesitoList) {
			CbBxQuesito.addItem(nome);
		}
		CbBxQuesito.setSelectedIndex(qList);
		CbBxQuesito.setBounds(100, 100, 350, 25);
		
		lblNota = new JLabel("Nota: ");
		lblNota.setBounds(50, 250, 100, 25);
		txtNota = new JTextField(15);
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
		
		btnInserir.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("Inserir".equals(cmd)) {
			if(eList < (escolaList.size()-1)) {
				eList++;
				CbBxEscola.setSelectedIndex(eList);
			} else {
				eList=0;
				CbBxEscola.setSelectedIndex(eList);
				if (jList < (juradoList.size()-1)) {
					juradoNbr++;
					jList++;
					CbBxJurado.setSelectedIndex(jList);
				} else {
					jList = 0;
					juradoNbr++;
					CbBxJurado.setSelectedIndex(jList);
				}
			}
			System.out.println(juradoNbr);
			if (qList < (quesitoList.size()-1) && juradoNbr == 6) {
				juradoNbr=1;
				qList++;
				CbBxQuesito.setSelectedIndex(qList);
			}
		}
	}

}
