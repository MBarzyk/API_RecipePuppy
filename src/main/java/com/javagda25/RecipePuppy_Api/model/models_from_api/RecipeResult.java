package com.javagda25.RecipePuppy_Api.model.models_from_api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class RecipeResult {
    private String title;
    private String href;
    private String ingredients;
    private String thumbnail;
}
