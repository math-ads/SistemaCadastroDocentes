package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.swing.SwingUtilities;

import view.TelaCadastro;
import view.TelaListagem;

public class MenuController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("CADASTROS")) {
			abrirTelaCadastro();
		}
		if (cmd.equals("CONSULTAS")) {
			abrirTelaListagem();
		}
		
	}

	private void abrirTelaListagem() {
		SwingUtilities.invokeLater(() -> {
	        TelaListagem telaListagem = null;
			try {
				telaListagem = new TelaListagem();
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			telaListagem.setVisible(true);
	    });
	}
	
	private void abrirTelaCadastro() {
		SwingUtilities.invokeLater(() -> {
	        TelaCadastro telaCadastro = new TelaCadastro();
			telaCadastro.setVisible(true);
	    });
	}

}
