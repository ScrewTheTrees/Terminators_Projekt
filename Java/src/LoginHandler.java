import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Created by Robin on 15/02/2017.
 */
public class LoginHandler {
    Connection connection = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    public LoginHandler(Connection connection) {
            this.connection = connection;
    }

    public boolean login(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Username: ");System.out.flush();
        String username = scan.nextLine();
        System.out.print("Password: ");System.out.flush();
        String password = scan.nextLine();
        int count = 0;
        try {
            pstm = connection.prepareStatement("SELECT usr, pwd FROM user_account WHERE usr = ? AND pwd = ?");
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while(rs.next()){
                count++;
            }
        } catch(Exception e){}

        if (count > 0){
            return true;
        } else {
            return false;
        }
    }

    public void closeAll(){
        try {
            if(rs != null){
                rs.close();
            }
            if(pstm != null){
                pstm.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
