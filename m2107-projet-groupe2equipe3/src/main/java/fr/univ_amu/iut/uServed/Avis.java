package fr.univ_amu.iut.uServed;

import java.util.Date;

public class Avis {
    private int note;
    private String commentaireAvis;
    private Date date;

    public Avis (int _note , String _commentaireAvis , Date _date){
        note=_note;
        commentaireAvis=_commentaireAvis;
        date=_date;
    }
    public String getCommentaireAvis() {
        return commentaireAvis;
    }

    public void setCommentaireAvis(String commentaireAvis) {
        this.commentaireAvis = commentaireAvis;
    }

    public Date getDate() {
        return date;
    }

    public int getNote() {
        return note;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
