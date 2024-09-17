//Alexander Ruvalcaba, Adrian Martinez, Agustin Villicana, Dynya McKinney
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {

	private static ArrayList<Card> deck = new ArrayList<>();
	private static int currentCardIndex = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean turn = true;
		String playerDecision = "";

		while (turn) {
			initializeDeck();
			shuffleDeck();
			int playerTotal = 0;
			int dealerTotal = 0;

			playerTotal = dealInitialPlayerCards();
			dealerTotal = dealInitialDealerCards();

			// Player's turn
			playerTotal = playerTurn(scanner, playerTotal);
			if (playerTotal > 21) {
				System.out.println("You busted! Dealer wins.");
				continue; // Starts the next game
			}

			// Dealer's turn
			dealerTotal = dealerTurn(dealerTotal);

			// Determine the winner
			determineWinner(playerTotal, dealerTotal);

			// Ask the player if they want to play again
			System.out.println("Would you like to play another hand? (yes or no)");
			playerDecision = scanner.nextLine().toLowerCase();

			while (!(playerDecision.equals("no") || playerDecision.equals("yes"))) {
				System.out.println("Invalid action. Please type 'yes' or 'no'.");
				playerDecision = scanner.nextLine().toLowerCase();
			}

			if (playerDecision.equals("no")) {
				turn = false;
			}
		}
		System.out.println("Thanks for playing!");
	}

	// Algorithm to create deck
	private static void initializeDeck() {
		String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		deck.clear(); // Clear the deck for a new game

		for (String suit : SUITS) {
			for (int i = 0; i < RANKS.length; i++) {
				int value = (i < 9) ? i + 2 : 10; // Cards 2-10 have values of 2-10, face cards are 10
				if (RANKS[i].equals("Ace")) {
					value = 11; // Ace starts as 11
				}
				deck.add(new Card(value, suit, RANKS[i]));
			}
		}
	}

	// Algorithm to shuffle the deck
	private static void shuffleDeck() {
		Collections.shuffle(deck);
		currentCardIndex = 0; // Reset the card index
	}

	// Deal initial player cards
	private static int dealInitialPlayerCards() {
		Card card1 = dealCard();
		Card card2 = dealCard();

		System.out.println("Your cards: " + card1 + " and " + card2);
		return card1.getValue() + card2.getValue();
	}

	// Deal initial dealer card
	private static int dealInitialDealerCards() {
		Card card1 = dealCard();
		System.out.println("Dealer's card: " + card1);
		return card1.getValue();
	}

	// Player's turn
	private static int playerTurn(Scanner scanner, int playerTotal) {
		while (true) {
			System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?");
			String action = scanner.nextLine().toLowerCase();

			if (action.equals("hit")) {
				Card newCard = dealCard();
				playerTotal += newCard.getValue();
				System.out.println("You drew a " + newCard);

				if (playerTotal > 21) {
					System.out.println("You busted! Dealer wins.");
					return playerTotal;
				}
			} else if (action.equals("stand")) {
				break;
			} else {
				System.out.println("Invalid action. Please type 'hit' or 'stand'.");
			}
		}
		return playerTotal;
	}

	// Dealer's turn
	private static int dealerTurn(int dealerTotal) {
		while (dealerTotal < 17) {
			Card newCard = dealCard();
			dealerTotal += newCard.getValue();
		}
		System.out.println("Dealer's total is " + dealerTotal);
		return dealerTotal;
	}

	// Determine the winner
	private static void determineWinner(int playerTotal, int dealerTotal) {
		if (dealerTotal > 21 || playerTotal > dealerTotal) {
			System.out.println("You win!");
		} else if (dealerTotal == playerTotal) {
			System.out.println("It's a tie!");
		} else {
			System.out.println("Dealer wins!");
		}
	}

	// Deal a card from the deck
	private static Card dealCard() {
		return deck.get(currentCardIndex++);
	}
}