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
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UC11","root","spyke289");
           
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    }

