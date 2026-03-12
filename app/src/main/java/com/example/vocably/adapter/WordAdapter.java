package com.example.vocably.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import com.example.vocably.R;
import com.example.vocably.db.dto.WordDto;
import com.example.vocably.model.Word;
import com.example.vocably.view.ViewWord;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<Word> words;

    private LayoutInflater layoutInflater;

    public WordAdapter(List<Word> words, LayoutInflater layoutInflater) {
        this.words = words;
        this.layoutInflater = layoutInflater;
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.word_view, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.txtWord.setText(words.get(position).getWord());
        holder.txtDescription.setText(words.get(position).getMeaning());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtWord;
        private final TextView txtDescription;
        private final MaterialCardView cardWord;

        public WordViewHolder(View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWord);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            cardWord = itemView.findViewById(R.id.cardWord);
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
