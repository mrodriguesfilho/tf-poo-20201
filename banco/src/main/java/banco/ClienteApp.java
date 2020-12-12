package banco;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClienteApp implements Banco {

	public static int numeroAgencia = 1;
	
    private ConnFactory db = new ConnFactory();
    private Connection conn;

    long time = System.currentTimeMillis();
    Timestamp ts = new Timestamp(time);
 
    protected ClienteApp(){
        try {
            this.conn = db.getConnection();
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public boolean criarConta(Cliente dadosCliente)  {
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

	public boolean login(Conta dadosLogin) throws InvalidCredentialsException {
        
		try {
            if(this.conn.isClosed()) {
                this.conn = this.db.getConnection();
            }

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT senha FROM contas WHERE numeroagencia=? AND numeroconta=?");

            PreparedStatement ps = this.conn.prepareStatement(sql.toString());

            ps.setInt(1, dadosLogin.getNumeroAgencia());
            ps.setInt(2, dadosLogin.getNumeroConta());

            ResultSet rs = ps.executeQuery();
            
           
            System.out.println("\n"+dadosLogin.getSenha()+"\n");
            System.out.println("\n"+rs+"\n");
            if(rs.next()){
                if(!dadosLogin.getSenha().equals(rs.getString("senha").trim())) {
                    throw new InvalidCredentialsException();
                } else {
                	return true;
                }
            }else {
            	System.out.println("Nenhum registro encontrado!");
            	return false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
            ps.setInt(1, conta1.getNumeroAgencia());
            ps.setInt(2, conta1.getNumeroConta());
            ResultSet rs = ps.executeQuery();

            if(rs.next()){

            	System.out.println("Saldo disp.: R$" + rs.getDouble("saldo"));
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
                    ps.executeUpdate();

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

	public boolean deposito(Conta conta, double valor) {
		try {
			if (this.conn.isClosed()) {
                this.conn = this.db.getConnection();
            }
			
			StringBuilder sql = new StringBuilder();
            sql.append("UPDATE contas SET saldo=saldo+? WHERE numeroagencia=? AND numeroconta=?");
            PreparedStatement ps = this.conn.prepareStatement(sql.toString());
            ps.setDouble(1, valor);
            ps.setInt(2, conta.getNumeroAgencia());
            ps.setInt(3, conta.getNumeroConta());
            
            if (ps.executeUpdate() > 0) {
            	sql.setLength(0);
                ps.clearParameters();
                sql.append("INSERT INTO historico (numeroagencia, numeroconta, data, valor, operacao) VALUES (?,?,?,?,?)");
                ps = this.conn.prepareStatement(sql.toString());
                ps.setInt(1, conta.getNumeroAgencia());
                ps.setInt(2, conta.getNumeroConta());
                ps.setTimestamp(3, ts);
                ps.setDouble(4, valor);
                ps.setString(5, "DEPOSITOU");
                ps.executeUpdate();
                
                return true;
            } else {
            	return false;
            }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean sacar(Conta conta, double valor) {
		try {
			
			if (this.conn.isClosed()) {
                this.conn = this.db.getConnection();
            }
			
			StringBuilder sql = new StringBuilder();
			PreparedStatement ps = this.conn.prepareStatement(sql.toString());
			sql.append("SELECT saldo FROM contas WHERE numeroagencia=? AND numeroconta=?");
			ps = this.conn.prepareStatement(sql.toString());
			ps.setInt(1, conta.getNumeroAgencia());
			ps.setInt(2, conta.getNumeroConta());
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			if(rs.getDouble("saldo") >= valor ) {
            	
				sql.setLength(0);
                ps.clearParameters();
	            sql.append("UPDATE contas SET saldo=? WHERE numeroagencia=? AND numeroconta=?");
	            ps = this.conn.prepareStatement(sql.toString());
	            valor = rs.getDouble("saldo") - valor;
	            ps.setDouble(1, valor);
	            ps.setInt(2, conta.getNumeroAgencia());
	            ps.setInt(3, conta.getNumeroConta());
	            ps.executeUpdate();
	            
	            if (ps.executeUpdate() > 0) {
	            	sql.setLength(0);
	                ps.clearParameters();
	                sql.append("INSERT INTO historico (numeroagencia, numeroconta, data, valor, operacao) VALUES (?,?,?,?,?)");
	                ps = this.conn.prepareStatement(sql.toString());
	                ps.setInt(1, conta.getNumeroAgencia());
	                ps.setInt(2, conta.getNumeroConta());
	                ps.setTimestamp(3, ts);
	                ps.setDouble(4, valor);
	                ps.setString(5, "SACOU");
	                ps.executeUpdate();
	                
	                return true;
	            } else {
	            	return false;
	            }
			}else {
				return false;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public ArrayList<String> extrato(Conta conta) {
        try {

            if (this.conn.isClosed()) {
                this.conn = this.db.getConnection();
            }

            ArrayList<String> extrato = new ArrayList<String>();

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT data, operacao, valor FROM historico WHERE numeroagencia=? AND numeroconta=? ORDER BY data ASC");
            PreparedStatement ps = this.conn.prepareStatement(sql.toString());
            ps.setInt(1, conta.getNumeroAgencia());
            ps.setInt(2, conta.getNumeroConta());

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
            	Timestamp  ts = rs.getTimestamp("data");
            	String[] data = ts.toString().split(" ")[0].split("-");
            	
                extrato.add("" + data[2] + "/" + data[1] + "/" + data[0] + " || " + rs.getString("operacao") + " R$ " + rs.getDouble("valor"));
            }

            return extrato;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
	}

}
