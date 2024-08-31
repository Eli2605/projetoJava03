package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Produto;
import factories.ConnectionFactory;

public class ProdutoRepository {

	public void inserir(Produto produto) {

		try {
			var connection = ConnectionFactory.getConnection();

			var statement = connection
					.prepareStatement("INSERT INTO produto_01(id, nome, preco, quantidade) VALUES(?,?,?,?)");
			statement.setObject(1, produto.getId());
			statement.setString(2, produto.getNome());
			statement.setDouble(3, produto.getPreco());
			statement.setInt(4, produto.getQuantidade());
			statement.execute();

			connection.close();

			System.out.println("\nProduto Cadastrado com Sucesso!");

		} catch (Exception e) {
			System.out.println("\nfalha ao inserir produto.");
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(Produto produto) {

		try {

			var connection = ConnectionFactory.getConnection();

			var statement = connection.prepareStatement("UPDATE produto SET nome=?, preco=?, quantidade=? WHERE id=?");
			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getPreco());
			statement.setInt(3, produto.getQuantidade());
			statement.setObject(4, produto.getId());
			statement.execute();

			connection.close();

			System.out.println("\nPRODUTO ATUALIZADO COM SUCESSO.");

		} catch (Exception e) {
			System.out.println("\nFalha ao atualizar produto.");
			System.out.println(e.getMessage());
		}
	}

	public void excluir(UUID id) {

		try {

			var connection = ConnectionFactory.getConnection();

			var statement = connection.prepareStatement("DELETE FROM produto WHERE id=?");
			statement.setObject(1, id);
			statement.execute(); // execute só grava , alterar, excluir ele não devolve nada.

			connection.close();

			System.out.println("\nPRODUTO EXCLUÍDO COM SUCESSO!");

		} catch (Exception e) {
			System.out.println("\nFalha ao excluir produto.");
			System.out.println(e.getMessage());

		}

	}
	
	public List<Produto> consultar(){
		
		var lista = new ArrayList<Produto>();
		
		try {
			
			var connection = ConnectionFactory.getConnection();
			
			var statement = connection.prepareStatement("SELECT id, nome, preco, quantidade FROM produto ORDER BY nome");
			var result = statement.executeQuery();// executeQuery excuta e devolve a consulta
			
			//enquanto houver produto, leia.
			while(result.next()) {
				
				//capturando os dados de cada produto lido da consulta 
				var produto = new Produto();
				produto.setId(UUID.fromString(result.getString("id")));
				produto.setNome(result.getString("nome"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQuantidade(result.getInt("quantidade"));
				
				lista.add(produto);//adcionando o produito dentro da lista 
				
			}
			//fechar a conexão com banco de dado 
			connection.close();
			
		}
		catch(Exception e) {
			System.out.println("\nFalha ao consultar produto.");
			System.out.println(e.getMessage());
			
		}
		
		//retorna a lista 
		return lista;
			
	}
	
	public Produto obterPorId(UUID id) {
		
		//Declarando um objeto produto vazio
		Produto produto = null;
		
		try {
			
			//abrindo conexão com o banco de dados
			var connection = ConnectionFactory.getConnection();
			
			//escrever o comando SQL que será executado nop banco de dados 
			var statement = connection.prepareStatement("SELECT id, nome, preco, quantidade FROM produto WHERE id=?");
			statement.setObject(1, id);
			var result = statement.executeQuery();
			
			//lendo o produto se for encontrado 
			if(result.next()) {
				
				produto = new Produto();
				
				produto.setId(UUID.fromString(result.getString("id")));
				produto.setNome(result.getString("nome"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQuantidade(result.getInt("quantidade"));
				
			}
			
			connection.close();
			
		}
		catch(Exception e) {
			System.out.println("\nFalha ao consultar por id.");
			System.out.println(e.getMessage());
			
		}
		
		//retornando o produto
		return produto;
		
		
		
	}

}
