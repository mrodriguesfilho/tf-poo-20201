package banco;

public class Conta {
	
	private String senha;
	private int numeroConta;
	private int numeroAgencia;
	private double saldo;
	
	public Conta(int numeroConta, int numeroAgencia, String senha) {
		this.numeroConta = numeroConta;
		this.numeroAgencia = numeroAgencia;
		this.senha = senha;
	}

	public Conta(int numeroConta, int numeroAgencia) {
		this.numeroConta = numeroConta;
		this.numeroAgencia = numeroAgencia;
	}
	
	public Conta(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public int getNumeroAgencia() {
		return numeroAgencia;
	}

	public void setNumeroAgencia(int numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	

	
}
