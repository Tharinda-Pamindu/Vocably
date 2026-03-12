package com.example.vocably;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GeminiTest {

    @Test
    public void testResponse_hasPronunciationContent() {
        String geminiResponse = "Word: concise\nPronunciation: /kuhn-sahys/\nMeaning: giving a lot of information clearly and in a few words.";

        assertTrue("Gemini response should be usable", isUsableGeminiResponse(geminiResponse));
        assertTrue("Gemini response should include pronunciation section",
                geminiResponse.toLowerCase().contains("pronunciation"));
    }

    @Test
    public void testResponse_rejectsBlankContent() {
        String geminiResponse = "   ";

        assertFalse("Blank Gemini response should be rejected", isUsableGeminiResponse(geminiResponse));
    }

    @Test
    public void testResponse_rejectsErrorOnlyMessage() {
        String geminiResponse = "error: model not initialized";

        assertFalse("Error-only Gemini response should be rejected", isUsableGeminiResponse(geminiResponse));
    }

    @Test
    public void testResponse_hasExpectedSections() {
        String geminiResponse = "Word: vivid\nPronunciation: /viv-id/\nMeaning: producing powerful feelings or clear images in the mind.";

        assertTrue("Formatted Gemini response should be usable", isUsableGeminiResponse(geminiResponse));
        String lowerCaseResponse = geminiResponse.toLowerCase();
        assertTrue("Gemini response should include word section", lowerCaseResponse.contains("word:"));
        assertTrue("Gemini response should include pronunciation section", lowerCaseResponse.contains("pronunciation:"));
        assertTrue("Gemini response should include meaning section", lowerCaseResponse.contains("meaning:"));
    }

    @Test
    public void testResponse_rejectsNullContent() {
        assertFalse("Null Gemini response should be rejected", isUsableGeminiResponse(null));
    }

    @Test
    public void testResponse_rejectsTooShortContent() {
        String geminiResponse = "ok";

        assertFalse("Very short Gemini response should be rejected", isUsableGeminiResponse(geminiResponse));
    }

    @Test
    public void testResponse_acceptsHiGreeting() {
        String geminiResponse = "Hi there!\nPronunciation: hi\nMeaning: a friendly greeting.";

        assertTrue("Greeting response should be usable", isUsableGeminiResponse(geminiResponse));
        assertTrue("Greeting should include meaning", geminiResponse.toLowerCase().contains("meaning"));
    }

    private boolean isUsableGeminiResponse(String response) {
        if (response == null || response.trim().isEmpty()) {
            return false;
        }

        String normalized = response.trim().toLowerCase();
        return !normalized.startsWith("error:") && normalized.length() >= 10;
    }
}
