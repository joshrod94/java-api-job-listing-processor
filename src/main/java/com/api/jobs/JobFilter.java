package com.api.jobs;

import java.util.ArrayList;
import java.util.List;

public class JobFilter {
    public static List<JobListing> allJobs(List<JobListing> inputList) {
        // returns everything as a copy of the list in case the original list is modified
        return new ArrayList<>(inputList);
    }

    public static List<JobListing> filterByTitle(List<JobListing> inputList, String keyword) {
        // returns jobs where title contains keyword
        return inputList.stream()
                // Convert both title and keyword to lowercase for case-insensitive comparison
                .filter(job -> job.title.toLowerCase().contains(keyword.toLowerCase()))
                // Collect the filtered results into a new list
                .toList();
    }

    public static List<JobListing> filterByCompanyName(List<JobListing> inputList, String keyword) {
        // returns jobs where company name contains keyword
        return inputList.stream()
                // Convert both company name and keyword to lowercase for case-insensitive comparison
                .filter(job -> job.companyName.toLowerCase().contains(keyword.toLowerCase()))
                // Collect the filtered results into a new list
                .toList();
    }

    public static List<JobListing> filterByCategory(List<JobListing> inputList, String keyword) {
        // returns jobs where category contains keyword
        return inputList.stream()
                // Convert both category and keyword to lowercase for case-insensitive comparison
                .filter(job -> job.category.toLowerCase().contains(keyword.toLowerCase()))
                // Collect the filtered results into a new list
                .toList();
    }

    public static List<JobListing> filterByDescription(List<JobListing> inputList, String keyword) {
        // returns jobs where description contains keyword
        return inputList.stream()
                // Convert both description and keyword to lowercase for case-insensitive comparison
                .filter(job -> job.description.toLowerCase().contains(keyword.toLowerCase()))
                // Collect the filtered results into a new list
                .toList();
    }
}
