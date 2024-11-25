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

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import model.Disciplina;
import model.Inscricao;
import model.Professor;

public class DisciplinaController implements ActionListener {
	
	private JTextField tfDisciplinaCodigo;
	private JTextField tfDisciplinaNome;
	private JComboBox<String> cbDisciplinaDia; // Tipo correto para JComboBox
	private JTextField tfDisciplinaHorario;
	private JTextField tfDisciplinaCarga; // Quantidade de horas
	private JComboBox<String> cbDisciplinaCurso; // Código do curso
	private JTextArea taDisciplinaAvisos;

	// Construtor atualizado
	public DisciplinaController(
		JTextField tfDisciplinaCodigo, 
		JTextField tfDisciplinaNome, 
		JTextField tfDisciplinaQuantHoras,
		JTextField tfDisciplinaHorario, 
		JTextField tfDisciplinaCarga,
		JComboBox<String> cbDisciplinaCurso,
		JTextArea taDisciplinaAvisos,
		JComboBox<String> cbDisciplinaDia
	) {
		this.tfDisciplinaCodigo = tfDisciplinaCodigo;
		this.tfDisciplinaNome = tfDisciplinaNome;
		this.tfDisciplinaCarga = tfDisciplinaCarga;
		this.tfDisciplinaHorario = tfDisciplinaHorario;
		this.tfDisciplinaCarga = tfDisciplinaCarga;
		this.cbDisciplinaCurso = cbDisciplinaCurso;
		this.taDisciplinaAvisos = taDisciplinaAvisos;
		this.cbDisciplinaDia = cbDisciplinaDia; // Atribui o JComboBox
	}
	
	public DisciplinaController() {

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
		if (cmd.equals("Excluir")) {
			try {
				excluir();
			} catch (IOException e1) {
				
				System.err.println(e1.getMessage());
			} catch (Exception e1) {
				
				System.err.println(e1.getMessage());
			}
		}
	}

