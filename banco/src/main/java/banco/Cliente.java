package banco;

public class Cliente extends Pessoa{
	private Conta contaCliente;

	public Cliente(String nome, String cpf, Conta contaCliente) {
		super(nome, cpf);
		this.contaCliente = contaCliente;
	}

	public Conta getContaCliente() {
		return contaCliente;
	}

	public void setContaCliente(Conta contaCliente) {
		this.contaCliente = contaCliente;
	}
	
}
