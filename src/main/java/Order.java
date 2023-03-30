import java.util.ArrayList;

public class Order extends Request{
    private ArrayList<Product> listOfProducts = new ArrayList<Product>();
    private double totalPrice;
    private User buyer;
    private ArrayList<Seller> listOFSellers = new ArrayList<Seller>();

    public Order(User buyer) {
        this.buyer = buyer;
        this.listOfProducts.addAll(buyer.getShoppingCart());
        for (Product prod : buyer.getShoppingCart()){
            this.totalPrice += prod.getPrice();
        }
        for (Product product: listOfProducts){
            this.listOFSellers.add(product.getSeller());
        }
    }

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(ArrayList<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public ArrayList<Seller> getListOFSellers() {
        return listOFSellers;
    }

    public void setListOFSellers(ArrayList<Seller> listOFSellers) {
        this.listOFSellers = listOFSellers;
    }
}
