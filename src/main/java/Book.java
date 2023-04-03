public class Book extends Product{
    private String author;
    private int yearOfPublish;
    private String isbn;

    public Book(String name, double price, Seller seller, int quantity, String category, String author, int yearOfPublish, String isbn) {
        super(name, price, seller, quantity, category);
        this.author = author;
        this.yearOfPublish = yearOfPublish;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublish() {
        return yearOfPublish;
    }

    public void setYearOfPublish(int yearOfPublish) {
        this.yearOfPublish = yearOfPublish;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return super.toString() +
                " author=" + author +
                " year of publish=" + yearOfPublish +
                " ISBN: " + isbn ;
    }
}
