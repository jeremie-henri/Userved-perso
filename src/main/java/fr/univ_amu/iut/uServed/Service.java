package fr.univ_amu.iut.uServed;

public class Service extends Annonce {
    private String serviceDemande;
    private String description;
    private String connaissancesReq;
    private double prixService;
    private int dureeService;

    public Service(Utilisateur _utilisateur, ModerateurAuto _modAuto,String _intituleAnnonce, String _serviceDemande, String _description, String _connaissancesReq, double _prixService, int _dureeService) {
        serviceDemande = _serviceDemande;
        description = _description;
        connaissancesReq = _connaissancesReq;
        prixService = _prixService;
        dureeService = _dureeService;
        intituleAnnonce = _intituleAnnonce;
        utilisateur=_utilisateur;
        modAuto=_modAuto;
    }

    public String getServiceDemande() {
        return serviceDemande;
    }

    public String getDescription() {
        return description;
    }

    public String getConnaissancesReq() {
        return connaissancesReq;
    }

    public double getPrixService() {
        return prixService;
    }

    public int getDureeService() {
        return dureeService;
    }

    public void setServiceDemande(String serviceDemande) {
        this.serviceDemande = serviceDemande;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setConnaissancesReq(String connaissancesReq) {
        this.connaissancesReq = connaissancesReq;
    }

    public void setPrixService(double prixService) {
        this.prixService = prixService;
    }

    public void setDureeService(int dureeService) {
        this.dureeService = dureeService;
    }
}
