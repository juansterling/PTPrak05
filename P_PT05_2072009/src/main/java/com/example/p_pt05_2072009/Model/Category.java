package com.example.p_pt05_2072009.Model;

public class Category {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private int id;

    @Override
    public String toString() {
        return category;
    }

    private String category;

    public Category(int id, String category) {
        this.id = id;
        this.category = category;
    }
}
