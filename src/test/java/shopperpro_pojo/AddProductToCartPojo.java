package shopperpro_pojo;

public class AddProductToCartPojo {

    private int productId;
    private int quantity;

    // Default constructor
    public AddProductToCartPojo() {
    }

    // Parameterized constructor
    public AddProductToCartPojo(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getter
    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setter
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}