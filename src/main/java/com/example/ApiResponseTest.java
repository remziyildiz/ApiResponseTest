package com.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class ApiResponseTest {
    public static void main(String[] args) throws IOException {
        int smallCount = 0;  // Küçük boyutlu yanıtların sayısı
        int largeCount = 0;  // Büyük boyutlu yanıtların sayısı

        // 100 istek atacağız
        for (int i = 0; i < 100; i++) {
            // Rastgele limit değeri seçiyoruz (1 ile 50 arasında)
            Random random = new Random();
            int randomLimit = random.nextInt(50) + 1;  // 1 ile 50 arasında rastgele limit
            String urlString = "https://publicapis.io/random-dog-animals-api?limit=" + randomLimit;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                long contentLength = connection.getContentLengthLong();

                // Yanıt boyutunu yazdırma
                System.out.println("Request " + (i+1) + " Response size: " + contentLength + " byte");

                // 1.050.000 byte'dan küçükse "küçük", büyükse "büyük" koşulu
                if (contentLength < 1050000) {
                    smallCount++;
                } else {
                    largeCount++;
                }
            }
        }

        // Sonuçları yazdırma
        System.out.println("Küçük Yanit Sayisi: " + smallCount);
        System.out.println("Büyük Yanit Sayisi: " + largeCount);
    }
}