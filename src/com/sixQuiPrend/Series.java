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
        int indexOfSerieToAddCard = this.getIndexOfSerieToAddCard(card.getValue());
        if (indexOfSerieToAddCard != -1) {
            this.serieList.get(indexOfSerieToAddCard).addCardToSerie(card);
        }
    }

    public int getIndexOfSerieToAddCard(int valueCard) {
        int minDist = Integer.MAX_VALUE;
        int resIndex = -1;
        for (int i = 0; i < this.serieList.size(); ++i) {
            Serie serie = this.serieList.get(i);
            ArrayList<Card> cards = serie.getCards();
            int topCard = cards.get(cards.size() - 1).getValue();
            if (valueCard > topCard && valueCard - topCard < minDist) {
                resIndex = i;
                minDist = valueCard - topCard;
            }
        }
        return resIndex;

    }
}
