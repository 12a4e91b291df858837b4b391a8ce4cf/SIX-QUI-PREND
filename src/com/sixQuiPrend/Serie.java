package com.sixQuiPrend;

import java.util.ArrayList;

public class Serie {
    private ArrayList<Card> cards;
    private int nbMaxCardInSerie;

    public Serie(int valueCard, int nbMaxCardInSerie) {
        cards = new ArrayList<>();
        cards.add(new Card(valueCard));
        this.nbMaxCardInSerie = nbMaxCardInSerie;
    }

    public boolean addCardToSerie(Card card) {
        if (this.cards.size() < this.nbMaxCardInSerie) {
            cards.add(card);
            return true;
        }
        return false;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
