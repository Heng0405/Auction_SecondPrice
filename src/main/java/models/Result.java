package models;

public class Result {

    private AuctionObject auctionObject;
    private Buyer buyer;
    private int price;

    public Result(AuctionObject auctionObject, Buyer buyer, int price) {
        this.auctionObject = auctionObject;
        this.buyer = buyer;
        this.price = price;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public int getPrice() {
        return price;
    }

    public AuctionObject getAuctionObject() {
        return auctionObject;
    }

}
