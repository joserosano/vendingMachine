package api;

import coin.Coin;
import machine.VendingMachineDelivery;
import operation.exception.ChangeNotAvailableException;
import operation.exception.NotEnoughCreditException;
import operation.exception.ProductNotAvailableException;
import product.Product;
import product.ProductName;

import java.util.List;

public interface VendingMachine {
    /**
     * Accepts a coin inserted by the customer and adds credit
     * @param coin
     */
    void insertCoin(Coin coin);

    /**
     * Purchase of a product with name ProductName.
     * @param name
     * @return the desired product and the change if any
     * @throws ProductNotAvailableException if product is not available
     * @throws NotEnoughCreditException if user did not introduce enough credit
     * @throws ChangeNotAvailableException if vending machine does not have the required change
     */
    VendingMachineDelivery buy(ProductName name);


    /**
     * Refunds the money introduced by customer
     */
    List<Coin> refund();

    /**
     * Refills inventory with list of products
     * @param products
     */
    void refillProducts(List<Product> products);


    /**
     * Refills inventory with list of coins
     * @param coins
     */
    void refillCoins(List<Coin> coins);
}
