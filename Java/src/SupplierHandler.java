import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SupplierHandler {

    private Connection connection = null;
    private PreparedStatement pstm = null;
    private Statement stm = null;
    private ResultSet rs = null;
    private static ArrayList<Supplier> supplierList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public SupplierHandler(Connection connection){
        this.connection = connection;
        fillSupplierList();
    }

    public void addSupplier(){
        try {
            pstm = connection.prepareStatement("INSERT INTO Leverantör(Namn, epost, telefonnummer) VALUES(?, ?, ?)");
            System.out.print("Name: ");System.out.flush();
            pstm.setString(1, scan.nextLine());
            System.out.print("Email: ");System.out.flush();
            pstm.setString(2, scan.nextLine());
            System.out.print("Phone number: ");System.out.flush();
            pstm.setString(3, scan.nextLine());
            pstm.execute();
            System.out.println("Supplier successfully added!");
        } catch (Exception e){
            System.out.println("There was an error while adding the supplier! \n" + e.toString());
        }
    }

    public void fillSupplierList(){
        supplierList.clear();
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM Leverantör");
            while (rs.next()){
                supplierList.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeAll(){
        try {
            if(pstm != null){
                pstm.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Supplier> getSupplierList(){
        return supplierList;
    }
}
