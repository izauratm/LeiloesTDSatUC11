import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adm
 */
public class conectaDAO {
        
    public Connection connectDB(){
        Connection conn = null;
        
        try {
         String url = "jdbc:mysql://localhost:3306/UC11?useSSL=false";   
         String user = "root";
         String password = "spyke289";
         conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}

