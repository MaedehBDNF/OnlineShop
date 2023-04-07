public class Coat extends Product{
    private double size;
    private int numOfPockets;

    public Coat(String name, double price, Seller seller, int quantity, String category, double size, int numOfPockets) {
        super(name, price, seller, quantity, category);
        this.size = size;
        this.numOfPockets = numOfPockets;
    }

    public double getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getNumOfPockets() {
        return numOfPockets;
    }

    public void setNumOfPockets(int numOfPockets) {
        this.numOfPockets = numOfPockets;
    }

    @Override
    public String toString() {
        return super.toString() +
                " size=" + size + "\n" +
                " number of pockets=" + numOfPockets;
    }
}
