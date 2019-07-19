package com.javagda25.RecipePuppy_Api.model.models_from_api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class RecipeTitle {
    private String title;
    private Double version;
    private String href;
    private List<RecipeResult> results;
}
