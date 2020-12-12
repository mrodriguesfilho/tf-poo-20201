package banco;

import static org.junit.Assert.*;
import java.sql.*;

import org.junit.Test;

public class TesteMetodos {
    
    
	@Test
	public void deveriaSacar() {
	   
		ConnFactory db = new ConnFactory();
	    Connection conn = null;
	    
        try {
            conn = db.getConnection();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		try {
            if (conn.isClosed()) {
                conn = db.getConnection();
            }

            StringBuilder sql = new StringBuilder();
    		sql.append("DELETE FROM contas WHERE numeroconta=227");
    		PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.executeUpdate();
			
        	sql.setLength(0);
            ps.clearParameters();
            sql.append("INSERT INTO contas (numeroconta, numeroagencia, nomecliente, senha, saldo, cpf) VALUES (227, 2,'Cliente Teste' , 123 , 100, 12312312312)");
        	ps = conn.prepareStatement(sql.toString());
			ps.executeUpdate();
  
            ClienteApp capp = new ClienteApp();
            Conta conta = new Conta(227, 2);
            double valor = 10;
            
            assertTrue(capp.sacar(conta, valor));
            
        	sql.setLength(0);
            ps.clearParameters();
    		sql.append("DELETE FROM contas WHERE numeroconta=227");
			ps = conn.prepareStatement(sql.toString());
			ps.executeUpdate();
			
           

        }catch(Exception e) {
            e.printStackTrace();
        }

    }	
	
	@Test
	public void deveriaDepositar() {
	   
		ConnFactory db = new ConnFactory();
	    Connection conn = null;
	    
        try {
            conn = db.getConnection();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		try {
            if (conn.isClosed()) {
                conn = db.getConnection();
            }
            
            StringBuilder sql = new StringBuilder();
    		sql.append("DELETE FROM contas WHERE numeroconta=228");
    		PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.executeUpdate();

        	sql.setLength(0);
            ps.clearParameters();
            sql.append("INSERT INTO contas (numeroconta, numeroagencia, nomecliente, senha, saldo, cpf) VALUES (228, 2,'Cliente Teste' , 123 , 100, 12312312312)");
			ps = conn.prepareStatement(sql.toString());
			ps.executeUpdate();
  
            ClienteApp capp = new ClienteApp();
            Conta conta = new Conta(228, 2);
            double valor = 10;
            
            capp.deposito(conta, valor);
            
            double ValorEsperado = 110;
            double ValorRetornado = capp.saldo(conta);
            assertEquals(ValorEsperado, ValorRetornado, 0.001);
            
        	sql.setLength(0);
            ps.clearParameters();
    		sql.append("DELETE FROM contas WHERE numeroconta=228");
			ps = conn.prepareStatement(sql.toString());
			ps.executeUpdate();
			
           

        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
