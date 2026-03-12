package com.example.vocably.tts;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class TextToSpeechManager {
    private static final String TAG = "TextToSpeechManager";
    private TextToSpeech textToSpeech;
    private final AtomicBoolean ready = new AtomicBoolean(false);

    public interface InitListener {
        void onReady();
        void onError(String message);
    }

    public TextToSpeechManager(Context context, InitListener listener) {
        textToSpeech = new TextToSpeech(context.getApplicationContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                if (textToSpeech != null) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    boolean languageOk = result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED;
                    ready.set(languageOk);
                    if (languageOk) {
                        if (listener != null) {
                            listener.onReady();
                        }
                    } else if (listener != null) {
                        listener.onError("English TTS not supported on this device");
                    }
                }
            } else {
                ready.set(false);
                if (listener != null) {
                    listener.onError("TTS initialization failed");
                }
            }
        });
    }

    public void speak(String text) {
        if (!ready.get() || textToSpeech == null) {
            Log.w(TAG, "speak called before TTS ready or textToSpeech is null");
            return;
        }
        if (text == null || text.trim().isEmpty()) {
            Log.w(TAG, "Empty text passed to speak");
            return;
        }
        textToSpeech.stop();
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "tts_word_playback");
    }

    public boolean isReady() {
        return ready.get();
    }

    public void stop() {
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    public void shutdown() {
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
    }
}
