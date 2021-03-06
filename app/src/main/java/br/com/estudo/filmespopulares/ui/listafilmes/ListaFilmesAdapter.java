package br.com.estudo.filmespopulares.ui.listafilmes;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

import br.com.estudo.filmespopulares.R;
import br.com.estudo.filmespopulares.data.model.Filme;

public class ListaFilmesAdapter extends RecyclerView.Adapter<ListaFilmesAdapter.ListaFilmesViewHolder> {

    private List<Filme> filmes;
    private static ItemFilmeClickListener itemFilmeClickListener;


    public ListaFilmesAdapter(ItemFilmeClickListener itemFilmeClickListener) {
        this.itemFilmeClickListener = itemFilmeClickListener;
        filmes = new ArrayList<>();
    }


    @NonNull
    @Override
    public ListaFilmesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filme, parent, false);

        return new ListaFilmesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaFilmesViewHolder holder, int position) {
        holder.bind(filmes.get(position));
    }

    @Override
    public int getItemCount() {
        return (filmes != null && filmes.size() > 0) ? filmes.size() : 0;
    }

    static class ListaFilmesViewHolder extends RecyclerView.ViewHolder {

        private TextView textTituloFilme;
        private ImageView imagePosterFilme;
        private Filme filme;

        public ListaFilmesViewHolder(View itemView) {
            super(itemView);

            textTituloFilme = itemView.findViewById(R.id.text_Titulo_Filme);
            imagePosterFilme = itemView.findViewById(R.id.image_poster_filme);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemFilmeClickListener != null){
                        itemFilmeClickListener.onItemFilmeClicado(filme);
                    }
                }
            });

        }

        private void bind(Filme filme) {
            this.filme = filme;
            textTituloFilme.setText(filme.getTitulo());

            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w342" + filme.getCaminhoPoster())
                    .into(imagePosterFilme);

        }
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
        notifyDataSetChanged();
    }

    public interface ItemFilmeClickListener {

        void onItemFilmeClicado(Filme filme);
    }
}
