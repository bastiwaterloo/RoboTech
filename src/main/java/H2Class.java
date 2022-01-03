import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class H2Class {
    public static void main(String[] a) throws Exception {
        String url = "jdbc:h2:mem:";

        try (Connection con = DriverManager.getConnection(url);
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery("SELECT 1+1")) {

            if (rs.next()) {

                System.out.println(rs.getInt(1));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(H2Class.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}