/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.RootPaneUI;


public class ProdutosDAO {
    
    //Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        try(Connection conn = new conectaDAO().connectDB();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getValor());
            pstmt.setString(3, produto.getStatus());
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.");
            
        }catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possível realizar o cadastro!"
                                                + "\n Tente Novamente.");
        }
    }
    
    public List<ProdutosDTO> listarProdutos(){
        String sql = "SELECT * FROM produtos";
        
        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet resultset = pstmt.executeQuery()) {
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return listagem;
    }
    
    public List<ProdutosDTO> listarProdutosVendidos(){
        String sql = "SELECT * FROM produtos WHERE status = 'vendido'";
        
        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet resultset = pstmt.executeQuery()) {
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return listagem;
    }

    void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'vendido' WHERE id = ?";
        
        try(Connection conn = new conectaDAO().connectDB();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Lista autalizada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

