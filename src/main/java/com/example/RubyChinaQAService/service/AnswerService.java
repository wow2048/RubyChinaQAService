package com.example.RubyChinaQAService.service;

import com.example.RubyChinaQAService.entity.vo.AnswerVO;

import java.util.List;

public interface AnswerService {
    List<AnswerVO> answer(String question);
}
