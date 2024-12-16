package martinezruiz.javier.pmdmtarea02.services;


import android.content.Context;

import java.util.ArrayList;

import martinezruiz.javier.pmdmtarea02.R;
import martinezruiz.javier.pmdmtarea02.models.Card;


/**
 * Clase que sirve de proveedor de objetos card
 */
public class CardService {


    public CardService(Context context) {
        cards = new ArrayList<>();


        add(new Card(
                        R.drawable.mario,
                        context.getString(R.string.name_mario),
                        context.getString(R.string.desc_mario),
                        context.getString(R.string.skill_mario)
                )
        );
        add(new Card(
                        R.drawable.luigi,
                        context.getString(R.string.name_luigi),
                        context.getString(R.string.desc_luigi),
                        context.getString(R.string.skill_luigi)
                )
        );
        add(new Card(
                        R.drawable.peach,
                        context.getString(R.string.name_peach),
                        context.getString(R.string.desc_peach),
                        context.getString(R.string.skill_peach)
                )
        );
        add(new Card(
                        R.drawable.toad,
                        context.getString(R.string.name_toad),
                        context.getString(R.string.desc_toad),
                        context.getString(R.string.skill_toad)
                )
        );
        add(new Card(
                        R.drawable.bowser,
                        context.getString(R.string.name_bowser),
                        context.getString(R.string.desc_bowser),
                        context.getString(R.string.skill_bowser)
                )
        );
        add(new Card(
                        R.drawable.yoshi,
                        context.getString(R.string.name_yoshi),
                        context.getString(R.string.desc_yoshi),
                        context.getString(R.string.skill_yoshi)
                )
        );
        add(new Card(
                        R.drawable.donkey_kong,
                        context.getString(R.string.name_donkey_kong),
                        context.getString(R.string.desc_donkey_kong),
                        context.getString(R.string.skill_donkey_kong)
                )
        );
        add(new Card(
                        R.drawable.wario,
                        context.getString(R.string.name_wario),
                        context.getString(R.string.desc_wario),
                        context.getString(R.string.skill_wario)
                )
        );


    }

    public CardService(ArrayList<Card> cards) {
        this.cards = cards;
    }


    public void add(Card card) {
        cards.add(card);
    }

    public void remove(int id) {
        cards.remove(id);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    private ArrayList<Card> cards;

}
