package inventory;

import coin.Coin;
import operation.exception.ChangeNotAvailableException;
import operation.exception.ProductNotAvailableException;
import product.Product;
import product.ProductName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Inventory {
    private final List<Product> products;
    private final List<Coin> coins;
    private int customerBalance;

    public Inventory(List<Product> products, List<Coin> coins) {
        this.products = products.isEmpty() ? new ArrayList<>() : products;
        this.coins = coins.isEmpty() ? new ArrayList<>() : coins;
        this.customerBalance = 0;
    }

    public void addCustomerCoin(Coin coin) {
        customerBalance += coin.getValue();
        coins.add(coin);
    }

    public int getCustomerBalance() {
        return customerBalance;
    }

    public Product getProduct(ProductName productName) {
        Optional<Product> product = products.stream()
                .filter( p -> p.getName().equals(productName))
                .findFirst();

        product.ifPresent(this::removeProduct);
        return product.orElseThrow(ProductNotAvailableException::new);
    }

    public List<Coin> getChange(int productPrice) {
        List<Coin> change = new ArrayList<>();
        int remainingChange = customerBalance - productPrice;

        while(remainingChange > 0) {
            //check 200 cts coins
            if (hasCoinWithValue(200) && remainingChange - 200 >= 0) {
                change.add(getCoinWithValue(200));
                remainingChange -= 200;
                continue;
            }
            //check 100 cts coins
            if (hasCoinWithValue(100) && remainingChange - 100 >= 0) {
                change.add(getCoinWithValue(100));
                remainingChange -= 100;
                continue;
            }
            //check 50 cts coins
            if (hasCoinWithValue(50) && remainingChange - 50 >= 0) {
                change.add(getCoinWithValue(50));
                remainingChange -= 50;
                continue;
            }
            //check 20 cts coins
            if (hasCoinWithValue(20) && remainingChange - 20 >= 0) {
                change.add(getCoinWithValue(20));
                remainingChange -= 20;
                continue;
            }
            //check 10 cts coins
            if (hasCoinWithValue(10) && remainingChange - 10 >= 0) {
                change.add(getCoinWithValue(10));
                remainingChange -= 10;
                continue;
            }
            //check 5 cts coins
            if (hasCoinWithValue(5) && remainingChange - 5 >= 0) {
                change.add(getCoinWithValue(5));
                remainingChange -= 5;
                continue;
            }
            //if we reach this, we are done with change calculation
            break;
        }

        // if there still remaining change then I put back coins on machine and throw exception
        if (remainingChange > 0) {
            change.forEach(this::addCoin);
            throw new ChangeNotAvailableException();
        }

        return change;
    }

    public void clearCustomerBalance() {
        customerBalance = 0;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public List<Coin> getRefund() {
        List<Coin> refund =  getChange(0);
        this.clearCustomerBalance();
        return refund;
    }

    public void refillProducts(List<Product> products) {
        this.products.addAll(products);
    }

    public void refillCoins(List<Coin> coins) {
        this.coins.addAll(coins);
    }

    private void addCoin(Coin coin) {
        this.coins.add(coin);
    }

    private void removeCoin(Coin coin) {
        coins.remove(coin);
    }

    private void removeProduct(Product product) {
        this.products.remove(product);
    }

    private Coin getCoinWithValue(int value) {
        Optional<Coin> coin = coins.stream()
                .filter( c -> c.getValue() == value)
                .findFirst();
        coin.ifPresent(this::removeCoin);
        return coin.orElseThrow(ChangeNotAvailableException::new);
    }

    private boolean hasCoinWithValue(int value) {
        return coins.stream()
                .anyMatch(coin -> coin.getValue() == value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return customerBalance == inventory.customerBalance &&
                Objects.equals(products, inventory.products) &&
                Objects.equals(coins, inventory.coins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, coins, customerBalance);
    }
}
