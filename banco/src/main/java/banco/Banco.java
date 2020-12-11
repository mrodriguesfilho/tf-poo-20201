package banco;

import java.util.ArrayList;

public interface Banco {
	
	boolean criarConta(Cliente dadosCliente);
	boolean login(Conta dadosLogin) throws Exception;
	double transferir(Conta conta1, Conta conta2, double valor);
	double saldo(Conta conta);
	ArrayList<String> extrato(Conta conta);
	
}
