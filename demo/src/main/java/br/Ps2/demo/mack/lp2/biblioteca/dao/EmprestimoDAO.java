package mack.lp2.biblioteca.dao;

import mack.lp2.biblioteca.model.emprestimo;
import java.sql.*;

public class EmprestimoDAO extends AbstractDAO {

    @Override
    public emprestimo create(Object obj) throws SQLException {
        emprestimo emp = (emprestimo) obj;
        String sql = "INSERT INTO emprestimo (livro_id, data_retirada) VALUES (?, ?)";

        try (Connection con = openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, emp.getLivro_id());
            stmt.setString(2, emp.getData_retirada());
            stmt.executeUpdate();
        }
        return emp;
    }

    @Override
    public emprestimo read(int id) throws SQLException {
        String sql = "SELECT * FROM emprestimo WHERE id = ?";
        emprestimo emp = null;

        try (Connection con = openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emp = new emprestimo(
                        rs.getInt("livro_id"),
                        rs.getString("data_retirada")
                );
            }
        }
        return emp;
    }

    @Override
    public emprestimo update(Object obj) throws SQLException {
        emprestimo emp = (emprestimo) obj;
        String sql = "UPDATE emprestimo SET livro_id = ?, data_retirada = ? WHERE id = ?";

        try (Connection con = openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, emp.getLivro_id());
            stmt.setString(2, emp.getData_retirada());
                stmt.setInt(3, emp.getLivro_id());
            stmt.executeUpdate();
        }
        return emp;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM emprestimo WHERE id = ?";

        try (Connection con = openConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}