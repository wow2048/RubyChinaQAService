package com.example.RubyChinaQAService.controller;

import com.example.RubyChinaQAService.entity.OperationRst;
import com.example.RubyChinaQAService.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qa")
public class QAController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/ask")
    public OperationRst getAnswer(@RequestParam("question") String question){
        return OperationRst.buildSuccess(answerService.answer(question));
    }
}
