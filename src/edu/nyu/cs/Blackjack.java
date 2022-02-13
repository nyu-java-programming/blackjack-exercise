package edu.nyu.cs;

import java.util.Scanner;

/**
 * A variation of the game of Blackjack.  
 * Complete this program according to the instructions in the README.md file as well as within the given comments below.
 */
public class Blackjack {

  /**
   * The main function is automatically called first in a Java program.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) throws Exception {

    // complete this function according to the instructions

    // user's turn
    Scanner scn = new Scanner(System.in);
    System.out.println("Welcome to Blackjack!");
    String userCards = getCard() + "," + getCard();
    displayCards(userCards, "user");

    // user loop
    boolean userWantsMore = true;
    boolean userIsBust = false;
    while (userWantsMore && !userIsBust) {
      System.out.println("Would you like to hit or stand?");
      String userResponse = scn.nextLine();
      if (userResponse.equals("hit")) {
        // user wants another card
        userCards += ", " + getCard();
        displayCards(userCards, "user");

        // check for bust
        int total = getTotal(userCards);
        if (total > 21) {
          userIsBust = true;
        }
      }
      else {
        // user does not want any more cards
        userWantsMore = false;
      }
    } // user loop

    // dealer's turn
    String dealerCards = getCard() + "," + getCard();
    boolean dealerWantsMore = true;
    boolean dealerIsBust = false;

    // dealer loop
    while (dealerWantsMore && !dealerIsBust) {
      // make random decision whether to ask for another card
      boolean dealerResponse = Math.random() > 0.5;
      if (dealerResponse) {
        // dealer wants another card
        dealerCards += ", " + getCard();

        // check for bust
        int total = getTotal(dealerCards);
        if (total > 21) {
          dealerIsBust = true;
        }
      }
      else {
        // dealer does not want any more cards
        dealerWantsMore = false;
      }
    } // dealer loop

    // calculate winner and display output
    if (userIsBust) {
      // user bust
      System.out.println("You have bust!");
      System.out.println("Dealer wins!");
    }
    else if (dealerIsBust) {
      // dealer bust
      displayCards(dealerCards, "dealer");
      System.out.println("The dealer has bust!");
      System.out.println("You win!");
    }
    else if (getTotal(userCards) > getTotal(dealerCards)) {
      // user wins
      displayCards(dealerCards, "dealer");
      System.out.println("You win!");
    }
    else if (getTotal(userCards) < getTotal(dealerCards)) {
      // dealer wins
      displayCards(dealerCards, "dealer");
      System.out.println("Dealer wins!");
    }
    else {
      // it's a tie!
      displayCards(dealerCards, "dealer");
      System.out.println("Tie!");
    }

  } // main

  public static int getCard() {
    int card = (int) (Math.random() * 10) + 2;
    return card;
  }

  public static void displayCards(String cards, String player) {
    String[] cardsArr = cards.split(",");
    String subject = (player == "user") ? "Your" : "The dealer's"; // subject of sentence
    String msg = subject + " cards are: "; // well-formatted sentence
    for (int i=0; i<cardsArr.length; i++) {
      if (i == 0) {
        msg += cardsArr[i].trim();
      }
      else if (cardsArr.length == 2 && i == 1) {
        msg += " and " + cardsArr[i].trim();
      }
      else if (i == cardsArr.length - 1) {
        // last card
        msg += ", and " + cardsArr[i].trim();
      }
      else {
        msg += ", " + cardsArr[i].trim();
      }
    }
    System.out.println(msg);
  }

  public static int getTotal(String cards) {
    String[] cardsArr = cards.split(",");
    int total = 0;
    for (String card : cardsArr) {
      total += Integer.parseInt(card.trim());
    }
    return total;
  }

}
