package banco;

public class Conta{
	
	private Pessoa cliente;
	private String senha;
	private int numeroConta;
	private int numeroAgencia;
	private double saldo;
	
	public Conta(Pessoa cliente, String senha, int numeroConta, int numeroAgencia) {
		this.cliente = cliente;
		this.senha = senha;
		this.numeroConta = numeroConta;
		this.numeroAgencia = numeroAgencia;
		saldo = 0;
	}
	
	
	public Conta(Pessoa cliente, String senha) {
		this.cliente = cliente;
		this.senha = senha;
	}
	
	public Conta() {}
	
	public Pessoa getCliente() {
		return cliente;
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
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