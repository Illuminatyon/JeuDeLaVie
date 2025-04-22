package universite_paris8.iut.fguerreiromarques.loupmouton24;
import java.math.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.fguerreiromarques.loupmouton24.modele.Acteur;
import universite_paris8.iut.fguerreiromarques.loupmouton24.modele.Environnement;
import universite_paris8.iut.fguerreiromarques.loupmouton24.modele.Loup;
import universite_paris8.iut.fguerreiromarques.loupmouton24.modele.Mouton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controler implements Initializable {

    private Map<String, Circle> sprites = new HashMap<>();

    private Environnement environnement;

    @FXML
    private TextField nbTourTexte;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private RadioButton SelectionMouton;

    @FXML
    private RadioButton SelectionLoup;

    @FXML
    private TextField nombreAnimaux;

    @FXML
    private Pane panneaudejeu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        environnement = new Environnement(1200,600);
    }

    @FXML
    public void ajouter(javafx.event.ActionEvent actionEvent) {
        //il va falloir faire la méthode qui va appele la méthode creersprite selon ce que j'ai
        if (SelectionMouton.isSelected()) {
            int nbAnimal = Integer.parseInt(nombreAnimaux.getText());
            for(int i = 0 ; i < nbAnimal ; i++){
                Acteur acteur = new Mouton(environnement);
                creerSprite(actionEvent);
                environnement.ajouter(acteur);
            }
        } else if (SelectionLoup.isSelected()) {
            int nbAnimal = Integer.parseInt(nombreAnimaux.getText());
            for(int i = 0 ; i < nbAnimal ; i++){
                Acteur acteur = new Loup(environnement);
                creerSprite(actionEvent);
                environnement.ajouter(acteur);
            }
        }
    }

    @FXML
    public void faireDesTours(javafx.event.ActionEvent actionEvent) {
        int nbTour = Integer.parseInt(nbTourTexte.getText());
        for(int i = 0 ; i < nbTour ; i++) {
            environnement.unTour();
            rafraichirVue();
        }
    }

    @FXML
    public void unTour(javafx.event.ActionEvent actionEvent) {
        environnement.unTour();
        rafraichirVue();
    }

    @FXML
    public void creerSprite(javafx.event.ActionEvent actionEvent) {
        if (SelectionMouton.isSelected()) {
            Circle r = new Circle(2);
            r.setFill(Color.WHITE);
            r.setTranslateX((double) Math.random()*635);
            r.setTranslateY((double) Math.random()*440);
            panneaudejeu.getChildren().add(r);
        }
        if(SelectionLoup.isSelected()){
            Circle r = new Circle(3);
            r.setFill(Color.RED);
            r.setTranslateX((double) Math.random()*635);
            r.setTranslateY((double) Math.random()*440);
            panneaudejeu.getChildren().add(r);
        }
    }

    public void rafraichirVue() {
        // Supprimer les sprites des morts
        sprites.entrySet().removeIf(entry -> {
            String id = entry.getKey();
            if (environnement.getActeur(id) == null) {
                panneaudejeu.getChildren().remove(entry.getValue());
                return true;
            }
            return false;
        });

        // Mettre à jour les positions et ajouter les nouveaux
        for (Acteur acteur : environnement.getActeurs()) {
            String id = acteur.getId();
            Circle sprite = sprites.get(id);
            if (sprite != null) {
                sprite.setTranslateX(acteur.getX());
                sprite.setTranslateY(acteur.getY());
            } else {
                Circle r = new Circle(acteur instanceof Mouton ? 2 : 3);
                r.setFill(acteur instanceof Mouton ? Color.WHITE : Color.RED);
                r.setTranslateX(acteur.getX());
                r.setTranslateY(acteur.getY());
                sprites.put(id, r);
                panneaudejeu.getChildren().add(r);
            }
        }
    }


}
