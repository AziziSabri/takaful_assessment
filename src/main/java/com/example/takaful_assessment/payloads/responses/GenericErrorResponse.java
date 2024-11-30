package com.example.takaful_assessment.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericErrorResponse {
    private Boolean isError;
    private String message;
}
