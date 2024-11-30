package com.example.takaful_assessment.service_impls;

import com.example.takaful_assessment.payloads.requests.QuestionOneAnswerRequest;
import com.example.takaful_assessment.payloads.responses.NumRowListResponse;
import com.example.takaful_assessment.service_interfaces.PascalTriangleServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.example.takaful_assessment.utils.Constants.*;

@Service
public class PascalTriangleServiceImpl implements PascalTriangleServiceInterface {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public PascalTriangleServiceImpl(ObjectMapper objectMapper) {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = objectMapper;
    }

    @Override
    public void processTriangle() throws Exception {
//        String getUrl = QuestionOneUrl;
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(QuestionOneUrl))
                .header(xApiKeyHeader, xApiKeyValueHeader)
                .GET().build();

        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        if (getResponse.statusCode() != 200) {
            throw new Exception("Error: Failed to fetch data: " + getResponse.body());
        }

        // Parse response into QuestionOneResponse object
        NumRowListResponse response = objectMapper.readValue(getResponse.body(), NumRowListResponse.class);

        // Generate Pascal's Triangle
        List<List<Integer>> pascalsTriangle = generatePascalsTriangle(response.getNumRowList());

        QuestionOneAnswerRequest request = new QuestionOneAnswerRequest(
                response.getId(),
                pascalsTriangle
        );

        String payloadJsonMapper = objectMapper.writeValueAsString(request);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI(QuestionOneUrl))
                .header(xApiKeyHeader, xApiKeyValueHeader)
                .POST(HttpRequest.BodyPublishers.ofString(payloadJsonMapper)).build();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        if (postResponse.statusCode() != 200) {
            throw new Exception("Error: Failed to fetch data: " + postResponse.body());
        }

        System.out.println("Response from POST: " + postResponse.body());
    }

    private List<List<Integer>> generatePascalsTriangle(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);

            for (int j = 1; j < i; j++) {
                row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
            }

            if (i > 0) {
                row.add(1);
            }

            triangle.add(row);
        }

        return triangle;
    }
}
