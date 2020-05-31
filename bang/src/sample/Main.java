package sample;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    Button carda, cardb, cardc, cardd, carde, cardf, cardg, cardh, end, takehit;
    Game banggame;
    Player person;
    Scene gameplay;
    Stage myStage;
    HashMap<String, Scene> sceneMap;
    Card bangcard;
    Card missedcard;
    Label turnnum, health, role, needdiscard;
    Label remainingcards; // testing purposes, comment out when done

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Bang");
        myStage = primaryStage;
        sceneMap = new HashMap<String, Scene>();

        bangcard = new Card("Bang", 0);
        missedcard = new Card("Missed", 1);

        person = new Player(0);
        banggame = new Game(4);
        carda = new Button();
        cardb = new Button();
        cardc = new Button();
        cardd = new Button();
        carde = new Button();
        cardf = new Button();
        cardg = new Button();
        cardh = new Button();
        end = new Button("End turn");
        takehit = new Button("Take damage");

        takehit.setDisable(true);

        health = new Label();
        role = new Label();
        turnnum = new Label();
        needdiscard = new Label();
        remainingcards = new Label(); // remove when done testing

        banggame.start();
        setnewpositions();

        carda.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                banggame.play(0);
                setnewpositions();
            }
        });

        cardb.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                banggame.play(1);
                setnewpositions();
            }
        });

        cardc.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                banggame.play(2);
                setnewpositions();
            }
        });

        cardd.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                banggame.play(3);
                setnewpositions();
            }
        });

        carde.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                banggame.play(4);
                setnewpositions();
            }
        });

        cardf.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                banggame.play(5);
                setnewpositions();
            }
        });

        cardg.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                banggame.play(6);
                setnewpositions();
            }
        });

        cardh.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                banggame.play(7);
                setnewpositions();
            }
        });

        end.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                banggame.attemptend();
                setnewpositions();
            }
        });

        takehit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                banggame.takehit();
            }
        });

        VBox addons = new VBox(10, end, takehit);
        VBox stats = new VBox(10, turnnum, health, role, remainingcards);
        HBox playerhand = new HBox(10, carda, cardb, cardc, cardd, carde, cardf, cardg, cardh, addons, stats);
        VBox tryend = new VBox(10, playerhand, needdiscard);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(70));
        pane.setBottom(tryend);

        gameplay = new Scene(pane, 800, 400);

        sceneMap.put("gaming", gameplay);

        myStage.setScene(sceneMap.get("gaming"));
        myStage.show();
    }

    public void setnewpositions() {
        ArrayList<Card> hand = banggame.getHand();
        turnnum.setText("Player " + banggame.getTurn() + "'s turn");
        health.setText("Health: " + banggame.healthstat());
        role.setText("Role: " + banggame.rolestat());
        if (banggame.indiscard()) {
            needdiscard.setText("You need to discard some cards");
        } else {
            needdiscard.setText("");
        }

        // display the deck size, don't show in actual game, so delete once done testing
        remainingcards.setText("Remaining: " + banggame.left());
        int size = hand.size();

        carda.setDisable(true);
        cardb.setDisable(true);
        cardc.setDisable(true);
        cardd.setDisable(true);
        carde.setDisable(true);
        cardf.setDisable(true);
        cardg.setDisable(true);
        cardh.setDisable(true);

        carda.setText("");
        cardb.setText("");
        cardc.setText("");
        cardd.setText("");
        carde.setText("");
        cardf.setText("");
        cardg.setText("");
        cardh.setText("");

        if (size >= 1) {
            Card ca = hand.get(0);
            carda.setText(ca.getName());
            if (banggame.indiscard()) {
                carda.setDisable(false);
            } else {
                if (ca.equals(missedcard)) {
                    carda.setDisable(true);
                } else if (ca.equals(bangcard) && banggame.playedbang()) {
                    carda.setDisable(true);
                } else {
                    carda.setDisable(false);
                }
            }
        }

        if (size >= 2) {
            Card cb = hand.get(1);
            cardb.setText(cb.getName());
            if (banggame.indiscard()) {
                cardb.setDisable(false);
            } else {
                if (cb.equals(missedcard)) {
                    cardb.setDisable(true);
                } else if (cb.equals(bangcard) && banggame.playedbang()) {
                    cardb.setDisable(true);
                } else {
                    cardb.setDisable(false);
                }
            }
        }

        if (size >= 3) {
            Card cc = hand.get(2);
            cardc.setText(cc.getName());
            if (banggame.indiscard()) {
                cardc.setDisable(false);
            } else {
                if (cc.equals(missedcard)) {
                    cardc.setDisable(true);
                } else if (cc.equals(bangcard) && banggame.playedbang()) {
                    cardc.setDisable(true);
                } else {
                    cardc.setDisable(false);
                }
            }
        }

        if (size >= 4) {
            Card cd = hand.get(3);
            cardd.setText(cd.getName());
            if (banggame.indiscard()) {
                cardd.setDisable(false);
            } else {
                if (cd.equals(missedcard)) {
                    cardd.setDisable(true);
                } else if (cd.equals(bangcard) && banggame.playedbang()) {
                    cardd.setDisable(true);
                } else {
                    cardd.setDisable(false);
                }
            }
        }

        if (size >= 5) {
            Card ce = hand.get(4);
            carde.setText(ce.getName());
            if (banggame.indiscard()) {
                carde.setDisable(false);
            } else {
                if (ce.equals(missedcard)) {
                    carde.setDisable(true);
                } else if (ce.equals(bangcard) && banggame.playedbang()) {
                    carde.setDisable(true);
                } else {
                    carde.setDisable(false);
                }
            }
        }

        if (size >= 6) {
            Card cf = hand.get(5);
            cardf.setText(cf.getName());
            if (banggame.indiscard()) {
                cardf.setDisable(false);
            } else {
                if (cf.equals(missedcard)) {
                    cardf.setDisable(true);
                } else if (cf.equals(bangcard) && banggame.playedbang()) {
                    cardf.setDisable(true);
                } else {
                    cardf.setDisable(false);
                }
            }
        }

        if (size >= 7) {
            Card cg = hand.get(6);
            cardg.setText(cg.getName());
            if (banggame.indiscard()) {
                cardg.setDisable(false);
            } else {
                if (cg.equals(missedcard)) {
                    cardg.setDisable(true);
                } else if (cg.equals(bangcard) && banggame.playedbang()) {
                    cardg.setDisable(true);
                } else {
                    cardg.setDisable(false);
                }
            }
        }

        if (size >= 8) {
            Card ch = hand.get(7);
            cardh.setText(ch.getName());
            if (banggame.indiscard()) {
                cardh.setDisable(false);
            } else {
                if (!ch.equals(missedcard)) {
                    cardh.setDisable(true);
                } else if (ch.equals(bangcard) && banggame.playedbang()) {
                    cardh.setDisable(true);
                } else {
                    cardh.setDisable(false);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
