package banco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaClienteLogin extends JFrame {
	
	public TelaClienteLogin(final JFrame parent) {
		super("Identifique-se");
		
		parent.setVisible(false);
		
		this.setSize(265, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JLabel lblAgencia = new JLabel("Agência");
		JLabel lblConta = new JLabel("Conta");
		JLabel lblSenha = new JLabel("Senha");
		
		final JTextField agencia = new JTextField();
		final JTextField conta = new JTextField();
		final JPasswordField senha = new JPasswordField();
		
		JButton btnLogar = new JButton("Logar");
		JButton btnVoltar = new JButton("Voltar");
		
		lblAgencia.setBounds(30, 20, 100, 100);
		lblConta.setBounds(120, 20, 100, 100);
		lblSenha.setBounds(30, 80, 100, 100);
		
		agencia.setBounds(30, 85, 85, 20);
		conta.setBounds(120, 85, 100, 20);
		senha.setBounds(30, 145, 190, 20);
		
		btnLogar.setBounds(30, 200, 190, 40);
		btnVoltar.setBounds(30, 15, 190, 25);
		
		final JFrame instancia = this;
		
		btnLogar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				ClienteApp clienteApp = new ClienteApp();
				Conta objConta = new Conta(Integer.parseInt(agencia.getText()), Integer.parseInt(conta.getText()), new String(senha.getPassword()));
				
				if (clienteApp.login(objConta)) {
					new TelaCliente(instancia, objConta);
				} else {
					JOptionPane.showMessageDialog(instancia, "Credenciais inválidas", "", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		btnVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					parent.setVisible(true);
					instancia.dispose();		
			}
			
		});
		
		this.getContentPane().add(lblAgencia);
		this.getContentPane().add(lblConta);
		this.getContentPane().add(lblSenha);
		
		this.getContentPane().add(agencia);
		this.getContentPane().add(conta);
		this.getContentPane().add(senha);
		
		this.getContentPane().add(btnLogar);
		this.getContentPane().add(btnVoltar);
		
		this.setVisible(true);
		
	}
	
}
