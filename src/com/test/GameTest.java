package com.test;

import com.sixQuiPrend.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;

public class GameTest {
    @Test
    public void failWithLessThanTwoPlayers() throws Exception {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> new Game("configOnePlayer.txt"));
        Assertions.assertEquals("Le nombre de joueurs doit être entre 2 et 10", thrown.getMessage());
    }

    @Test
    public void failWithMoreThanTenPlayers() throws Exception {
        Exception thrown = Assertions.assertThrows(Exception.class, () -> new Game("configElevenPlayer.txt"));
        Assertions.assertEquals("Le nombre de joueurs doit être entre 2 et 10", thrown.getMessage());
    }

    @Test
    public void testGetPlayersName() throws Exception {
        Game game = new Game();
        Assert.assertEquals("Paul", game.getPlayers().get(0).getName());
        Assert.assertEquals("Aba", game.getPlayers().get(1).getName());
        Assert.assertEquals("Paris", game.getPlayers().get(2).getName());
        Assert.assertEquals("Japon", game.getPlayers().get(3).getName());
    }

    @Test
    public void testGetPlayersHandAndSeries() throws Exception {
        Game game = new Game();
        for (Player player : game.getPlayers()) {
            Assert.assertEquals(10, player.getHand().size());
        }


        int[] nbEncountered = new int[105];
        Arrays.fill(nbEncountered, 0);
        for (Player player : game.getPlayers()) {
            for (Card card : player.getHand()) {
                nbEncountered[card.getValue()] += 1;
            }
        }

        for (int i = 0; i < 105; i++) {
            Assert.assertTrue(nbEncountered[i] <= 1);
        }

        for (Serie serie : game.getSeries().getSerieList()) {
            for (Card card : serie.getCards()) {
                nbEncountered[card.getValue()] += 1;
            }
        }
        for (int i = 0; i < 105; i++) {
            Assert.assertTrue(nbEncountered[i] <= 1);
        }

    }

    @Test
    public void testAreAllCardsInDeck() throws Exception {
        Game game = new Game();
        ArrayList<Integer> deck = game.getDeck();
        int cardsSeen[] = new int[105];
        for (int i = 0; i < 104; i++) {
            int card = deck.get(i);
            cardsSeen[card] = cardsSeen[card] + 1;
        }
        for (int i = 1; i <= 104 ; i++) {
            Assert.assertEquals(1, cardsSeen[i]);
        }
    }

    @Test
    public void testAreFourCardsInSeries() throws Exception {
        Game game = new Game();
        Series series = game.getSeries();
        Assert.assertEquals(4, series.getSerieList().size());
        for (Serie serie: series.getSerieList()) {
            Assert.assertEquals(1, serie.getCards().size());
        }
    }

    @Test
    public void testEachPlayerGotTenCards() throws Exception {
        Game game = new Game();
        for (Player player: game.getPlayers()) {
            Assert.assertEquals(10, player.getHand().size());
        }
    }

    @Test
    public void testIsEndGameWheNoCardLeftToPlay() throws Exception {
        Game game = new Game();
        for(Player player : game.getPlayers()) {
            player.getHand().clear();
        }
        Assert.assertTrue(game.isEndGame());
    }
}
