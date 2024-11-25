package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


import controller.LoginController;


import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfLoginUsuario;
	private JPasswordField tfLoginSenha;

// lançar a aplicação
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//criar o frame
	public TelaLogin() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(179, 16, 9));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel containerCredenciais = new JPanel();
		containerCredenciais.setLayout(null);
		containerCredenciais.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(128, 128, 128)));
		containerCredenciais.setBounds(136, 66, 337, 287);
		contentPane.add(containerCredenciais);
		
		JLabel lblLoginUsuario = new JLabel("Usuário:");
		lblLoginUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoginUsuario.setBounds(35, 35, 266, 30);
		containerCredenciais.add(lblLoginUsuario);
		
		tfLoginUsuario = new JTextField();
		tfLoginUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfLoginUsuario.setColumns(10);
		tfLoginUsuario.setBounds(35, 65, 266, 30);
		containerCredenciais.add(tfLoginUsuario);
		
		JLabel lblLoginSenha = new JLabel("Senha:");
		lblLoginSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLoginSenha.setBounds(35, 125, 266, 30);
		containerCredenciais.add(lblLoginSenha);
		
		JButton btnLoginCadastrar = new JButton("Cadastrar-se");
		btnLoginCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoginCadastrar.setBackground(UIManager.getColor("Button.background"));
		btnLoginCadastrar.setBounds(161, 253, 129, 23);
		containerCredenciais.add(btnLoginCadastrar);
		
		tfLoginSenha = new JPasswordField();
		tfLoginSenha.setBounds(35, 155, 266, 31);
		containerCredenciais.add(tfLoginSenha);
		
		JButton btnLoginEntrar = new JButton("Entrar");
		btnLoginEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLoginEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoginEntrar.setBackground(UIManager.getColor("Button.background"));
		btnLoginEntrar.setBounds(47, 253, 104, 23);
		containerCredenciais.add(btnLoginEntrar);
		
		
		JTextArea taLoginAvisos = new JTextArea();
		taLoginAvisos.setFont(new Font("Monospaced", Font.BOLD, 12));
		taLoginAvisos.setBackground(UIManager.getColor("Panel.background"));
		taLoginAvisos.setForeground(new Color(0, 0, 0));
		taLoginAvisos.setEditable(false);
		taLoginAvisos.setWrapStyleWord(true);
		taLoginAvisos.setLineWrap(true);
		taLoginAvisos.setBounds(35, 192, 266, 50);
		containerCredenciais.add(taLoginAvisos);
		
		// interacao com botoes
		LoginController lCont = new LoginController(tfLoginUsuario, tfLoginSenha, taLoginAvisos);
	
		btnLoginEntrar.addActionListener(lCont);
		btnLoginCadastrar.addActionListener(lCont);
			
	}
}
