package machine;

import coin.Coin;
import product.Product;

import java.util.List;
import java.util.Objects;

public class VendingMachineDelivery {
    private final Product product;
    private final List<Coin> change;

    public VendingMachineDelivery(Product product, List<Coin> change) {
        this.product = product;
        this.change = change;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendingMachineDelivery that = (VendingMachineDelivery) o;
        return product.equals(that.product) &&
                Objects.equals(change, that.change);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, change);
    }
}
