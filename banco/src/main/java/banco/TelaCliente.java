package banco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TelaCliente extends JFrame {
	
	public TelaCliente(final JFrame parent, final Conta conta) {
		super("Seja bem-vindo!");
		
		parent.setVisible(false);
		
		this.setSize(300, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JButton btnConsultarSaldo = new JButton("Consultar Saldo");
		JButton btnSacar = new JButton("Sacar");
		JButton btnDepositar = new JButton("Depositar");
		JButton btnTransferir = new JButton("Transferir");
		JButton btnExtrato = new JButton("Extrato");
		JButton btnSair = new JButton("Sair");
		
		btnConsultarSaldo.setBounds(30, 30, 230, 60);
		btnSacar.setBounds(30, 100, 230, 60);
		btnDepositar.setBounds(30, 170, 230, 60);
		btnTransferir.setBounds(30, 240, 230, 60);
		btnExtrato.setBounds(30, 310, 230, 60);
		btnSair.setBounds(30, 380, 230, 60);
		
		final ClienteApp clienteApp = new ClienteApp();
		
		final JFrame instancia = this;
		
		btnConsultarSaldo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(instancia, "Seu saldo é: R$ " + clienteApp.saldo(conta), "Saldo", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		btnSacar.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent e) {
					try {
						double valor = Double.parseDouble(JOptionPane.showInputDialog(instancia, "Quanto deseja sacar?", ""));
						if (clienteApp.sacar(conta, valor))
							JOptionPane.showMessageDialog(instancia, "Saque realizado com sucesso", "Saque", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(instancia, "Saldo insuficiente", "Saque", JOptionPane.ERROR_MESSAGE);
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(instancia, "Insira um valor válido.\nUse um ponto para separar a parte decimal.;", "Saque", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			
			}
		);
		
		btnDepositar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						try {
							double valor = Double.parseDouble(JOptionPane.showInputDialog(instancia, "Quanto deseja depositar?", ""));
							if (clienteApp.deposito(conta, valor)) {
								JOptionPane.showMessageDialog(instancia, "Depositado com sucesso", "", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(instancia, "Houve um erro!\nTente novamente", "", JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception exc) {
							JOptionPane.showMessageDialog(instancia, "Insira um valor válido.\nUse um ponto para separar a parte decimal.;", "Saque", JOptionPane.ERROR_MESSAGE);
						}

					}
					
				}
		);

		btnTransferir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				double valor = Double.parseDouble(JOptionPane.showInputDialog(instancia, "Quanto deseja transferir?", ""));
				int agenciaDestino = Integer.parseInt(JOptionPane.showInputDialog(instancia, "Qual a agência de destino?", ""));
				int contaDestino = Integer.parseInt(JOptionPane.showInputDialog(instancia, "Qual a conta de destino?", ""));
				
				if (clienteApp.transferir(conta, new Conta(contaDestino, agenciaDestino), valor) == 1) {
					JOptionPane.showMessageDialog(instancia, "Transferência realizada com sucesso", "Transferência", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(instancia, "Saldo insuficiente", "Transferência", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		btnExtrato.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// Mostra somente as 10 últimas operações
				String extrato = "";
				int c = 0;
				for (String registro : clienteApp.extrato(conta)) {
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
		
		
		this.getContentPane().add(btnConsultarSaldo);
		this.getContentPane().add(btnSacar);
		this.getContentPane().add(btnDepositar);
		this.getContentPane().add(btnTransferir);
		this.getContentPane().add(btnExtrato);
		this.getContentPane().add(btnSair);
		
		
		this.setVisible(true);
	}
}

