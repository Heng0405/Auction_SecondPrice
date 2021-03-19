package models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Bid {

    private int price;
    private Buyer buyer;

    public Bid(int price, Buyer buyer) {
        this.price = price;
        this.buyer = buyer;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof Bid)) {
            return false;
        }
        Bid that = (Bid) o;
        return new EqualsBuilder()
                .append(this.price, that.price)
                .append(this.buyer, that.buyer)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                    .append(price)
                    .append(buyer)
                    .toHashCode();
    }
}
