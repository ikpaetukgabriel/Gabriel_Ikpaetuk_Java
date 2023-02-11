package com.company.summativeprojectone.controller;

import com.company.summativeprojectone.models.Definition;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class DefinitionController {
    private static List<Definition> definitionList = new ArrayList<>(Arrays.asList(
                new Definition(0, "Apple",
                        "The round fruit of a tree of the rose family, which typically has thin red or green skin and crisp flesh. Many varieties have been developed as dessert or cooking fruit or for making cider."),
                new Definition(1, "Orange",
                                       "A round juicy citrus fruit with a tough bright reddish-yellow rind."),
                new Definition(2, "Banana",
                                       "A long curved fruit which grows in clusters and has soft pulpy flesh and yellow skin when ripe."),
                new Definition(3, "Nectarine",
                                       "A peach of a variety with smooth, thin, brightly colored skin and rich firm flesh."),
                new Definition(4, "Blueberry",
                                       "The small sweet blue-black edible berry of the blueberry plant."),
                new Definition(5, "Mango",
                                       "A fleshy, oval, yellowish-red tropical fruit that is eaten ripe or used green for pickles or chutneys."),
                new Definition(6, "Tangerine",
                                       "A small citrus fruit with a loose skin, especially one of a variety with deep orange-red skin."),
                new Definition(7, "Kiwi",
                                       "A fruit with a thin hairy skin, green flesh, and black seeds."),
                new Definition(8, "Pineapple",
                                       "a large juicy tropical fruit consisting of aromatic edible yellow flesh surrounded by a tough segmented skin and topped with a tuft of stiff leaves."),
                new Definition(9, "Strawberry",
                                       "A sweet soft red fruit with a seed-studded surface.")
    ));
    @RequestMapping(value = "/word", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Definition getWord() {
        Random rand = new Random();
        return definitionList.get(rand.nextInt(definitionList.size()));
    }
}
