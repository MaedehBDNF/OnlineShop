public class Car extends Product{
    private String manufacture;
    private String gear;
    private String color;
    private int numOfSeats;
    private int numOfDoors;

    public Car(String name, double price, Seller seller, int quantity, String category, String manufacture, String gear, String color, int numOfSeats, int numOfDoors) {
        super(name, price, seller, quantity, category);
        this.manufacture = manufacture;
        this.gear = gear;
        this.color = color;
        this.numOfSeats = numOfSeats;
        this.numOfDoors = numOfDoors;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    @Override
    public String toString() {
        return super.toString() +
                " manufacture: " + manufacture +
                " gear: " + gear +
                " color: " + color +
                " number of seats=" + numOfSeats +
                " number of doors=" + numOfDoors;
    }
}
