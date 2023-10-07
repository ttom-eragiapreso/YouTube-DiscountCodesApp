package com.ttomcodes.YouTubeDiscountCodesApp.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;

public class YouTubeService {

    private final String APPLICATION_NAME = "YouTube API Example";
    private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private HttpTransport HTTP_TRANSPORT;
    
     
    public void createHttpTransport() {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException("Error initializing HTTP transport.", e);
        }
    }
       
    public YouTube createYouTubeService(){
        createHttpTransport();
        HttpRequestInitializer requestInitializer = request -> request.getHeaders().set("Referer", "http://localhost/");
        return new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
