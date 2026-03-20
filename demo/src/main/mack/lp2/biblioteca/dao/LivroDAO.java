package mack.lp2.biblioteca.dao;

import mack.lp2.biblioteca.model.Livro;
import java.sql.*;

public class LivroDAO extends AbstractDAO {

    @Override
    public Livro create(Object obj) throws SQLException {
        Livro livro = (Livro) obj;
        String sql = "INSERT INTO livro (titulo, autor) VALUES (?, ?)";

        try (Connection con = openConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getAutor());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                livro.setId(rs.getInt(1));
            }
        }
        return livro;
    }

    @Override
    public Livro read(int id) throws SQLException {
        String sql = "SELECT * FROM livro WHERE id = ?";
        Livro livro = null;

        try (Connection con = openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor")
                );
            }
        }
        return livro;
    }

    @Override
    public Livro update(Object obj) throws SQLException {
        Livro livro = (Livro) obj;
        String sql = "UPDATE livro SET titulo = ?, autor = ? WHERE id = ?";

        try (Connection con = openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getId());
            stmt.executeUpdate();
        }
        return livro;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM livro WHERE id = ?";

        try (Connection con = openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}