package ics4.cardlab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1200, 800);
        stage.setScene(scene);

        Card[][] deck = new Card[4][14];
        for (int i = 0; i < deck.length; i++) {
            for (int j = 0; j < deck[0].length; j++) {
                deck[i][j] = new Card(i, j+1);
                pane.getChildren().add(deck[i][j].getImage());
                deck[i][j].getImage().relocate(j*80, i*120);
            }
        }
        Button shuffle = new Button("Shuffle");
        shuffle.relocate(1100, 750);
        shuffle.setOnAction(event -> {
            shuffle(deck);
        });
        pane.getChildren().addAll(shuffle);
        stage.show();
    }

    public static void shuffle(Card[][] cards) {
        Card[][] shuffled = new Card[4][];
        int rsuit, rrank;

        for (int i = 0; i < cards.length; i++) {
            do {
                rsuit = (int)(Math.random() * 4);
            }while(shuffled[rsuit] != null);
            shuffled[rsuit] = new Card[14];
            for (int j = 0; j < cards[i].length; j++) {
                do{
                    rrank = (int)(Math.random() * 14);
                }while(shuffled[rsuit][rrank] != null);
                shuffled[rsuit][rrank] = cards[i][j];
                cards[i][j].getImage().relocate(0, 0);
            }
        }
        deal(shuffled);
    }

    // do for each and deal
    // fix with counter for relocate
    public static void deal(Card[][] cards) {
        int i = 0;
        for (Card[] suits : cards) {
            for (Card card : suits) {
                card.slide(i%14*80, i/14*120);
                i++;
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

}