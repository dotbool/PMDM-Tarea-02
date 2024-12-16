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
        //Supongo que con el data binding, que no se como funciona aún, se podrá hacer desde el
        //xml si cada pojo card tuviera una variable background por ejemplo pero no voy a mezclar
        //un  pojo con un estilo
//        int stilo = 0;

//        Field[] drawables = R.drawable.class.getDeclaredFields();
//        List<Integer> list = new ArrayList<>();
//        for(Field f: drawables){
//           if(f.getName().startsWith("gradiente")){
//               stilo = itemView.getContext().getResources().getIdentifier(f.getName(), "drawable", itemView.getContext().getPackageName());
//               if(!list.contains(stilo)){
//                   list.add(stilo);
//               }
//
//                stilo = list.get(new Random().nextInt(list.size()));
//           }
//        }

//        binding.idLayout.setBackground(ContextCompat.getDrawable(itemView.getContext(), stilo));

        binding.executePendingBindings();
    }

    private final CardBinding binding;
}
