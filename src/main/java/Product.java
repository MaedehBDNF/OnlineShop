import java.util.ArrayList;
import java.util.UUID;

public abstract class Product {
    private UUID uuid;
    private String name;
    private double price;
    private Seller seller;
    private int quantity;
    private String category; // {ELECTRONICS= {TV,...}, CLOTHES= {Coat,...}, BOOKS= {Book}, TOOLS= {Hummer,...},
    // FURNITURE= {Seat,...}, JEWEL= {Ring,...}, KITCHEN_UTENSILS= {Pot,...}, VEHICLE= {Car,...},
    // STATIONERY= {Pen,...}, TOYS= {Puzzle,...}}
    private ArrayList<String> comments = new ArrayList<String>();

    public Product(String name, double price, Seller seller, int quantity, String category) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.quantity = quantity;
        this.category = category;
    }

    public UUID getUuid() {
        return uuid;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }
    
    public boolean equals(Product product){
        if (this.name.equalsIgnoreCase(product.getName())){
            if (this.price == product.getPrice()){
                if (this.category.equalsIgnoreCase(product.getCategory())){
                    return this.seller.equals(product.getSeller());
                } else { return false; }
            } else { return false; }
        } else { return false; }
    }

    @Override
    public String toString() {
        if (this.quantity == 0){
            return  " {name='" + name + "'\n" +
                    " price=" + price + "\n" +
                    " seller=" + seller.getCompanyName() + "\n" +
                    " category='" + category + '\'' + "\n" +
                    " comments=" + comments + "\n" +
                    "'OUT OF STOCK'";

        } else {
            return  " {name='" + name + "'\n" +
                    " price=" + price + "\n" +
                    " seller=" + seller.getCompanyName() + "\n" +
                    " quantity=" + quantity + "\n" +
                    " category='" + category + '\'' + "\n" +
                    " comments=" + comments + "\n";
        }
    }
}
