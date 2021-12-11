package com.brunoshiroma.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CategoryEntity extends PanacheEntityBase {
    
    @Id
    @GeneratedValue
    private Long categoryId;

    private String name;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
