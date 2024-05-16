
package com.mycompany.atv2;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroView extends JFrame {
    private JTextField campoNome;
    private JButton btnCadastrar;
    private JButton btnVisualizarLista;
    
    public CadastroView() {
        initComponents();
    }
    
    private void initComponents() {
        campoNome = new JTextField(20);
        btnCadastrar = new JButton("Cadastrar");
        btnVisualizarLista = new JButton("Visualizar Lista");
        
        btnCadastrar.addActionListener(e -> cadastrarItem());
        btnVisualizarLista.addActionListener(e -> visualizarLista());
        
        JPanel panel = new JPanel();
        panel.add(new JLabel("Nome do Item:"));
        panel.add(campoNome);
        panel.add(btnCadastrar);
        panel.add(btnVisualizarLista);
        
        add(panel);
        
        setTitle("Cadastro de Itens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void cadastrarItem() {
        String nome = campoNome.getText();
        if (!nome.isEmpty()) {
            if (ItemDAO.cadastrarItem(nome)) {
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                campoNome.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar item.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nome do item não pode estar vazio.");
        }
    }
    
    private void visualizarLista() {
        ListagemView listagem = new ListagemView();
        listagem.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CadastroView::new);
    }
}