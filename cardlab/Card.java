package ics4.cardlab;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * A class where each instance will be one Card from playing cards
 * This class uses the Knight rank so there are 4 suits with 14 ranks
 * The unicode cardfaces are used to display the cards in JavaFX using labels
 */
public class Card {
    // instance variables to store rank, suit, and cardface for each card
    private int rank, suit;
    private Label image;
    private TranslateTransition slide;

    /**
     * Construct a card instance given the suit and rank
     * @param suit value 0-4 (Spades,Hearts,Diamonds,Clubs)
     * @param rank value 1-14 (A-K including C after J)
     */
    public Card(int suit, int rank) {
        this.rank = rank;
        this.suit = suit;
        String utf16 = "\ud83c";
        char temp = '\udca0';
        Font font = Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 100);

        temp += rank + (suit * 16);

        this.image = new Label(utf16+temp);
        this.image.setFont(font);
        this.image.setTextFill(suit % 3 == 0 ? Color.BLACK : Color.RED);

        slide = new TranslateTransition(Duration.seconds(1), this.image);
    }

    public int getRank() {
        return this.rank;
    }

    public int getSuit() {
        return this.suit;
    }

    public Label getImage() {
        return this.image;
    }

    public void slide(int x, int y) {
        this.slide.setToX(x);
        this.slide.setToY(y);
        this.slide.play();
    }
}
