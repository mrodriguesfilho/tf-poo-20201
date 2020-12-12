package banco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TelaGerente extends JFrame {
	
	public TelaGerente(final JFrame parent) {
		super("Seja bem-vindo!");
		
		parent.setVisible(false);
		
		this.setSize(300, 430);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JButton btnFinalizarConta = new JButton("Finalizar conta");
		JButton btnExtrato = new JButton("Extrato");
		JButton btnListarClientes = new JButton("Listar clientes");
		JButton btnTransferir = new JButton("Transferir");
		JButton btnSair = new JButton("Sair");
		
		btnFinalizarConta.setBounds(30, 30, 230, 60);
		btnListarClientes.setBounds(30, 100, 230, 60);
		btnTransferir.setBounds(30, 170, 230, 60);
		btnExtrato.setBounds(30, 240, 230, 60);
		btnSair.setBounds(30, 310, 230, 60);
		
		final GerenteApp gerenteApp = new GerenteApp();
		
		final JFrame instancia = this;
		
		btnFinalizarConta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String[] dadosConta = new String(JOptionPane.showInputDialog(instancia, "Qual conta deseja encerrar?\nObs: Insira no formato AGÊNCIA/NÚMERO DA CONTA", "")).split("/");
				int numAgencia = Integer.parseInt(dadosConta[0]);
				int numConta = Integer.parseInt(dadosConta[1]);
				
				int resultado = gerenteApp.FinalizarConta(new Conta(numConta, numAgencia));
				if (resultado == 1) {
					JOptionPane.showMessageDialog(instancia, "A conta foi finalizada", "Finalização de conta", JOptionPane.INFORMATION_MESSAGE);
				} else if (resultado == -1) {
					JOptionPane.showMessageDialog(instancia, "A conta ainda possui um saldo maior que zero", "Não foi possível finalizar a conta", JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(instancia, "Houve um erro!\nTente novamente", "Não foi possível finalizar a conta", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		btnListarClientes.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						String clientes = "";
						ArrayList<String> clientes_lista = gerenteApp.listarClientes();
						int CLIENTES_POR_PAGINA = 2;
						
						for (int i = 0; i < clientes_lista.size(); i++) {
							clientes += clientes_lista.get(i);
							if ((i+1) % CLIENTES_POR_PAGINA == 0) {
								if (JOptionPane.showConfirmDialog(instancia, clientes + "\nPÁGINA " + ((i+1)/2)+ " DE " + (int) Math.ceil(clientes_lista.size()/CLIENTES_POR_PAGINA) + "\n\nAPERTE 'SIM' P/ PRÓXIMA PÁGINA", "Lista de clientes", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
									break;
								clientes = "";
							}
						}			
						
					}
					
				}
		);

		btnTransferir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				double valor = Double.parseDouble(JOptionPane.showInputDialog(instancia, "Quanto deseja transferir?", ""));
				int agenciaOrigem = Integer.parseInt(JOptionPane.showInputDialog(instancia, "Qual a agência de origem?", ""));
				int contaOrigem = Integer.parseInt(JOptionPane.showInputDialog(instancia, "Qual a conta de origem?", ""));
				int agenciaDestino = Integer.parseInt(JOptionPane.showInputDialog(instancia, "Qual a agência de destino?", ""));
				int contaDestino = Integer.parseInt(JOptionPane.showInputDialog(instancia, "Qual a conta de destino?", ""));
				
				if ((new ClienteApp()).transferir(new Conta(contaOrigem, agenciaOrigem), new Conta(contaDestino, agenciaDestino), valor) == 1) {
					JOptionPane.showMessageDialog(instancia, "Transferência realizada com sucesso", "Transferência", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(instancia, "Saldo insuficiente", "Transferência", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		btnExtrato.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String[] dadosConta = new String(JOptionPane.showInputDialog(instancia, "De qual conta deseja o extrato?\nObs: Insira no formato AGÊNCIA/NÚMERO DA CONTA", "")).split("/");
				int numAgencia = Integer.parseInt(dadosConta[0]);
				int numConta = Integer.parseInt(dadosConta[1]);
				
				// Mostra somente as 10 últimas operações
				String extrato = "";
				int c = 0;
				for (String registro : (new ClienteApp()).extrato(new Conta(numConta, numAgencia))) {
					extrato += registro + "\n\n";
					c += 1;
					if (c == 10) break;
				}
				JOptionPane.showMessageDialog(instancia, extrato, "Extrato das últimas operações", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		btnSair.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		
		this.getContentPane().add(btnFinalizarConta);
		this.getContentPane().add(btnListarClientes);
		this.getContentPane().add(btnTransferir);
		this.getContentPane().add(btnExtrato);
		this.getContentPane().add(btnSair);
		
		
		this.setVisible(true);
	}
}

