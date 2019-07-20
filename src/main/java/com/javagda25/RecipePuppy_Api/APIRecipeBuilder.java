package com.javagda25.RecipePuppy_Api;

import com.javagda25.RecipePuppy_Api.model.models_used_to_create_URL.IngredientsEnum;

import java.util.List;

public class APIRecipeBuilder {
    private static final String BASE_URL = "http://www.recipepuppy.com/api/?i={ingredients}";

    private final StringBuilder builder;

    public APIRecipeBuilder() {
        builder = new StringBuilder(BASE_URL);
    }

    public void appendIngredients (List<IngredientsEnum> ingredientsEnumList) {
        final Integer[] ints = new Integer[]{3};
        if (builder.toString().contains("{ingredients}")) {
            int pozycjaAmount = builder.indexOf("{ingredients}");
            builder.replace(pozycjaAmount, pozycjaAmount + 13, ingredientsEnumList.get(0).toString().toLowerCase());
            for (int i = 1; i < ingredientsEnumList.size(); i++) {
                builder.append("," + ingredientsEnumList.get(i).toString().toLowerCase());
            }
            builder.append("&p=" + String.valueOf(3));
        }
    }

    public void getNewPage () {
        final Integer[] ints = new Integer[]{3};
        int pozycjaAmount = builder.indexOf("&p=");
        builder.replace(pozycjaAmount, pozycjaAmount + 4, String.valueOf(ints[0]++));
    }

    public String compileURL () {
        return builder.toString();
    }
}
