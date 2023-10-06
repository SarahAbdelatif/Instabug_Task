package org.example.dataModels;

public class ProductDM {

    private String name;
    private String description;
    private double price;
    private String type;
    private String upc;
    private double shipping;
    private String manufacturer;
    private String model;
    private String url;
    private String image;

    public ProductDM(String name, String description, double price, String type, String upc, double shipping, String manufacturer, String model, String url, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.upc = upc;
        this.shipping = shipping;
        this.manufacturer = manufacturer;
        this.model = model;
        this.url = url;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
