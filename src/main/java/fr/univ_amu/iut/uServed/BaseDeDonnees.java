package fr.univ_amu.iut.uServed;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Observable;

public class BaseDeDonnees {

    private ArrayList<Utilisateur> tabUtilisateurs;
    private ArrayList<Annonce> tabAnnonce;
    private ArrayList<String> tabFiltre;


    public BaseDeDonnees (){
        tabUtilisateurs = new ArrayList<>();
        tabAnnonce = new ArrayList<>();
        tabFiltre =  new ArrayList<>();

        tabFiltre.add("Drogue");
        tabFiltre.add("Prostitution");
        tabFiltre.add("Meutre");
        tabFiltre.add("Assasinat");
    }

    public void addAnnonce (Annonce a) {
        tabAnnonce.add(a);
    }

    public void addUtilisateur (Utilisateur u){
        tabUtilisateurs.add(u);
    }

    public ArrayList<Annonce> getTabAnnonce() {
        return tabAnnonce;
    }

    public ArrayList<Utilisateur> getTabUtilisateurs() {
        return tabUtilisateurs;
    }

    public ArrayList<String> getFiltre() {return tabFiltre;}

}
