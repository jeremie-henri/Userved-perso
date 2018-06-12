package fr.univ_amu.iut.uServed;

import java.util.ArrayList;

public class BaseDeDonnees {

    private ArrayList<Utilisateur> tabUtilisateurs;
    private ArrayList<Evenement> tabEvent = new ArrayList<>();
    private ArrayList<Service> tabService = new ArrayList<>();
    private ArrayList<Vente> tabVente = new ArrayList<>();

    private ArrayList<String> tabFiltre = new ArrayList<>();

    public BaseDeDonnees (){
        tabUtilisateurs = new ArrayList<>();
        tabFiltre.add("Evenement");
        tabFiltre.add("Service");
        tabFiltre.add("Vente");
    }

    public void addService (Service s){ tabService.add(s);}
    public void addEvenement (Evenement e){ tabEvent.add(e);}
    public void addVente (Vente v){ tabVente.add(v);}
    public void addUtilisateur (Utilisateur u){
        tabUtilisateurs.add(u);
    }

    public ArrayList<Evenement> getTabEvent() {
        return tabEvent;
    }

    public ArrayList<Service> getTabService() {
        return tabService;
    }

    public ArrayList<Vente> getTabVente() {
        return tabVente;
    }

    public ArrayList<Utilisateur> getTabUtilisateurs() {
        return tabUtilisateurs;
    }

    public ArrayList<String> getTabFiltre() {
        return tabFiltre;
    }
}
