package com.example.takaful_assessment.controllers;

import com.example.takaful_assessment.service_interfaces.PascalTriangleServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PascalsTriangleController {
    private final PascalTriangleServiceInterface pascalTriangleServiceInterface;

    @GetMapping("/process-triangle")
    public String processTriangle() {
        try {
            pascalTriangleServiceInterface.processTriangle();
            return "Processing completed.";
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
