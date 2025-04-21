package com.api.jobs;

public class JobListing {
    public String title;
    public String companyName;
    public String category;
    public String url;
    public String description;

    public JobListing(String title, String companyName, String category, String description, String url) {
        this.title = title;
        this.companyName = companyName;
        this.category = category;
        this.description = description;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Tittle: " + title + "\n" +
               "Company Name: " + companyName + "\n" +
               "Category: " + category + "\n" +
               "Description: " + description + "\n" +
               "URL: " + url + "\n";
    }
}
