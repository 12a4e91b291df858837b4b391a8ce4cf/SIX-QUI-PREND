package com.test;

import com.sixQuiPrend.Card;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestCard {
    @Test
    public void testNumeroCarte() {
        int value = 5;
        Card card = new Card(value);
        Assert.assertEquals(value, card.getValue());
    }

    @Test
    public void testCardWithTwoBullHead() {
        ArrayList<Card> cardFinishingByFive = new ArrayList<>();
        for (int i = 1; i <= 104; ++i) {
            boolean cardFinishingWithFive = i % 5 == 0 && i % 10 != 0;
            boolean specialCase = i == 55;
            if (cardFinishingWithFive && !specialCase) {
                cardFinishingByFive.add(new Card(i));
            }
        }
        for (Card card : cardFinishingByFive) {
            Assert.assertEquals(card.getBullHeads(), 2);
        }
    }

    @Test
    public void testCardWithThreeBullHead() {
        ArrayList<Card> cardFinishingByZero = new ArrayList<>();
        for (int i = 1; i <= 104; ++i) {
            boolean cardFinishingWithZero = i % 10 == 0;
            if (cardFinishingWithZero) {
                cardFinishingByZero.add(new Card(i));
            }
        }
        for (Card card : cardFinishingByZero) {
            Assert.assertEquals(card.getBullHeads(), 3);
        }
    }

    @Test
    public void testCardWithFiveBullHead() {
        ArrayList<Card> cardWithSameNumber = new ArrayList<>();
        for (int i = 1; i <= 104; ++i) {
            boolean cardFinishingWithSameNumber = i % 11 == 0;
            boolean specialCase = i == 55;
            if (cardFinishingWithSameNumber && !specialCase) {
                cardWithSameNumber.add(new Card(i));
            }
        }
        for (Card card : cardWithSameNumber) {
            Assert.assertEquals(card.getBullHeads(), 5);
        }
    }

    @Test
    public void testSpecialCaseOfSevenBullHeads() {
        Assert.assertEquals(new Card(55).getBullHeads(), 7);
    }

    @Test
    public void testDefaultCaseOfOneBullHead() {
        ArrayList<Card> defaultCards = new ArrayList<>();
        for (int i = 1; i <= 104; ++i) {
            boolean cardFinishingWithFive = i % 5 == 0 && i % 10 != 0;
            boolean specialCase = i == 55;
            boolean cardFinishingWithZero = i % 10 == 0;
            boolean cardFinishingWithSameNumber = i % 11 == 0;
            boolean defaultCase = !cardFinishingWithFive && !specialCase && !cardFinishingWithZero && !cardFinishingWithSameNumber;
            if (defaultCase) {
                defaultCards.add(new Card(i));
            }
        }
        for (Card card : defaultCards) {
            Assert.assertEquals(card.getBullHeads(), 1);
        }
    }
}
