package com.cthiebaud.javafx;

import javafx.scene.image.Image;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.io.InputStream;

public class ImageReaderHttp {

    public static Image get(String filename) {
        // String imageUrl = "https://cthiebaud.com/moi/" + filename;
        String imageUrl = "https://picsum.photos/200/300";
        Image image = null;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Create HTTP GET request
            HttpGet request = new HttpGet(imageUrl);

            // Execute the request
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // Check if the response is successful (status code 200)
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        try (InputStream inputStream = entity.getContent()) {
                            return new Image(inputStream);
                        }
                    }
                } else {
                    // Handle unsuccessful response (e.g., log error, display message)
                    System.out.println("Failed to fetch image: " + response.getStatusLine().getReasonPhrase());
                }
            }
        } catch (IOException e) {
            // Handle IO exception (e.g., log error, display message)
            e.printStackTrace();
        }

        return image;
    }
}
