package machine;

import api.VendingMachine;
import coin.Coin;
import inventory.Inventory;
import operation.CustomerOperationService;
import operation.OwnerOperationService;
import product.Product;
import product.ProductName;

import java.util.List;

public class VendingMachineImpl implements VendingMachine {
    private final Inventory inventory;
    private final CustomerOperationService customerOperationService;
    private final OwnerOperationService ownerOperationService;

    public VendingMachineImpl(Inventory inventory,
                              CustomerOperationService customerOperationService,
                              OwnerOperationService ownerOperationService) {
        this.inventory = inventory;
        this.customerOperationService = customerOperationService;
        this.ownerOperationService = ownerOperationService;
    }

    @Override
    public void insertCoin(Coin coin) {
        customerOperationService.addCustomerCoin(coin, inventory);
    }

    @Override
    public VendingMachineDelivery buy(ProductName name) {
        return customerOperationService.buy(name, inventory);
    }

    @Override
    public List<Coin> refund() {
        return customerOperationService.refund(inventory);
    }

    @Override
    public void refillProducts(List<Product> products) {
        ownerOperationService.refillProducts(products, inventory);
    }

    @Override
    public void refillCoins(List<Coin> coins) {
        ownerOperationService.refillCoins(coins, inventory);
    }
}
