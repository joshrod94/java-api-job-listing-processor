package com.api.jobs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
    public List<JobListing> extractJobs (String json) {
        List<JobListing> jobList = new ArrayList<>();
        // Parse the JSON string and extract job listings
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode jobs = root.path("jobs");

            for (JsonNode job : jobs) {
                String title = job.path("title").asText();
                String companyName = job.path("company_name").asText();
                String category = job.path("category").asText();
                String description = job.path("description").asText();
                String cleanDescription = description.replaceAll("<[^>]*>", ""); // Remove HTML tags so results look cleaner
                String url = job.path("url").asText();

                JobListing jobListing = new JobListing(title, companyName, category, cleanDescription, url);
                jobList.add(jobListing);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobList;
    }
}
