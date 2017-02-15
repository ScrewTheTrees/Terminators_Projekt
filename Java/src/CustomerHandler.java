import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerHandler {
    private Connection connection = null;
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    Scanner scan = new Scanner(System.in);

    public CustomerHandler(Connection connection){
        this.connection = connection;
    }

    public void addCustomer(){

        try {
            pstm = connection.prepareStatement("INSERT INTO Kund(Förnamn, Efternamn, Gata, Ort, Epost) VALUES(?, ?, ?, ?, ?)");
            System.out.print("First name: ");System.out.flush();
            pstm.setString(1, scan.nextLine());
            System.out.print("Last name: ");System.out.flush();
            pstm.setString(2, scan.nextLine());
            System.out.print("Street name: ");System.out.flush();
            pstm.setString(3, scan.nextLine());
            System.out.print("City: ");System.out.flush();
            pstm.setString(4, scan.nextLine());
            System.out.print("Email adress: ");System.out.flush();
            pstm.setString(5, scan.nextLine());
            pstm.execute();
            System.out.println("Customer successfully added!");
        } catch (Exception e){
            System.out.println("There was an error while adding the customer! \n" + e.toString());
        }
    }

    public void listCustomersAndOrderValue(){
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM KundLista");
            System.out.println("Customers and their total order value:");
            while (rs.next()){
                System.out.println("\tCustomer: " + rs.getString(1) + ", Total order value: " + rs.getString(2) + " SEK");
            }
            System.out.println("Press enter to continue...");
            scan.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeAll(){
        try {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(pstm != null){
                pstm.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
