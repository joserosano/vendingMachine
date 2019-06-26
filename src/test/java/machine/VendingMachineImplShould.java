package machine;

import coin.Coin;
import coin.Coin100;
import coin.Coin200;
import coin.Coin50;
import inventory.Inventory;
import machine.VendingMachineImpl;
import operation.CustomerOperationService;
import operation.OwnerOperationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import product.ProductName;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineImplShould {
    @Mock
    private Inventory inventory;
    @Mock
    private CustomerOperationService customerOperationService;
    @Mock
    private OwnerOperationService ownerOperationService;

    private VendingMachineImpl vendingMachineImpl;

    @Before
    public void setup() {
        vendingMachineImpl = new VendingMachineImpl(inventory, customerOperationService, ownerOperationService);
    }

    @Test
    public void add_coin_to_inventory() {
        //arrange
        Coin coin = new Coin50();
        //act
        vendingMachineImpl.insertCoin(coin);
        //assert
        verify(customerOperationService).addCustomerCoin(coin, inventory);
    }

    @Test
    public void buy_product() {
        //arrange
        vendingMachineImpl.insertCoin(new Coin100());
        //act
        vendingMachineImpl.buy(ProductName.COKE);
        //assert
        verify(customerOperationService).buy(ProductName.COKE, inventory);
    }

    @Test
    public void refund_customer_money() {
        //act
        vendingMachineImpl.refund();
        //assert
        verify(customerOperationService).refund(inventory);
    }

    @Test
    public void refill_products() {
        //act
        vendingMachineImpl.refillProducts(TestUtils.oneSpriteAndOneWater());
        //assert
        verify(ownerOperationService).refillProducts(TestUtils.oneSpriteAndOneWater(), inventory);
    }

    @Test
    public void refill_coins() {
        //act
        vendingMachineImpl.refillCoins(two200Coins());
        //assert
        verify(ownerOperationService).refillCoins(two200Coins(), inventory);
    }

    private List<Coin> two200Coins() {
        return new ArrayList<Coin>() {{
            add(new Coin200());
            add(new Coin200());
        }};
    }

}
