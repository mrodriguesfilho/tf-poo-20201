package banco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaClienteAcao extends JFrame {
	
	public TelaClienteAcao(final JFrame parent) {
		super("O que deseja fazer?");
		
		parent.setVisible(false);
		
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		JButton btnEntrar = new JButton("Logar");
		JButton btnCriarConta = new JButton("Criar conta");
		JButton btnVoltar = new JButton("Voltar");
		
		btnEntrar.setBounds(30, 30, 230, 60);
		btnCriarConta.setBounds(30, 110, 230, 60);
		btnVoltar.setBounds(30, 200, 230, 35);
		
		final JFrame instancia = this;
		
		btnEntrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TelaClienteLogin(instancia);
			}
			
		});
		
		btnCriarConta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new TelaCriarConta(instancia);
			}
			
		});
		
		btnVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				instancia.dispose();
			}
			
		});
		
		
		this.getContentPane().add(btnEntrar);
		this.getContentPane().add(btnCriarConta);
		this.getContentPane().add(btnVoltar);
		
		this.setVisible(true);
	}
}

