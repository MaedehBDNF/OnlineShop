public class Pen extends Product{
    private String manufacture;
    private float thickness;
    private String color;

    public Pen(String name, double price, Seller seller, int quantity, String category, String manufacture, float thickness, String color) {
        super(name, price, seller, quantity, category);
        this.manufacture = manufacture;
        this.thickness = thickness;
        this.color = color;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() +
                " manufacture: '" + manufacture + '\'' + "\n" +
                " thickness=" + thickness + "\n" +
                " color: " + color;
    }
}
