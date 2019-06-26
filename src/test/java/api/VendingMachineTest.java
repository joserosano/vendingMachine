package api;

import api.VendingMachine;
import coin.Coin;
import coin.Coin100;
import coin.Coin200;
import coin.Coin50;
import inventory.Inventory;
import machine.VendingMachineFactory;
import operation.CustomerOperationService;
import operation.OwnerOperationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import product.*;
import util.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VendingMachineTest {
    @Mock
    private CustomerOperationService customerOperationService;
    @Mock
    private OwnerOperationService ownerOperationService;

    private VendingMachine vendingMachine;

    @Before
    public void setup() {
        vendingMachine = VendingMachineFactory.getDexmaVendingMachine(aProductList(),
                changeOfCokeBoughtWith200cts(),
                customerOperationService,
                ownerOperationService);
    }

    @Test
    public void insert_coin() {
        //act
        vendingMachine.insertCoin(a200Coin());
        //assert
        verify(customerOperationService).addCustomerCoin(eq(a200Coin()), any(Inventory.class));
    }

    @Test
    public void buy_coke() {
        //arrange
        vendingMachine.insertCoin(new Coin100());
        vendingMachine.insertCoin(new Coin50());
        //act
        vendingMachine.buy(ProductName.COKE);

        //assert
        verify(customerOperationService).buy(eq(ProductName.COKE), any(Inventory.class));
    }

    @Test
    public void refund_money() {
        //arrange
        vendingMachine.insertCoin(new Coin200());
        //act
        vendingMachine.refund();

        //assert
        verify(customerOperationService).refund(any(Inventory.class));
    }

    @Test
    public void refill_products() {
        //act
        vendingMachine.refillProducts(TestUtils.oneSpriteAndOneWater());
        //assert
        verify(ownerOperationService).refillProducts(eq(TestUtils.oneSpriteAndOneWater()), any(Inventory.class));
    }

    @Test
    public void refill_coins() {
        //act
        vendingMachine.refillCoins(two200Coins());
        //assert
        verify(ownerOperationService).refillCoins(eq(two200Coins()), any(Inventory.class));
    }

    private Coin a200Coin() {
        return new Coin200();
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

    private List<Coin> two200Coins() {
        return new ArrayList<Coin>() {{
            add(new Coin200());
            add(new Coin200());
        }};
    }
}
