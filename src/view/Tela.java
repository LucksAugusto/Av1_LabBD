package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Tela extends JFrame {
	private String[] escolaList = {"Unidos da vila maria","Rio tiete"};
	private String[] juradoList = {"Zé","Zó"};
	private String[] quesitoList = {"Bunda","Peito"};

	private static final long serialVersionUID = 1L;
	
	private JPanel screenPanel;
	private JComboBox CbBxEscola;
	private JComboBox CbBxJurado;
	private JComboBox CbBxQuesito;
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
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		
		lblEscola = new JLabel("Escola: ");
		lblEscola.setBounds(50, 20, 100, 25);
		CbBxEscola = new JComboBox(escolaList);
		CbBxEscola.setBounds(100, 20, 450, 25);
		
		lblJurado = new JLabel("Jurado: ");
		lblJurado.setBounds(50, 60, 100, 25);
		CbBxJurado = new JComboBox(juradoList);
		CbBxJurado.setBounds(100, 60, 450, 25);
		
		lblQuesito = new JLabel("Quesito: ");
		lblQuesito.setBounds(50, 100, 100, 25);
		CbBxQuesito = new JComboBox(quesitoList);
		CbBxQuesito.setBounds(100, 100, 350, 25);
		
		lblNota = new JLabel("Nota: ");
		lblNota.setBounds(50, 250, 100, 25);
		txtNota = new JTextField(15);
		txtNota.setBounds(100, 250, 100, 25);
		btnInserir = new JButton("Inserir");
		btnInserir.setBounds(200, 250, 100, 25);
		
		btnQuesito = new JButton("Ver Quesitos");
		btnQuesito.setBounds(200, 300, 100, 25);
		btnTotal = new JButton("Ver Total");
		btnTotal.setBounds(320, 300, 100, 25);
		
		
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
	}

}
