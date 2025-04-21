package com.api.jobs;

import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        System.out.println("Starting Job API Processor...\n");
        // Create an instance of ApiClient and fetch job listings
        ApiClient apiClient = new ApiClient();
        String json = apiClient.getJobListingsJson("https://remotive.io/api/remote-jobs");
        // Process the JSON data
        DataProcessor dataProcessor = new DataProcessor();
        List<JobListing> jobs = dataProcessor.extractJobs(json);
        // Print the job listings
        System.out.println("Total Jobs Found: " + jobs.size() + "\n");
        for (JobListing job : jobs) {
            System.out.println(job);
            System.out.println("--------------------------------------------------------------------------");
        }
    }
}
