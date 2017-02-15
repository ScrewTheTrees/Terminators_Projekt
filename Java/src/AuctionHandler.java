import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Robin on 15/02/2017.
 */
public class AuctionHandler {
    private Connection connection;
    private Statement stm = null;
    private CallableStatement cstm = null;
    private ResultSet rs = null;
    private ResultSet rs2 = null;
    private PreparedStatement pstm = null;

    private static ProductHandler productHandler = null;

    ArrayList<Supplier> suppliers = null;
    ArrayList<Product> products = null;
    ArrayList<String> auctions = null;

    private Scanner scan = new Scanner(System.in);

    public AuctionHandler(Connection connection) {
        this.connection = connection;
        productHandler = new ProductHandler(connection);
    }

    public void listActiveAuctionsAndHistory(){
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT Auktion.AuktionId, Produkt.Namn, Auktion.StartDatum, Auktion.SlutDatum FROM Kund\n" +
                    "INNER JOIN Bud ON Kund.KundNummer = Bud.KundNummer\n" +
                    "INNER JOIN Auktion ON Bud.AuktionId = Auktion.AuktionId\n" +
                    "INNER JOIN auktionsprodukt ON Auktion.AuktionId = auktionsprodukt.AuktionId\n" +
                    "INNER JOIN Produkt ON Auktionsprodukt.Produktnummer = Produkt.Produktnummer\n" +
                    "WHERE SlutDatum > current_date()\n" +
                    "GROUP BY Produkt.Namn;");
            System.out.println("Current auctions and bidding history:");
            while (rs.next()){
                System.out.println("\t" + rs.getString(2) + ", " + rs.getString(3) +
                        ", " + rs.getString(4));
                pstm = connection.prepareStatement("SELECT Förnamn, Efternamn, Budsumma, AcceptPris, Namn AS Produkt_Namn FROM Kund\n" +
                        "INNER JOIN Bud ON Kund.KundNummer = Bud.KundNummer\n" +
                        "INNER JOIN Auktion ON Bud.AuktionId = Auktion.AuktionId\n" +
                        "INNER JOIN auktionsprodukt ON Auktion.AuktionId = auktionsprodukt.AuktionId\n" +
                        "INNER JOIN Produkt ON Auktionsprodukt.Produktnummer = Produkt.Produktnummer\n" +
                        "WHERE Auktion.AuktionId = ?\n" +
                        "GROUP BY Kund.Förnamn\n" +
                        "ORDER BY Budsumma ASC");
                pstm.setString(1, rs.getString(1));
                rs2 = pstm.executeQuery();
                while(rs2.next()){
                    System.out.println("\t\t" + rs2.getString(1) + ", " + rs2.getString(2) + ", Bid: " +
                                        rs2.getString(3) + " SEK");
                }
                System.out.println(" ");
            }
            System.out.println("Press enter to continue...");
            scan.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listClosedAuctions(){
        System.out.print("Start date: ");System.out.flush();
        String startdatum = scan.nextLine();
        System.out.print("End date: ");System.out.flush();
        String slutdatum = scan.nextLine();
        try {
            cstm = connection.prepareCall("{CALL GetAvslutadeAuktioner(?, ?)}");
            cstm.setString(1, startdatum);
            cstm.setString(2, slutdatum);
            cstm.execute();
            rs = cstm.getResultSet();
            System.out.println("Closed auctions:");
            while (rs.next()){
                System.out.println("\tAuctionID: " + rs.getString(1) + ", Provision: " + rs.getString(2) + " SEK");
            }
            System.out.println("Press enter to continue...");
            scan.nextLine();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listTotalProvision(){
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM TotalProvision");

            System.out.println("Total provision per month:");
            while (rs.next()){
                System.out.println("\tMonth: " + rs.getString(1) + ", Provision: " + rs.getString(2) + " SEK");
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
            if(rs2 != null){
                rs2.close();
            }
            if(stm != null){
                stm.close();
            }
            if(pstm != null){
                pstm.close();
            }
            if(cstm != null){
                cstm.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addAuctionFromSupplier(){
        int choice;
        suppliers = SupplierHandler.getSupplierList();

        for (int i = 0; i < suppliers.size(); i++){
            System.out.println(suppliers.get(i).toString());
        }
        System.out.print("Choose a supplier: ");System.out.flush();
        choice = scan.nextInt();
        productHandler.fillProductListFromSupplier(choice);
        products = productHandler.getProductsFromSupplier();

        for (int i = 0; i < products.size(); i++){
            System.out.println(products.get(i).toString());
        }
        System.out.print("Choose a product: ");
        int productnumber = scan.nextInt();

        System.out.print("Start price: ");
        int startPrice = scan.nextInt();

        System.out.print("Accepting price: ");
        int acceptPrice = scan.nextInt();

        System.out.print("Start date: ");
        Scanner scanner = new Scanner(System.in); // Var tvungen att lägga till denna scanner då denna rad hoppades över annars...
        String startdatum = scanner.nextLine();

        System.out.print("End date: ");
        String slutdatum = scanner.nextLine();

        try {
            cstm = connection.prepareCall("{CALL SkapaAuktion(?, ?, ?, ?, ?)}");
            cstm.setInt(1, startPrice);
            cstm.setInt(2, acceptPrice);
            cstm.setString(3, startdatum);
            cstm.setString(4, slutdatum);
            cstm.setInt(5, productnumber);
            cstm.execute();
            System.out.println("Auction created");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
