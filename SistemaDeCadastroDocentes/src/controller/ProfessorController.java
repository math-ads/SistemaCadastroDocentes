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

import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.edu.fateczl.Lista;
import br.edu.fateczl.No;
import model.Curso;
import model.Professor;


public class ProfessorController implements ActionListener {
	private JTextField tfProfessoresCpf;
	private JTextField tfProfessorCpf;
	private JTextField tfProfessorNome;
	private JTextField tfProfessorArea;
	private JTextField tfProfessorPontuacao;
	private JTextArea taProfessorAvisos;
	private JTextArea taProfessoresListagem;
	
	public ProfessorController(JTextField tfProfessoresCpf,JTextField tfProfessorCpf, JTextField tfProfessorNome,
			JTextField tfProfessorArea, JTextField tfProfessorPontuacao, JTextArea taProfessorAvisos, JTextArea taProfessoresListagem) {
		this.tfProfessoresCpf =  tfProfessoresCpf;
		this.tfProfessorCpf = tfProfessorCpf;
		this.tfProfessorNome = tfProfessorNome;
		this.tfProfessorArea = tfProfessorArea;
		this.tfProfessorPontuacao = tfProfessorPontuacao;
		this.taProfessorAvisos = taProfessorAvisos;
		this.taProfessoresListagem = taProfessoresListagem;
	}
	public ProfessorController() {
		
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Salvar")) {
			try {
				cadastro();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (cmd.equals("Buscar")) {
			try {
				busca();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}}
		
			
			if (cmd.equals("Excluir")) {
				try {
					excluir();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}}
	

	private void excluir() throws Exception {
		Professor professor = new Professor();
		
		professor.setCpfProfessor(tfProfessoresCpf.getText());
		String cpfBusca = professor.getCpfProfessor();
	   
		
		buscaProfessorCpf(professor, cpfBusca);
		
		try {
			professor.setPontuacaoProfessor(Integer.parseInt(tfProfessorPontuacao.getText()));
		} catch (Exception e) {
			taProfessorAvisos.setText(e.getMessage());
		}
		
		
			String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
			File dir = new File (path);
			if (!dir.exists()) {
				dir.mkdir();
			}
			
			File arq = new File(path, "professor.csv"); 
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
		
			boolean cadastrado = professorCadastrado(arq, professor.getCpfProfessor());
			
			if (cadastrado == true) {
				excluirProfessor(professor.toString(), professor);
			} else {
				taProfessorAvisos.setText("Professor não encontrado");
				tfProfessorCpf.setText("");
				tfProfessorNome.setText("");
				tfProfessorArea.setText("");
				tfProfessorPontuacao.setText("");
			}
		
			taProfessoresListagem.setText("Professor excluído com sucesso!");
	}
		
	
	
	private Professor buscaProfessorCpf(Professor professor, String cpfProfessor) throws IOException {
		 String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
	        File arq = new File(path, "professor.csv");
	        if (arq.exists()&&arq.isFile()) {
	       
		BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq))); 
     String linha;
		 while ((linha = fw.readLine()) != null) {
          String[] vetLinha = linha.split(";");
                if( vetLinha[0].equals(cpfProfessor)) {
                	professor.setCpfProfessor(vetLinha[0]);
                	professor.setNomeProfessor(vetLinha[1]);
                	professor.setAreaInteresse(vetLinha[2]);
                	professor.setNomeProfessor(vetLinha[3]);
                }
		
           }
		fw.close();
		
		}
	        return professor;	

		
	}
	public static void excluirProfessor(String csvProfessor, Professor professor) throws Exception {
        // Caminho para os arquivos
        String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
        File arq = new File(path, "professor.csv");
        File auxArq = new File(path, "professorAux.csv");
        
        // Verifica se o arquivo original existe
		if(arq.exists()&&arq.isFile()) {
			BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));
			BufferedWriter pw = new BufferedWriter(new FileWriter(auxArq,true));
			
			//cria lista de professores
			Lista<Professor> listaProfessores = new Lista<Professor>() ;    		
		    String linha;
		    
		    // monta lista de professores 
		    while ((linha = fw.readLine()) != null) {
				Professor professorAux = new Professor();
				String[] vetLinha = linha.split(";");
						
						if (!vetLinha[0].equals(professor.getCpfProfessor())) {
							
							if(listaProfessores.isEmpty()) {
								professorAux.setCpfProfessor(vetLinha[0]);
								professorAux.setNomeProfessor(vetLinha[1]);
								professorAux.setAreaInteresse(vetLinha[2]);
								
								try {
								professorAux.setPontuacaoProfessor(Integer.parseInt(vetLinha[3]));
								} catch (Exception e) {
								
								}
								
								listaProfessores.addFirst(professorAux);
							} else {
								professorAux.setCpfProfessor(vetLinha[0]);
								professorAux.setNomeProfessor(vetLinha[1]);
								professorAux.setAreaInteresse(vetLinha[2]);
								
								try {
									professorAux.setPontuacaoProfessor(Integer.parseInt(vetLinha[3]));
								} catch (Exception e) {
								
								}
								
								listaProfessores.addLast(professorAux);
							}
							
							pw.write(linha);
							pw.newLine();
						}
		    }
				
			pw.close();
			fw.close();
		}
		
       // Substitui o arquivo original pelo auxiliar
       arq.delete();
       auxArq.renameTo(arq);
	}


	private void busca() throws IOException {
		Professor professor = new Professor();
		professor.setCpfProfessor(tfProfessorCpf.getText());
		professor.setNomeProfessor(tfProfessorNome.getText());
		professor.setAreaInteresse(tfProfessorArea.getText());
		professor.setPontuacaoProfessor(Integer.parseInt(tfProfessorPontuacao.getText()));
		
		professor = buscaProfessor(professor);
		
		if (professor.getCpfProfessor() != null) {
			taProfessorAvisos.setText("Professor: " + professor.getNomeProfessor() + " - CPF: " + professor.getCpfProfessor() + " - Área de Interesse: " + professor.getAreaInteresse() + " - PONTUAÇÃO: " + professor.getPontuacaoProfessor());
			
		} else {
			taProfessorAvisos.setText("Professor não encontrado!");
		}
		
	}
	
	//BUSCAR PROFESSOR ATRAVÉS DO CPF
	private Professor buscaProfessor(Professor professor) throws IOException {
			String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
			File arq = new File(path, "professor.csv");
			if (arq.exists() && arq.isFile()) {
				FileInputStream fis = new FileInputStream(arq);
				InputStreamReader isr = new InputStreamReader (fis); 
				BufferedReader buffer = new BufferedReader (isr);
				String linha = buffer.readLine();
				while (linha != null) {
					String[] vetLinha = linha.split(";");
					if (vetLinha[0].equals(professor.getCpfProfessor())) {
						professor.setNomeProfessor(vetLinha[1]);
						professor.setAreaInteresse(vetLinha[2]);
						professor.setPontuacaoProfessor(Integer.parseInt(vetLinha[3]));
						break; 
					}
					linha = buffer.readLine();
					
				}
				buffer.close();
				isr.close();
				fis.close();
			}
			return professor;
	}

	private void cadastro() throws Exception {
		
		Professor professor = new Professor();
		professor.setCpfProfessor(tfProfessorCpf.getText());
		professor.setNomeProfessor(tfProfessorNome.getText());
		professor.setAreaInteresse(tfProfessorArea.getText());
		try {
		professor.setPontuacaoProfessor(Integer.parseInt(tfProfessorPontuacao.getText()));
		} catch (Exception e) {
			taProfessorAvisos.setText(e.getMessage());
		}
		
			String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
			File dir = new File (path);
			if (!dir.exists()) {
				dir.mkdir();
			}
			
			File arq = new File(path, "professor.csv"); 
			boolean cadastrado = professorCadastrado(arq, professor.getCpfProfessor());
			
			if(cadastrado==true) {
				atualizaProfessor(professor);
			}
			else {
		
		cadastraProfessor(professor.toString());
		
		tfProfessorCpf.setText("");
		tfProfessorNome.setText("");
		tfProfessorArea.setText("");
		tfProfessorPontuacao.setText("");}
		}
	

	private void atualizaProfessor(Professor professor) throws Exception { 
	    // Caminho para os arquivos
	    String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
	    File arq = new File(path, "professor.csv");
	    File auxArq = new File(path, "professorAux.csv");
	    
	    // Verifica se o arquivo original existe
	    if (arq.exists() && arq.isFile()) {
	        BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));
	        BufferedWriter pw = new BufferedWriter(new FileWriter(auxArq, true));
	        
	        Lista<Professor> listaProfessores = new Lista<Professor>(); // Lista para armazenar os professores atualizados
	        String linha;
	        
	        while ((linha = fw.readLine()) != null) {
	            String[] vetLinha = linha.split(";");
	            Professor professorAux = new Professor();
	            
	            // Sempre substitui o professor com o CPF igual ao informado
	            if (vetLinha[0].equals(professor.getCpfProfessor())) {
	                professorAux.setCpfProfessor(professor.getCpfProfessor());
	                professorAux.setNomeProfessor(professor.getNomeProfessor());
	                professorAux.setAreaInteresse(professor.getAreaInteresse());
	                professorAux.setPontuacaoProfessor(professor.getPontuacaoProfessor());
	            } else {
	                professorAux.setCpfProfessor(vetLinha[0]);
	                professorAux.setNomeProfessor(vetLinha[1]);
	                professorAux.setAreaInteresse(vetLinha[2]);
	                try {
	                    professorAux.setPontuacaoProfessor(Integer.parseInt(vetLinha[3]));
	                } catch (Exception e) {
	                    // Tratar a exceção
	                }
	            }

	            listaProfessores.addLast(professorAux); // Adiciona o professor atualizado ou original
	            pw.write(professorAux.getCpfProfessor() + ";" + professorAux.getNomeProfessor() + ";" + 
	                     professorAux.getAreaInteresse() + ";" + professorAux.getPontuacaoProfessor());
	            pw.newLine();
	        }
	        
	        // Reordena os professores por pontuação após a atualização
	        ordenarPorPontuacaoArquivo(listaProfessores, arq);

	        pw.close();
	        fw.close();

	        // Substitui o arquivo original pelo auxiliar
	        arq.delete();
	        auxArq.renameTo(arq);

	        taProfessorAvisos.setText("Professor atualizado com sucesso!");
	    } else {
	    	taProfessorAvisos.setText("Arquivo não encontrado.");
	    }
	}


	//CADASTRAR PROFESSOR
	private void cadastraProfessor(String csvProfessor) throws Exception {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
		File dir = new File (path);
		
		if (!dir.exists()) {
			dir.mkdir();
		}
		Lista<Professor>listaProfessores = montarLista();
		
		File arq = new File(path, "professor.csv"); 
		boolean existe = false;
		BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));
		BufferedWriter pw = new BufferedWriter(new FileWriter(arq,true));
		if (arq.exists()) {
			existe = true;
		}
		 String[] vetLinha = csvProfessor.split(";");
         Professor professor= new Professor();
		if(listaProfessores.isEmpty()) {
    		professor.setCpfProfessor(vetLinha[0]);
    		professor.setNomeProfessor(vetLinha[1]);
    		professor.setAreaInteresse(vetLinha[2]);
    		try {
    		professor.setPontuacaoProfessor(Integer.parseInt(vetLinha[3]));
    		} catch (Exception e) {
    		
    		}
    		
        listaProfessores.addFirst(professor);} 
    	else {
    		professor.setCpfProfessor(vetLinha[0]);
    		professor.setNomeProfessor(vetLinha[1]);
    		professor.setAreaInteresse(vetLinha[2]);
    		try {
    		professor.setPontuacaoProfessor(Integer.parseInt(vetLinha[3]));
    		} catch (Exception e) {
    		
    		}
    		 listaProfessores.addLast(professor);
	}
		ordenarPorPontuacaoArquivo(listaProfessores, arq);
		
		pw.flush();
		pw.close();
		fw.close();
		taProfessorAvisos.setText("Professor cadastrado com sucesso!");
	}

	private void ordenarPorPontuacaoArquivo(Lista<Professor> listaProfessores, File arq) throws Exception {
	    Professor[] professores = listaParaArray(listaProfessores);

	    // Bubble Sort no array de professores
	    for (int i = 0; i < professores.length - 1; i++) {
	        for (int j = 0; j < professores.length - i - 1; j++) {
	            if (professores[j].getPontuacaoProfessor() < professores[j + 1].getPontuacaoProfessor()) {
	                Professor temp = professores[j];
	                professores[j] = professores[j + 1];
	                professores[j + 1] = temp;
	            }
	        }
	    }

	    // Sobrescreve o arquivo com os dados ordenados
	    try (BufferedWriter pw = new BufferedWriter(new FileWriter(arq))) {
	        for (Professor professor : professores) {
	            pw.write(professor.toString());
	            pw.newLine();
	        }
	    }
	}
		
		public Professor[] listaParaArray(Lista<Professor> listaProfessores) throws Exception {
		    // Cria um array do tamanho da lista
		    Professor[] professores = new Professor[listaProfessores.size()];

		    // Itera pela lista e preenche o array
		    for (int i = 0; i < listaProfessores.size(); i++) {
		        professores[i] = listaProfessores.get(i);
		    }

		    return professores;
		}
	
	//CHECAR SE O PROFESSOR JÁ ESTÁ CADASTRADO ATRAVÉS DO CPF 
	public boolean professorCadastrado(File arq, String cpfProfessor) throws IOException {
		if (!arq.exists()) {
	        return false; 
	    }

	    try (BufferedReader ler = new BufferedReader(new FileReader(arq))) {
	        String linha;
	        while ((linha = ler.readLine()) != null) {
	            String[] vetLinha = linha.split(";");
	            if (vetLinha[0].equals(cpfProfessor)) {
	                return true; 
	            }
	        }
	    }
	    return false; 
	}
	public static Lista<Professor> montarLista() throws Exception {
		   String path = System.getProperty("user.home") + File.separator + "SistemaCadastroDocentes";
	        File arq = new File(path, "professor.csv");
	        Lista<Professor> listaProfessores = new Lista<>();
	        if (!arq.exists()) {
	            // Cria o arquivo vazio, caso ele não exista
	            arq.createNewFile();
	            System.out.println("Arquivo professor.csv criado.");
	        }
		BufferedReader fw = new BufferedReader(new InputStreamReader(new FileInputStream(arq))); 
        String linha;
		 while ((linha = fw.readLine()) != null) {
             String[] vetLinha = linha.split(";");
             Professor professor = new Professor();
			if(listaProfessores.isEmpty()) {
        		professor.setCpfProfessor(vetLinha[0]);
        		professor.setNomeProfessor(vetLinha[1]);
        		professor.setAreaInteresse(vetLinha[2]);
        		try {
        		professor.setPontuacaoProfessor(Integer.parseInt(vetLinha[3]));
        		} catch (Exception e) {
        		
        		}
        		
            listaProfessores.addFirst(professor);}
        	else {
        		professor.setCpfProfessor(vetLinha[0]);
        		professor.setNomeProfessor(vetLinha[1]);
        		professor.setAreaInteresse(vetLinha[2]);
        		try {
        		professor.setPontuacaoProfessor(Integer.parseInt(vetLinha[3]));
        		} catch (Exception e) {
        		
        		}
        		listaProfessores.addLast(professor);
        	}
              
                  
              }
		fw.close();
		
		return listaProfessores;
}}
