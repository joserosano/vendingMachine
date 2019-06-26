package util;

import coin.*;
import product.Coke;
import product.Product;
import product.Sprite;
import product.Water;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static List<Coin> oneOfEachCoin() {
        List<Coin> coins = new ArrayList<>();
        coins.add(new Coin200());
        coins.add(new Coin100());
        coins.add(new Coin50());
        coins.add(new Coin20());
        coins.add(new Coin10());
        coins.add(new Coin5());
        return coins;
    }

    public static List<Product> oneOfEachDrink() {
        List<Product> products = new ArrayList<>();
        products.add(new Coke());
        products.add(new Sprite());
        products.add(new Water());
        return products;
    }

    public static List<Product> oneSpriteAndOneWater() {
        List<Product> products = new ArrayList<>();
        products.add(new Sprite());
        products.add(new Water());
        return products;
    }
}
