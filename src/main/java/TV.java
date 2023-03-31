public class TV extends Product {
    private double inches;
    private int pixels;

    public TV(String name, double price, Seller seller, int quantity, String category, double inches, int pixels) {
        super(name, price, seller, quantity, category);
        this.inches = inches;
        this.pixels = pixels;
    }

    public double getInches() {
        return inches;
    }

    public void setInches(double inches) {
        this.inches = inches;
    }

    public int getPixels() {
        return pixels;
    }

    public void setPixels(int pixels) {
        this.pixels = pixels;
    }
}
