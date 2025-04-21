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
        // Filters for the job listings
        List<JobListing> allJobs = dataProcessor.extractJobs(json);
        List<JobListing> filteredByTitle = JobFilter.filterByTitle(allJobs, "developer");
        List<JobListing> filteredByCompanyName = JobFilter.filterByCompanyName(allJobs, "Google");
        List<JobListing> filteredByCategory = JobFilter.filterByCategory(allJobs, "software");
        List<JobListing> filteredByDescription = JobFilter.filterByDescription(allJobs, "remote");
        // Print the job listings
        System.out.println("Total Jobs Found: " + allJobs.size() + "\n");
        System.out.println("Total Jobs Found Matching Criteria: " + filteredByTitle.size() + "\n");
        for (JobListing job : filteredByTitle) {
            System.out.println(job);
            System.out.println("--------------------------------------------------------------------------" + "\n");
        }
        // Send the job listings to another API or service
        ApiForwarder apiForwarder = new ApiForwarder();
        apiForwarder.sendJobs(filteredByTitle);
    }
}
