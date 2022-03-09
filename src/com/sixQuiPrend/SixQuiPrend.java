package com.sixQuiPrend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SixQuiPrend {

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        displayWelcomeMessage(game);
        while (!game.isEndGame()) {
            ArrayList<Choice> choices = getPlayerChoices(game);
            Collections.sort(choices);
            displayChoices(choices);
            for (Choice choice : choices) {
                Player player = choice.player;
                Card card = choice.card;
                choice.setPreviousPenalty(choice.player.getPenalty());
                player.playCardInSerie(game.getSeries(), card);
            }
            displayRecapRound(choices);
            clearScreen();
        }
        displayEndGameResult(game);
    }

    private static void displayRecapRound(ArrayList<Choice> choices) {
        String res = "";
        for (Choice choice : choices) {
            int scoreDiff = choice.player.getPenalty() - choice.getPreviousPenalty();
            if (scoreDiff != 0) {
                res += choice.player.getName() + " a ramassé " + scoreDiff + " têtes de boeufs.\n";
            }
        }
        if (res.equals("")) {
            res = "Aucun joueur ne ramasse de tête de boeufs";
        }
        System.out.println(res);
    }

    private static void displayChoices(ArrayList<Choice> choices) {
        String res = "Les cartes ";
        for (int i = 0; i < choices.size(); i++) {
            Choice choice = choices.get(i);
            res += choice.card.getValue() + " (" + choice.player.getName() + ") ";
            if (i < choices.size() - 2) {
                res += ", ";
            } else if (i == choices.size() - 2) {
                res += "et ";
            }
        }
        res += " ont été posées";
        System.out.println(res);
    }

    private static void displayEndGameResult(Game game) {
        ArrayList<Player> players = game.getPlayers();
        Collections.sort(players);
        System.out.println("** Score Final");
        for (Player player : players) {
            System.out.println(player.getName() + " a ramassé " + player.getPenalty() + " tête de boeufs");
        }
    }

    private static void displayWelcomeMessage(Game game) {
        String concatedNames = "";
        ArrayList<Player> players = game.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            concatedNames += players.get(i).getName();
            if (i < players.size() - 2) {
                concatedNames += ", ";
            } else if (i == players.size() - 2) {
                concatedNames += " et ";
            }
        }
        String welcomeMessage = "Les " + players.size() + " joueurs sont " + concatedNames + ". Merci de jouer à 6 qui prend !";
        System.out.println(welcomeMessage);
    }

    private static void displayTableAndPlayersHand(Player player, Game game) throws IOException {
        String startTurnString = "A " + player.getName() + " de jouer";
        System.out.println(startTurnString);
        System.in.read();
        Series series = game.getSeries();
        System.out.println(series);
        String playersCards = "Vos cartes : " + Card.concatenateCards(player.getHand());
        System.out.println(playersCards);
    }

    private static ArrayList<Choice> getPlayerChoices(Game game) throws IOException {
        ArrayList<Player> players = game.getPlayers();
        ArrayList<Choice> choices = new ArrayList<>();
        for (Player player : players) {
            Choice choice = new Choice(player);
            displayTableAndPlayersHand(player, game);
            choice.chooseCard(player);
            choices.add(choice);
        }
        return choices;
    }

    private static void clearScreen() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
