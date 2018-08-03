package br.com.fabricadeprogramador;



import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.ConexaoFactory;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		
		UsuarioDAO usuDao = new UsuarioDAO();
		Usuario usu = new Usuario();
		usu.setLogin("sfsantos"); usu.setSenha("123");
		Usuario usuRetorno = usuDao.autenticar(usu);
		if(usuRetorno!=null)
		System.out.println( usuRetorno.getId() +" "+ usuRetorno.getNome()+ " "+ usuRetorno.getLogin() + " "+ usuRetorno.getSenha());
		
		ConexaoFactory.getConnetion();

	}

}
