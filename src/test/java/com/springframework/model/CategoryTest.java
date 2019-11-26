package com.springframework.model;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    Category category;

    @BeforeEach
    public void setUp(){
        category=new Category();
    }
    @Test
    void getId() {
        Long idValue=4l;
        category.setId(idValue);
        assertEquals(idValue,category.getId());
    }

    @Test
    void getCategoryName() {
    }

    @Test
    void getRecipes() {
    }
}