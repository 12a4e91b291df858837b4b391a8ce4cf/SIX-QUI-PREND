package com.sixQuiPrend;

import java.util.ArrayList;

public class Card {
    private final int value;
    private final int bullHeads;

    public Card(int value) {
        this.value = value;
        this.bullHeads = computeBullHeads(value);
    }

    private int computeBullHeads(int bullHeads) {
        if (bullHeads == 55) {
            return 7;
        } else if (bullHeads % 11 == 0) {
            return 5;
        } else if (bullHeads % 5 == 0 && bullHeads % 10 != 0) {
            return 2;
        } else if (bullHeads % 10 == 0) {
            return 3;
        }
        return 1;
    }

    public int getValue() {
        return this.value;
    }

    public int getBullHeads() {
        return this.bullHeads;
    }

    public String toString() {
        return "" + this.value + ((this.bullHeads != 1) ? " (" + this.bullHeads + ")" : "");
    }

    public static String concatenateCards(ArrayList<Card> cards) {
        String res = "";
        for (int i = 0; i < cards.size(); i++) {
            res += cards.get(i);
            if (i != cards.size() - 1) {
                res += ", ";
            }
        }
        return res;
    }
}
