public class Hummer extends Product{
    private String commodity;
    private double mass;

    public Hummer(String name, double price, Seller seller, int quantity, String category, String commodity, double mass) {
        super(name, price, seller, quantity, category);
        this.commodity = commodity;
        this.mass = mass;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return super.toString() +
                " commodity: " + commodity + "\n" +
                " mass=" + mass + "}" ;
    }
}
