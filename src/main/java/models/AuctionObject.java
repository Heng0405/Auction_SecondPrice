package models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class AuctionObject {

    private int reservePrice;
    private String name;
    private List<Buyer> buyers;

    public AuctionObject(int reservePrice, String name) {
        this.reservePrice = reservePrice;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(int reservePrice) {
        this.reservePrice = reservePrice;
    }

    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof AuctionObject)) {
            return false;
        }
        AuctionObject that = (AuctionObject) o;
        return new EqualsBuilder()
                .append(this.name, that.name)
                .append(this.reservePrice, that.reservePrice)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(name)
                .append(reservePrice)
                .toHashCode();
    }
}
