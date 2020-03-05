package com.dybcatering.live4teach.Estudiante.Inicio;

public class Token {

	private String TokenUsuario;

	public Token(String tokenUsuario) {
		TokenUsuario = tokenUsuario;
	}


	public Token(Token tokenUsuario) {
	}

	public String getToken() {
		return TokenUsuario;
	}

	public void setToken(String tokenUsuario) {
		TokenUsuario = tokenUsuario;
	}
}
