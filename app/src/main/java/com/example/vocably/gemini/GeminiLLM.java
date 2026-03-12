package com.example.vocably.gemini;


import android.content.Context;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GeminiLLM {
    private static GeminiLLM geminiLLM;

    private GenerativeModelFutures model;

    private final ExecutorService executorService;

    private GeminiLLM(Context ctx) {
        this.executorService = Executors.newSingleThreadExecutor();

        String apiKey = getApiKeyFromAssets(ctx);

        if (apiKey != null) {
            GenerativeModel gm = new GenerativeModel("gemini-2.5-flash", apiKey);
            this.model = GenerativeModelFutures.from(gm);
        }
    }

    private String getApiKeyFromAssets(Context ctx) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = ctx.getAssets().open("config.properties");
            properties.load(inputStream);
            return properties.getProperty("GEMINI_API_KEY");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized GeminiLLM getInstance(Context ctx) {
        if (geminiLLM == null) {
            geminiLLM = new GeminiLLM(ctx.getApplicationContext());
        }
        return geminiLLM;
    }

    public void generateResponse(String prompt, GeminiResponseCallback callback) {
        if (model == null) {
            callback.onError("Model not initialized. Check API Key.");
            return;
        }

        Content content = new Content.Builder().addText(prompt).build();
        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);

        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                callback.onResponse(result.getText());
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError(t.getMessage());
            }
        }, executorService);
    }

    public interface GeminiResponseCallback {
        void onResponse(String response);

        void onError(String error);
    }

}
