package model;

public class Inscricao {
	
	private String cpfProfessor;
	private String disciplina;
	private String codigoProcesso;
	
	public Inscricao(String cpfProfessor, String disciplina, String codigoProcesso) {
		
		this.cpfProfessor = cpfProfessor;
		this.disciplina = disciplina;
		this.codigoProcesso = codigoProcesso;
	}
	
	public Inscricao () {
		this ("","","");
	}

	public String getCpfProfessor() {
		return cpfProfessor;
	}

	public void setCpfProfessor(String cpfProfessor) {
		this.cpfProfessor = cpfProfessor;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getCodigoProcesso() {
		return codigoProcesso;
	}

	public void setCodigoProcesso(String codigoProcesso) {
		this.codigoProcesso = codigoProcesso;
	}

	public String toString() {
		return cpfProfessor + ";" + disciplina + ";" + codigoProcesso;
	}
}
