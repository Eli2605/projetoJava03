package principal;

import java.util.Scanner;

import controllers.ProdutoController;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("\nSistema de Controle de Produto:\n");
		
		System.out.println("(1) Cadastrar Produtos");
		System.out.println("(2) Consultar Produtos");
		System.out.println("(3) Atualizar Produtos");
		System.out.println("(4) Excluir Produtos");
		
		System.out.println("\nINFORME A OPÇÃO DESEJADA: ");
		var opção = scanner.nextLine();
		
		var produtoController = new ProdutoController();
		
		switch(opção) {
		case "1": produtoController.cadastrarProduto(); break;
		case "2": produtoController.consultarProdutos(); break;
		case "3": produtoController.atualizarProduto(); break;
		case "4": produtoController.excluirProduto(); break;
		}
		
		System.out.println("\nDESEJA CONTINUAR? (S,N):");
		var continuar = scanner.nextLine();
		
		if(continuar.equalsIgnoreCase("S")) {
			
			main(args);
		}
		else {
			System.out.println("\nFim Do Programa!");
		}
		
		
	}

}
