package com.sixQuiPrend;

import java.util.ArrayList;
import java.util.Scanner;

public class Series {
    private final ArrayList<Serie> serieList;
    private final int nbMaxCardInSerie;

    public Series(int[] cards, int nbMaxCardInSerie) {
        this.serieList = new ArrayList<>();
        this.nbMaxCardInSerie = nbMaxCardInSerie;
        for (int i = 0; i < cards.length; ++i) {
            this.serieList.add(new Serie(cards[i]));
        }
    }

    public ArrayList<Serie> getSerieList() {
        return this.serieList;
    }

    public int addCardAndGetPenalty(Card card) {
        int indexOfSerieToAddCard = this.getIndexOfSerieToAddCard(card.getValue());
        if (indexOfSerieToAddCard != -1) {
            Serie serie = this.serieList.get(indexOfSerieToAddCard);
            if (serie.getCards().size() == this.nbMaxCardInSerie - 1) {
                return restartSerieAndGetPenalty(indexOfSerieToAddCard, card);
            }
            this.serieList.get(indexOfSerieToAddCard).getCards().add(card);
            return 0;
        } else {
            Scanner scan = new Scanner(System.in);
            System.out.print("Saisissez votre série: ");
            String choice = scan.nextLine();
            while (!(Helper.isNumeric(choice) && 1 <= Integer.parseInt(choice) && Integer.parseInt(choice) <= this.serieList.size())) {
                System.out.print("Ce n'est pas une serie valide, saisissez votre série: ");
                choice = scan.nextLine();
            }
            int indexSerie = Integer.parseInt(choice) - 1;
            return this.restartSerieAndGetPenalty(indexSerie, card);
        }
    }

    private int restartSerieAndGetPenalty(int indexSerie, Card card) {
        Serie serieToRetrieve = this.serieList.get(indexSerie);
        int totalPenalty = serieToRetrieve.getTotalPenalty();
        serieToRetrieve.getCards().clear();
        serieToRetrieve.getCards().add(card);
        return totalPenalty;
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
