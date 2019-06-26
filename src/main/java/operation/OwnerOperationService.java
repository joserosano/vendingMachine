package operation;

import coin.Coin;
import inventory.Inventory;
import product.Product;

import java.util.List;

public class OwnerOperationService {

    public void refillProducts(List<Product> products, Inventory inventory) {
        inventory.refillProducts(products);
    }

    public void refillCoins(List<Coin> coins, Inventory inventory) {
        inventory.refillCoins(coins);
    }
}
