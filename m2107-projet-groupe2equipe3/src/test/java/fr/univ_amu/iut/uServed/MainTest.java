package fr.univ_amu.iut.uServed;


import javafx.scene.image.Image;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainTest {

    private Utilisateur u = new Utilisateur("Alachiel", "motDePasse" , new Profil("Duvoisin","Nicolas","0615517649",'H',"123 Rue Bidon , 13800 Aix-En-Provence","Je n'ai aucune bio","Mon statut est une statue","nicolas.duvoisin@etu.univ-amu.fr","06/03/1999","salut"));
    private Avis a = new Avis(5,"Excellent vendeur",new Date(06/03/99));
    /*
    private Evenement e = new Evenement(u,new ModerateurAuto(),"Sortie entre amis","Stade Nautique","Piscine",4,12,"Materiel de plongée","Demain","Aix");
    private Vente v = new Vente(u,"Vente de voiture",new ModerateurAuto(),"Voiture","Audi A3","Presque neuf","Audi pas cher , jamais servie",new Image("logo.png"),30000,"Aix");
    private Service s = new Service(u,new ModerateurAuto(),"Passeur d'aspirateur","Nettoyage d'intérieur","Je cherche quelqu'un pour du ménage intérieur","Aucune",5,5,"Istres");
    */
    @Test
    public void creerUtilisateur() {
//        assertThat(Main.getBD().getTabUtilisateurs().get(0)).isEqualTo(u);
    }

    @Test
    public void creerAvis(){
 //       u.getProfil().addAvis(a);
 //       assertThat(Main.getBD().getTabUtilisateurs().get(0).getProfil().getAvisUti().get(0)).isEqualTo(a);
    }

    @Test
    public void testTabFiltres (){
        assertThat(Main.getBD().getTabFiltre().get(0)).isEqualTo("Evenement");
    }

    @Test
    public void testCreationAnnonces (){
        /*
        assertThat(Main.getBD().getTabService().get(0)).isEqualTo(s);
        assertThat(Main.getBD().getTabVente().get(0)).isEqualTo(v);
        assertThat(Main.getBD().getTabEvent().get(0)).isEqualTo(e);
        */
    }


}