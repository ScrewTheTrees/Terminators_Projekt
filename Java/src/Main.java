import java.sql.*;
import java.util.Scanner;

public class Main {
    private static boolean loggedIn = true;
    private static Scanner scan = new Scanner(System.in);

    private static Connection conn = null;
    private static Statement stm = null;
    private static PreparedStatement pstm = null;
    private static CallableStatement cstm = null;
    private static ResultSet rs = null;

    private static CustomerHandler customerHandler = null;
    private static SupplierHandler supplierHandler = null;
    private static AuctionHandler auctionHandler = null;
    private static LoginHandler loginHandler = null;

    private static void openConnection() {
        String URL = "jdbc:mysql://localhost:3306/Auktion?useSSL=false";
        try {
            conn = DriverManager.getConnection(URL, "root", "gr3ddsemla");
        } catch (Exception e){
            // Do nothing
        }
    }

    private static void closeConnection(){
        try {
            if (rs != null) {
                rs.close();
            }
            if (cstm != null) {
                cstm.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void printReports(){
        System.out.println("- Reports");
        System.out.println("1. View auctions within a date interval");
        System.out.println("2. View customers and their total order value");
        System.out.println("3. View total provision per month");
    }

    private static void printMenu(){
        System.out.println("1. Add a supplier");
        System.out.println("2. Add a customer");
        System.out.println("3. Add an auction from a supplier");
        System.out.println("4. List active auctions and view bidding history");
        System.out.println("5. Create reports");
        System.out.println("0. Exit");
    }

    private static void login(){
        loggedIn = loginHandler.login();
        if (loggedIn){
            System.out.println("Login successful!");
        } else {
            System.out.println("Incorrect username or password.");
        }
    }

    private static void setupHandlers(){
        loginHandler = new LoginHandler(conn);
        customerHandler = new CustomerHandler(conn);
        supplierHandler = new SupplierHandler(conn);
        auctionHandler = new AuctionHandler(conn);
    }

    private static void closeAllHandlers(){
        loginHandler.closeAll();
        customerHandler.closeAll();
        supplierHandler.closeAll();
        auctionHandler.closeAll();
    }

    public static void main(String[] args){
        openConnection();
        setupHandlers();
        login();
        int menuChoice;

        if(loggedIn){
            while(true){

                printMenu();
                System.out.print(">> ");System.out.flush();
                menuChoice = scan.nextInt();

                switch (menuChoice) {
                    case 1:
                        // Add a supplier
                        supplierHandler.addSupplier();
                        break;
                    case 2:
                        // Add a customer
                        customerHandler.addCustomer();
                        break;
                    case 3:
                        // Add an auction from a supplier
                        auctionHandler.addAuctionFromSupplier();
                        break;
                    case 4:
                        // List active auctions and view bidding history
                        auctionHandler.listActiveAuctionsAndHistory();
                        break;
                    case 5:
                        printReports();
                        System.out.print("Which report do you want to create?: ");
                        int report = scan.nextInt();
                        switch (report){
                            case 1:
                                auctionHandler.listClosedAuctions();
                                break;
                            case 2:
                                customerHandler.listCustomersAndOrderValue();
                                break;
                            case 3:
                                auctionHandler.listTotalProvision();
                                break;
                        }
                        break;
                    case 0:
                        closeAllHandlers();
                        closeConnection();
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Illegal input");
                }
            }
        }
    }
}
