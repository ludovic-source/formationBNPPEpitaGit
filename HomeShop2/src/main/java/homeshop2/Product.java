package homeshop2;

public class Product {

    private String name;
    private String description;
    private Double price;

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void look() {
        System.out.println("Nom du produit: " + name + "%n prix = " + price + "%n description : " + description);
    }

    public void buy(Bill facture, Integer quantity) {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
