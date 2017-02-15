import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ConnectionHandler connectionHandler = null;
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Kund> customerList = new ArrayList();
    private static ArrayList<Auktion> auctionList = new ArrayList();
    private static ArrayList<Leverantor> supplierList = new ArrayList();

    public void printMenu(){
        System.out.println("1.\tList customers");
        System.out.println("2.\tList suppliers");
        System.out.println("3.\tExit");
    }

    public void init() throws Exception{
        connectionHandler = new ConnectionHandler();
        connectionHandler.startConnection();
        customerList = connectionHandler.getCustomers();
        supplierList = connectionHandler.getSupplierList();
    }

    public static void main(String[] args) throws Exception{
        Main main = new Main();
        main.init();

        int choice = 0;
        boolean go = true;
        main.printMenu();

        while (go) {
            System.out.print("Menu choice: ");System.out.flush();
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    for (Kund k : customerList) {
                        System.out.println(k.toString());
                    }
                    break;

                case 2:
                    for (Leverantor l : supplierList) {
                        System.out.println(l.toString());
                    }
                    break;
                case 3:
                    System.out.println("Exiting program");
                    go = false;
                    break;
                default:
                    System.out.println("Incorrect menu choice.");

            }
        }




        connectionHandler.closeConnection();
    }
}
