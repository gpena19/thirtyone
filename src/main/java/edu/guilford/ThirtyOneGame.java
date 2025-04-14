package edu.guilford;

import java.util.Scanner;

public class ThirtyOneGame {
    private PlayerGroup playerGroup = new PlayerGroup();
    private Stockpile stockpile = new Stockpile();
    private DiscardPile discardPile = new DiscardPile();
    private boolean gameOver = false;
    private boolean roundKnocked = false;
    private int turnsAfterKnock = 0;

    public void setupGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter player " + (i + 1) + " name: ");
            playerGroup.addPlayer(scanner.nextLine());
        }

        for (Player player : playerGroup.getPlayers()) {
            for (int i = 0; i < 3; i++) {
                player.drawCard(stockpile.getDeck());
            }
        }
        discardPile.addCard(stockpile.drawCard());
    }

    public void playGame() {
        while (!gameOver) {
            // Check if there's only one player left
            if (playerGroup.getPlayers().size() == 1) {
                System.out.println(playerGroup.getPlayers().get(0).getName() + " wins the game!");
                break;
            }

            Player currentPlayer = playerGroup.getCurrentPlayer();
            System.out.println("\nIt's " + currentPlayer.getName() + "'s turn!");
            System.out.println("Starting hand: " + currentPlayer.getHand());

            currentPlayer.takeTurn(discardPile, stockpile);

            System.out.println("New hand: " + currentPlayer.getHand());
            System.out.println("Hand value: " + currentPlayer.getHandValue());

            if (currentPlayer.getHandValue() == 31) {
                System.out.println(currentPlayer.getName() + " hit 31! They win!");
                gameOver = true;
                break;
            }

            if (!roundKnocked && currentPlayer.decideToKnock()) {
                roundKnocked = true;
                turnsAfterKnock = playerGroup.getPlayers().size() - 1;
            } else if (roundKnocked) {
                turnsAfterKnock--;
                if (turnsAfterKnock == 0) {
                    System.out.println("Round ends after knock.");
                    resolveRound();
                    eliminatePlayers();
                    playerGroup.resetTurnOrder();  // ✅ reset safely after eliminations
                    resetRound();
                    continue;
                }
            }

            playerGroup.nextTurn();
        }

        System.out.println("\nFinal Hands:");
        for (Player p : playerGroup.getPlayers()) {
            System.out.println(p.getName() + ": " + p.getHand() + " (" + p.getHandValue() + "), Lives: " + p.getLives());
        }

        System.out.println("Game over!");
    }

    private void resolveRound() {
        Player loser = playerGroup.getPlayers().get(0);
        for (Player player : playerGroup.getPlayers()) {
            if (player.getHandValue() < loser.getHandValue()) {
                loser = player;
            }
        }
        loser.loseLife();
        System.out.println(loser.getName() + " had the lowest hand and lost a life. Lives left: " + loser.getLives());
    }

    private void eliminatePlayers() {
        playerGroup.getPlayers().removeIf(p -> p.getLives() <= 0);
    }

    private void resetRound() {
        roundKnocked = false;

        int cardsNeeded = playerGroup.getPlayers().size() * 3;

        if (stockpile.getDeck().size() < cardsNeeded) {
            stockpile.reshuffleFromDiscard(discardPile);
        }

        if (stockpile.getDeck().size() < cardsNeeded) {
            System.out.println("Deck too small even after reshuffle. Rebuilding full deck.");
            stockpile = new Stockpile();       // Reset stockpile completely
            discardPile = new DiscardPile();   // Reset discard pile
        }

        for (Player player : playerGroup.getPlayers()) {
            player.clearHand();
            player.setKnocked(false);
            for (int i = 0; i < 3; i++) {
                player.drawCard(stockpile.getDeck());
            }
        }

        discardPile.addCard(stockpile.drawCard());
    }

    public static void main(String[] args) {
        ThirtyOneGame game = new ThirtyOneGame();
        game.setupGame();
        game.playGame();
    }
}