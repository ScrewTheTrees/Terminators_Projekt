import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Robin on 10/02/2017.
 */
public class ConnectionHandler {
    private Statement stm = null;
    private PreparedStatement pstm = null;
    private static ArrayList<Kund> customerList = new ArrayList();
    private static ArrayList<Auktion> auctionList = new ArrayList();
    private static ArrayList<Leverantor> supplierList = new ArrayList();
    private Connection connection = null;
    ResultSet rs = null;

    public void startConnection()throws Exception{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Auktion?useSSL=false", "root","");
    }

    public void closeConnection(){
        if(rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(stm != null) {
            try {
                stm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(pstm != null) {
            try {
                pstm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList getCustomers(){
        customerList.clear();
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM Kund");

            while(rs.next()){
                customerList.add(new Kund(rs.getInt("KundNummer"), rs.getString("Förnamn"), rs.getString("Efternamn"), rs.getString("Gata"), rs.getString("Ort"), rs.getString("Epost")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return customerList;
    }

    public ArrayList getSupplierList(){

        auctionList.clear();
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM Leverantör");

            while(rs.next()){
                supplierList.add(new Leverantor(rs.getInt("LeverantörId"), rs.getString("Namn"), rs.getString("Epost"), rs.getString("Telefonnummer")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return supplierList;
    }

    public void addSupplier(String namn, String epost, String telefon){
        try {
            pstm = connection.prepareStatement("INSERT INTO Leverantör(Namn, epost, telefonnummer) " +
                                                "VALUES(?, ?, ?)");
            pstm.setString(1, namn);
            pstm.setString(1, epost);
            pstm.setString(1, telefon);
            pstm.executeQuery();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
