package com.api.jobs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.ArrayList;
import java.util.List;

public class ApiForwarder {
    public static void sendJobs (List<JobListing> jobList) {
        // This method would send the job listings to another API or service
        ObjectMapper mapper = new ObjectMapper();
        CloseableHttpClient client = HttpClients.createDefault();
        // Initialize counters for success and failure
        int successCount = 0;
        int failureCount = 0;
        List<String> failedJobs = new ArrayList<>();

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
                var response = client.execute(postRequest);
                int statusCode = response.getCode();
                // Read and discard the body (to release resources)
                String responseBody = EntityUtils.toString(response.getEntity());
                response.close();

                if (statusCode == 200) {
                    System.out.println("Sent job: " + job.title);
                    successCount++;
                } else {
                    System.out.println("Failed to send job: " + job.title + " (Status code: " + statusCode + ")");
                    failureCount++;
                    failedJobs.add(job.title + " - Status Code Error: " + statusCode);
                }
                // Optional: delay to prevent rate limiting
                Thread.sleep(250);
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
        // Print summary of sent jobs
        System.out.println("\n============ Summary ============");
        System.out.println("Total jobs attempted: " + jobList.size());
        System.out.println("Success: " + successCount);
        System.out.println("Failed: " + failureCount);

        if (!failedJobs.isEmpty()) {
            System.out.println("\nJobs that failed to send:");
            for (String failure : failedJobs) {
                System.out.println(" - " + failure);
            }
        }
    }
}
