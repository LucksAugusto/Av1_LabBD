package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Tela extends JFrame {

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		screenPanel = new JPanel();
		screenPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		screenPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(screenPanel);
	}

}
