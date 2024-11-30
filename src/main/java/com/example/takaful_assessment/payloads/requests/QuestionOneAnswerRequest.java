package com.example.takaful_assessment.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionOneAnswerRequest {
    private String id;
    private List<List<Integer>> answer;
}
