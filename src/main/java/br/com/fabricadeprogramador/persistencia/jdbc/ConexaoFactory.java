package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	
	public static Connection getConnetion(){
		
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/fabricaweb_db",
					"postgres", "123");
		} catch (SQLException e) {
			/*Embrulhando a Exception SQLException em uma RuntimeException para
			a classe que chamar a conexao não ficar dependente do JDBC */
			throw new RuntimeException( "Não conectou com o banco!", e);}
		}
		

}

