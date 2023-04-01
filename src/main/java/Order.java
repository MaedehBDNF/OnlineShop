import java.util.*;

public class Order extends Request{
    private Map<Product, Integer> listOfProducts = new HashMap<Product, Integer>();
    private double totalPrice;
    private User buyer;
    private Set<Seller> listOFSellers = new HashSet<Seller>();

    public Order(User buyer) {
        super();
        this.buyer = buyer;
        this.listOfProducts.putAll(buyer.getShoppingCart());
        for (Map.Entry<Product, Integer> entry : buyer.getShoppingCart().entrySet()){
            this.totalPrice += entry.getKey().getPrice();
        }
        for (Map.Entry<Product, Integer> entry : buyer.getShoppingCart().entrySet()){
            this.listOFSellers.add(entry.getKey().getSeller());
        }
    }

    public Map<Product, Integer> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(Map<Product, Integer> listOfProducts) {
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

    public Set<Seller> getListOFSellers() {
        return listOFSellers;
    }

    public void setListOFSellers(Set<Seller> listOFSellers) {
        this.listOFSellers = listOFSellers;
    }

    @Override
    public String toString() {
        return  "Order: " +
                "id=" + getId() +
                ", buyer=" + this.buyer.getUsername() +
                ", isSubmitted=" + isSubmitted() +
                ", listOfProducts=" + listOfProducts +
                ", totalPrice=" + totalPrice +
                ", listOFSellers=" + listOFSellers ;
    }
}
