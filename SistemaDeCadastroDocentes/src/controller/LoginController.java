package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.Login;
import view.TelaCrud;
import view.TelaLogin;

public class LoginController implements ActionListener {
	private JTextField tfLoginUsuario;
	private JPasswordField  pfLoginSenha;
	 private JTextArea taLoginAvisos;
	
	public LoginController( JTextField tfLoginUsuario, JPasswordField  pfLoginSenha, JTextArea taLoginAvisos ) {
		this.tfLoginUsuario = tfLoginUsuario;
		this.pfLoginSenha = pfLoginSenha;
		this.taLoginAvisos= taLoginAvisos;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("Cadastrar-se")) {
			try {
				cadastrar();
			} catch (IOException e1) {
				
				System.err.println(e1.getMessage());
			}
		}

		if (cmd.equals("Entrar")) {
				try {
					entrar();
				} catch (PropertyVetoException e1) {
					
					System.err.println(e1.getMessage());
				} catch (IOException e1) {
					
					System.err.println(e1.getMessage());
				}
			
		}
	}
	
    // modulo de resposta ao botao entrar
	private void entrar() throws IOException, PropertyVetoException {
		Login login = new Login();
	    login.setUsuario(tfLoginUsuario.getText());
	    login.setSenha(String.valueOf(pfLoginSenha.getPassword()));

	    String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
	    File arq = new File(path, "login.csv");

	    boolean loginValido = false;
	    boolean usuarioEncontrado = false;
	    String loginFinded = "";

	    if (arq.exists() && arq.isFile()) {
	    	try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)))) {
	            String linha;

	            while ((linha = buffer.readLine()) != null) {
	                String[] vetLinha = linha.split(";");

	                if (vetLinha[0].equals(login.getUsuario())) {
	                    usuarioEncontrado = true;
	                    loginFinded = linha;

	                    if (vetLinha[1].equals(login.getSenha())) {
	                        loginValido = true;
	                        break;
	                    }
	                }
	            }
	        }
	    }

	 // Atualiza o JTextArea com a mensagem apropriada
	    if (loginValido) {
	    	taLoginAvisos.setText("Login bem-sucedido! Bem-vindo, " + login.getUsuario() + ".");
	        abrirTelaMenu(); // Abre a tela principal
	    } else if (usuarioEncontrado) {
	    	taLoginAvisos.setText("Senha incorreta! Tente novamente");
	    } else {
	    	taLoginAvisos.setText("Usuário não encontrado! Cadastre-se para continuar");
	    }
	}

	private void abrirTelaMenu() throws PropertyVetoException {
	    SwingUtilities.invokeLater(() -> {
	        //   TelaPrincipal telaPrincipal = new TelaPrincipal();
				try {
					TelaCrud telaPrincipal = new TelaCrud();
					telaPrincipal.setVisible(true);
					telaPrincipal.setBounds(100, 100, 640, 480);
					
					TelaLogin telaLogin = new TelaLogin();
					telaLogin.setVisible(false);
					telaLogin.dispose();
				} catch (PropertyVetoException e) {
					
					System.err.println(e.getMessage());
				} catch (FileNotFoundException e) {
					
					System.err.println(e.getMessage());
				} catch (IOException e) {
					
					System.err.println(e.getMessage());
				}
	        	
	    });
	}
		
	private void cadastrar() throws IOException {
		Login login = new Login();
		login.setUsuario(tfLoginUsuario.getText());
		login.setSenha(String.valueOf(pfLoginSenha.getPassword()));

		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
	    File arq = new File(path, "login.csv");
	    
	    String loginFinded = "";

	    boolean usuarioEncontrado = false;
	    
	    if (arq.exists() && arq.isFile()) {
	        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arq)))) {
	            
	        	String linha;
	            
	        	while ((linha = buffer.readLine()) != null) {
	            	String[] vetLinha = linha.split(";");
	            	
	            	if (vetLinha[0].equals(login.getUsuario())) {
	            		usuarioEncontrado = true;
	            		loginFinded = linha;
	            		break;
	            	}
	            }
	        }
	    }
	    
	    if (usuarioEncontrado) {
	    	taLoginAvisos.setText("Usuário encontrado no sistema! Entre ou insira outro usuário");
	    } else {
	    	cadastraLogin(login.toString());
	    }
	}

	private void cadastraLogin(String csvLogin) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";

		File dir = new File(path);

		if (!dir.exists()) {
			dir.mkdir();
		}

		File arq= new File(path,"login.csv");
		boolean existe = false;
		
		if (arq.exists()) {
			existe = true;
		}
		
		FileWriter fw = new FileWriter(arq, existe);

		PrintWriter pw= new PrintWriter(fw);
		pw.write(csvLogin+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}
		
}
	

	
