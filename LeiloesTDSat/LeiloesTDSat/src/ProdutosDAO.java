/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO { 
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagemVIEW = new ArrayList<>();
    
    public ProdutosDAO() {
        conn = new conectaDAO().connectDB();
    }
    public void cadastrarProduto (ProdutosDTO produto){      
                   
        try {
            String sql="INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Cadastro foi realizado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
        } finally {
            try {
                if (prep != null) {
                    prep.close(); 
                }
                if (conn != null) {
                    conn.close(); 
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão!" + e.getMessage());
            }
        }          
    }
    public ArrayList<ProdutosDTO> listarProdutos(){
    ArrayList<ProdutosDTO> produtos = new ArrayList<>();
    String sql = "SELECT * FROM produtos"; 
    try {
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id")); 
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor")); 
            produto.setStatus(resultset.getString("status"));
            produtos.add(produto);
        }
    } catch (Exception e) {
        System.err.println("Erro ao listar produtos: " + e.getMessage());
    } finally {
        try {
            if (resultset != null) resultset.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
    return produtos;
    }

    public void venderProduto(int parseInt) {
         String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UC11?useSSL=false, root, spyke289");
         PreparedStatement prep = conn.prepareStatement(sql)) {

       // prep.setInt(1, id);
        prep.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace(); 
    }
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
    ArrayList<ProdutosDTO> produtos = new ArrayList<>();    
    }
}