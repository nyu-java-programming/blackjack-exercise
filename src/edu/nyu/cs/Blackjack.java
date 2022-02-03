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
    Scanner scn = new Scanner(System.in);
    System.out.println("Welcome to Blackjack!");
    String cards = getCard() + "," + getCard();
    displayCards(cards);

    boolean wantsMore = true;
    boolean isBust = false;
    while (wantsMore && !isBust) {
      System.out.println("Would you like to hit or stand?");
      String response = scn.nextLine();
      if (response.equals("hit")) {
        cards += ", " + getCard();
        displayCards(cards);

        // check for bust
        int total = getTotal(cards);
        if (total > 21) {
          isBust = true;
          System.out.println("You have bust!");
          System.out.println("Dealer wins!");
        }
      }
      else {
        wantsMore = false;
      }
    }
  }

  public static int getCard() {
    int card = (int) (Math.random() * 10) + 2;
    return card;
  }

  public static void displayCards(String cards) {
    String[] cardsArr = cards.split(",");
    String msg = "Your cards are: ";
    for (int i=0; i<cardsArr.length; i++) {
      if (i == 0) {
        msg += cardsArr[i];
      }
      else if (cardsArr.length == 2 && i == 1) {
        msg += " and " + cardsArr[i];
      }
      else if (i == cardsArr.length - 1) {
        // last card
        msg += ", and " + cardsArr[i];
      }
      else {
        msg += ", " + cardsArr[i];
      }
    }
    System.out.println(msg);
  }

  public static int getTotal(String cards) {
    String[] cardsArr = cards.split(",");
    int total = 0;
    for (String card : cardsArr) {
      total += Integer.parseInt(card.strip());
    }
    return total;
  }

}
