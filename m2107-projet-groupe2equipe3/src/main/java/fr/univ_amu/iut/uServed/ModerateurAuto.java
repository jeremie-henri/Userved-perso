package fr.univ_amu.iut.uServed;

import java.util.ArrayList;

public class ModerateurAuto {

    private ArrayList<String> motsInterdits = new ArrayList<>();

    public ModerateurAuto ()
    {
        motsInterdits.add("chenapant");
        motsInterdits.add("bachi-bouzouk");
        motsInterdits.add("brigand");
        motsInterdits.add("canaille");
        motsInterdits.add("ectoplasme");
        motsInterdits.add("oryctérope");
        motsInterdits.add("ornithorynque");
    }
    public boolean verificationAvis (Avis a)
    {
        for(String s :motsInterdits)
        {
            if (a.getCommentaireAvis().contains(s))
                return false;
        }
        return true;
    }

    public boolean verificationAnnonce (Annonce a)
    {
        for(String s : motsInterdits)
        {
            if (a.getIntituleAnnonce().contains(s))
                return false;
        }
        return true;
    }
}
