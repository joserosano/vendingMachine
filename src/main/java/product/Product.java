package product;

import java.util.Objects;

public abstract class Product {
    private final ProductName name;
    private final int price;

    Product(ProductName name) {
        this.name = name;
        this.price = ProductTypeService.getPrice(name);
    }

    public ProductName getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                name == product.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
