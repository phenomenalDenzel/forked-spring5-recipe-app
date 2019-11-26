package com.springframework.bootstrap;

import com.springframework.model.*;
import com.springframework.repositories.CategoryRepository;
import com.springframework.repositories.RecipeRepository;
import com.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipe());
        log.debug("Loading Bootstrap data");
    }

    private List<Recipe> getRecipe(){
        List<Recipe> recipes=new ArrayList<>();

        //get UOMs
        Optional<UnitOfMeasure> eachOptional=unitOfMeasureRepository.findByDescription("Each");
        if(!eachOptional.isPresent()){
            throw  new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> tableSpoonOptional=unitOfMeasureRepository.findByDescription("Table Spoon");
        if(!tableSpoonOptional.isPresent()){
            throw  new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> teaSpoonOptional=unitOfMeasureRepository.findByDescription("Tea Spoon");
        if(!teaSpoonOptional.isPresent()){
            throw  new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> cupOptional=unitOfMeasureRepository.findByDescription("Cup");
        if(!cupOptional.isPresent()){
            throw  new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> pinchOptional=unitOfMeasureRepository.findByDescription("Pinch");
        if(!pinchOptional.isPresent()){
            throw  new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> ounceOptional=unitOfMeasureRepository.findByDescription("Ounce");
        if(!ounceOptional.isPresent()){
            throw  new RuntimeException("Expected UOM not found");
        }
        //get Optionals
        UnitOfMeasure teaSpoon=teaSpoonOptional.get();
        UnitOfMeasure tableSpoon=tableSpoonOptional.get();
        UnitOfMeasure cup=cupOptional.get();
        UnitOfMeasure pinch=pinchOptional.get();
        UnitOfMeasure ounce=ounceOptional.get();
        UnitOfMeasure each=eachOptional.get();

        //get Category
        Optional<Category> americanOptional=categoryRepository.findByCategoryName("American");
        if(!americanOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> mexicanOptional=categoryRepository.findByCategoryName("Mexican");
        if(!mexicanOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        //get Optionals
        Category american=americanOptional.get();
        Category mexican=mexicanOptional.get();

        //Yummy guac
        Recipe guacRecipe=new Recipe();
        guacRecipe.setDescription("Perfect Guacamela");
        guacRecipe.setCookTime(0);
        guacRecipe.setPrepTime(10);
        guacRecipe.setServings(5);
        guacRecipe.setDifficulty(Difficulty.Easy);
        guacRecipe.setDirection("1.kskskakaklalalalalalalalallalalalallalalallalalallalalallalalalalalaallkaskask"+
                                "\n" +
                                "2. kakakakakaldkeene ejew wkw iwekekwekwekwee jjjejejwewkwkwekkkwekweelelwejweej" +
                                "\n" +
                                "3. sjkasakajkasjkasjkjaff ffhfkjee ewelweeleklf eeekefjlef elelewlewlwelleleleleewle" +
                                "skalaalalalalasl jdjajdd jskakak kkkakaskksskkskaslaslsalaslasllslslsllllsalaslsllasl" +
                                "kskksfkf dkdkdkddk kdksdkkskskskskskksks kskkskskskskksksks kskskkskskskskkskskksksksk" +
                                "\n\n" +
                                "see more at http://www.recipehooks.com");
        Notes guacNotes=new Notes();
        guacNotes.setRecipeNotes("aklasklasksakdkdjdkjdladkfnf  fjerjekeeke ekekekekekekkekekekekekekkekekekekekkekekek" +
                                    "ajjsjsjsjsd djjdjdj djdkdkdk ddkkdkkeleleelllrlfllflflflfllflfflfllflflflflflfllfl" +
                                    "skskks  skskdskffjefkdkkdkdkdkd dkdkdkddkskskskkskskskkskskskskskkskskskskkskskkskskkss" +
                                    "jdkdkskskslslsls slslsllslsls skskskksksksksks ksksksksksslslsllslsllslslsllslslsl" +
                                    "\n\n" +
                                    "dkskksksks skksksksk skkskkskkkskkkkkkkkkkskskskks");
        guacRecipe.setNotes(guacNotes);
        guacRecipe.addIngredient(new Ingredient("Ripe Avocados",new BigDecimal(2),each));
        guacRecipe.addIngredient(new Ingredient("Koshar salt",new BigDecimal(5),teaSpoon));
        guacRecipe.addIngredient(new Ingredient("Fresh lime juice or lemon juice",new BigDecimal(2),tableSpoon));
        guacRecipe.addIngredient(new Ingredient("Minced red onion or thinly sliced onion",new BigDecimal(2),tableSpoon));
        guacRecipe.addIngredient(new Ingredient("Ripe Avocados",new BigDecimal(2),each));
        guacRecipe.addIngredient(new Ingredient("Cerrano chillis,stems and seeds reved minced",new BigDecimal(2),each));
        guacRecipe.addIngredient(new Ingredient("Cillanro",new BigDecimal(2),tableSpoon));
        guacRecipe.addIngredient(new Ingredient("Freshly grated black pepper",new BigDecimal(2),ounce));
        guacRecipe.addIngredient(new Ingredient("Ripe  Tomatoe seeds and pulp removed",new BigDecimal(5),each));

        guacRecipe.getCategories().add(american);
        guacRecipe.getCategories().add(mexican);

        recipes.add(guacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe= new Recipe();
        tacosRecipe.setDescription("Spicy Grilled chicken taco");
        tacosRecipe.setCookTime(0);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setServings(5);
        tacosRecipe.setDifficulty(Difficulty.Moderate);
        tacosRecipe.setDirection("1.kskskakaklalalalalalalalallalalalallalalallalalallalalallalalalalalaallkaskask"+
                "\n" +
                "2. kakakakakaldkeene ejew wkw iwekekwekwekwee jjjejejwewkwkwekkkwekweelelwejweej" +
                "\n" +
                "3. sjkasakajkasjkasjkjaff ffhfkjee ewelweeleklf eeekefjlef elelewlewlwelleleleleewle" +
                "skalaalalalalasl jdjajdd jskakak kkkakaskksskkskaslaslsalaslasllslslsllllsalaslsllasl" +
                "kskksfkf dkdkdkddk kdksdkkskskskskskksks kskkskskskskksksks kskskkskskskskkskskksksksk" +
                "\n\n" +
                "see more at http://www.recipehooks.com");
        Notes tacosNotes=new Notes();
        tacosNotes.setRecipeNotes("aklasklasksakdkdjdkjdladkfnf  fjerjekeeke ekekekekekekkekekekekekekkekekekekekkekekek" +
                "ajjsjsjsjsd djjdjdj djdkdkdk ddkkdkkeleleelllrlfllflflflfllflfflfllflflflflflfllfl" +
                "skskks  skskdskffjefkdkkdkdkdkd dkdkdkddkskskskkskskskkskskskskskkskskskskkskskkskskkss" +
                "jdkdkskskslslsls slslsllslsls skskskksksksksks ksksksksksslslsllslsllslslsllslslsl" +
                "\n\n" +
                "dkskksksks skksksksk skkskkskkkskkkkkkkkkkskskskks");
        tacosRecipe.setNotes(tacosNotes);

        tacosRecipe.addIngredient(new Ingredient("Ancho chill powder",new BigDecimal(2),tableSpoon));
        tacosRecipe.addIngredient(new Ingredient("Dried Origano",new BigDecimal(1),teaSpoon));
        tacosRecipe.addIngredient(new Ingredient("Dried Cunin",new BigDecimal(1),teaSpoon));
        tacosRecipe.addIngredient(new Ingredient("Sugar",new BigDecimal(1),teaSpoon));
        tacosRecipe.addIngredient(new Ingredient("Salt",new BigDecimal(5),pinch));
        tacosRecipe.addIngredient(new Ingredient("Cloved of garlic chipped",new BigDecimal(1),each));
        tacosRecipe.addIngredient(new Ingredient("Sugar",new BigDecimal(1),teaSpoon));
        tacosRecipe.addIngredient(new Ingredient("Finely grated orange zestr",new BigDecimal(1),tableSpoon));
        tacosRecipe.addIngredient(new Ingredient("Freshed squeezed orange juice",new BigDecimal(5),tableSpoon));
        tacosRecipe.addIngredient(new Ingredient("Olive oil",new BigDecimal(2),tableSpoon));
        tacosRecipe.addIngredient(new Ingredient("Boneless chicken thighs",new BigDecimal(4),tableSpoon));
        tacosRecipe.addIngredient(new Ingredient("Small core titerlizer",new BigDecimal(8),each));
        tacosRecipe.addIngredient(new Ingredient("Curry tomatoes halved",new BigDecimal(3),teaSpoon));

        tacosRecipe.getCategories().add(american);
        tacosRecipe.getCategories().add(mexican);

        recipes.add(tacosRecipe);
        return recipes;
    }
}
