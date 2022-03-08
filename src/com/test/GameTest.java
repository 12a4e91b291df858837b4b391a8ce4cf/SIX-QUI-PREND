package com.test;

import com.sixQuiPrend.Card;
import com.sixQuiPrend.Game;
import com.sixQuiPrend.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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
    public void testGetPlayersHands() throws Exception {
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

    }
}
