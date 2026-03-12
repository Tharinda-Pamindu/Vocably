package com.example.vocably.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import com.example.vocably.R;
import com.example.vocably.db.dto.WordDto;
import com.example.vocably.model.Word;
import com.example.vocably.tts.TextToSpeechManager;
import com.example.vocably.view.ViewWord;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<Word> words;
    private final LayoutInflater layoutInflater;
    private final TextToSpeechManager textToSpeechManager;

    public WordAdapter(List<Word> words, LayoutInflater layoutInflater) {
        this.words = words;
        this.layoutInflater = layoutInflater;
        Context context = layoutInflater.getContext();
        textToSpeechManager = new TextToSpeechManager(context, new TextToSpeechManager.InitListener() {
            @Override
            public void onReady() {
                // no-op
            }

            @Override
            public void onError(String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.word_view, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = words.get(position);
        holder.txtWord.setText(word.getWord());
        holder.txtDescription.setText(word.getMeaning());
        holder.btnSpeakWord.setOnClickListener(v -> {
            if (!textToSpeechManager.isReady()) {
                Toast.makeText(v.getContext(), "Text-to-speech is loading", Toast.LENGTH_SHORT).show();
                return;
            }
            textToSpeechManager.speak(word.getWord());
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtWord;
        private final TextView txtDescription;
        private final MaterialCardView cardWord;
        private final ImageButton btnSpeakWord;

        public WordViewHolder(View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWord);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            cardWord = itemView.findViewById(R.id.cardWord);
            btnSpeakWord = itemView.findViewById(R.id.btnSpeakWordItem);
            View clickableView = cardWord != null ? cardWord : itemView;
            clickableView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getBindingAdapterPosition();
            if (position == RecyclerView.NO_POSITION) return;

            Word word = words.get(position);
            Context context = view.getContext();

            Intent intent = new Intent(context, ViewWord.class);
            intent.putExtra("word_text", word.getWord());
            intent.putExtra("word_description", word.getMeaning());

            context.startActivity(intent);
        }
    }
}
