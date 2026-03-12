import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws Exception {

        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://db.mdxdtohklaqwbhazssvi.supabase.co:5432/postgres";
        String username = "postgres";
        String password = "rpzYWadTSFERbxO3";

        Connection con = DriverManager.getConnection(url, username, password);

        Statement stmt = con.createStatement();

        // Inserir livro
        String sqlLivro = "INSERT INTO livro (titulo, autor) VALUES (?, ?)";

        PreparedStatement pstmtLivro = con.prepareStatement(sqlLivro);
        pstmtLivro.setString(1, "Dom Casmurro");
        pstmtLivro.setString(2, "Machado de Assis");

        int qteLivro = pstmtLivro.executeUpdate();

        if (qteLivro >= 1) {
            System.out.println("Livro inserido com sucesso");
        }

        // Inserir empréstimo
        String sqlEmprestimo = "INSERT INTO emprestimo (livro_id, data_retirada) VALUES (?, ?)";

        PreparedStatement pstmtEmp = con.prepareStatement(sqlEmprestimo);
        pstmtEmp.setInt(1, 1);
        pstmtEmp.setDate(2, java.sql.Date.valueOf("2026-03-12"));

        int qteEmp = pstmtEmp.executeUpdate();

        if (qteEmp >= 1) {
            System.out.println("Empréstimo registrado");
        }

        // Listar livros
        ResultSet rs = stmt.executeQuery("SELECT * FROM livro");

        System.out.println("\nLista de Livros:");

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("titulo") + " - " +
                    rs.getString("autor")
            );
        }

        // Listar empréstimos com JOIN
        ResultSet rs2 = stmt.executeQuery(
                "SELECT livro.titulo, emprestimo.data_retirada " +
                "FROM emprestimo " +
                "JOIN livro ON livro.id = emprestimo.livro_id"
        );

        System.out.println("\nEmpréstimos:");

        while (rs2.next()) {
            System.out.println(
                    rs2.getString("titulo") + " - " +
                    rs2.getDate("data_retirada")
            );
        }

        con.close();
    }
}