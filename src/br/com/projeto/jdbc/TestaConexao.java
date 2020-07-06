package br.com.projeto.jdbc;

import javax.swing.JOptionPane;

public class TestaConexao {

    public static void main(String[] args) {
        
        try {
         new ConnectionFactory().getConnetion();
            JOptionPane.showMessageDialog(null, "connectado com sucesso");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, " erro ocorrido: " + erro);
        }
        
    }
}
