package machine;

import api.VendingMachine;
import coin.Coin;
import inventory.Inventory;
import operation.CustomerOperationService;
import operation.OwnerOperationService;
import product.Product;

import java.util.List;

public class VendingMachineFactory {
    public static VendingMachine getDexmaVendingMachine(List<Product> products,
                                                        List<Coin> coins,
                                                        CustomerOperationService customerOperationService,
                                                        OwnerOperationService ownerOperationService) {
        final Inventory inventory = new Inventory(products, coins);
        return new VendingMachineImpl(inventory, customerOperationService, ownerOperationService);
    }
}
