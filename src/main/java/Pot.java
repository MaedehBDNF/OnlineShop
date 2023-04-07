public class Pot extends Product{
    private String commodity;
    private double volume;

    public Pot(String name, double price, Seller seller, int quantity, String category, String commodity, double volume) {
        super(name, price, seller, quantity, category);
        this.commodity = commodity;
        this.volume = volume;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return super.toString() +
                " commodity: " + commodity + "\n" +
                " volume=" + volume + "}" ;
    }
}
