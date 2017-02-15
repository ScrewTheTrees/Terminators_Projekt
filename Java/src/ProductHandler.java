import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Robin on 15/02/2017.
 */
public class ProductHandler {
    private Connection connection = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    private ArrayList<Product> productList = new ArrayList<>();

    public ProductHandler (Connection connection){
        this.connection = connection;
    }

    public void fillProductListFromSupplier(int productNumber){
        productList.clear();
        try {
            pstm = connection.prepareStatement("SELECT produkt.Produktnummer, produkt.namn, produkt.Provision, produkt.Beskrivning FROM produkt\n" +
                    "\tinner join produktleverantör on produktleverantör.Produktnummer = produkt.Produktnummer\n" +
                    "    inner join leverantör on leverantör.LeverantörId = produktleverantör.LeverantörId\n" +
                    "    where leverantör.LeverantörId = ?");
            pstm.setInt(1, productNumber);
            rs = pstm.executeQuery();
            while (rs.next()){
                productList.add(new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getProductsFromSupplier(){
        return productList;
    }
}
