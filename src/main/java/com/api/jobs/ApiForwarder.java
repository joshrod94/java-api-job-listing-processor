package com.api.jobs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.List;

public class ApiForwarder {
    public static void sendJobs (List<JobListing> jobList) {
        // This method would send the job listings to another API or service
        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();

        for (JobListing job : jobList) {
            // For each job listing, create a POST request to send the job data
            try {
                // Convert the job listing to a JSON string
                String json = mapper.writeValueAsString(job);
                // Create a POST request to send the job listing
                HttpPost postRequest = new HttpPost("https://httpbin.org/post");
                // Set the content type to application/json
                postRequest.setHeader("Content-Type", "application/json");
                // Set the JSON string as the entity of the POST request
                postRequest.setEntity(new StringEntity(json));
                // Execute the POST request
                client.execute(postRequest);
                System.out.println("Sent job: " + job.title);
            } catch (Exception e) {
                System.out.println("Error sending job: " + job.title);
                e.printStackTrace();
            }
        }
        // Close the client
        try {
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
