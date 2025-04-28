package universite_paris8.iut.fguerreiromarques.loupmouton24;
/* CORRECTION DU TP2 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

import java.net.URL;
import java.util.ResourceBundle;

public class Controler implements Initializable{
    private Environnement env;

    @FXML
    private Pane panneauJeu;

    @FXML
    private ToggleGroup groupeRadio;

    @FXML
    private RadioButton ajoutLoup;

    @FXML
    private RadioButton ajoutMouton;

    @FXML
    private TextField nbIndividus;

    @FXML
    private TextField saisieNbTours;

    @FXML
    void ajouter(ActionEvent event) {
        //System.out.println("clic ajouter");
        RadioButton selectedToggleButton =(RadioButton) groupeRadio.getSelectedToggle();
        int nb= Integer.parseInt(this.nbIndividus.getText());
        Acteur a;
        if(selectedToggleButton.equals(ajoutLoup)) {
            for(int i=0; i<nb;i++){
                a=new Loup(this.env);
                this.env.ajouter(a);
                creerSprite(a);
            }
        }
        else{
            for(int i=0; i<nb;i++){
                a=new Mouton(this.env);
                this.env.ajouter(a);
                creerSprite(a);
            }
        }

    }

    @FXML
    void faireDesTours(ActionEvent event) {
        //System.out.println("clic Lancer");
        int nt= Integer.parseInt(this.saisieNbTours.getText());
        for(int i=0;i<nt;i++){
            this.env.unTour();
            this.rafraichirPanneauJeu();
        }
    }

    @FXML
    void unTour(ActionEvent event) {
        //System.out.println("clic unTour");
        this.env.unTour();
        this.rafraichirPanneauJeu();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.env=new Environnement(300,300);

        // mettre cela pour que les acteurs ne sortent pas visuellement du panneau de jeu en bas et a sroite...
        this.panneauJeu.setMaxWidth(305); // 5== largeur de l'image ou du rectangle.
        this.panneauJeu.setMaxHeight(305);
    }

    private void creerSprite(Acteur a) {
        //System.out.println("ajouter sprite");
        Circle r;
        if( a instanceof Loup){
            r= new Circle(3);
            r.setFill(Color.RED);
        }
        else{
            r= new Circle(2);
            r.setFill(Color.WHITE);
        }
        // ils ont le meme identifiant
        r.setId(a.getId());
        r.setTranslateX(a.getX());
        r.setTranslateY(a.getY());
        r.setOnMouseClicked(e-> System.out.println("clic sur acteur"+ e.getSource()));
        panneauJeu.getChildren().add(r);
    }

    public void rafraichirPanneauJeu(){
        for(Acteur a : this.env.getActeurs()){
            Circle c = (Circle) this.panneauJeu.lookup("#"+a.getId());
            // si c'est un nouveau né
            if(c==null){
                creerSprite(a);
            }
            else {
                c.setTranslateX(a.getX());
                c.setTranslateY(a.getY());
            }
        }
        // pour enlever les morts, il faut parcourir les sprites...
        for (int i=this.panneauJeu.getChildren().size()-1; i>=0;i--){
            Node c=this.panneauJeu.getChildren().get(i) ;
            Acteur a = this.env.getActeur(c.getId());
            if(a==null){
                this.panneauJeu.getChildren().remove(c);
            }
        }
    }
}
