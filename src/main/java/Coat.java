public class Coat extends Product{
    private float size;
    private int numOfPockets;

    public Coat(String name, double price, Seller seller, int quantity, String category, float size, int numOfPockets) {
        super(name, price, seller, quantity, category);
        this.size = size;
        this.numOfPockets = numOfPockets;
    }

    public float getSize() {
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
                " size=" + size +
                " number of pockets=" + numOfPockets;
    }
}
