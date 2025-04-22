package universite_paris8.iut.fguerreiromarques.loupmouton24;


import universite_paris8.iut.fguerreiromarques.loupmouton24.modele.Environnement;
import universite_paris8.iut.fguerreiromarques.loupmouton24.modele.VueConsole;

public class MainConsole {

    public static void main(String[] args) {
        Environnement env= new Environnement(20, 20);
        VueConsole vue = new VueConsole(env);
        vue.go();

    }

}
