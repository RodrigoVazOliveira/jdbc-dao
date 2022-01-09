package dev.rvz.models;

import java.util.List;
import java.util.Objects;

public class Category {
    private final Integer id;
    private final String name;
    private final List<Product> products;

    public Category(Integer id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class builder {
        private Integer id;
        private String name;
        private List<Product> products;

        public builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public builder setName(String name) {
            this.name = name;
            return this;
        }

        public builder setProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public Category build() {
            return new Category(this.id, this.name, this.products);
        }
    }
}
