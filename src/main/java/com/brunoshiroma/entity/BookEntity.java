package com.brunoshiroma.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;

@Entity
public class BookEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long bookId;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<CategoryEntity> categories;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
}