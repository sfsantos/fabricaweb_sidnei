package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {

	private Connection con = ConexaoFactory.getConnetion();
	
	public void cadastrar(Usuario usu) {
		
		String sql = "INSERT INTO USUARIO (nome, login, senha) VALUES (?,?,md5(?))";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			
			preparador.execute();
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(Usuario usu){


		String sql = "UPDATE USUARIO SET NOME=?, LOGIN=?, SENHA=md5(?) WHERE ID=?";
		
		 
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			
			preparador.execute();
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {

            throw new RuntimeException(e);
		}
			
		
	}
	
	public void excluir(Usuario usu){

		
		String sql = "DELETE FROM USUARIO WHERE ID=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, usu.getId());
			preparador.execute();
			System.out.println("Excluído com sucesso!");
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		
	}
	
	public void salvar(Usuario usu){
		if(usu.getId()!=null && usu.getId()>0){
			alterar(usu);
		}else{
			cadastrar(usu);
		}
	}
	
	public Usuario buscaPorId(Integer Id){
		Usuario usuRetorno = null;
		String sql = "SELECT *FROM USUARIO WHERE ID=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, Id);
			
			ResultSet  resultado = preparador.executeQuery();
			
			if(resultado.next()){
				
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
			}
			System.out.println("Encontrado com sucesso!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return usuRetorno;
	}
	
	public List<Usuario> buscarTodos(){
		List<Usuario> listaRetorno = new ArrayList<Usuario>();
			
		String sql = "select * from usuario order by id";
		try (PreparedStatement preparador = con.prepareStatement(sql)){
		//Retorno da consulta em Resultset
		ResultSet resultado = preparador.executeQuery();
		//Navegada nos registros 
		while(resultado.next()){
		//instancia o objeto Usuario
		Usuario usu = new Usuario();
		//Carga de dados no usuário 
		usu.setId(resultado.getInt("id"));
		usu.setNome(resultado.getString("nome")); 
		usu.setLogin(resultado.getString("login"));
		usu.setSenha(resultado.getString("senha"));
		//adiciona na lista 
		listaRetorno.add(usu);
		}
		
		System.out.println("Busca com sucesso!");
		}catch (SQLException e) {
			 throw new RuntimeException(e);
		}
		return listaRetorno;
	
		
	}

	public Usuario autenticar(Usuario usuConsulta){
		
		//Objeto de retorno do método
		Usuario usuRetorno = null;
		String sql = "select * from usuario where login=? and senha=md5(?)";
		 try (PreparedStatement preparador = con.prepareStatement(sql)){
		preparador.setString(1, usuConsulta.getLogin());
		preparador.setString(2, usuConsulta.getSenha());
		//Retorno da consulta em Resultset
		ResultSet resultado = preparador.executeQuery();
		//Se tem registro 
		if(resultado.next()){
		//instancia o objeto Usuario 
			usuRetorno = new Usuario();
		usuRetorno.setId(resultado.getInt("id")); 
		usuRetorno.setNome(resultado.getString("nome"));
		usuRetorno.setLogin(resultado.getString("login")); 
		usuRetorno.setSenha(resultado.getString("senha"));
		System.out.println("Usuário Autenticado");
		}
		 }
		 catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return usuRetorno;
}
}





