package com.nik.leafexperiments.controller;

import com.nik.leafexperiments.entity.Question;
import com.nik.leafexperiments.stackexchange.StackExchangeLookupService;
import com.nik.leafexperiments.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class QuestionController {
    @Autowired
    public QuestionService questionService;

    @GetMapping("/q")
    public String getQuestions(Model model, @RequestParam String str,
                               @RequestParam String fromDate, @RequestParam String toDate) {
        ArrayList<Question> questions = new ArrayList<>();
        StackExchangeLookupService stackExchangeLookupService = new StackExchangeLookupService(questions, str, fromDate, toDate);
        questionService.saveAllQuestions(questions);
        model.addAttribute("questions", questions);
        return "table";
    }


}
