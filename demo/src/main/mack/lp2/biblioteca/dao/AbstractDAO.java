package mack.lp2.biblioteca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO {

    // URL do seu banco (ajuste se necessário)
    protected String databaseURL = "jdbc:postgresql://db.mdxdtohklaqwbhazssvi.supabase.co:5432/postgres";

    // Usuário e senha do banco
    protected String user = "postgres";
    protected String password ="rpzYWadTSFERbxO3";

    // Método para abrir conexão
    protected Connection openConnection() throws SQLException {
        return DriverManager.getConnection(databaseURL, user, password);
    }

    // Métodos abstratos (CRUD)
    public abstract Object create(Object obj) throws SQLException;

    public abstract Object read(int id) throws SQLException;

    public abstract Object update(Object obj) throws SQLException;

    public abstract void delete(int id) throws SQLException;
}