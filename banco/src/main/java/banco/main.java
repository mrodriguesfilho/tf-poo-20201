package banco;

public class main {
	
	public static void main(String[] args) {
	
	Conta conta = new Conta();
	Pessoa pessoa = new Pessoa("M�rcio", "cpf");
	String senha = "teste123";
			
	Conta conta1 = new Conta(pessoa, senha);
	
	Banco c1 = new Cliente();
		
	c1.criarConta(conta1);
	
	
	
		
	}
}