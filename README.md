# Java API Job Listing Processor

This Java application demonstrates a complete data integration pipeline using REST APIs. It fetches job listings from a public API, parses and transforms the data, applies flexible filters, and sends the filtered results to a mock API endpoint. This project is designed to highlight JSON API interaction, data processing, and clean modular code organization using Java.

## What It Does

- Sends a GET request to a public job listing API (`https://remotive.io/api/remote-jobs`)
- Parses the JSON response using Jackson
- Transforms the data into structured `JobListing` objects
- Applies various filters:
  - All jobs
  - Jobs with a specific keyword in the title
  - Jobs by company name
  - Jobs by category
  - Jobs by description
- Sends each filtered job listing via POST to `https://httpbin.org/post`
- Prints success/failure for each POST
- Provides a summary:
  - Total jobs sent
  - Success count
  - Failure count
  - List of failed jobs with HTTP status codes

## Technologies Used

- Java 17+
- Apache HttpClient 5
- Jackson (Databind)
- Maven
- IntelliJ IDEA

## Project Structure

```
src
└── main
    └── java
        └── com.api.jobs
            ├── ApiClient.java          # GETs job data from the API
            ├── DataProcessor.java      # Parses JSON and creates JobListing objects
            ├── JobListing.java         # POJO representing a single job
            ├── JobFilter.java          # Filtering logic for job criteria
            ├── ApiForwarder.java       # POSTs job data to another service
            └── AppRunner.java          # Main class to run the process
```

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/java-api-job-listing-processor.git
   ```
2. Open the project in IntelliJ IDEA
3. Let Maven import dependencies from `pom.xml`
4. Run `AppRunner.java`

## Sample Output

```
Filtered by title (contains 'developer'):
Total: 80

Sent job: Java Developer
Sent job: Frontend Developer
Failed to send job: WordPress Developer IV (Status code: 502)

=== Summary ===
Total jobs attempted: 80
Success: 79
Failed: 1

Jobs that failed to send:
 - WordPress Developer IV - Status: 502
```

## License

This project is licensed under the [Creative Commons Attribution-NonCommercial 4.0 International License](https://creativecommons.org/licenses/by-nc/4.0/).

You are free to use, share, and adapt this project for non-commercial purposes. Commercial use is not permitted.
