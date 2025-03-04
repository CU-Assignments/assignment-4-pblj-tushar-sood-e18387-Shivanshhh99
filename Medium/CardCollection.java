import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Card {
    private String suit;
    private String rank;

    Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

public class CardCollection {
    private static HashMap<String, List<Card>> cardCollection = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCards();
        while (true) {
            System.out.println("Card Collection System");
            System.out.println("1. Find Cards by Symbol");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    findCardsBySymbol();
                    break;
                case 2:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeCards() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            List<Card> cardsInSuit = new ArrayList<>();
            for (String rank : ranks) {
                cardsInSuit.add(new Card(suit, rank));
            }
            cardCollection.put(suit, cardsInSuit);
        }
    }

    private static void findCardsBySymbol() {
        System.out.print("Enter the symbol (e.g., Hearts, Spades, Clubs, Diamonds): ");
        String symbol = scanner.nextLine();

        List<Card> cards = cardCollection.get(symbol);
        if (cards != null && !cards.isEmpty()) {
            System.out.println("Cards of " + symbol + ":");
            for (Card card : cards) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for the symbol: " + symbol);
        }
    }
}
