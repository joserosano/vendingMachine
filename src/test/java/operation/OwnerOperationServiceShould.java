package operation;

import inventory.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.TestUtils;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OwnerOperationServiceShould {
    @Mock
    private Inventory inventory;

    private OwnerOperationService ownerOperationService;

    @Before
    public void setup() {
        ownerOperationService = new OwnerOperationService();
    }

    @Test
    public void refill_one_of_each_drink() {
        //act
        ownerOperationService.refillProducts(TestUtils.oneOfEachDrink(), inventory);
        //assert
        verify(inventory).refillProducts(TestUtils.oneOfEachDrink());
    }

    @Test
    public void refill_one_of_each_coin() {
        //act
        ownerOperationService.refillCoins(TestUtils.oneOfEachCoin(), inventory);
        //assert
        verify(inventory).refillCoins(TestUtils.oneOfEachCoin());
    }
}
