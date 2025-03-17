package edu.guilford;

import java.util.Scanner;

public class ThirtyOneGame {
    private PlayerGroup playerGroup = new PlayerGroup();
    private Stockpile stockpile = new Stockpile();
    private DiscardPile discardPile = new DiscardPile();
    private boolean gameOver = false;

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
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            Player currentPlayer = playerGroup.getCurrentPlayer();
            System.out.println("\nIt's " + currentPlayer.getName() + "'s turn!");
            System.out.println("Your hand: \n" + currentPlayer.getHand());
            System.out.println("Top of discard pile: " + discardPile.peekTopCard());
            
            System.out.println("Do you want to draw from (1) Stockpile or (2) Discard pile?");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) currentPlayer.drawCard(stockpile.getDeck());
            else currentPlayer.getHand().addCard(discardPile.drawTopCard());

            System.out.println("Choose a card to discard (0, 1, or 2): ");
            int discardIndex = scanner.nextInt();
            scanner.nextLine();

            Card discardedCard = currentPlayer.getHand().getCard(discardIndex);
            currentPlayer.getHand().removeCard(discardedCard);
            discardPile.addCard(discardedCard);

            System.out.println("Your new hand:\n" + currentPlayer.getHand());
            System.out.println("Your total hand value: " + currentPlayer.getHandValue());

            if (currentPlayer.getHandValue() == 31) {
                System.out.println(currentPlayer.getName() + " has reached 31! They win!");
                gameOver = true;
                break;
            }
            System.out.println("Press enter to end turn.");
            scanner.nextLine();
            playerGroup.nextTurn();
        }
        System.out.println("Game over!");
    }

    public static void main(String[] args) {
        ThirtyOneGame game = new ThirtyOneGame();
        game.setupGame();
        game.playGame();
    }
}