/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author fabio
 */
public class FornecedoresDAO {
   private Connection con;
    
    public FornecedoresDAO(){
        this.con = new ConnectionFactory().getConnetion();
    }
    // cadastro fornededores
    public void cadastrarFornecedores(Fornecedores obj){
        try{
            
            String sql = "insert into tb_fornecedores (nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?)";
            //passo conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt=con.prepareStatement(sql);
            
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
        // executar o comando sql
            
        stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
        
        }
        catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro"+ erro);
        }
    }
        
}
