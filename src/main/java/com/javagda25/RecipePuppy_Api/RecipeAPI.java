package com.javagda25.RecipePuppy_Api;

import com.google.gson.Gson;
import com.javagda25.RecipePuppy_Api.model.models_from_api.RecipeResult;
import com.javagda25.RecipePuppy_Api.model.models_from_api.RecipeTitle;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class RecipeAPI {
    private final static Gson GSON = new Gson();

    public RecipeTitle loadURLbyInputStream (String requestURL) {
        RecipeTitle recipeTitle = null;

        try {
            URL url = new URL(requestURL);

            recipeTitle = GSON.fromJson(new InputStreamReader(url.openStream()), RecipeTitle.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recipeTitle;
    }
}
