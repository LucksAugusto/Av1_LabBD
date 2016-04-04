package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import model.NotasModel;
import model.TotalModel;
import persistance.DataDAO;

public class TelaQuesitos extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JPanel quesitoPanel;
	private JTable tblQuesito;
	private JButton btnFechar;
	private JLabel lblTabela;
	private String quesito;
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public TelaQuesitos(String quesito) throws Exception {
		this.quesito = quesito;
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
		setTitle("Total de Pontos");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 650, 550);
		setLocationRelativeTo(null);
		quesitoPanel = new JPanel();
		quesitoPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		quesitoPanel.setLayout(new BorderLayout());
		setContentPane(quesitoPanel);
		
		lblTabela = new JLabel(quesito,SwingConstants.CENTER);
		lblTabela.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTabela.setBounds(10,10,640,25);
		
		btnFechar = new JButton("Voltar a apuração");
		btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBounds(10, 157, 371, 141);
		quesitoPanel.add(scrollPane,BorderLayout.CENTER);
		tblQuesito = new JTable();
		scrollPane.setViewportView(tblQuesito);
		NotasModel modelo = new NotasModel();
		Color myColor = Color.decode("#EEEEEE");
		tblQuesito.setBackground(myColor);
		tblQuesito.getTableHeader().setReorderingAllowed(false);
		tblQuesito.setModel(modelo);
		
		DataDAO dDAO = new DataDAO();
		
		dDAO.carregaQuesito(tblQuesito, quesito);
		btnFechar.addActionListener(this);
		
		quesitoPanel.add(lblTabela,BorderLayout.PAGE_START);
		quesitoPanel.add(btnFechar,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();		
		if("Voltar a apuração".equals(cmd)){
			this.setVisible(false);
		}		
	}
	

}
