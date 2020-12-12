package banco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaGerenteLogin extends JFrame {
	
	public TelaGerenteLogin(final JFrame parent) {
		super("Identifique-se");
		
		parent.setVisible(false);
		
		this.setSize(265, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		JLabel lblMatricula = new JLabel("Matrícula");
		JLabel lblSenha = new JLabel("Senha");
		
		final JTextField matricula = new JTextField();
		final JPasswordField senha = new JPasswordField();
		
		JButton btnLogar = new JButton("Logar");
		JButton btnVoltar = new JButton("Voltar");
		
		lblMatricula.setBounds(30, 65, 100, 10);
		lblSenha.setBounds(30, 80, 100, 100);
		
		matricula.setBounds(30, 85, 190, 20);
		senha.setBounds(30, 145, 190, 20);
		
		btnLogar.setBounds(30, 200, 190, 40);
		btnVoltar.setBounds(30, 15, 190, 25);
		
		final JFrame instancia = this;
		
		btnLogar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					if ((new GerenteApp()).login((new Gerente(Integer.parseInt(matricula.getText()), new String(senha.getPassword()))))) {
						new TelaGerente(instancia);
					} else {
						JOptionPane.showMessageDialog(instancia, "Essa conta de gerente não existe", "", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(instancia, "Credenciais Inválidas", "", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		btnVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					parent.setVisible(true);
					instancia.dispose();		
			}
			
		});
		
		this.getContentPane().add(lblMatricula);
		this.getContentPane().add(lblSenha);
		
		this.getContentPane().add(matricula);
		this.getContentPane().add(senha);
		
		this.getContentPane().add(btnLogar);
		this.getContentPane().add(btnVoltar);
		
		this.setVisible(true);
		
	}
	
}
