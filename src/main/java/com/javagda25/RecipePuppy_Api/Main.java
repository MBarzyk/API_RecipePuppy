package com.javagda25.RecipePuppy_Api;

import com.javagda25.RecipePuppy_Api.model.models_from_api.RecipeResult;
import com.javagda25.RecipePuppy_Api.model.models_used_to_create_URL.IngredientsEnum;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ScannerContentLoader scannerContentLoader = new ScannerContentLoader();

        List<IngredientsEnum> ingredientsEnumList = scannerContentLoader.returnListOfIngridients();

        APIRecipeBuilder recipeBuilder = new APIRecipeBuilder();

        recipeBuilder.appendIngredients(ingredientsEnumList);
        String obtainedURL = recipeBuilder.compileURL();
        System.out.println("URL is: " + obtainedURL);

        Map<Integer, RecipeResult> recipiesMap = scannerContentLoader.getMapOfIngredients(recipeBuilder);

        recipiesMap.forEach((key, value) -> System.out.println(key + ". " + value.getTitle().trim()));

        scannerContentLoader.letUserDecideIfHeLikesRecipies(recipiesMap, recipeBuilder);
    }
}
