import java.util.ArrayList;

public abstract class Product {
    private String name;
    private double price;
    private Seller seller;
    private int quantity;
    private String category; // {ELECTRONICS= {TV,...}, CLOTHES= {Coat,...}, BOOKS= {Book}, TOOLS= {Hummer,...},
    // FURNITURE= {Seat,...}, JEWEL= {Ring,...}, KITCHEN_UTENSILS= {Pot,...}, VEHICLE= {Car,...},
    // STATIONERY= {Pen,...}, TOYS= {Puzzle,...}}
    private ArrayList<String> comments = new ArrayList<String>();

    public Product(String name, double price, Seller seller, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", seller=" + seller.getCompanyName() +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", comments=" + comments +
                '}';
    }
}
