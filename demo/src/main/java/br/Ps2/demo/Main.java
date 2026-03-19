package mack.lp2.biblioteca;

import java.util.Scanner;

import mack.lp2.biblioteca.dao.LivroDAO;
import mack.lp2.biblioteca.dao.EmprestimoDAO;
import mack.lp2.biblioteca.model.Livro;
import mack.lp2.biblioteca.model.emprestimo;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LivroDAO livroDAO = new LivroDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        int opcao = 0;

        do {
            System.out.println("\n===== SISTEMA BIBLIOTECA =====");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Buscar livro");
            System.out.println("3 - Atualizar livro");
            System.out.println("4 - Deletar livro");
            System.out.println("5 - Criar empréstimo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            try {
                switch (opcao) {

                    case 1:
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();

                        System.out.print("Autor: ");
                        String autor = sc.nextLine();

                        Livro novoLivro = new Livro(0, titulo, autor);
                        livroDAO.create(novoLivro);

                        System.out.println("✅ Livro cadastrado com ID: " + novoLivro.getId());
                        break;

                    case 2:
                        System.out.print("ID do livro: ");
                        int idBusca = sc.nextInt();

                        Livro livro = livroDAO.read(idBusca);

                        if (livro != null) {
                            System.out.println("📚 " + livro.getNome() + " - " + livro.getAutor());
                        } else {
                            System.out.println("❌ Livro não encontrado");
                        }
                        break;

                    case 3:
                        System.out.print("ID do livro: ");
                        int idUpdate = sc.nextInt();
                        sc.nextLine();

                        Livro livroUpdate = livroDAO.read(idUpdate);

                        if (livroUpdate != null) {
                            System.out.print("Novo título: ");
                            String novoTitulo = sc.nextLine();

                            System.out.print("Novo autor: ");
                            String novoAutor = sc.nextLine();

                            livroUpdate.setNome(novoTitulo);
                            livroUpdate.setAutor(novoAutor);

                            livroDAO.update(livroUpdate);

                            System.out.println("✅ Livro atualizado!");
                        } else {
                            System.out.println("❌ Livro não encontrado");
                        }
                        break;

                    case 4:
                        System.out.print("ID do livro: ");
                        int idDelete = sc.nextInt();

                        livroDAO.delete(idDelete);

                        System.out.println("🗑️ Livro deletado!");
                        break;

                    case 5:
                        System.out.print("ID do livro: ");
                        int idLivro = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Data (YYYY-MM-DD): ");
                        String data = sc.nextLine();

                        emprestimo emp = new emprestimo(idLivro, data);
                        emprestimoDAO.create(emp);

                        System.out.println("📖 Empréstimo registrado!");
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("❌ Opção inválida");
                }

            } catch (Exception e) {
                System.out.println("⚠️ Erro: " + e.getMessage());
            }

        } while (opcao != 0);

        sc.close();
    }
}