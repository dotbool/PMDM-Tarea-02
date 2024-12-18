package martinezruiz.javier.pmdmtarea02.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import martinezruiz.javier.pmdmtarea02.R;
import martinezruiz.javier.pmdmtarea02.models.Card;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import martinezruiz.javier.pmdmtarea02.databinding.CardBinding;

public class CardViewHolder extends ViewHolder {

    public CardViewHolder(CardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        Context ctx = itemView.getContext();
    }

    public void bind (Card card){

        binding.idImg.setImageResource(card.getImgId());
        binding.idText.setText(card.getName());
        // Esto se ha hecho para darle un fondo aleatorio a las cards.
        //pero será modificado en próximas versiones, Android dice que la
        //biblioteca refect degrada mucho el rendimiento de la app

        //Busca todos los drawables cuyo nombre empiece por gradiente y se lo pone a la card
        //en cuestión como background

        int stilo = 0;

        Field[] drawables = R.drawable.class.getDeclaredFields();
        List<Integer> list = new ArrayList<>();
        for(Field f: drawables){
           if(f.getName().startsWith("gradiente")){
               stilo = itemView.getContext().getResources().getIdentifier(f.getName(),
                       "drawable", itemView.getContext().getPackageName());
               if(!list.contains(stilo)){
                   list.add(stilo);
               }

                stilo = list.get(new Random().nextInt(list.size()));
               binding.idLayout.setBackground(ContextCompat.getDrawable(itemView.getContext(), stilo));

           }
        }

        //No se que hace esto.
        binding.executePendingBindings();
    }

    private final CardBinding binding;
}
