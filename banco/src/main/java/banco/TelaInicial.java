package banco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaInicial extends JFrame {
	
	public TelaInicial() {
		super("Bem-vindo(a) ao Banco");
		
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		JButton btnCliente = new JButton("Cliente");
		JButton btnGerente = new JButton("Gerente");
		JButton btnSair = new JButton("Sair");
		
		btnCliente.setBounds(30, 30, 230, 60);
		btnGerente.setBounds(30, 110, 230, 60);
		btnSair.setBounds(30, 200, 230, 35);
		
		final JFrame instancia = this;
		
		btnCliente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TelaClienteAcao(instancia);
			}
			
		});
		
		btnGerente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TelaGerenteLogin(instancia);
			}
			
		});
		
		btnSair.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		
		this.getContentPane().add(btnCliente);
		this.getContentPane().add(btnGerente);
		this.getContentPane().add(btnSair);
		
		this.setVisible(true);
	}
}
