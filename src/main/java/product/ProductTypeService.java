package product;

import java.util.EnumMap;

public class ProductTypeService {
    private static final EnumMap<ProductName, Integer> types = new EnumMap<ProductName, Integer>(ProductName.class) {{
        put(ProductName.COKE, 150);
        put(ProductName.SPRITE, 140);
        put(ProductName.WATER, 150);
    }};

    public static int getPrice(ProductName name) {
        return types.get(name);
    }
}
