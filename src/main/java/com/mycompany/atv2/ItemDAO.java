
package com.mycompany.atv2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDAO {
    public static boolean cadastrarItem(String nome) {
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO itens (nome) VALUES (?)")) {
            pstmt.setString(1, nome);
            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar item: " + ex.getMessage());
            return false;
        }
    }
}