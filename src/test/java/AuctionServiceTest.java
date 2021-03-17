import exceptions.AuctionException;
import models.AuctionObject;
import models.Bid;
import models.Buyer;
import models.Result;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import services.AuctionService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class AuctionServiceTest {

    @InjectMocks
    private AuctionService auctionService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * This is the unit test to validate the algorithm
     */
    @Test
    public void auctionTest() throws AuctionException {
        Buyer A = new Buyer("A");
        Buyer B = new Buyer("B");
        Buyer C = new Buyer("C");
        Buyer D = new Buyer("D");
        Buyer E = new Buyer("E");
        List<Buyer> buyers = Stream.of(A,B,C,D,E).collect(Collectors.toList());

        Bid bid1 = new Bid(110, A);
        Bid bid2 = new Bid(130, A);
        Bid bid3 = new Bid(125, C);
        Bid bid4 = new Bid(105, D);
        Bid bid5 = new Bid(115, D);
        Bid bid6 = new Bid(132, E);
        Bid bid7 = new Bid(135, E);
        Bid bid8 = new Bid(140, E);
        List<Bid> bids = Stream.of(bid1,bid2,bid3,bid4,bid5,bid6,bid7,bid8).collect(Collectors.toList());

        AuctionObject auctionObject = new AuctionObject(100, "AuctionObject");
        auctionObject.setBuyers(buyers);

        Result result = auctionService.findWinner(auctionObject, bids);
        Buyer buyer = new Buyer("E");
        Assert.assertEquals(130, result.getPrice());
        Assert.assertEquals(result.getBuyer(), buyer);
    }

    /**
     * Test when there is no enough buyer
     * @throws AuctionException
     */
    @Test
    public void when_JustOnePlayer_then_Exception() throws AuctionException {
        Buyer A = new Buyer("A");
        List<Buyer> buyers = Stream.of(A).collect(Collectors.toList());
        Bid bid1 = new Bid(110, A);
        Bid bid2 = new Bid(130, A);
        List<Bid> bids = Stream.of(bid1,bid2).collect(Collectors.toList());
        AuctionObject auctionObject = new AuctionObject(100, "AuctionObject");
        auctionObject.setBuyers(buyers);
        thrown.expect(AuctionException.class);
        thrown.expectMessage(
                "There is no enough buyer"
        );
        auctionService.findWinner(auctionObject, bids);
    }

    /**
     * Test when there are two buyers who bid with the highest price
     * @throws AuctionException
     */
    @Test
    public void when_TwoWinners_then_Exception() throws AuctionException {
        Buyer A = new Buyer("A");
        Buyer B = new Buyer("B");
        Buyer C = new Buyer("C");
        List<Buyer> buyers = Stream.of(A,B,C).collect(Collectors.toList());
        Bid bid1 = new Bid(130, A);
        Bid bid2 = new Bid(130, B);
        Bid bid4 = new Bid(120, B);
        Bid bid3 = new Bid(125, C);
        List<Bid> bids = Stream.of(bid1,bid2,bid3,bid4).collect(Collectors.toList());
        AuctionObject auctionObject = new AuctionObject(100, "AuctionObject");
        auctionObject.setBuyers(buyers);
        thrown.expect(AuctionException.class);
        thrown.expectMessage("There are more than one winner");
        auctionService.findWinner(auctionObject,bids);
    }



}
