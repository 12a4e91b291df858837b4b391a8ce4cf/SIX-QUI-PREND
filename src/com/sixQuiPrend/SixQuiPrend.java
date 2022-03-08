package com.sixQuiPrend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SixQuiPrend {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        displayWelcomeMessage(game);
        ArrayList<Player> players = game.getPlayers();
        for (int i = 0; ; i = (i + 1) % players.size()) {
            Player player = players.get(i);
            if (player.getHand().size() == 0) {
                break;
            }
            displayTable(player, game);
            playCard(player, game);
            clearScreen();
        }
        System.out.println("La partie est finie");
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

    private static void displayTable(Player player, Game game) throws IOException {
        String startTurnString = "A " + player.getName() + " de jouer";
        System.out.println(startTurnString);
        System.in.read();
        Series series = game.getSeries();
        for (int i = 0; i < series.getSerieList().size(); i++) {
            Serie serie = series.getSerieList().get(i);
            String message = "- Série n°" + (i + 1) + " : " + Card.concatenateCards(serie.getCards());
            System.out.println(message);
        }
        String playersCards = "Vos cartes : " + Card.concatenateCards(player.getHand());
        System.out.println(playersCards);
    }

    private static void playCard(Player player, Game game) {
        System.out.print("Saisissez votre choix :");
        String choice = scan.nextLine();
        while (!(isNumeric(choice) && player.getCardIndex(Integer.parseInt(choice)) != -1)) {
            System.out.print("Vous n'avez pas cette carte, saisissez votre choix :");
            choice = scan.nextLine();
        }
        int cardIndex = player.getCardIndex(Integer.parseInt(choice));
        player.playCardInSerie(game.getSeries(), cardIndex);
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
