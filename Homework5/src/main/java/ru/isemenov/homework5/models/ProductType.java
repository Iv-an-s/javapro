package ru.isemenov.homework5.models;

public class ProductType {
    private Long id;
    private String title;

    public ProductType() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
