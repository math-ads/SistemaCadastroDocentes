package model;

public class Curso {
	
	private String codigoCurso;
	private String nomeCurso;
	private String areaConhecimento;
	
	public Curso(String codigoCurso, String nomeCurso, String areaConhecimento) {
		this.codigoCurso = codigoCurso;
		this.nomeCurso = nomeCurso;
		this.areaConhecimento = areaConhecimento;
	}
	
	public Curso() {
		this ("","","");
	}
	
	public String getCodigoCurso() {
		return codigoCurso;
	}
	
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public String getAreaConhecimento() {
		return areaConhecimento;
	}
	public void setAreaConhecimento(String areaConhecimento) {
		this.areaConhecimento = areaConhecimento;
	}

	public String toString() {
		return codigoCurso + ";" + nomeCurso + ";" + areaConhecimento;
	}
}
