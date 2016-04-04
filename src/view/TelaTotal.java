package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import model.TotalModel;
import persistance.DataDAO;

public class TelaTotal extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JPanel totalPanel;
	private JTable tblTotal;
	private JButton btnFechar;
	private JLabel lblTabela;

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public TelaTotal() throws Exception {
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
		setBounds(100, 100, 450, 550);
		setLocationRelativeTo(null);
		totalPanel = new JPanel();
		totalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		totalPanel.setLayout(new BorderLayout());
		setContentPane(totalPanel);
		
		lblTabela = new JLabel("Tabela de Pontos",SwingConstants.CENTER);
		lblTabela.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTabela.setBounds(10,10,440,25);
		
		btnFechar = new JButton("Voltar a apuração");
		btnFechar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBounds(10, 157, 371, 141);
		totalPanel.add(scrollPane,BorderLayout.CENTER);
		tblTotal = new JTable();
		scrollPane.setViewportView(tblTotal);
		TotalModel modelo = new TotalModel();
		Color myColor = Color.decode("#EEEEEE");
		tblTotal.setBackground(myColor);
		tblTotal.getTableHeader().setReorderingAllowed(false);
		tblTotal.setModel(modelo);
		
		DataDAO dDAO = new DataDAO();
		
		dDAO.carregaTotal(tblTotal);
		btnFechar.addActionListener(this);
		
		totalPanel.add(lblTabela,BorderLayout.PAGE_START);
		totalPanel.add(btnFechar,BorderLayout.SOUTH);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();		
		if("Voltar a apuração".equals(cmd)){
			this.setVisible(false);
		}
	}

}
