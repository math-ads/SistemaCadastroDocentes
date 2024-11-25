package model; 

public class Disciplina {

    private String codigoDisciplina;
    private String nomeDisciplina;
    private String diaSemana;
    private String horaInicio;
    private int quantHoras;
    private String curso;

    public Disciplina(String codigoDisciplina, String nomeDisciplina, String diaSemana, String horaInicio, int quantHoras, String curso) {
        this.codigoDisciplina = codigoDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.quantHoras = quantHoras;
        this.curso = curso;
    }
    
    public Disciplina () {

    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
    	this.horaInicio = horaInicio;
    }

    public int getQuantHoras() {
        return quantHoras;
    }

    public void setQuantHoras(int quantHoras) {
        if (quantHoras >= 0) {
            this.quantHoras = quantHoras;
        } else {
            throw new IllegalArgumentException("Quantidade de horas deve ser positiva.");
        }
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String toString() {
        return codigoDisciplina + ";" + nomeDisciplina + ";" + diaSemana + ";" + horaInicio + ";" + quantHoras + ";" + curso;
    }
}
