package com.sixQuiPrend;

import java.util.ArrayList;

public class Series {
    private final ArrayList<Serie> serieList;

    public Series(int[] cards, int nbMaxCardInSerie) {
        this.serieList = new ArrayList<>();
        for (int i = 0; i < cards.length; ++i) {
            this.serieList.add(new Serie(cards[i], nbMaxCardInSerie));
        }
    }

    public ArrayList<Serie> getSerieList() {
        return this.serieList;
    }

    public void addCard(Card card) {
        for (int i = 0; i < serieList.size(); i++) {
            boolean didSucceed = this.serieList.get(i).addCardToSerie(card);
            if (didSucceed) {
                return;
            }
        }
    }
}
