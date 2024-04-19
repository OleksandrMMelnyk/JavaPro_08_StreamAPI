import java.time.LocalDate;

public class Product {
    private final int id;
    private final String type;
    private int price;
    private final boolean discount;
    private final LocalDate createDate;

    private Product(int id, String type, int price, boolean discount, LocalDate createDate) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.createDate = createDate;
    }

    public static ProductBuilder builder(String type, int price) {
        return new ProductBuilder(type, price);
    }

    public static class ProductBuilder {
        private int id;
        private String type;
        private int price;
        private boolean discount;
        private LocalDate createDate;

        public ProductBuilder(String type, int price) {
            this.type = type;
            this.price = price;
        }

        public ProductBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ProductBuilder price(int price) {
            this.price = price;
            return this;
        }

        public ProductBuilder isDiscount(boolean discount) {
            this.discount = discount;
            return this;
        }

        public ProductBuilder addDate(LocalDate createDate) {
            this.createDate = createDate;
            return this;
        }

        public Product build() {
            return new Product(id, type, price, discount, createDate);
        }
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isDiscount() {
        return discount;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void printTypePrice() {
        System.out.println("type: " + type + ", price = " + price);
    }

    public void printTypePriceDiscount() {
        System.out.println("type: " + type + ", price = " + price + ", discount - " + discount);
    }

    public void printTypePriceDiscountDate() {
        System.out.println("type: " + type + ", price = " + price + ", discount - " + discount
                + ", createDate: " + createDate);
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", type:'" + type +
                "', price ='" + price +
                "', discount - '" + discount +
                "', createDate: '" + createDate +
                "'}";
    }
}
