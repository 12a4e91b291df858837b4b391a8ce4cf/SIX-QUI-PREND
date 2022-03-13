package com.test;

import com.sixQuiPrend.Card;
import com.sixQuiPrend.Player;
import com.sixQuiPrend.Series;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerTest {
    @Test
    public void testPlayerName() {
        String name = "Paul";
        Player player = new Player(name);
        Assert.assertEquals(name, player.getName());
    }

    @Test
    public void testPlayerHand() {
        String name = "Paul";
        ArrayList<Card> expectedHand = new ArrayList<>();
        int maxCardValue = 5;
        for (int i = 0; i < maxCardValue; i++) {
            expectedHand.add(new Card(i));
        }
        Player player = new Player(name, expectedHand);

        Assert.assertEquals(player.getName(), name);
        for (int i = 0; i < maxCardValue; i++) {
            Card expectedCard = expectedHand.get(i);
            Assert.assertEquals(expectedCard.getValue(), player.getHand().get(i).getValue());
        }
    }

    @Test
    public void testPenalty() {
        String name = "Paul";
        ArrayList<Card> receivedPenaltyCards = new ArrayList<>();
        receivedPenaltyCards.add(new Card(102));
        receivedPenaltyCards.add(new Card(5));
        receivedPenaltyCards.add(new Card(55));
        receivedPenaltyCards.add(new Card(20));
        receivedPenaltyCards.add(new Card(22));

        Player player = new Player(name);
        player.addPenalty(receivedPenaltyCards);
        Assert.assertEquals(18, player.getPenalty());
    }

    @Test
    public void testIncreasePenalty() {
        Player player = new Player("Police");
        int penaltyBefore = player.getPenalty();
        Assert.assertEquals(0, penaltyBefore);
        Card card1 = new Card(55);
        Card card2 = new Card(7);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        player.addPenalty(cards);
        int penaltyAfter = player.getPenalty();
        Assert.assertEquals(8, penaltyAfter);
    }

    @Test
    public void testPlayCardInSerie() {
        int[] valueCardsHand = {5, 6 ,7, 8};
        ArrayList<Card> cardsHand = new ArrayList<>();
        for (int valueCard: valueCardsHand) {
            cardsHand.add(new Card(valueCard));
        }
        Player player = new Player("Police", cardsHand);

        int[] valueCardsSerie = {1, 2, 3, 4};
        Series series = new Series(valueCardsSerie, 4);

        int nbHandBefore = player.getHand().size();
        Assert.assertEquals(4, nbHandBefore);
        player.playCardInSerie(series, player.getHand().get(0));
        int nbHandAfter = player.getHand().size();
        Assert.assertEquals(3, nbHandAfter);
    }

    @Test
    public void testComparaisonBetweenPlayer() {
        Player player1 = new Player("Ahmed");
        Player player2 = new Player("Aristide");
        Player player3 = new Player("Ilyas");
        player1.setPenalty(10);
        player2.setPenalty(10);
        player3.setPenalty(1);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Collections.sort(players);

        Assert.assertEquals(player3, players.get(0));
        Assert.assertEquals(player1, players.get(1));
        Assert.assertEquals(player2, players.get(2));
    }
}
