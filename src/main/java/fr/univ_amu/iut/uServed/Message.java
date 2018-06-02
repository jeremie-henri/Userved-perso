package fr.univ_amu.iut.uServed;

import java.util.Date;

public class Message {
    private String contenuMessage;
    private Date dateMessage;
    private Utilisateur envoyeur;
    private Utilisateur receveur;

    public Message (String _contenuMessage,Date _dateMessage , Utilisateur _envoyeur , Utilisateur _receveur) {
        contenuMessage=_contenuMessage;
        dateMessage=_dateMessage;
        envoyeur = _envoyeur;
        receveur = _receveur;
    }

    public void sendMessage ()
    {
        receveur.addMessage(this);
    }

    public String getContenuMessage() {
        return contenuMessage;
    }
}
