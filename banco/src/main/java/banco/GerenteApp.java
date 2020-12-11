package banco;

import java.sql.*;
import java.util.ArrayList;

public class GerenteApp implements Banco{
	
	private static int numeroAgencia = 1;
	
    private ConnFactory db = new ConnFactory();
    private Connection conn;

    long time = System.currentTimeMillis();
    Timestamp ts = new Timestamp(time);	
	
	public boolean criarConta(Cliente dadosCliente) {
		try {
			
			if(this.conn.isClosed()) {
				this.conn = this.db.getConnection();
			}
			
			StringBuilder sql = new StringBuilder();		
			sql.append("INSERT INTO contas (numeroagencia, nomecliente, senha, saldo, cpf) VALUES (? ,? ,? ,?, ?)");
			PreparedStatement ps = this.conn.prepareStatement(sql.toString());
			ps.setInt(1,  numeroAgencia);
			ps.setString(2, dadosCliente.getNome());
			ps.setString(3, dadosCliente.getContaCliente().getSenha());
			ps.setInt(4, 0);
			ps.setString(5, dadosCliente.getCpf());
			ps.executeUpdate();
			
			
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean login(Gerente dadosGerente) {
		
		try {
            if(this.conn.isClosed()) {
                this.conn = this.db.getConnection();
            }

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT senha FROM gerentes WHERE matricula=?");

            PreparedStatement ps = this.conn.prepareStatement(sql.toString());

            ps.setInt(1, dadosGerente.getMatricula());
 
            ResultSet rs = ps.executeQuery();
            
           
            System.out.println("\n"+dadosGerente.getSenha()+"\n");
            System.out.println("\n"+rs+"\n");

            if(rs.next()){
                if(!dadosGerente.getSenha().equals(rs.getString("senha").trim())) {
                    
                	return false;
                }else{
                	
                	return true;
              }
                
            }else{
            	
            	return false;
            }
            
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            
        	try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        	
        }
		
		return false;
	}

	public double transferir(Conta conta1, Conta conta2, double valor) {
        try {
            if (this.conn.isClosed()) {
                this.conn = this.db.getConnection();
            }

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT saldo FROM contas WHERE numeroagencia=? AND numeroconta=?");
            PreparedStatement ps = this.conn.prepareStatement(sql.toString());
            ps.setInt(1, conta2.getNumeroAgencia());
            ps.setInt(2, conta2.getNumeroConta());
            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                if( rs.getDouble("saldo") >= valor ){
                    sql.setLength(0);
                    ps.clearParameters();
                    sql.append("UPDATE contas SET saldo=saldo+? WHERE numeroagencia=? AND numeroconta=? ");
                    ps = this.conn.prepareStatement(sql.toString());
                    ps.setDouble(1, valor);
                    ps.setInt(2, conta2.getNumeroAgencia());
                    ps.setInt(3, conta2.getNumeroConta());
                    ps.executeUpdate();

                    sql.setLength(0);
                    ps.clearParameters();
                    sql.append("INSERT INTO historico (numeroagencia, numeroconta, data, valor, operacao) VALUES (?,?,?,?,?)");
                    ps = this.conn.prepareStatement(sql.toString());
                    ps.setInt(1, conta2.getNumeroAgencia());
                    ps.setInt(2, conta2.getNumeroConta());
                    ps.setTimestamp(3, ts);
                    ps.setDouble(4, valor);
                    ps.setString(5, "RECEBEU");
                    ps.executeUpdate();

                    sql.setLength(0);
                    ps.clearParameters();
                    sql.append("UPDATE contas SET saldo=saldo-? WHERE numeroagencia=? AND numeroconta=?");
                    ps = this.conn.prepareStatement(sql.toString());
                    ps.setDouble(1, valor);
                    ps.setInt(2, conta1.getNumeroAgencia());
                    ps.setInt(3, conta1.getNumeroConta());

                    sql.setLength(0);
                    ps.clearParameters();
                    sql.append("INSERT INTO historico (numeroagencia, numeroconta, data, valor, operacao) VALUES (?,?,?,?,?)");
                    ps = this.conn.prepareStatement(sql.toString());
                    ps.setInt(1, conta1.getNumeroAgencia());
                    ps.setInt(2, conta1.getNumeroConta());
                    ps.setTimestamp(3, ts);
                    ps.setDouble(4, valor);
                    ps.setString(5, "ENVIOU");
                    ps.executeUpdate();


                    return 1.0;
                }else{
                    return 0;
                }

            }else{
                return -1;
            }

        }catch( Exception e){
            e.printStackTrace();
            return 0;
        }
	}

	public double saldo(Conta conta) {
	      
			try {
	            if (this.conn.isClosed()) {
	                this.conn = this.db.getConnection();
	            }

	            StringBuilder sql = new StringBuilder();
	            sql.append("SELECT saldo FROM contas WHERE numeroagencia=? AND numeroconta=?");
	            PreparedStatement ps = this.conn.prepareStatement(sql.toString());
	            ps.setInt(1, conta.getNumeroAgencia());
	            ps.setInt(2, conta.getNumeroConta());
	            ResultSet rs = ps.executeQuery();

	            if(rs.next()){
	                return rs.getDouble("saldo");
	            }else{
	                return 0;
	            }

	        }catch(Exception e){
	            e.printStackTrace();
	            return 0;
	        }
			
	}

	public ArrayList<String> extrato(Conta conta) {
        try {

            if (this.conn.isClosed()) {
                this.conn = this.db.getConnection();
            }

            ArrayList<String> extrato = new ArrayList<String>();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT data, operacao, valor FROM historico WHERE numeroagencia=? AND numeroconta=? ORDER BY data DESC");
            PreparedStatement ps = this.conn.prepareStatement(sql.toString());
            ps.setInt(1, conta.getNumeroAgencia());
            ps.setInt(2, conta.getNumeroConta());

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                extrato.add("Data: "+rs.getTimestamp("data")+" / OPERAÇÃO: "+rs.getString("operacao")+" / Valor: "+rs.getDouble("valor"));
            }

            return extrato;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
	}

	
	public int FinalizarConta(Cliente cliente) {
		try {
			
			if(this.conn.isClosed()) {
				this.conn = this.db.getConnection();
			}
			
						
			StringBuilder sql = new StringBuilder();		
			sql.append("SELECT saldo FROM contas WHERE numeroagencia=? AND numeroconta=?");
			PreparedStatement ps = this.conn.prepareStatement(sql.toString());
            ps.setInt(1, cliente.getContaCliente().getNumeroAgencia());
            ps.setInt(2, cliente.getContaCliente().getNumeroConta());
            ResultSet rs = ps.executeQuery();			
			
            if(rs.next()) {
            	if(rs.getDouble("saldo") == 0) {
            		sql.setLength(0);
            		ps.clearParameters();
            		sql.append("DELETE FROM contas WHERE (numeroAgencia, numeroConta, cpf) VALUES (?, ?, ?)");
        			ps.setInt(1,  numeroAgencia);
        			ps.setInt(2, cliente.getContaCliente().getNumeroConta());
        			ps.setString(3, cliente.getCpf());
        			ps.executeUpdate();
        			return 1;
            	}else {
            		return -1;
            	}
            }else {
            	return 0;
            }
		
			
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
