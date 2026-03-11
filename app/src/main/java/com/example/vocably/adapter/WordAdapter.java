package com.example.vocably.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocably.R;
import com.example.vocably.model.Word;

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

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView txtWord, txtDescription;

        public WordViewHolder(View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWord);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }
    }
}
