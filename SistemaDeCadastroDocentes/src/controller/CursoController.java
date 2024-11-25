package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import model.Curso;
import model.Professor;

public class CursoController implements ActionListener {
	
	private JTextField tfCursoCodigo;
	private JTextField tfCursoNome;
	private JTextField tfCursoArea;
	private JTextArea taCursoAvisos;

	public CursoController(JTextField tfCursoCodigo, JTextField tfCursoNome, JTextField tfCursoArea,
			JTextArea taCursoAvisos) {

		this.tfCursoCodigo = tfCursoCodigo;
		this.tfCursoNome = tfCursoNome;
		this.tfCursoArea = tfCursoArea;
		this.taCursoAvisos = taCursoAvisos;
	}

	public CursoController() {
		
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Salvar")) {
			try {
				cadastro();
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
			}
		}
		
		if (cmd.equals("Buscar")) {
			try {
				busca();
			} catch (IOException e1) {
				
				System.err.println(e1.getMessage());
			}
		}
		
		if (cmd.equals("Excluir")) {
			excluir();
		}
	}

	private void excluir() {
		// TODO Auto-generated method stub
		
	}

	private void busca() throws IOException {
		Curso curso = new Curso();
		curso.setCodigoCurso(tfCursoCodigo.getText());
		curso = buscaCurso(curso);
		
		if (curso.getNomeCurso() != null) {
			taCursoAvisos.setText("Curso: " + curso.getNomeCurso() + " - Código: " + curso.getCodigoCurso() + " - Área do Conhecimento: " + curso.getAreaConhecimento());
		} else {
			taCursoAvisos.setText("Curso não encontrado!");
			
		}
	}
	
	//BUSCAR CURSO ATRAVÉS DO CÓDIGO DO CURSO
	private Curso buscaCurso(Curso curso) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
		File arq = new File(path, "curso.csv");
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader (fis); 
			BufferedReader buffer = new BufferedReader (isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[0].equals(curso.getCodigoCurso())) {
					curso.setNomeCurso(vetLinha[1]);
					if (vetLinha.length > 2) {
			     		curso.setAreaConhecimento(vetLinha[2]);
			     	}
					break; 
				}
				linha = buffer.readLine();
				
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return curso;
	}

	private void cadastro() throws IOException {
		Curso curso = new Curso();
		curso.setCodigoCurso(tfCursoCodigo.getText());
		curso.setNomeCurso(tfCursoNome.getText());
		curso.setAreaConhecimento(tfCursoArea.getText());
		 
		if (curso.getCodigoCurso().trim().length() > 0 && curso.getNomeCurso().trim().length() > 0) {
			String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
			File dir = new File (path);
			if (!dir.exists()) {
				dir.mkdir();
			}
			
			File arq = new File(path, "curso.csv"); 
			boolean cadastrado = cursoCadastrado(arq, curso.getCodigoCurso(), curso.getNomeCurso());
			
			if (cadastrado == true) {
				atualizaCurso(curso);
			}
			else {
				cadastraCurso(curso.toString());
			}
			
			tfCursoCodigo.setText("");
			tfCursoNome.setText("");
			tfCursoArea.setText("");	
		} else {
			taCursoAvisos.setText("Existem campos (*) que não foram preenchidos");
		}
		
	}

	private void atualizaCurso(Curso curso) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
        File arq = new File(path, "curso.csv");
        File auxArq= new File(path, "cursoAux.csv");
        
        if(arq.exists()&&arq.isFile()) {
        	
        }
        
        BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));
        BufferedWriter pw = new BufferedWriter(new FileWriter(auxArq,true));
        Lista<Curso> listaCurso = new Lista<Curso>() ;    	
        String linha;
        
	    while ((linha = fw.readLine()) != null) {
	        String[] vetLinha = linha.split(";");
	        Curso cursoAux = new Curso();
	        if(!vetLinha[0].equals(curso.getCodigoCurso())) {
	        	
	        		if(listaCurso.isEmpty()) {
		          		cursoAux.setCodigoCurso(vetLinha[0]);
		          		cursoAux.setNomeCurso(vetLinha[1]);
		          		if (vetLinha.length > 2) {
				     		cursoAux.setAreaConhecimento(vetLinha[2]);
				     	}
		          		listaCurso.addFirst(curso);
	        		} else {
		          		cursoAux.setCodigoCurso(vetLinha[0]);
		          		cursoAux.setNomeCurso(vetLinha[1]);
		          		if (vetLinha.length > 2) {
				     		cursoAux.setAreaConhecimento(vetLinha[2]);
				     	}
	          		
			            try {
							listaCurso.addLast(curso);
						} catch (Exception e) {
							cadastro();
						}
	        		}     
	        		
	        		pw.write(linha+"\r\n");
	        	
	        } else {
	        	if(listaCurso.isEmpty()) {
	 
	        		listaCurso.addFirst(curso);
	            } else {
	            	
		            try {
						listaCurso.addLast(curso);
					} catch (Exception e) {
						cadastro();
					}
		        }    
	        	
	    		pw.write(curso.toString()+"\r\n");
	        }
	        	
	        
	    }
	    
		pw.flush();
		pw.close();
		fw.close();
		
		// Substitui o arquivo original pelo auxiliar
	    arq.delete();
	    auxArq.renameTo(arq);
	    taCursoAvisos.setText("Curso atualizado");
    }

	//CADASTRAR NOVO CURSO
	private void cadastraCurso(String csvCurso) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
		File dir = new File (path);
		
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		File arq = new File(path, "curso.csv"); 
		
		//monta a lista para atualizar
		Lista<Curso> listaCurso = montarLista();
		BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));
		BufferedWriter pw = new BufferedWriter(new FileWriter(arq, true));
		String[] vetLinha = csvCurso.split(";");
	    Curso curso = new Curso();
         
		if(listaCurso.isEmpty()) {
			curso.setCodigoCurso(vetLinha[0]);
			curso.setNomeCurso(vetLinha[1]);
			if (vetLinha.length > 2) {
	     		curso.setAreaConhecimento(vetLinha[2]);
	     	}
			
			listaCurso.addFirst(curso);
		} else {
			curso.setCodigoCurso(vetLinha[0]);
			curso.setNomeCurso(vetLinha[1]);
			if (vetLinha.length > 2) {
	     		curso.setAreaConhecimento(vetLinha[2]);
	     	}
			
		    try {
				listaCurso.addLast(curso);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
        
		pw.write(csvCurso+"\r\n");
		
		pw.flush();
		pw.close();
		fw.close();
		taCursoAvisos.setText("Curso cadastrado com sucesso!");
	}
		
	//CHECAR SE O CURSO JÁ ESTÁ CADASTRADO ATRAVÉS DO CÓDIGO DO CURSO
	public boolean cursoCadastrado(File arq, String codigo, String nome) throws IOException {
		if (!arq.exists()) {
	        return false; 
	    }

	    try (BufferedReader ler = new BufferedReader(new FileReader(arq))) {
	        String linha;
	        while ((linha = ler.readLine()) != null) {
	            String[] vetLinha = linha.split(";");
	            if (vetLinha[0].equals(codigo) || vetLinha[1].equals(nome)) {
	                return true; 
	            }
	        }
	    }
	    return false; 
	}
	
	public static Lista<Curso> montarLista() throws IOException {
	    String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
        File arq = new File(path, "curso.csv");
        Lista<Curso> listaCurso = new Lista<>();
        
        if (!arq.exists()) {
            // Cria o arquivo vazio, caso ele não exista
            arq.createNewFile();
            System.out.println("Arquivo curso.csv criado.");
        }
	        
		BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq))); 
		String linha;
		
		while ((linha = fw.readLine()) != null) {
	        String[] vetLinha = linha.split(";");
	        Curso curso = new Curso();
        
			if(listaCurso.isEmpty()) {
		     	curso.setCodigoCurso(vetLinha[0]);
		     	curso.setNomeCurso(vetLinha[1]);
		     	
		     	if (vetLinha.length > 2) {
		     		curso.setAreaConhecimento(vetLinha[2]);
		     	}
	     		listaCurso.addFirst(curso);
	         } else {
	     		curso.setCodigoCurso(vetLinha[0]);
	     		curso.setNomeCurso(vetLinha[1]);
	     		if (vetLinha.length > 2) {
		     		curso.setAreaConhecimento(vetLinha[2]);
		     	}
	     		
	     		try {
	    			listaCurso.addLast(curso);
	    		} catch (Exception e) {
	    			System.err.println(e.getMessage());
	    		}
	         }
		}
		 
		fw.close();
		
		return listaCurso;
	}
	
}
