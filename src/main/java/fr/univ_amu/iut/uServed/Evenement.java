package fr.univ_amu.iut.uServed;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import java.util.Date;

public class Evenement extends Annonce{

    private String lieu;
    private String activite;
    private int nombreMaxPers;
    private int duree;
    private ObservableList<Utilisateur> participants = new SimpleListProperty<>();
    private String equipement;
    private Date date;
    private IntegerBinding sizeProperty = Bindings.size(participants);
    private IntegerProperty nombrePersonnes = new SimpleIntegerProperty();

    public Evenement (Utilisateur _utilisateur, ModerateurAuto _modAuto,String _intituleAnnonce,String _lieu, String _activite, int _nombreMaxPers, int _duree, String _equipement, Date _date)
    {
        utilisateur=_utilisateur;
        modAuto=_modAuto;
        intituleAnnonce=_intituleAnnonce;
        lieu=_lieu;
        activite=_activite;
        nombreMaxPers=_nombreMaxPers;
        duree=_duree;
        equipement=_equipement;
        date=_date;
        nombrePersonnes.bind(sizeProperty);
        participants.add(utilisateur);
    }

    public void ajouterPersonne (Utilisateur u)
    {
        participants.add(u);
    }

    public String getLieu() {
        return lieu;
    }

    public String getActivite() {
        return activite;
    }

    public int getNombreMaxPers() {
        return nombreMaxPers;
    }

    public int getDuree() {
        return duree;
    }

    public ObservableList<Utilisateur> getParticipants() {
        return participants;
    }

    public String getEquipement() {
        return equipement;
    }

    public Date getDate() {
        return date;
    }

    public int getNombrePersonnes() {
        return nombrePersonnes.get();
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public void setNombreMaxPers(int nombreMaxPers) {
        this.nombreMaxPers = nombreMaxPers;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNombrePersonnes(int nombrePersonnes) {
        this.nombrePersonnes.set(nombrePersonnes);
    }
}
