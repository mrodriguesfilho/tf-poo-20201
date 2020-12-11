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
				
			}
			
		});
		
		btnDepositar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
					}
					
				});

		btnTransferir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				double quantidade = Double.parseDouble(JOptionPane.showInputDialog(instancia, "Quanto deseja transferir?", ""));
				int agenciaDestino = Integer.parseInt(JOptionPane.showInputDialog(instancia, "Qual a agência de destino?", ""));
				int contaDestino = Integer.parseInt(JOptionPane.showInputDialog(instancia, "Qual a conta de destino?", ""));
				
				if (clienteApp.transferir(conta, new Conta(agenciaDestino, contaDestino), quantidade) == 1) {
					JOptionPane.showMessageDialog(instancia, "Transferência realizada com sucesso", "Transferência", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(instancia, "Saldo insuficiente", "Transferência", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		btnExtrato.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
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

