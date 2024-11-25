package model;

public class Login {
	private String usuario;
	private String senha;

	public Login (String usuario, String senha) {
		this.usuario = usuario;
		this.senha= senha;
	}
	
	public Login() {

	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		if (usuario == null || usuario.length() == 0 ) {
			 throw new IllegalArgumentException("Digite usuário");
		} else {
			this.usuario = usuario;
		}
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		if (senha == null) {
			 throw new IllegalArgumentException("Digite senha");
		} else {
			this.senha = senha;
		}
	}

	@Override
	public String toString() {
		return usuario+";"+senha;
	}
}
