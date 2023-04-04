public class Seat extends Product{
    private String commodityOfStructure;
    private String commodityOfSeat;

    public Seat(String name, double price, Seller seller, int quantity, String category, String commodityOfStructure, String commodityOfSeat) {
        super(name, price, seller, quantity, category);
        this.commodityOfStructure = commodityOfStructure;
        this.commodityOfSeat = commodityOfSeat;
    }

    public String getCommodityOfStructure() {
        return commodityOfStructure;
    }

    public void setCommodityOfStructure(String commodityOfStructure) {
        this.commodityOfStructure = commodityOfStructure;
    }

    public String getCommodityOfSeat() {
        return commodityOfSeat;
    }

    public void setCommodityOfSeat(String commodityOfSeat) {
        this.commodityOfSeat = commodityOfSeat;
    }

    @Override
    public String toString() {
        return super.toString() +
                " commodity of structure: " + commodityOfStructure + "\n" +
                " commodity of seat: " + commodityOfSeat;
    }
}

