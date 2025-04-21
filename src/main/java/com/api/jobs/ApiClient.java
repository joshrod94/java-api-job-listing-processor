package com.api.jobs;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class ApiClient {
    public String getJobListingsJson(String apiUrl) {
        //New HTTPClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //Create a GET request
        HttpGet request = new HttpGet(apiUrl);
        //Execute the request
        try (var response = httpClient.execute(request)) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
