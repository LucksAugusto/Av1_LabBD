package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.NotasModel;
import persistance.DataDAO;

public class TelaQuesitos extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JPanel quesitoPanel;
	private JTable tblQuesito;
	private JButton btnFechar;
	private JLabel lblTabela;
	private JTextField txtPesquisa;
	
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public TelaQuesitos(String quesito) throws Exception {
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
		
		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {

			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				filterTable(tblQuesito,txtPesquisa);
			}
		});
		JPanel painelInicial = new JPanel(new GridLayout(2, 1));
		painelInicial.add(lblTabela);
		painelInicial.add(txtPesquisa);
		quesitoPanel.add(painelInicial,BorderLayout.PAGE_START);
		quesitoPanel.add(btnFechar,BorderLayout.SOUTH);
	}
	
	public void filterTable(JTable nometbl, JTextField pesquisa){
		DefaultTableModel model = (DefaultTableModel) nometbl.getModel();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);  
		nometbl.setRowSorter(sorter);
		String text = pesquisa.getText();  
		if (text.length() == 0) {  
		          sorter.setRowFilter(null);  
		} else {  
		          sorter.setRowFilter(RowFilter.regexFilter(text));
		          nometbl.setRowSorter(sorter);
		}
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();		
		if("Voltar a apuração".equals(cmd)){
			this.setVisible(false);
		}		
	}
	

}
