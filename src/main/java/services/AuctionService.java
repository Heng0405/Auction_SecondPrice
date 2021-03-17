package services;

import exceptions.AuctionException;
import models.AuctionObject;
import models.Bid;
import models.Buyer;
import models.Result;

import java.util.List;

public class AuctionService {

    public static Result findWinner(AuctionObject auctionObject, List<Bid> bids) throws AuctionException {
        List<Buyer> buyers = auctionObject.getBuyers();
        /**
         * Check if there are more than one buyer
         */
        if(buyers.size() < 2) {
            throw new AuctionException("There is no enough buyer");
        }
        /**
         * Find the highest price
         */
        int highestPrice = bids.stream()
                               .filter(e -> e.getPrice() > auctionObject.getReservePrice())
                               .mapToInt(Bid::getPrice)
                               .max()
                               .orElseThrow(() -> new AuctionException("Price has to be higher than " + auctionObject.getReservePrice()));
        /**
         * Check if there are more than one buyers who has bid with the highestPrice
         */
        long winnerCount = bids.stream()
                               .filter(e -> e.getPrice() == highestPrice)
                               .count();
        if(winnerCount > 1) {
            throw new AuctionException("There are more than one winner");
        }
        /**
         * Find the winner
         */
        Buyer winner = bids.stream()
                           .filter(e -> e.getPrice() == highestPrice)
                           .map(Bid::getBuyer)
                           .findAny()
                           .get();
        /**
         * Find the winning price
         */
        int winningPrice = bids.stream()
            .filter(e -> !e.getBuyer().equals(winner))
            .mapToInt(Bid::getPrice)
            .max()
            .getAsInt();

        return new Result(auctionObject, winner, winningPrice);
    }

}
