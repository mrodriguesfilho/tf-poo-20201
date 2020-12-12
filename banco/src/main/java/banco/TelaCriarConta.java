package banco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaCriarConta extends JFrame {
	
	public TelaCriarConta(final JFrame parent) {
		super("Crie sua conta");
		
		if (parent != null)
			parent.setVisible(false);
		
		this.setSize(265, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JLabel lblNome = new JLabel("Nome");
		JLabel lblCpf = new JLabel("CPF");
		JLabel lblSenha = new JLabel("Senha");
		
		final JTextField nome = new JTextField();
		final JTextField cpf = new JTextField();
		final JPasswordField senha = new JPasswordField();
		
		JButton btnCriar = new JButton("Criar");
		JButton btnVoltar = new JButton("Voltar");
		
		lblNome.setBounds(30, 20, 100, 100);
		lblCpf.setBounds(120, 20, 100, 100);
		lblSenha.setBounds(30, 80, 100, 100);
		
		nome.setBounds(30, 85, 85, 20);
		cpf.setBounds(120, 85, 100, 20);
		senha.setBounds(30, 145, 190, 20);
		
		btnCriar.setBounds(30, 200, 190, 40);
		btnVoltar.setBounds(30, 15, 190, 25);
		
		final ClienteApp clienteApp = new ClienteApp(); 
		
		final JFrame instancia = this;
		
		btnCriar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Conta conta = new Conta(new String(senha.getPassword()));
				Cliente cliente = new Cliente(nome.getText(), cpf.getText(), conta);
				
				if (clienteApp.criarConta(cliente)) {
					JOptionPane.showMessageDialog(instancia, "Conta criada com sucesso.", "", JOptionPane.INFORMATION_MESSAGE);
					parent.setVisible(true);
					instancia.dispose();
				} else {
					JOptionPane.showMessageDialog(instancia, "A conta não foi criada. Tente novamente.", "", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		btnVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					parent.setVisible(true);
					instancia.dispose();		
			}
			
		});
		
		this.getContentPane().add(lblNome);
		this.getContentPane().add(lblCpf);
		this.getContentPane().add(lblSenha);
		
		this.getContentPane().add(nome);
		this.getContentPane().add(cpf);
		this.getContentPane().add(senha);
		
		this.getContentPane().add(btnCriar);
		this.getContentPane().add(btnVoltar);
		
		this.setVisible(true);
		
	}
	
}
