/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO { 
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
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
                return listagem;
    }
        }

