package product;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductTypeServiceShould {

    @Test
    public void return_price_of_product() {
        //act
        final int price = ProductTypeService.getPrice(ProductName.COKE);
        //assert
        assertEquals(price, 150);
    }
}
