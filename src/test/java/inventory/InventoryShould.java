package inventory;

import coin.*;
import operation.exception.ChangeNotAvailableException;
import operation.exception.ProductNotAvailableException;
import org.junit.Before;
import org.junit.Test;
import product.Coke;
import product.Product;
import product.ProductName;
import util.TestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InventoryShould {
    private Inventory inventory;

    @Before
    public void setup() {
        inventory = new Inventory(Collections.emptyList(), Collections.emptyList());
    }

    @Test
    public void add_coin_to_coin_list() {
        //arrange
        Coin coin = new Coin100();
        //act
        inventory.addCustomerCoin(coin);
        //assert
        assertEquals(inventory.getCustomerBalance(), 100);
    }

    @Test
    public void return_customer_balance() {
        //arrange
        inventory.addCustomerCoin(new Coin100());
        //act
        final int customerBalance = inventory.getCustomerBalance();
        //assert
        assertEquals(customerBalance, 100);
    }

    @Test
    public void get_an_available_product() {
        //arrange
        inventory = new Inventory(aProductList(), Collections.emptyList());
        //act
        Product product = inventory.getProduct(ProductName.COKE);
        //assert
        assertEquals(product, aCoke());
    }

    @Test(expected = ProductNotAvailableException.class)
    public void throw_exception_when_get_an_unavailable_product() {
        //act
        inventory.getProduct(ProductName.SPRITE);
    }

    @Test
    public void return_correct_change() {
        //arrange
        inventory = new Inventory(Collections.emptyList(), expectedChange());
        //act
        inventory.addCustomerCoin(new Coin200());
        List<Coin> change = inventory.getChange(140);
        //assert
        assertEquals(expectedChange(), change);
    }

    @Test(expected = ChangeNotAvailableException.class)
    public void throw_exception_when_no_available_change() {
        //act
        inventory.addCustomerCoin(new Coin200());
        inventory.getChange(140);
    }

    @Test
    public void add_products_to_inventory_when_refill() {
        //arrange

        //act
        inventory.refillProducts(TestUtils.oneOfEachDrink());

        //assert

        /*
        En el email he explicado que haría un último refactor y sería añadir una capa de Repositorio con 2 objetivos:
        hacer esto testeable, y por otro lado acercarlo más a una implementación que tendríamos en un caso real. Estamos
        almacenando todo en memoria dentro de una variable privada, por lo que o bien uso PowerMockito o similar(ugh)
        o más bien si fuese un caso en la vida real, seguramente estaríamos guardando en un repositorio. En tal caso
        nuestro assert podría ser una serie de verify() que comprueben que efectivamente hemos hecho un
        repository.save(product) para cada uno de esos products que entran como input. Idem con el refill de coins.
         */
    }

    @Test
    public void add_coins_to_inventory_when_refill() {
        //arrange

        //act
        inventory.refillCoins(TestUtils.oneOfEachCoin());

        //assert

        /*
        Similar al comentario de arriba.
         */
    }

    private List<Coin> expectedChange() {
        return new ArrayList<Coin>() {{
           add(new Coin50());
           add(new Coin5());
           add(new Coin5());
        }};
    }

    private Product aCoke() {
        return new Coke();
    }

    private List<Product> aProductList() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Coke());
        return products;
    }
}
