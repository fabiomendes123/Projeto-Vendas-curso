/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

/**
 *
 * @author fabio
 */
public class ProdutosDAO {

    private Connection con;

    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnetion();
    }

    // Metodo cadastrar produtos
    public void cadastrar(Produtos obj) {
        try {
            String sql = "insert into tb_produtos(descricao,preco,qtd_estoque,for_id)values (?,?,?,?)";
            //conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
 
            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
    }
    // metodo listar produtos
    public List<Produtos> listarProdutos(){
            try {
                // 1o passo criara a lista
                List<Produtos> lista = new ArrayList<>();
                // 2 passpo criar o sql organizar e executar
                String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                        +"inner join tb_fornecedores as f on (p.for_id = f.id)";
                
                PreparedStatement stmt =con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()){
                    Produtos obj = new Produtos();
                    Fornecedores f = new Fornecedores();
                    
                    obj.setId(rs.getInt("p.id"));
                    obj.setDescricao(rs.getString("p.descricao"));
                    obj.setPreco(rs.getDouble("p.preco"));
                    obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                    
                    f.setNome(rs.getString(("f.nome")));
                    
                    obj.setFornecedor(f);
                    
                    lista.add(obj);
                }
                
                return lista;
            }catch (SQLException erro){
                 JOptionPane.showMessageDialog(null, "Erro." + erro);
        }
        return null;       
    }
    
    // Metodo alterar
    public void alterar (Produtos obj){
            try {
                // 1o passo criara a lista
                
                String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?, for_id=? where id=?";
                
                PreparedStatement stmt =con.prepareStatement(sql);
                stmt.setString(1, obj.getDescricao());
                stmt.setDouble(2, obj.getPreco());
                stmt.setInt(3, obj.getQtd_estoque());
                
                stmt.setInt(4, obj.getFornecedor().getId());
                
                stmt.setInt(5, obj.getId());
                
                stmt.execute();
                stmt.close();
                
                JOptionPane.showMessageDialog(null, "Alterado com sucesso");
                
            }catch (SQLException erro){
                 JOptionPane.showMessageDialog(null, "Erro." + erro);
        }
     
    }
     //Excluir
    public void excluir (Produtos obj){
            try {
                
                String sql = "delete from tb_produtos where id=?";
                
                PreparedStatement stmt =con.prepareStatement(sql);
                
                stmt.setInt(1, obj.getId());
                
                stmt.execute();
                stmt.close();
                
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
                
            }catch (SQLException erro){
                 JOptionPane.showMessageDialog(null, "Erro." + erro);
        }
     
    }
   
    public List<Produtos> listarProdutosPorNome(String nome){
            try {
                // 1o passo criara a lista
                List<Produtos> lista = new ArrayList<>();
                // 2 passpo criar o sql organizar e executar
                String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                        +"inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";
                
                PreparedStatement stmt =con.prepareStatement(sql);
                stmt.setString(1, nome);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()){
                    Produtos obj = new Produtos();
                    Fornecedores f = new Fornecedores();
                    
                    obj.setId(rs.getInt("p.id"));
                    obj.setDescricao(rs.getString("p.descricao"));
                    obj.setPreco(rs.getDouble("p.preco"));
                    obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                    
                    f.setNome(rs.getString(("f.nome")));
                    
                    obj.setFornecedor(f);
                    
                    lista.add(obj);
                }
                
                return lista;
            }catch (SQLException erro){
                 JOptionPane.showMessageDialog(null, "Erro." + erro);
        }
        return null;       
    }
      public Produtos consultaPorNome(String nome){
            try {
                // 1o passo criara a lista
                List<Produtos> lista = new ArrayList<>();
                // 2 passpo criar o sql organizar e executar
                String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                        +"inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao = ?";
                
                PreparedStatement stmt =con.prepareStatement(sql);
                stmt.setString(1, nome);
                
                ResultSet rs = stmt.executeQuery();
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                if (rs.next()){
                    
                    obj.setId(rs.getInt("p.id"));
                    obj.setDescricao(rs.getString("p.descricao"));
                    obj.setPreco(rs.getDouble("p.preco"));
                    obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                    
                    f.setNome(rs.getString(("f.nome")));
                    
                    obj.setFornecedor(f);
                    
                }
                return obj;
                
            }catch (SQLException erro){
                 JOptionPane.showMessageDialog(null, "Produto não encontrado!" + erro);
        }
        return null;       
    }
      public Produtos buscaPorCodigo(int id){
            try {
                // 1o passo criara a lista
                List<Produtos> lista = new ArrayList<>();
                // 2 passpo criar o sql organizar e executar
                String sql = "select p.id,p.descricao,p.preco,p.qtd_estoque,f.nome from tb_produtos as p "
                        +"inner join tb_fornecedores as f on (p.for_id = f.id) where p.id = ?";
                
                PreparedStatement stmt =con.prepareStatement(sql);
                stmt.setInt(1, id);
                
                ResultSet rs = stmt.executeQuery();
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                
                if (rs.next()){
                    
                    obj.setId(rs.getInt("p.id"));
                    obj.setDescricao(rs.getString("p.descricao"));
                    obj.setPreco(rs.getDouble("p.preco"));
                    obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                    
                    f.setNome(rs.getString(("f.nome")));
                    
                    obj.setFornecedor(f);
                    
                }
                return obj;
                
            }catch (SQLException erro){
                 JOptionPane.showMessageDialog(null, "Produto não encontrado!" + erro);
        }
        return null;       
    }
}