public class AuthorizationRequest extends Request{
    private Seller seller;
    public AuthorizationRequest(Seller requester) {
        super();
        this.seller = requester;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "AuthorizationRequest for " + this.seller.getUsername();
    }
}
