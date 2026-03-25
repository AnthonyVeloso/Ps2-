package br.Ps2.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mack.lp2.biblioteca.dao.LivroDAO;
import mack.lp2.biblioteca.dao.EmprestimoDAO;
import mack.lp2.biblioteca.model.Livro;
import mack.lp2.biblioteca.model.emprestimo;

import java.util.Scanner;

@SpringBootApplication
public class BancoDeDadosApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BancoDeDadosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        LivroDAO livroDAO = new LivroDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        int opcao = -1;

        do {
            System.out.println("\n===== SISTEMA BIBLIOTECA =====");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Buscar livro");
            System.out.println("3 - Atualizar livro");
            System.out.println("4 - Deletar livro");
            System.out.println("5 - Criar empréstimo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine(); // Limpar buffer

                try {
                    executarMenu(opcao, sc, livroDAO, emprestimoDAO);
                } catch (Exception e) {
                    System.out.println(" Erro na operação: " + e.getMessage());
                }
            } else {
                System.out.println(" Por favor, digite um número.");
                sc.nextLine();
            }

        } while (opcao != 0);

        sc.close();
        System.exit(0); // Garante que o processo encerre no terminal
    }

    private void executarMenu(int opcao, Scanner sc, LivroDAO livroDAO, EmprestimoDAO emprestimoDAO) throws Exception {
        switch (opcao) {
            case 1:
				System.out.print("Digite o ID do livro: ");
				int id = Integer.parseInt(sc.nextLine());    
				System.out.print("Título: ");
				String titulo = sc.nextLine();   
				System.out.print("Autor: ");
				String autor = sc.nextLine();
				// Criamos o objeto com o ID digitado
				Livro novoLivro = new Livro(id, titulo, autor);   
				// O DAO agora enviará esse ID para o banco
				livroDAO.create(novoLivro);  
				System.out.println("✅ Livro cadastrado com sucesso!");
				break;
            case 2:
                System.out.print("ID: ");
                Livro l = livroDAO.read(sc.nextInt());
                if (l != null) System.out.println(l.getNome() + " - " + l.getAutor());
                else System.out.println(" Não encontrado.");
                break;
            case 5:
                System.out.print("ID Livro: ");
                int idL = sc.nextInt(); sc.nextLine();
                System.out.print("Data (YYYY-MM-DD): ");
                String d = sc.nextLine();
                emprestimoDAO.create(new emprestimo(idL, d));
                System.out.println(" Empréstimo ok!");
                break;
            case 0:
                System.out.println("Saindo...");
                break;
        }
    }
}