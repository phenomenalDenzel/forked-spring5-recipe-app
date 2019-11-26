package com.springframework.services;

import com.springframework.model.Recipe;
import com.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImpTest {
    private RecipeServiceImp recipeServiceImp;
    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeServiceImp=new RecipeServiceImp(recipeRepository);
    }

    @Test
    void getRecipes() {
        Set<Recipe> recipeData=new HashSet<>();
        recipeData.add(new Recipe());
        when(recipeServiceImp.getRecipes()).thenReturn(recipeData);
        Set<Recipe> recipes=recipeServiceImp.getRecipes();
        assertEquals(recipes.size(),1);
        verify(recipeRepository, times(1)).findAll();
    }
}