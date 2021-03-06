package banco;

import java.util.ArrayList;

public class Gerente extends Pessoa{
	
	private int matricula;
	private String senha;
	
	public Gerente(String nome, String cpf, int matricula, String senha) {
		super(nome, cpf);
		this.matricula = matricula;
		this.senha = senha;
	}
	
	public Gerente(int matricula, String senha) {
		super("", "");
		this.matricula = matricula;
		this.senha = senha;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
