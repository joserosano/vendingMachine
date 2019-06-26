package operation;

import coin.*;
import operation.exception.ChangeNotAvailableException;
import operation.exception.NotEnoughCreditException;
import operation.exception.ProductNotAvailableException;
import inventory.Inventory;
import machine.VendingMachineDelivery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import product.Coke;
import product.Product;
import product.ProductName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CustomerOperationServiceShould {
    private CustomerOperationService customerOperationService;
    private Inventory inventory;

    @Before
    public void setup() {
        inventory = new Inventory(aProductList(), Collections.emptyList());
        customerOperationService = new CustomerOperationService();
    }

    @Test
    public void buy_with_exact_money() {
        //arrange
        inventory.addCustomerCoin(new Coin100());
        inventory.addCustomerCoin(new Coin50());
        //act
        VendingMachineDelivery delivery = customerOperationService.buy(ProductName.COKE, inventory);
        //assert
        assertEquals(delivery, aCokeWithNoChange());
        assertEquals(inventory.getCustomerBalance(), 0);
    }

    @Test
    public void buy_with_change_returned() {
        //arrange
        inventory = new Inventory(aProductList(), changeOfCokeBoughtWith200cts());
        inventory.addCustomerCoin(new Coin200());
        //act
        VendingMachineDelivery delivery = customerOperationService.buy(ProductName.COKE, inventory);
        //assert
        assertEquals(delivery, aCokeWith60Cts());
        assertEquals(inventory.getCustomerBalance(), 0);
    }

    @Test(expected = ProductNotAvailableException.class)
    public void throw_exception_if_buy_unavailable_product() {
        //arrange
        inventory = new Inventory(Collections.emptyList(), changeOfCokeBoughtWith200cts());
        inventory.addCustomerCoin(new Coin200());
        //act
        customerOperationService.buy(ProductName.COKE, inventory);
    }

    @Test(expected = ChangeNotAvailableException.class)
    public void throw_exception_if_buy_with_unavailable_change() {
        //arrange
        inventory = new Inventory(aProductList(), Collections.emptyList());
        inventory.addCustomerCoin(new Coin200());
        //act
        customerOperationService.buy(ProductName.COKE, inventory);
    }

    @Test(expected = NotEnoughCreditException.class)
    public void throw_exception_if_buy_with_not_enough_customer_credit() {
        //act
        customerOperationService.buy(ProductName.COKE, inventory);
    }

    @Test
    public void refund_customer_money_without_coins_introduced() {
        //act
        List<Coin> refund = customerOperationService.refund(inventory);
        assertEquals(inventory.getCustomerBalance(), 0);
        assertTrue(refund.isEmpty());
    }

    @Test
    public void refund_customer_money_with_coins_introduced() {
        //arrange
        inventory.addCustomerCoin(new Coin200());
        //act
        List<Coin> refund = customerOperationService.refund(inventory);
        assertEquals(inventory.getCustomerBalance(), 0);
        assertEquals(refund, refunded200Cts());
    }

    private List<Coin> refunded200Cts() {
        return new ArrayList<Coin>() {{
            add(new Coin200());
        }};
    }

    private VendingMachineDelivery aCokeWith60Cts() {
        return new VendingMachineDelivery(new Coke(), changeOfCokeBoughtWith200cts());
    }

    private VendingMachineDelivery aCokeWithNoChange() {
        return new VendingMachineDelivery(new Coke(), Collections.emptyList());
    }

    private List<Product> aProductList() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Coke());
        return products;
    }

    private List<Coin> changeOfCokeBoughtWith200cts() {
        return new ArrayList<Coin>() {{
            add(new Coin50());
        }};
    }
}
