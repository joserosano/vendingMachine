package operation;

import coin.Coin;
import operation.exception.ChangeNotAvailableException;
import operation.exception.NotEnoughCreditException;
import inventory.Inventory;
import machine.VendingMachineDelivery;
import product.Product;
import product.ProductName;
import product.ProductTypeService;

import java.util.List;

public class CustomerOperationService {

    public VendingMachineDelivery buy(ProductName productName, Inventory inventory) {
        // if customer has credit enough...
        if (inventory.getCustomerBalance() >= ProductTypeService.getPrice(productName)) {
            // ...we get the product...
            Product product = inventory.getProduct(productName);
            List<Coin> change;
            try {
                // ...try to get the change
                change = inventory.getChange(product.getPrice());
            } catch (ChangeNotAvailableException ex) {
                // if there is no change we put back the product in the machine
                returnProductToInventory(product, inventory);
                throw ex;
            }
            inventory.clearCustomerBalance();
            return new VendingMachineDelivery(product, change);
        } else {
            throw new NotEnoughCreditException();
        }
    }

    public List<Coin> refund(Inventory inventory) {
        return inventory.getRefund();
    }

    public void addCustomerCoin(Coin coin, Inventory inventory) {
        inventory.addCustomerCoin(coin);
    }

    private void returnProductToInventory(Product product, Inventory inventory) {
        inventory.addProduct(product);
    }
}