	private void excluir() throws Exception {
		Disciplina disciplina = new Disciplina();
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setDiaSemana((String) cbDisciplinaDia.getSelectedItem());  
		disciplina.setHoraInicio(tfDisciplinaHorario.getText());
	    disciplina.setQuantHoras(Integer.parseInt(tfDisciplinaCarga.getText()));
		disciplina.setCurso((String) cbDisciplinaCurso.getSelectedItem());	
		
		CursoController cCont = new CursoController();
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
		File dir = new File (path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File(path, "curso.csv"); 
		boolean cursoExiste = cCont.cursoCadastrado(arq, null, disciplina.getCurso());   
		if (cursoExiste==true) {
			arq = new File(path, "disciplina.csv"); 
			boolean cadastrado = disciplinaCadastrada(arq, disciplina.getCodigoDisciplina());
			if( cadastrado == true ) {
				excluirDisciplina(disciplina);
			}
		} else {
			taDisciplinaAvisos.setText("Selecione um curso (*)");
		}
		
		tfDisciplinaCodigo.setText("");
		tfDisciplinaNome.setText("");
		tfDisciplinaHorario.setText("");
		tfDisciplinaCarga.setText("");
		cbDisciplinaCurso.setSelectedIndex(0);
	}

	private void excluirDisciplina(Disciplina disciplina) throws Exception {
		String codigo= disciplina.getCodigoDisciplina();
		excluirInscritos(codigo); 
		
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
        File arq = new File(path, "disciplina.csv");
        File auxArq = new File(path, "disciplinaAux.csv");
        
        if(arq.exists()&&arq.isFile()) {
        	BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));
    		BufferedWriter pw = new BufferedWriter(new FileWriter(auxArq,true));
    		Lista<Disciplina> listaDisciplinas = new Lista<Disciplina>() ;
    		 String linha;
             while ((linha = fw.readLine()) != null) {
                 String[] vetLinha = linha.split(";");
    		Disciplina disciplinaAux = new Disciplina();
    		if(!vetLinha[0].equals( disciplina.getCodigoDisciplina())) {
    			if(listaDisciplinas.isEmpty()) {
    				disciplinaAux.setCodigoDisciplina(vetLinha[0]);
    	    		disciplinaAux.setNomeDisciplina(vetLinha[1]);
    	    		disciplinaAux.setDiaSemana(vetLinha[2]);
    	    		try {
    	    		disciplinaAux.setHoraInicio(vetLinha[3]);
    	    		} catch (Exception e) { 		
    	    		}
    	    		disciplinaAux.setQuantHoras(Integer.parseInt(vetLinha[4]));
    	    		disciplina.setCurso(vetLinha[5]);

    	        listaDisciplinas.addFirst(disciplina);}
    	    	else {
    	    		disciplinaAux.setCodigoDisciplina(vetLinha[0]);
    	    		disciplina.setNomeDisciplina(vetLinha[1]);
    	    		disciplina.setDiaSemana(vetLinha[2]);
    	    		try {
    	    		disciplinaAux.setHoraInicio(vetLinha[3]);
    	    		} catch (Exception e) { 		
    	    		}
    	    		disciplinaAux.setQuantHoras(Integer.parseInt(vetLinha[4]));
    	    		disciplina.setCurso(vetLinha[5]);
    	 
    	   
    	    		
    	        listaDisciplinas.addLast(disciplinaAux);
    	    	}
    		      pw.write(linha);
                  pw.newLine();
    
             pw.close();
     		 fw.close();
     		 arq.delete();
             auxArq.renameTo(arq);
             taDisciplinaAvisos.setText("Disciplina atualizada!");
  		}
	     		
		
		
	}}
	        }

	private void excluirInscritos(String codigo) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
        File arq = new File(path, "inscricoes.csv");
        File auxArq = new File(path, "inscricoesAux.csv");
        if(arq.exists()&&arq.isFile()) {
        	BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));
    		BufferedWriter pw = new BufferedWriter(new FileWriter(auxArq,true));
    		Lista<Inscricao> listaInscricoes = new Lista<Inscricao>() ;
    		 String linha;
    		 while ((linha = fw.readLine()) != null) {
                 String[] vetLinha = linha.split(";");
    		Inscricao inscricaoAux= new Inscricao();
    		
    		
    		if (!vetLinha[1].equals(codigo)) {
    			if(listaInscricoes.isEmpty()) {
    				inscricaoAux.setCpfProfessor(vetLinha[0]);
    				inscricaoAux.setDisciplina(vetLinha[1]);
    				inscricaoAux.setCodigoProcesso(vetLinha[2]);	
    	        listaInscricoes.addFirst(inscricaoAux);}
    	    	else {
    	    		inscricaoAux.setCpfProfessor(vetLinha[0]);
    				inscricaoAux.setDisciplina(vetLinha[1]);
    				inscricaoAux.setCodigoProcesso(vetLinha[2]);	
    	        listaInscricoes.addLast(inscricaoAux);
    	        }
    			pw.write(linha);
                pw.newLine();
  		} 
    		
         }
    		 pw.close();
      		fw.close();
      		  arq.delete();
      	        auxArq.renameTo(arq);}
        
	}

	private void busca() throws IOException {
		Disciplina disciplina = new Disciplina();
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setDiaSemana((String) cbDisciplinaDia.getSelectedItem());  
		disciplina.setHoraInicio(tfDisciplinaHorario.getText());
	    disciplina.setQuantHoras(Integer.parseInt(tfDisciplinaCarga.getText()));
		disciplina.setCurso((String) cbDisciplinaCurso.getSelectedItem());
		
		disciplina = buscaDisciplina(disciplina);
		
		if (disciplina.getNomeDisciplina() != null) {
			taDisciplinaAvisos.setText("Disciplina: " + disciplina.getNomeDisciplina() + 
					" - Código: " + disciplina.getCodigoDisciplina() + " - Dia da Semana: " + disciplina.getCodigoDisciplina() 
					+ " - Hora Início: " + disciplina.getHoraInicio() + " - Horas semanais: " + disciplina.getQuantHoras() + " - Curso: " 
					+ disciplina.getCurso());
		} else {
			taDisciplinaAvisos.setText("Disciplina não encontrada!");
			
		}
	}
	
	//BUSCAR DISCIPLINA ATRAVÉS DO CÓDIGO DA DISCIPLINA
	private Disciplina buscaDisciplina(Disciplina disciplina) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
		File arq = new File(path, "disciplina.csv");
		
		
		if (arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader (fis); 
			BufferedReader buffer = new BufferedReader (isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if (vetLinha[0].equals(disciplina.getCodigoDisciplina())) {
					disciplina.setNomeDisciplina(vetLinha[1]);
					disciplina.setDiaSemana(vetLinha[2]);
					disciplina.setHoraInicio(vetLinha[3]);
					disciplina.setQuantHoras(Integer.parseInt(vetLinha[4]));
					disciplina.setCurso(vetLinha[5]);
			
					break; 
				}
				linha = buffer.readLine();
				
			}
			buffer.close();
			isr.close();
			fis.close();
		}
		return disciplina;
	}

	private void cadastro() throws IOException {
		Disciplina disciplina = new Disciplina();
		disciplina.setCodigoDisciplina(tfDisciplinaCodigo.getText());
		disciplina.setNomeDisciplina(tfDisciplinaNome.getText());
		disciplina.setDiaSemana((String) cbDisciplinaDia.getSelectedItem());
		disciplina.setHoraInicio(tfDisciplinaHorario.getText());
		disciplina.setCurso((String) cbDisciplinaCurso.getSelectedItem());	
		
		if (tfDisciplinaCarga.getText().matches("^\\d+$")) {
			disciplina.setQuantHoras(Integer.parseInt(tfDisciplinaCarga.getText()));
		}
		
		if (disciplina.getCodigoDisciplina().trim().length() > 0 && disciplina.getNomeDisciplina().trim().length() > 0) {
			CursoController cCont = new CursoController();
			String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
			File dir = new File (path);
			
			if (!dir.exists()) {
				dir.mkdir();
			}
			
			File arq = new File(path, "curso.csv"); 
			boolean cursoExiste = cCont.cursoCadastrado(arq, null, disciplina.getCurso());   
			
			if (cursoExiste == true) {
				arq = new File(path, "disciplina.csv"); 
				boolean cadastrado = disciplinaCadastrada(arq, disciplina.getCodigoDisciplina());
				if( cadastrado == true ) {
					atualizaDisciplina(disciplina);
				}
				else {
					cadastraDisciplina(disciplina.toString());
				}
				
				tfDisciplinaCodigo.setText("");
				tfDisciplinaNome.setText("");
				tfDisciplinaHorario.setText("");
				tfDisciplinaCarga.setText("");
				cbDisciplinaCurso.setSelectedIndex(0);
				cbDisciplinaDia.setSelectedIndex(0);
			
			} else {
				taDisciplinaAvisos.setText("Selecione um curso (*)");
			}
		} else {
			taDisciplinaAvisos.setText("Existem campos (*) que não foram preenchidos");
		}
	}


	private void atualizaDisciplina(Disciplina disciplina) throws IOException {
		// TODO Auto-generated method stub
		 String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
	        File arq = new File(path, "disciplina.csv");
	        File auxArq = new File(path, "disciplinaAux.csv");
	        if(arq.exists()&&arq.isFile()) {
	        	BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));
	    		BufferedWriter pw = new BufferedWriter(new FileWriter(auxArq,true));
	    		Lista<Disciplina> listaDisciplinas = new Lista<Disciplina>();
	    		
	    		String linha;
	    		 
	            while ((linha = fw.readLine()) != null) {
					String[] vetLinha = linha.split(";");
					Disciplina disciplinaAux = new Disciplina();
					if (!vetLinha[0].equals( disciplina.getCodigoDisciplina())) {
							if (listaDisciplinas.isEmpty()) {
								disciplinaAux.setCodigoDisciplina(vetLinha[0]);
					    		disciplinaAux.setNomeDisciplina(vetLinha[1]);
					    		disciplinaAux.setDiaSemana(vetLinha[2]);
					    		try {
					    		disciplinaAux.setHoraInicio(vetLinha[3]);
					    		} catch (Exception e) { 		
					    		}
					    		disciplinaAux.setQuantHoras(Integer.parseInt(vetLinha[4]));
					    		disciplina.setCurso(vetLinha[5]);
					 
					   
					    		
					    		listaDisciplinas.addFirst(disciplina);
					        } else {
					    		disciplinaAux.setCodigoDisciplina(vetLinha[0]);
					    		disciplina.setNomeDisciplina(vetLinha[1]);
					    		disciplina.setDiaSemana(vetLinha[2]);
					    		try {
					    			disciplinaAux.setHoraInicio(vetLinha[3]);
					    		} catch (Exception e) { 		
					    		}
					    		
					    		disciplinaAux.setQuantHoras(Integer.parseInt(vetLinha[4]));
					    		disciplina.setCurso(vetLinha[5]);

						        try {
									listaDisciplinas.addLast(disciplinaAux);
								} catch (Exception e) {
									System.err.println(e.getMessage());
								}
					    	}
							
						    pw.write(linha);
					        pw.newLine();
					} else {
						if (listaDisciplinas.isEmpty()) {
							
							 listaDisciplinas.addFirst(disciplina);
							 
						} else {
								
								try {
									listaDisciplinas.addLast(disciplina);
								} catch (Exception e) {
									System.err.println(e.getMessage());
								}
								
						}
						
						pw.write(disciplina.toString());
					    pw.newLine();
					}
            
	    			
	    		}
	             pw.close();
	     		fw.close();
	     		 arq.delete();
	             auxArq.renameTo(arq);
	             taDisciplinaAvisos.setText("Disciplina atualizada!");}
	     		
	        }
	      
    	
	

	//CADASTRAR NOVA DISCIPLINA
	private void cadastraDisciplina(String csvDisciplina) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
		File dir = new File (path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		
		File arq = new File(path, "disciplina.csv"); 
		if (!arq.exists()) {
            // Cria o arquivo vazio, caso ele não exista
            arq.createNewFile();
            System.out.println("Arquivo disciplina.csv criado.");
        }
		BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));
		BufferedWriter pw = new BufferedWriter(new FileWriter(arq,true));
		
		Lista<Disciplina>listaDisciplinas = montarLista();
		 String[] vetLinha = csvDisciplina.split(";");
        Disciplina disciplina = new Disciplina();

		if(listaDisciplinas.isEmpty()) {
    		disciplina.setCodigoDisciplina(vetLinha[0]);
    		disciplina.setNomeDisciplina(vetLinha[1]);
    		disciplina.setDiaSemana(vetLinha[2]);
    		disciplina.setQuantHoras(Integer.parseInt(vetLinha[4]));
    		disciplina.setCurso(vetLinha[5]);  		
        listaDisciplinas.addFirst(disciplina);}
    	else {
    		disciplina.setCodigoDisciplina(vetLinha[0]);
    		disciplina.setNomeDisciplina(vetLinha[1]);
    		disciplina.setDiaSemana(vetLinha[2]);
    		try {
    		disciplina.setHoraInicio(vetLinha[3]);
    		} catch (Exception e) { 		
    		}
    		disciplina.setQuantHoras(Integer.parseInt(vetLinha[4]));
    		disciplina.setCurso(vetLinha[5]);
 
        try {
			listaDisciplinas.addLast(disciplina);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
    	}
	
        pw.write(csvDisciplina+"\r\n");
		
		pw.flush();
		pw.close();
		fw.close();
		taDisciplinaAvisos.setText("Disciplina cadastrada com sucesso!");
	}

	private Lista<Disciplina> montarLista() throws IOException {
		 String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
	        File arq = new File(path, "disciplina.csv");
	        Lista<Disciplina> listaDisciplinas = new Lista<>();
	        if (!arq.exists()) {
	        	
	            // Cria o arquivo vazio, caso ele não exista
	            arq.createNewFile();
	            
	            System.out.println("Arquivo disciplina.csv criado.");
	        }
		BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq))); 
     String linha;
     
     while ((linha = fw.readLine()) != null) {
         String[] vetLinha = linha.split(";");
         Disciplina disciplina = new Disciplina();
         
		if (listaDisciplinas.isEmpty()) {
    		disciplina.setCodigoDisciplina(vetLinha[0]);
    		disciplina.setNomeDisciplina(vetLinha[1]);
    		disciplina.setDiaSemana(vetLinha[2]);
    		
    		try {
    			disciplina.setHoraInicio(vetLinha[3]);
    		} catch (Exception e) { 
    			
    		}
    		
    		disciplina.setQuantHoras(Integer.parseInt(vetLinha[4]));
    		disciplina.setCurso(vetLinha[5]);
    		listaDisciplinas.addFirst(disciplina);
        } else {
    		disciplina.setCodigoDisciplina(vetLinha[0]);
    		disciplina.setNomeDisciplina(vetLinha[1]);
    		disciplina.setDiaSemana(vetLinha[2]);
    		
    		try {
    			disciplina.setHoraInicio(vetLinha[3]);
    		} catch (Exception e) { 
    			
    		}
    		
    		disciplina.setQuantHoras(Integer.parseInt(vetLinha[4]));
    		disciplina.setCurso(vetLinha[5]);
    		try {
				listaDisciplinas.addLast(disciplina);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
    	}
          
              
          
     }
    
     fw.close();
     return(listaDisciplinas);
     
     
	}

	//CHECAR SE A DISCIPLINA JÁ ESTÁ CADASTRADA ATRAVÉS DO CÓDIGO DA DISCIPLINA
	private boolean disciplinaCadastrada(File arq, String disciplinaCodigo) throws IOException {
		 if (!arq.exists()) {
		        return false; 
		    }

		    try (BufferedReader ler = new BufferedReader(new FileReader(arq))) {
		        String linha;
		        while ((linha = ler.readLine()) != null) {
		            String[] vetLinha = linha.split(";");
		            if (vetLinha[0].equals(disciplinaCodigo)) {
		                return true; 
		            }
		        }
		    }
		    return false; 
	}
}
