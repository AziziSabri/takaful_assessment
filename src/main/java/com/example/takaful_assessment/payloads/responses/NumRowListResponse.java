package com.example.takaful_assessment.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NumRowListResponse {
    private String id;
    private Integer numRowList;
}
