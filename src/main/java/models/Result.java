package models;

import exceptions.AuctionException;
import services.AuctionService;
import services.CreatePlayersAndBidsService;

import java.util.List;

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

    public static void main(String[] args) {
        String path = "src/main/resources/players.txt";
        List<Bid> bids = CreatePlayersAndBidsService.getBids(path);
        List<Buyer> buyers = CreatePlayersAndBidsService.getBuyers(path);

        AuctionObject auctionObject = new AuctionObject(100, "AuctionObject");
        auctionObject.setBuyers(buyers);
        try {
            Result result = AuctionService.findWinner(auctionObject, bids);
            System.out.println(String.format("Auction buyer : %s", result.getBuyer().getName()));
            System.out.println(String.format("Auction bid price : %d", result.getPrice()));
        }catch (AuctionException e) {
            e.printStackTrace();
        }
    }

}
