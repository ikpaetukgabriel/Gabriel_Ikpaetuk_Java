package com.company.summativeprojectone.controller;

import com.company.summativeprojectone.models.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class AnswerController {
    private static List<String> answerList = new ArrayList<>(Arrays.asList(
            "No",
            "It is certain",
            "Yes",
            "I'll think about",
            "Ask me later",
            "For sure"));

    @RequestMapping(value = "/magic", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Answer askQuestion(@RequestBody Answer answer) {
        Random rand = new Random();
        answer.setAnswer(answerList.get(rand.nextInt(answerList.size())));
        return answer;
    }
}
