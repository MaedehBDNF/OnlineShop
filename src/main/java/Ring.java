public class Ring extends Product{
    private double size;
    private String commodity;

    public Ring(String name, double price, Seller seller, int quantity, String category, double size, String commodity) {
        super(name, price, seller, quantity, category);
        this.size = size;
        this.commodity = commodity;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    @Override
    public String toString() {
        return super.toString() +
                " size= " + size +
                " commodity: " + commodity ;
    }
}
