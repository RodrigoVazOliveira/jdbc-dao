package dev.rvz.models;

public class Product {
    private final Integer id;
    private final String name;
    private final String description;
    private final Integer category;

    public Product(Integer id, String name, String description, Integer category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
