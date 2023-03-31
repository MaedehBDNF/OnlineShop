public class Puzzle extends Product{
    private int numOfPieces;

    public Puzzle(String name, double price, Seller seller, int quantity, String category, int numOfPieces) {
        super(name, price, seller, quantity, category);
        this.numOfPieces = numOfPieces;
    }

    public int getNumOfPieces() {
        return numOfPieces;
    }

    public void setNumOfPieces(int numOfPieces) {
        this.numOfPieces = numOfPieces;
    }
}
