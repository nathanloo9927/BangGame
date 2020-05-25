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
    Button carda, cardb, cardc, cardd, carde, cardf, cardg, cardh, end;
    Player person;
    Scene gameplay;
    Stage myStage;
    HashMap<String, Scene> sceneMap;

    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Bang");
        myStage = primaryStage;
        sceneMap = new HashMap<String, Scene>();

        person = new Player(0);
        carda = new Button();
        cardb = new Button();
        cardc = new Button();
        cardd = new Button();
        carde = new Button();
        cardf = new Button();
        cardg = new Button();
        cardh = new Button();
        end = new Button();

        person.beginturn();
        setnewpositions();

        carda.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                person.playCard(0);
                setnewpositions();
            }
        });

        cardb.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                person.playCard(1);
                setnewpositions();
            }
        });

        cardc.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                person.playCard(2);
                setnewpositions();
            }
        });

        cardd.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                person.playCard(3);
                setnewpositions();
            }
        });

        carde.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                person.playCard(4);
                setnewpositions();
            }
        });

        cardf.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                person.playCard(5);
                setnewpositions();
            }
        });

        cardg.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                person.playCard(6);
                setnewpositions();
            }
        });

        cardh.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                person.playCard(7);
                setnewpositions();
            }
        });

        HBox playerhand = new HBox(10, carda, cardb, cardc, cardd, carde, cardf, cardg, cardh);

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(70));
        pane.setBottom(playerhand);

        gameplay = new Scene(pane, 700, 400);

        sceneMap.put("gaming", gameplay);

        myStage.setScene(sceneMap.get("gaming"));
        myStage.show();
    }

    public void setnewpositions() {
        ArrayList<Card> hand = person.getHand();
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
            carda.setText(hand.get(0).getName());
            carda.setDisable(false);
        }

        if (size >= 2) {
            cardb.setText(hand.get(1).getName());
            cardb.setDisable(false);

        }

        if (size >= 3) {
            cardc.setText(hand.get(2).getName());
            cardc.setDisable(false);
        }

        if (size >= 4) {
            cardd.setText(hand.get(3).getName());
            cardd.setDisable(false);
        }

        if (size >= 5) {
            carde.setText(hand.get(4).getName());
            carde.setDisable(false);
        }

        if (size >= 6) {
            cardf.setText(hand.get(5).getName());
            cardf.setDisable(false);
        }

        if (size >= 7) {
            cardg.setText(hand.get(6).getName());
            cardg.setDisable(false);
        }

        if (size >= 8) {
            cardh.setText(hand.get(7).getName());
            cardh.setDisable(false);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
