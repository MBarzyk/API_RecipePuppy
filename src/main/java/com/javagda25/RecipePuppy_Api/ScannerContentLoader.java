package com.javagda25.RecipePuppy_Api;

import com.javagda25.RecipePuppy_Api.model.models_from_api.RecipeResult;
import com.javagda25.RecipePuppy_Api.model.models_used_to_create_URL.IngredientsEnum;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class ScannerContentLoader {
    private Scanner scanner = new Scanner(System.in);

    public List<IngredientsEnum> returnListOfIngridients() {
        String ingredient = null;
        List<IngredientsEnum> listOfIngredients = new ArrayList<>();
        System.out.println("Add an ingredient: " + Arrays.toString(IngredientsEnum.values()));

        do {
            try {
                ingredient = scanner.nextLine();
                listOfIngredients.add(IngredientsEnum.valueOf(ingredient.toUpperCase()));
                if (ingredient.equalsIgnoreCase("end")) {
                    System.out.println("Thanks for using this api! :)");
                } else {
                    System.out.println("Ingredient added!" + " (write END to stop)");
                }
            } catch (IllegalArgumentException iae) {
                System.err.println("Wrong ingredient, try again!");
            }
        } while (!ingredient.equalsIgnoreCase(IngredientsEnum.END.toString()));
        listOfIngredients.remove(IngredientsEnum.END);
        return listOfIngredients;
    }

    public Map<Integer, RecipeResult> getMapOfIngredients(APIRecipeBuilder recipeBuilder) {
        RecipeAPI api = new RecipeAPI();
        final Integer[] ints = new Integer[]{1};
        Map<Integer, RecipeResult> resultMap = new HashMap<>();

        try {
            resultMap = api
                    .loadURLbyInputStream(recipeBuilder.compileURL())
                    .getResults()
                    .stream()
                    .collect(Collectors.toMap(o -> (ints[0]++), r -> r));
        } catch (NullPointerException e) {
            System.err.println("There weren't any more recipies available!");
        } return resultMap;
    }

    public void letUserDecideIfHeLikesRecipies(Map<Integer, RecipeResult> recipiesMap, APIRecipeBuilder apiRecipeBuilder) {
        int currentPage = 3;
        String answer;
        do {

            System.out.println("Are you interested in any recipe?" + " (write ABORT to end)");
            answer = scanner.nextLine();
            switch (answer.toLowerCase()) {
                case "yes":
                    System.out.println("Which recipe interests you?");
                    String number = scanner.nextLine();
                    Integer parsedNumber = null;
                    try {
                        parsedNumber = Integer.parseInt(number);
                    } catch (IllegalArgumentException iae) {
                        System.err.println("Please input a number!");
                    }
                    if (parsedNumber > 10 || parsedNumber < 1) {
                        System.out.println("Wrong number! Try again");
                    } else {
                        System.out.println("The link to the recipe: " + recipiesMap.get(parsedNumber).getHref());
                        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                            try {
                                Desktop.getDesktop().browse(new URI(recipiesMap.get(parsedNumber).getHref()));
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                case "no":
                    currentPage++;
                    if (currentPage >= 10) {
                        System.err.println("You are too picky! Screw it guys, I'm going home!!!");
                        System.exit(0);
                    } else {
                        apiRecipeBuilder.getNewPage(currentPage);
                        System.out.println("URL is: " + apiRecipeBuilder.compileURL());
                        recipiesMap = getMapOfIngredients(apiRecipeBuilder);
                        recipiesMap.forEach((key, value) -> System.out.println(key + ". " + value.getTitle().trim()));
                    }
                    break;
                default:
                    if (answer.equalsIgnoreCase("abort")) {
                        System.out.println("Bye bye");
                    } else {
                        System.out.println("Wrong choice, try again!");
                    }
                    break;
            }
        } while (!answer.equalsIgnoreCase("abort"));
    }
}
