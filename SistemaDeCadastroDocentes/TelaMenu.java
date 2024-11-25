package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MenuController;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TelaMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenu frame = new TelaMenu();
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
	public TelaMenu() {
		setTitle("Sistema de Chamadas Públicas - Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(198, 9, 9));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel tbMenu = new JPanel();
		tbMenu.setBounds(0, 48, 626, 395);
		contentPane.add(tbMenu);
		tbMenu.setLayout(null);
		
		JButton btnMenuCadastros = new JButton("CADASTROS");
		btnMenuCadastros.setToolTipText("CRUD de Disciplinas, Professores, Cursos  Inscrições.");
		btnMenuCadastros.setBackground(new Color(198, 9, 9));
		btnMenuCadastros.setForeground(new Color(255, 255, 255));
		btnMenuCadastros.setFont(new Font("Dubai", Font.BOLD, 28));
		btnMenuCadastros.setBounds(64, 141, 203, 65);
		tbMenu.add(btnMenuCadastros);
		
		JButton btnMenuConsultas = new JButton("CONSULTAS");
		btnMenuConsultas.setToolTipText("Consultar Inscritos e Disciplinas com processos abertos");
		btnMenuConsultas.setForeground(Color.WHITE);
		btnMenuConsultas.setFont(new Font("Dubai", Font.BOLD, 28));
		btnMenuConsultas.setBackground(new Color(198, 9, 9));
		btnMenuConsultas.setBounds(345, 141, 203, 65);
		tbMenu.add(btnMenuConsultas);
		
		JLabel lblMenuTitulo = new JLabel("Menu Principal");
		lblMenuTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuTitulo.setFont(new Font("Dubai", Font.BOLD, 30));
		lblMenuTitulo.setBounds(154, 32, 322, 58);
		tbMenu.add(lblMenuTitulo);
		
		MenuController controller = new MenuController();
        btnMenuCadastros.setActionCommand("CADASTROS");
        btnMenuCadastros.addActionListener(controller);

        btnMenuConsultas.setActionCommand("CONSULTAS");
        btnMenuConsultas.addActionListener(controller);
	}
}
