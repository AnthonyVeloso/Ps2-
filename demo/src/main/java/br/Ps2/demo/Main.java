

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = jdbc:postgresql://db.mdxdtohklaqwbhazssvi.supabase.co:5432/postgres?user=postgres&password=?
        String username="postgres";
        String password="rpzYWadTSFERbxO3";
        Connection con = DriverManager.getConnection(url, username, password);

        Statement stmt = con.createStatement();

        //Consultando
        ResultSet rs = stmt.executeQuery("SELECT * FROM LIVROS");

        while(rs.next()){
            System.out.println(rs.getString("ID"));
        }

        //inserindo
        String sql_insert = "INSERT INTO PROPRIETARIOS (NOME, CPF) VALUES(?,?)";

        PreparedStatement pstmt = con.prepareStatement(sql_insert);

        pstmt.setString(1,  "antonio");
        pstmt.setString(2,"333333333-33");

        int qte = pstmt.executeUpdate();
        if(qte >=1)
            System.out.println("inserido com sucesso");

    }
}