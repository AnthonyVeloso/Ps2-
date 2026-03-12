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

        // 1️⃣ Inserir livro
        String sqlLivro = "INSERT INTO Livro (titulo, autor) VALUES (?,?)";

        PreparedStatement pstmtLivro = con.prepareStatement(sqlLivro);
        pstmtLivro.setString(1, "Dom Casmurro");
        pstmtLivro.setString(2, "Machado de Assis");

        int qteLivro = pstmtLivro.executeUpdate();

        if(qteLivro >= 1){
            System.out.println("Livro inserido com sucesso");
        }

        // 2️⃣ Inserir empréstimo
        String sqlEmprestimo = "INSERT INTO Emprestimo (livro_id, data_retirada) VALUES (?,?)";

        PreparedStatement pstmtEmp = con.prepareStatement(sqlEmprestimo);
        pstmtEmp.setInt(1, 1);
        pstmtEmp.setDate(2, java.sql.Date.valueOf("2026-03-12"));

        int qteEmp = pstmtEmp.executeUpdate();

        if(qteEmp >= 1){
            System.out.println("Empréstimo registrado");
        }

        // 3️⃣ Listar livros
        ResultSet rs = stmt.executeQuery("SELECT * FROM Livro");

        System.out.println("\nLista de Livros:");

        while(rs.next()){
            System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("titulo") + " - " +
                    rs.getString("autor")
            );
        }

        // 4️⃣ Listar empréstimos com JOIN
        ResultSet rs2 = stmt.executeQuery(
                "SELECT Livro.titulo, Emprestimo.data_retirada " +
                "FROM Emprestimo " +
                "JOIN Livro ON Livro.id = Emprestimo.livro_id"
        );

        System.out.println("\nEmpréstimos:");

        while(rs2.next()){
            System.out.println(
                    rs2.getString("titulo") +
                    " - " +
                    rs2.getDate("data_retirada")
            );
        }

        con.close();
    }
}