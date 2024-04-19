
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(List.of(
                Product.builder("Book", 280)
                        .id(1).isDiscount(true)
                        .addDate(LocalDate.of(2023, 12, 30)).build(),
                Product.builder("Box", 200)
                        .id(2).isDiscount(false)
                        .addDate(LocalDate.of(2022, 11, 20)).build(),
                Product.builder("Box", 100)
                        .id(3).isDiscount(true)
                        .addDate(LocalDate.of(2021, 10, 10)).build(),
                Product.builder("Glass", 500)
                        .id(4).isDiscount(true)
                        .addDate(LocalDate.of(2020, 9, 5)).build(),
                Product.builder("Book", 30)
                        .id(5).isDiscount(false)
                        .addDate(LocalDate.of(2024, 2, 8)).build(),
                Product.builder("Book", 60)
                        .id(6).isDiscount(false)
                        .addDate(LocalDate.of(2024, 3, 15)).build(),
                Product.builder("Book", 180)
                        .id(7).isDiscount(true)
                        .addDate(LocalDate.of(2023, 7, 10)).build(),
                Product.builder("Glass", 135)
                        .id(8).isDiscount(true)
                        .addDate(LocalDate.of(2022, 4, 25)).build()));

        System.out.println("Books that are more expensive than 250 USD: ");
        expensiveBooks(products).forEach(Product::printTypePrice);

        System.out.println("\nBooks with discount 10%: ");
        discountForBooks(products).forEach(Product::printTypePriceDiscount);

        System.out.println("\nCheapest book: ");
        theCheapestBook(products).printTypePriceDiscount();

        System.out.println("\nLatest products: ");
        theLatestProducts(products).forEach(Product::printTypePriceDiscountDate);

        System.out.print("\nGeneral price for Books in this year: ");
        System.out.println(calculateGeneralPrice(products));

        System.out.println("\nGrouped products: ");
        groupByType(products)
                .forEach((k, v) -> {
                    System.out.println("\"" + k + "\": ");
                    v.forEach(System.out::println);
                });
    }

    public static List<Product> expensiveBooks(List<Product> products) {
        return products.stream()
                .filter(product -> product.getType().equals("Book") && product.getPrice() > 250)
                .toList();
    }

    public static List<Product> discountForBooks(List<Product> products) {
        return products.stream()
                .filter(product -> product.getType().equals("Book") && product.isDiscount())
                .peek(product -> product.setPrice((int) (product.getPrice() * 0.9)))
                .toList();
    }

    public static Product theCheapestBook(List<Product> products) {
        return products.stream()
                .filter(product -> product.getType().equals("Book"))
                .min(Comparator.comparingInt(Product::getPrice))
                .orElseThrow(() -> new NoSuchElementException("No product from the Book category was found!"));
    }

    public static List<Product> theLatestProducts(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getCreateDate).reversed())
                .limit(3)
                .toList();
    }

    public static int calculateGeneralPrice(List<Product> products) {
        return products.stream()
                .filter(product -> product.getCreateDate().getYear() == 2024)
                .filter(product -> product.getType().equals("Book") && product.getPrice() <= 75)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public static Map<String, List<Product>> groupByType(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getType));
    }
}





