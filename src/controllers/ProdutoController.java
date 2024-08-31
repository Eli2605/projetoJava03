package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Produto;
import repositories.ProdutoRepository;

public class ProdutoController {

	private Scanner scanner = new Scanner(System.in);

	public void cadastrarProduto() {

		try {

			System.out.println("\nCADASTRO DE PRODUTOS:\n");

			System.out.print("Nome do produto...:");
			var nome = scanner.nextLine();
			System.out.println("Preço...........:");
			var preco = Double.parseDouble(scanner.nextLine());
			System.out.println("Quantidade......:");
			var quantidade = Integer.parseInt(scanner.nextLine());

			var produto = new Produto(UUID.randomUUID(), nome, preco, quantidade);

			// enviar o produto para ser cadastrado no banco de dados
			var produtoRepository = new ProdutoRepository();
			produtoRepository.inserir(produto);

		} catch (Exception e) {
			System.out.println("\nFalha ao cadastrar o produto!");
			System.out.println(e.getMessage());

		}

	}

	public void atualizarProduto() {

		try {

			System.out.println("\nATUALIZAÇAÕ DE PRODUTOS:\n");

			System.out.println("INFORME O ID DO PRODUTO.:");
			var id = UUID.fromString(scanner.nextLine());

			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);

			if (produto != null) {

				System.out.println("\nDados do produto:");
				System.out.println("ID..........:" + produto.getId());
				System.out.println("Nome........:" + produto.getNome());
				System.out.println("preco.......:" + produto.getPreco());
				System.out.println("Quantidade..:" + produto.getQuantidade());

				System.out.println("ALtere o nome......:");
				produto.setNome(scanner.nextLine());

				System.out.println("ALtere o preço......:");
				produto.setPreco(Double.parseDouble(scanner.nextLine()));

				System.out.println("ALtere o quantidade......:");
				produto.setQuantidade(Integer.parseInt(scanner.nextLine()));

				produtoRepository.atualizar(produto);

			} else {
				System.out.println("\nProduto não encontrado. Verifique o ID informado.");
			}

		} catch (Exception e) {
			System.out.println("\nFalha ao cadastrar o produto!");
			System.out.println(e.getMessage());

		}
	}

	public void excluirProduto() {

		try {

			System.out.println("\nEXCLUSÃO DE PRODUTOS:\n");

			System.out.println("INFORME O ID DO PRODUTO.:");
			var id = UUID.fromString(scanner.nextLine());

			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);

			if (produto != null) {

				System.out.println("\nDados do produto:");
				System.out.println("ID..........:" + produto.getId());
				System.out.println("Nome........:" + produto.getNome());
				System.out.println("preco.......:" + produto.getPreco());
				System.out.println("Quantidade..:" + produto.getQuantidade());

				produtoRepository.excluir(produto.getId());

			} else {
				System.out.println("\nProduto não encontrado. Verifique o ID informado.");
			}

		} catch (Exception e) {
			System.out.println("\nFalha ao excluir o produto!");
			System.out.println(e.getMessage());

		}
	}

	public void consultarProdutos() {

		try {

			System.out.println("\nCONSULTA DE PRODUTOS: \n");

			var produtoRepository = new ProdutoRepository();
			var lista = produtoRepository.consultar();

			// percorrer todos os produtos contidos na lista
			for (Produto produto : lista) {// foreach (para cada item, leia...)

				System.out.println("ID..........:" + produto.getId());
				System.out.println("Nome........:" + produto.getNome());
				System.out.println("preco.......:" + produto.getPreco());
				System.out.println("Quantidade..:" + produto.getQuantidade());
				System.out.println("");

			}

		} catch (Exception e) {
			System.out.println("\nFalha ao consultar os produtos!");
			System.out.println(e.getMessage());
		}
	}

}