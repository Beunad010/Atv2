
package com.mycompany.atv2;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListagemView extends JFrame {
    private JTextArea areaLista;
    
    public ListagemView() {
        initComponents();
    }
    
    private void initComponents() {
        areaLista = new JTextArea(20, 30);
        areaLista.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(areaLista);
        
        add(scrollPane);
        
        setTitle("Lista de Itens");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        carregarItens();
    }
    
    private void carregarItens() {
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM itens");
             ResultSet rs = pstmt.executeQuery()) {
            StringBuilder lista = new StringBuilder("Itens cadastrados:\n");
            while (rs.next()) {
                lista.append(rs.getString("nome")).append("\n");
            }
            areaLista.setText(lista.toString());
        } catch (SQLException ex) {
            System.out.println("Erro ao listar itens: " + ex.getMessage());
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ListagemView::new);
    }
}