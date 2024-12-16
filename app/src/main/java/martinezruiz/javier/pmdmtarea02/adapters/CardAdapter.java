package martinezruiz.javier.pmdmtarea02.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import martinezruiz.javier.pmdmtarea02.MainActivity;
import martinezruiz.javier.pmdmtarea02.R;
import martinezruiz.javier.pmdmtarea02.databinding.CardBinding;
import martinezruiz.javier.pmdmtarea02.models.Card;
import martinezruiz.javier.pmdmtarea02.ui.DetailsFragment;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> {

    public CardAdapter(ArrayList<Card> cards) {
        this.cards = cards;
    }


    /**
     *  Se llama cuando se necesita un nuevo ViewHolder (que es un wrapper en torno a una view, en
     *  este caso una Card. LayoutInflater instancia una vista xml, (card.xml) en un objeto de tipo
     *  View y devuelve un ViewHolder con esa view
     * @param viewGroup The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        CardBinding binding = CardBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        Card currentCard = this.cards.get(position);
        holder.bind(currentCard);
        holder.itemView.setOnClickListener(view ->{
                        System.out.println(view);
                Fragment f = DetailsFragment.newInstance(
                        currentCard.getImgId(),
                        currentCard.getName(),
                        currentCard.getDescription(),
                        currentCard.getSkill());

                    if(f!=null){

                        MainActivity ma = (MainActivity) holder.itemView.getContext();
                        ma.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contenedor, f)
                                .commit();
                    }
//                    DrawerLayout drawer = ma.binding.drawerLayout;
//                    drawer.closeDrawers();
        }
        );



//        Intent detailsActivity = new Intent(holder.itemView.getContext(), DetailsActivity.class);
//        detailsActivity.putExtra("name", currentCard.getName());
//        detailsActivity.putExtra("imgId", currentCard.getImgId());
//        detailsActivity.putExtra("imgBigId", currentCard.getImgBigId());
//        detailsActivity.putExtra("description", currentCard.getDescription());
//        detailsActivity.putExtra("skill", currentCard.getSkill());
//        holder.itemView.setOnClickListener(view ->
//                holder.itemView.getContext().startActivity(detailsActivity));

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    private final ArrayList<Card> cards;
}
