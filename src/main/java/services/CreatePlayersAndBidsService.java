package services;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import models.Bid;
import models.Buyer;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreatePlayersAndBidsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePlayersAndBidsService.class);
    private final String path;

    public CreatePlayersAndBidsService(String path) {
        this.path = path;
    }

    public static String[] readFile(String path) {
        File file = new File(path);
        List<String> lines = new ArrayList<>();
        try(FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr))
        {
            while (true) {
                String line = br.readLine();
                if (StringUtils.isBlank(line)){
                    break;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines.stream().toArray(String[]::new);
    }

    public static List<Bid> getBids(String path) {
        String[] lines = readFile(path);
        List<Bid> bids = new ArrayList<>();
        List<String[]> buyerAndBids = Arrays.stream(lines)
                                            .filter(e -> e.split(":").length == 2)
                                            .map(e -> e.split(":")).collect(Collectors.toList());
        for (String[] buyerAndBid : buyerAndBids) {
            Buyer buyer = new Buyer(buyerAndBid[0]);
            String[] prices = buyerAndBid[1].split(",");
            for (String p : prices) {
                int price = Integer.parseInt(p.trim());
                Bid bid = new Bid(price, buyer);
                bids.add(bid);
            }
        }
        return bids;
    }

    public static List<Buyer> getBuyers(String path) {
        String[] lines = readFile(path);
        List<String> names = Arrays.stream(lines)
                                   .map(e -> e.charAt(0))
                                   .map(String::valueOf)
                                   .collect(Collectors.toList());
        return names.stream().map(Buyer::new).collect(Collectors.toList());
    }

}
