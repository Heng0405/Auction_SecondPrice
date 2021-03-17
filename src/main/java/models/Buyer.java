package models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Buyer {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Buyer(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof Buyer)) {
            return false;
        }
        Buyer that = (Buyer) o;
        return new EqualsBuilder()
                .append(this.name, that.name)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(name).toHashCode();
    }
}
