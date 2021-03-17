import exceptions.AuctionException;
import models.AuctionObject;
import models.Bid;
import models.Buyer;
import models.Result;
import services.AuctionService;
import services.CreatePlayersAndBidsService;

import java.util.List;

public class Launcher {

    public static void main(String[] args) {

            String path = "src/main/resources/players.txt";
            List<Bid> bids = CreatePlayersAndBidsService.getBids(path);
            List<Buyer> buyers = CreatePlayersAndBidsService.getBuyers(path);

            AuctionObject auctionObject = new AuctionObject(100, "AuctionObject");
            auctionObject.setBuyers(buyers);
            AuctionService auctionService = new AuctionService();
            try {
                Result result = auctionService.findWinner(auctionObject, bids);
                System.out.println(String.format("Auction buyer : %s", result.getBuyer().getName()));
                System.out.println(String.format("Auction bid price : %d", result.getPrice()));
            }catch (AuctionException e) {
                e.printStackTrace();
            }
    }
}
