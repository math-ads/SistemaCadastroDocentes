package model;

public class Professor {
	
	private String cpfProfessor;
	private String nomeProfessor;
	private String areaInteresse;
	private int pontuacaoProfessor;
	
	public Professor(String cpfProfessor, String nomeProfessor, String areaInteresse, int pontuacaoProfessor) {
		this.cpfProfessor = cpfProfessor;
		this.nomeProfessor = nomeProfessor;
		this.areaInteresse = areaInteresse;
		this.pontuacaoProfessor = pontuacaoProfessor;
	}
	
	public Professor () {

	}
	
	public String getCpfProfessor() {
		return cpfProfessor;
	}
	
	public void setCpfProfessor(String cpfProfessor) {
		if (cpfProfessor == null || cpfProfessor.length() != 11 ) {
		 throw new IllegalArgumentException("CPF inválido! Precisa conter 11 dígitos.");
		} else {
			this.cpfProfessor = cpfProfessor;
		}
	}
	
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	
	public String getAreaInteresse() {
		return areaInteresse;
	}
	
	public void setAreaInteresse(String areaInteresse) {
		this.areaInteresse = areaInteresse;
	}
	
	public int getPontuacaoProfessor() {
		return pontuacaoProfessor;
	}
	
	public void setPontuacaoProfessor(int pontuacaoProfessor) {
		this.pontuacaoProfessor = pontuacaoProfessor;
	}

	public String toString() {
		return cpfProfessor + ";" + nomeProfessor + ";" + areaInteresse + ";" + pontuacaoProfessor;
	}
}
