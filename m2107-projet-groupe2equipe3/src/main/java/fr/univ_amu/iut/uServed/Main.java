package fr.univ_amu.iut.uServed;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main extends Application{

    private BorderPane root = new BorderPane();
    private BooleanProperty connecte = new SimpleBooleanProperty(false);
    private Utilisateur currentUser;
    private Annonce currentAnnonce;
    private static BaseDeDonnees bd = new BaseDeDonnees();


    //Deux variables obligatoires pour modifier dans un event
    private char sexeVal = 'H';
    private String statusVal = "Professionnel";

    public static void main(String[] args) {
        //Utilisateur de test créé pour voir l'affichage
        bd.addUtilisateur(new Utilisateur("test","test",new Profil("Nicolas","Duvoisin",
                "0615517549",'H',"123 rue Bidon","Aix","Profil test","Professionnel",
                "nicolas.duvoisin@etu.univ-amu.fr","06/03/1999")));
        bd.addVente(new Vente(bd.getTabUtilisateurs().get(0),"jolie voiture",new ModerateurAuto(),"voiture","voiture","neuf","je vend ma voiture car mes voisin se pleigne de la vue",new Image("voiture.png"),10.0,"Aix"));
        bd.addVente(new Vente(bd.getTabUtilisateurs().get(0),"jolie voiture",new ModerateurAuto(),"voiture","voiture","neuf","je vend ma voiture car mes voisin se pleigne de la VUE",new Image("voiture.png"),10.0,"Istres"));
        bd.addVente(new Vente(bd.getTabUtilisateurs().get(0),"jolie voiture",new ModerateurAuto(),"voiture","voiture","neuf","je vend ma voiture car mes voisin se pleigne de la Vue",new Image("voiture.png"),10.0,"Istres"));
        bd.addService(new Service(bd.getTabUtilisateurs().get(0),new ModerateurAuto(),"besoin d'eun service voiture","voiture","voiture voiture","voiture",10.0,10,"Aix"));

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("uServed");
        primaryStage.setHeight(768);
        primaryStage.setWidth(1024);
        primaryStage.setResizable(false);
        initWebsite();
        interfaceConnexion();
        primaryStage.setScene(new Scene(root,1024,768));
        primaryStage.show();
    }

    //Affichage de l'interface de mot de passe oublié
    private void interfaceMdpOublie () {

        initWebsite();
        StackPane carreMdpOublie = new StackPane();
        carreMdpOublie.setStyle("-fx-border-color: black");
        carreMdpOublie.setPrefSize(400,400);
        carreMdpOublie.setMaxSize(400,400);

        VBox contenuCarreMdpOublie = new VBox();
        contenuCarreMdpOublie.setSpacing(100);
        contenuCarreMdpOublie.setAlignment(Pos.CENTER);

        HBox ligneEmail = new HBox();
        ligneEmail.setSpacing(20);
        ligneEmail.setAlignment(Pos.CENTER);

        Label email = new Label("E-mail");
        TextField emailField = new TextField();
        email.setAlignment(Pos.CENTER);

        Button resetEmail = new Button("Envoyer le mail de réinitialisation");
        Alert messageSent = new Alert(Alert.AlertType.INFORMATION);


        //Event de vérification de l'email
        resetEmail.setOnAction(event -> {
            boolean mailValide = false;
            PageDeConnexion pageDeConnexion = new PageDeConnexion();
            pageDeConnexion.saisirMail(emailField.getText());

            for (Utilisateur u : bd.getTabUtilisateurs())
            {

                if (pageDeConnexion.verificationMail())
                    mailValide=true;
                break;
            }
            if (mailValide) {
                //Envoyer le mail
                messageSent.setTitle("Information");
                messageSent.setHeaderText("Mail correct !");
                messageSent.setContentText("Un mail vous a été envoyé.");
                messageSent.showAndWait();
            }
            else {
                //Message d'erreur
                messageSent.setAlertType(Alert.AlertType.WARNING);
                messageSent.setTitle("Erreur");
                messageSent.setHeaderText("Mail incorrect !");
                messageSent.setContentText("Le mail n'existe pas.");
                messageSent.showAndWait();
            }
            interfaceConnexion();
        });

        ligneEmail.getChildren().addAll(email,emailField);
        contenuCarreMdpOublie.getChildren().addAll(ligneEmail,resetEmail);

        carreMdpOublie.getChildren().add(contenuCarreMdpOublie);

        root.setCenter(carreMdpOublie);

    }
    // Ajout du décor commun à toutes les pages
    private void initWebsite() {
        root.getChildren().clear();
        initEnTete();
        initPiedPage();
    }

    private void initEnTete() {
        //Vbox contenant le haut de la page
        VBox haut = new VBox();
        haut.setFillWidth(true);

        // Hbox permettant l'alignement de l'image , du titre et du menu déroulant.
        HBox hautHorizontal = new HBox();
        hautHorizontal.setPadding(new Insets(20,0, 0,0));
        hautHorizontal.setSpacing(300);
        hautHorizontal.setAlignment(Pos.CENTER);

        //Logo
        ImageView logo = new ImageView("logo.png");
        logo.setFitHeight(100);
        logo.setFitWidth(100);
        hautHorizontal.getChildren().add(logo);

        //Titre de la page
        Label title = new Label("uServed");
        title.setFont(new Font("Verdana",35));
        title.setOnMouseEntered(event -> {
            if(connecte.get()) {
                root.setCursor(Cursor.HAND);
            }
        });
        title.setOnMouseExited(event -> {
            if(connecte.get()) {
                root.setCursor(Cursor.DEFAULT);
            }
        });
        title.setOnMouseClicked(event -> {
            if(connecte.get()) {
                interfaceAccueil();
            }
        });
        hautHorizontal.getChildren().add(title);

        //Menu déroulant
        MenuBar menuDeroulant = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem afficherProfil = new MenuItem("Afficher le profil");
        MenuItem editerProfil = new MenuItem ("Editer le profil");
        MenuItem deconnexion = new MenuItem("Déconnexion");

        afficherProfil.setOnAction(event -> {
            interfaceProfil();
        });
        editerProfil.setOnAction(event -> {
            interfaceEditerProfil();
        });
        deconnexion.setOnAction(event -> {
            connecte.setValue(false);
            interfaceConnexion();
        });

        menu.getItems().addAll(afficherProfil,editerProfil,deconnexion);
        menuDeroulant.getMenus().add(menu);
        menuDeroulant.visibleProperty().bind(connecte);

        //Evenements quand on survole la barre du menu
        menuDeroulant.setOnMouseEntered(event ->
        {
            menu.show();
        });
        menuDeroulant.setOnMouseClicked(event ->
        {
            menu.hide();
        });

        hautHorizontal.getChildren().add(menuDeroulant);

        // Ligne de séparation
        Line separateur = new Line();
        separateur.setStartX(0);
        separateur.setEndX(1024);
        separateur.setStartY(300);
        separateur.setEndY(300);

        haut.getChildren().add(hautHorizontal);
        haut.getChildren().add(separateur);

        root.setTop(haut);      // Ajout de l'en-tête
    }

    private void initPiedPage() {

        VBox bas = new VBox();
        bas.setFillWidth(true);

        HBox basAlignement = new HBox();
        basAlignement.setSpacing(100);
        basAlignement.setAlignment(Pos.CENTER);

        // Ligne de séparation
        Line separateur = new Line();
        separateur.setFill(Color.BLACK);
        separateur.setStartX(0);
        separateur.setEndX(1024);
        separateur.setStartY(700);
        separateur.setEndY(700);
        bas.getChildren().add(separateur);


        // Ajout des messages d'erreur
        Alert pasDeConditionsDUtilisation = new Alert(Alert.AlertType.ERROR);
        pasDeConditionsDUtilisation.setTitle("Erreur !");
        pasDeConditionsDUtilisation.setHeaderText("Nous n'avons pas de conditions d'utilisation ...");
        pasDeConditionsDUtilisation.setContentText("On en fera au prochain semestre , promis !");

        Alert contact = new Alert(Alert.AlertType.INFORMATION);
        contact.setTitle("Contactez-Nous");
        contact.setHeaderText("Mail");
        contact.setContentText("nicolas.duvoisin@etu.univ-amu.fr");

        // Liens conditions d'utilisation & nous contacter
        Hyperlink conditionsUtilisations = new Hyperlink("Conditions d'utilisation");
        conditionsUtilisations.setVisited(false);
        conditionsUtilisations.setOnMouseClicked(event -> {
            pasDeConditionsDUtilisation.showAndWait();
        });

        Hyperlink contactezNous = new Hyperlink("Contactez-Nous");
        contactezNous.setVisited(false);
        contactezNous.setOnMouseClicked(event -> {
            contact.showAndWait();
        });

        basAlignement.getChildren().addAll(conditionsUtilisations,contactezNous);
        bas.getChildren().add(basAlignement);
        root.setBottom(bas);

    }

    // Affichage de l'interface de connexion
    private void interfaceConnexion() {
        initWebsite();
        VBox centerBox = new VBox();
        centerBox.setFillWidth(true);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(50);

        //Rectangle qui contiendra l'interface de connexion
        StackPane carreConnexion = new StackPane();
        carreConnexion.setStyle("-fx-border-color: black");
        carreConnexion.setPrefSize(400,400);
        carreConnexion.setMaxSize(400,400);


        //Vbox et Hbox qui contiennent le contenu du carré
        VBox contenuCarreConnexion = new VBox();
        contenuCarreConnexion.setAlignment(Pos.CENTER);
        HBox ligneIdentifiant = setLine();
        HBox ligneMdp = setLine();

        contenuCarreConnexion.setSpacing(50);

        Label identifiant = new Label("Identifiant       ");
        TextField idArea = new TextField();
        ligneIdentifiant.getChildren().addAll(identifiant,idArea);

        Label mdp = new Label("Mot de passe   ");
        PasswordField mdpArea = new PasswordField();
        ligneMdp.getChildren().addAll(mdp,mdpArea);

        Hyperlink mdpOublie = new Hyperlink("Mot de passe oublié ?");
        mdpOublie.setOnMouseClicked(event -> {
            interfaceMdpOublie();
        });

        Button boutonConnexion = new Button("Connexion");

        //Bouton d'inscription
        Button inscriptionButton = new Button("Inscription");
        inscriptionButton.setOnAction(event ->
        {
            interfaceInscription();
        });


        Alert alertConnection = new Alert(Alert.AlertType.ERROR);

        alertConnection.setTitle("Erreur !");
        alertConnection.setHeaderText("Mauvais ID ou mot de passe");
        alertConnection.setContentText("Assurez-vous de ne pas être en majuscules");


        //Bouton d'inscription
        boutonConnexion.setOnAction(event -> {
            initBoutonConnexion(idArea, mdpArea, alertConnection);
        });

        mdpArea.setOnKeyPressed(new EventHandler<KeyEvent>() {
            final KeyCombination combo = new KeyCodeCombination(KeyCode.ENTER);
            @Override
            public void handle(KeyEvent event) {
                if (combo.match(event)){
                    initBoutonConnexion(idArea, mdpArea, alertConnection);
                }
            }
        });

        contenuCarreConnexion.getChildren().addAll(ligneIdentifiant,ligneMdp,mdpOublie,boutonConnexion);

        carreConnexion.getChildren().add(contenuCarreConnexion);
        centerBox.getChildren().addAll(carreConnexion,inscriptionButton);


        root.setCenter(centerBox);
    }

    private void initBoutonConnexion(TextField idArea, PasswordField mdpArea, Alert alertConnection) {
        PageDeConnexion pageDeConnexion = new PageDeConnexion();
        pageDeConnexion.saisirIdentifiant(idArea.getText());
        pageDeConnexion.saisirMotdePasse(mdpArea.getText());
        if (pageDeConnexion.verificationInfoConnection()) {
            connecte.set(true);
            currentUser = pageDeConnexion.getUtilisateur();
            interfaceAccueil();
        }
        else {
            alertConnection.showAndWait();
            idArea.setText("");
            mdpArea.setText("");
        }
    }

    //Affichage de l'interface d'inscription
    private void interfaceInscription() {
        initWebsite();

        //Création de chaque ligne

        VBox inscription = new VBox();
        inscription.setMaxSize(700,400);
        inscription.setSpacing(20);
        inscription.setAlignment(Pos.CENTER);

        GridPane inscri = new GridPane();

        ColumnConstraints col1Contraint = new ColumnConstraints();
        col1Contraint.setPercentWidth(20);

        ColumnConstraints col2Contraint = new ColumnConstraints();
        col2Contraint.setPercentWidth(20);
        ColumnConstraints col3Contraint = new ColumnConstraints();
        col1Contraint.setPercentWidth(20);

        ColumnConstraints col4Contraint = new ColumnConstraints();
        col2Contraint.setPercentWidth(20);

        inscri.getColumnConstraints().addAll(col1Contraint,col2Contraint,col3Contraint,col4Contraint);

        HBox userNameLine = setLine();
        userNameLine.setAlignment(Pos.CENTER_LEFT);
        HBox sexeLine = setLine();
        sexeLine.setAlignment(Pos.CENTER_LEFT);
        HBox nomPrenomLine = setLine();
        nomPrenomLine.setAlignment(Pos.CENTER_LEFT);
        HBox mailLine = setLine();
        mailLine.setAlignment(Pos.CENTER_LEFT);
        HBox telephoneDateNaissanceLine = setLine();
        telephoneDateNaissanceLine.setAlignment(Pos.CENTER_LEFT);
        HBox adresseLine = setLine();
        adresseLine.setAlignment(Pos.CENTER_LEFT);
        HBox biographieLine = setLine();
        biographieLine.setAlignment(Pos.CENTER_LEFT);
        HBox statusLine = setLine();
        statusLine.setAlignment(Pos.CENTER_LEFT);
        HBox mdpLine = setLine();
        mdpLine.setAlignment(Pos.CENTER_LEFT);



        //Creation de chaque label de chaque ligne
        Label userName = new Label("Nom d'utilisateur ");
        Label sexe = new Label("Sexe ");
        Label sexeH = new Label("Homme ");
        Label sexeF = new Label("Femme ");
        Label nom = new Label("Nom ");
        Label mail = new Label("Mail");
        Label prenom = new Label("Prénom ");
        Label telephone = new Label("Téléphone ");
        Label dateNaissance = new Label("Date de naissance ");
        Label adresse = new Label("Adresse ");
        Label ville = new Label("Ville");
        Label biographie = new Label("Biographie ");
        Label status = new Label("Statut ");
        Label statusPro = new Label("Professionnel ");
        Label statusParti = new Label("Particulier ");
        Label mdp = new Label("Mot de passe ");

        //Creation des Textfield / CheckBox de chaque ligne
        TextField userNameField = new TextField();
        CheckBox sexeFieldH = new CheckBox();
        CheckBox sexeFieldF = new CheckBox();
        TextField nomField = new TextField();
        TextField prenomField = new TextField();
        TextField mailField = new TextField();
        TextField telephoneField = new TextField();
        DatePicker dateNaissanceField = new DatePicker();
        TextField adresseField = new TextField();
        TextField villeField = new TextField();
        TextArea biographieField = new TextArea();
        CheckBox statusFieldPro = new CheckBox();
        CheckBox statusFieldParti = new CheckBox();
        TextField mdpField = new TextField();





        //Evenements pour gérer les CheckBox
        sexeFieldH.setOnAction(event-> {
            sexeFieldF.setSelected(false);
            sexeVal = 'H';
        });
        sexeFieldF.setOnAction(event -> {
            sexeFieldH.setSelected(false);
            sexeVal = 'F';
        });

        statusFieldPro.setOnAction(event -> {
            statusFieldParti.setSelected(false);
            statusVal = "Professionnel";
        });
        statusFieldParti.setOnAction(event -> {
            statusFieldPro.setSelected(false);
            statusVal = "Particulier";
        });


        Button inscrire = new Button("S'inscrire");

        //Alerte si l'un des champs n'est pas rempli
        Alert pasToutRempli = new Alert(Alert.AlertType.ERROR);
        pasToutRempli.setTitle("Erreur !");
        pasToutRempli.setHeaderText("Champs non remplis !");
        pasToutRempli.setContentText("Merci de remplir tout les champs");

        //Inscription (avec vérification des champs)
        inscrire.setOnAction(event -> {
            LocalDate localDate = dateNaissanceField.getValue();
            if (areAllFieldsFilled(userNameField, nomField, prenomField, mailField, telephoneField, adresseField, biographieField, mdpField, localDate)) {
                pasToutRempli.showAndWait();
            }
            else {
                new Utilisateur(userNameField.getText(), mdpField.getText(), new Profil(nomField.getText(), prenomField.getText(), telephoneField.getText(), sexeVal, adresseField.getText(),villeField.getText(), biographieField.getText(), statusVal, mailField.getText(), localDate.toString()));
                interfaceConnexion();
            }
        });

        //Ajout de tout les élements à chaque ligne
        userNameLine.getChildren().addAll(userName,userNameField);
        sexeLine.getChildren().addAll(sexe,sexeFieldH,sexeH,sexeFieldF,sexeF);
        nomPrenomLine.getChildren().addAll(nom,nomField,prenom,prenomField);
        mailLine.getChildren().addAll(mail,mailField);
        telephoneDateNaissanceLine.getChildren().addAll(telephone,telephoneField,dateNaissance,dateNaissanceField);
        adresseLine.getChildren().addAll(adresse,adresseField,ville,villeField);
        biographieLine.getChildren().addAll(biographie,biographieField);
        statusLine.getChildren().addAll(status,statusFieldPro,statusPro,statusFieldParti,statusParti);
        mdpLine.getChildren().addAll(mdp,mdpField);

        //Ajout de toutes les lignes a l'affichage
        inscription.getChildren().addAll(userNameLine,sexeLine,nomPrenomLine,telephoneDateNaissanceLine,adresseLine,
                mailLine,biographieLine,statusLine,mdpLine,inscrire);

        root.setCenter(inscription);





    }

    //Vérifie que tout les champs ont bien été remplis
    private boolean areAllFieldsFilled(TextField userNameField, TextField nomField, TextField prenomField, TextField mailField, TextField telephoneField, TextField adresseField, TextArea biographieField, TextField mdpField, LocalDate localDate) {
        return userNameField.getText().isEmpty() || mdpField.getText().isEmpty() || nomField.getText().isEmpty() || prenomField.getText().isEmpty() ||
                telephoneField.getText().isEmpty() || adresseField.getText().isEmpty() || biographieField.getText().isEmpty() ||
                mailField.getText().isEmpty() || localDate.toString().isEmpty();
    }

    //Affiche l'interface d'acceuil
    private void interfaceAccueil() {
        initWebsite();
        VBox acceuil = new VBox();
        acceuil.setAlignment(Pos.CENTER);
        acceuil.setSpacing(50);
        acceuil.setFillWidth(true);
        acceuil.setPadding(new Insets(0,0,150,0));

        Label welcomeLabel = new Label("Bienvenue sur uServed !\nCe site permet de rassembler les voisins entre eux grâce à des fonctionnalitées simples.");
        welcomeLabel.setAlignment(Pos.CENTER);

        Line ligneSeparation = new Line(0,200,1024,200);

        HBox choixRechercheCreer = new HBox();
        choixRechercheCreer.setSpacing(150);
        choixRechercheCreer.setAlignment(Pos.CENTER);

        //Création des gros boutons ronds

        Button rechercher = new Button("Recherche une annonce");
        Button creer = new Button("Créer une annonce");

        creer.setStyle(
                "-fx-background-radius: 50em;" +
                        "-fx-min-width: 300px;" +
                        "-fx-max-width: 300px;" +
                        "-fx-min-height: 300px;" +
                        "-fx-max-height: 300px;"
        );

        rechercher.setStyle(
                "-fx-background-radius: 50em;" +
                        "-fx-min-width: 300px;" +
                        "-fx-max-width: 300px;" +
                        "-fx-min-height: 300px;" +
                        "-fx-max-height: 300px;"
        );

        creer.setOnAction(event -> {
            interfaceCreationAnnonce();
        });
        rechercher.setOnAction(event -> {
            interfaceRechercherAnnonce();
        });
        choixRechercheCreer.getChildren().addAll(rechercher,creer);
        acceuil.getChildren().addAll(welcomeLabel,ligneSeparation,choixRechercheCreer);
        root.setCenter(acceuil);
    }

    private void interfaceCreationAnnonce () {
        initWebsite();
        GridPane creationAnnonce = new GridPane();
        creationAnnonce.setVgap(20);
        creationAnnonce.setPadding(new Insets(10,100,10,100));
        //creationAnnonce.setGridLinesVisible(true);

        ColumnConstraints col1Contraint = new ColumnConstraints();
        col1Contraint.setPercentWidth(20);

        ColumnConstraints col2Contraint = new ColumnConstraints();
        col2Contraint.setPercentWidth(20);

        ColumnConstraints col3Contraint = new ColumnConstraints();
        col3Contraint.setPercentWidth(20);

        ColumnConstraints col4Contraint = new ColumnConstraints();
        col4Contraint.setPercentWidth(20);

        creationAnnonce.getColumnConstraints().addAll(col1Contraint,col2Contraint,col3Contraint,col4Contraint);


        Label enTete = new Label("Créer une annonce visible par les autres utilisateurs");
        enTete.setAlignment(Pos.CENTER);
        creationAnnonce.setHalignment(enTete, HPos.CENTER);
        creationAnnonce.add(enTete,1,0,3,1);

        Line separation = new Line(0,200,824,200);
        creationAnnonce.add(separation,0,1,3,1);

        CheckBox vente = new CheckBox("Vente");
        CheckBox service = new CheckBox("Service");
        CheckBox evenement = new CheckBox("Evenement");
        creationAnnonce.add(vente,1,2);
        creationAnnonce.add(service,2,2);
        creationAnnonce.add(evenement,3,2);

        Line separation2 = new Line(0,200,824,200);
        creationAnnonce.add(separation2,0,3,3,1);


        Label nomAnnonceText = new Label("Nom de l'annonce");
        creationAnnonce.add(nomAnnonceText,0,4);
        TextField nomAnnonceField = new TextField();
        creationAnnonce.add(nomAnnonceField,1,4);
        Label dateText = new Label("Date");                             //descendu d'une lignne et placé à gauche
        creationAnnonce.add(dateText,0,5);
        DatePicker datePicker = new DatePicker();
        creationAnnonce.add(datePicker,1,5);

        Label photoLabel = new Label("Photo");
        //Ajouter une photo ici ...
        creationAnnonce.add(photoLabel,3,4);            //remonté d'une ligne et placé à droite

        Label optionText = new Label("Option");
        creationAnnonce.add(optionText,0,6);
        ChoiceBox optionChoiceBox = new ChoiceBox();
        creationAnnonce.add(optionChoiceBox,1,6);

        Label descriptionText = new Label("Description");
        creationAnnonce.add(descriptionText,0,7);
        TextArea descriptionArea = new TextArea();
        creationAnnonce.add(descriptionArea,1,7,3,1);

        Button creer = new Button("Créer l'annonce");
        creationAnnonce.add(creer,2,8);                 //centré

        root.setCenter(creationAnnonce);
    }

    //Affichage de l'interface d'éditer le profil
    private void interfaceEditerProfil() {
        initWebsite();

        //Création des lignes

        VBox editerprofil = new VBox();
        editerprofil.setMaxSize(700, 400);
        editerprofil.setSpacing(20);
        editerprofil.setAlignment(Pos.CENTER);

        HBox nomPrenomLine = setLine();
        HBox mailLine = setLine();
        HBox biographieLine = setLine();
        HBox adresseLine = setLine();
        HBox telephoneDateNaissanceLine = setLine();
        HBox imageprofLine = setLine();

        //Creation de chaque label de chaque ligne

        Label nom = new Label("Nom ");
        Label mail = new Label("Mail");
        Label prenom = new Label("Prénom ");
        Label telephone = new Label("Téléphone ");
        Label dateNaissance = new Label("Date de naissance ");
        Label adresse = new Label("Adresse ");
        Label biographie = new Label("Biographie ");
        ImageView imageprof = new ImageView();

        //Creation des Textfield / CheckBox de chaque ligne
        TextField nomField = new TextField(currentUser.getProfil().getNomUti());
        TextField prenomField = new TextField(currentUser.getProfil().getPrenomUti());
        TextField mailField = new TextField(currentUser.getProfil().getMailUti());
        TextField telephoneField = new TextField(currentUser.getProfil().getTelephoneUti());
        DatePicker dateNaissanceField = new DatePicker();
        TextField adresseField = new TextField(currentUser.getProfil().getAdressePostaleUti());
        TextArea biographieField = new TextArea(currentUser.getProfil().getBioUti());
        ImageView imageprofField = new ImageView();

        Button validerModif = new Button("Valider les modifications");

        //Alerte si l'un des champs n'est pas rempli
        Alert pasToutRempli = new Alert(Alert.AlertType.ERROR);
        pasToutRempli.setTitle("Erreur !");
        pasToutRempli.setHeaderText("Champs non remplis !");
        pasToutRempli.setContentText("Merci de remplir tout les champs");

        //Editer le profil (avec vérification des champs)
        validerModif.setOnAction(event -> {
            LocalDate localDate = dateNaissanceField.getValue();
            if (areAllFieldsFilled2(nomField, prenomField, mailField, telephoneField, adresseField, biographieField, localDate)) {
                pasToutRempli.showAndWait();
            }
            else {
                currentUser.getProfil().setNomUti(nomField.getText());
                currentUser.getProfil().setPrenomUti(prenomField.getText());
                currentUser.getProfil().setMailUti(mailField.getText());
                currentUser.getProfil().setTelephoneUti(telephoneField.getText());
                currentUser.getProfil().setAdressePostaleUti(adresseField.getText());
                currentUser.getProfil().setBioUti(biographieField.getText());
                currentUser.getProfil().setDateNaissanceUti(localDate.toString());
                currentUser.getProfil().setPhotoProfilUti(imageprofField.getImage());
                interfaceAccueil();
            }
        });

        //Ajout de tout les élements à chaque ligne
        nomPrenomLine.getChildren().addAll(nom,nomField,prenom,prenomField);
        mailLine.getChildren().addAll(mail,mailField);
        telephoneDateNaissanceLine.getChildren().addAll(telephone,telephoneField,dateNaissance,dateNaissanceField);
        adresseLine.getChildren().addAll(adresse,adresseField);
        biographieLine.getChildren().addAll(biographie,biographieField);
        imageprofLine.getChildren().addAll(imageprofField);

        //Ajout de toutes les lignes a l'affichage
        editerprofil.getChildren().addAll(nomPrenomLine,telephoneDateNaissanceLine,adresseLine,
                mailLine,biographieLine, imageprofLine, validerModif);

        root.setCenter(editerprofil);


    }

    private void interfaceRechercherAnnonce(){
        initWebsite();

        GridPane rechercherAnnonce = new GridPane();
        rechercherAnnonce.setVgap(50);
        rechercherAnnonce.setHgap(50);
        rechercherAnnonce.setPadding(new Insets(20,100,10,100));
        rechercherAnnonce.setGridLinesVisible(true);

        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPrefWidth(root.getWidth()/2);

        ColumnConstraints columnConstraints2 = new ColumnConstraints();
        columnConstraints2.setPrefWidth(root.getWidth()/5);
        columnConstraints2.setHalignment(HPos.CENTER);

        ColumnConstraints columnConstraints3 = new ColumnConstraints();
        columnConstraints3.setPrefWidth(root.getWidth()/5);
        columnConstraints3.setHalignment(HPos.CENTER);

        rechercherAnnonce.getColumnConstraints().addAll(columnConstraints1,columnConstraints2,columnConstraints3);


        Label intro = new Label("Rechercher des annonces");
        intro.setFont(new Font("Verdana",15));
        rechercherAnnonce.setHalignment(intro,HPos.CENTER);
        rechercherAnnonce.add(intro,0,0,3,1);

        TextField recherche = new TextField();
        rechercherAnnonce.add(recherche,0,1);

        ChoiceBox filtreChoiceBox1 = new ChoiceBox();
        filtreChoiceBox1.setItems(FXCollections.observableArrayList(bd.getTabFiltre()));
        filtreChoiceBox1.getSelectionModel().selectFirst();
        rechercherAnnonce.add(filtreChoiceBox1,1,1);

        Button rechercherButton = new Button();
        rechercherButton.setText("Rechercher");
        rechercherAnnonce.add(rechercherButton,2,1);

        StackPane carreAnnonce1 = new StackPane();
        rechercherAnnonce.add(carreAnnonce1,0,2,3,1);
        HBox ligneAnnonce1 = new HBox ();
        ligneAnnonce1.setSpacing(40);
        VBox titreDescription1 = new VBox();
        Label titre1 = new Label();
        Label description1 = new Label();
        titreDescription1.getChildren().addAll(titre1,description1);
        carreAnnonce1.getChildren().addAll(ligneAnnonce1,titreDescription1);

        StackPane carreAnnonce2 = new StackPane();
        rechercherAnnonce.add(carreAnnonce2,0,3,3,1);
        HBox ligneAnnonce2 = new HBox ();
        ligneAnnonce2.setSpacing(40);
        VBox titreDescription2 = new VBox();
        Label titre2 = new Label();
        Label description2 = new Label();
        titreDescription2.getChildren().addAll(titre2,description2);
        carreAnnonce2.getChildren().addAll(ligneAnnonce2,titreDescription2);

        StackPane carreAnnonce3 = new StackPane();
        rechercherAnnonce.add(carreAnnonce3,0,4,3,1);
        HBox ligneAnnonce3 = new HBox ();
        ligneAnnonce3.setSpacing(40);
        VBox titreDescription3 = new VBox();
        Label titre3 = new Label();
        Label description3 = new Label();
        titreDescription3.getChildren().addAll(titre3,description3);
        carreAnnonce3.getChildren().addAll(ligneAnnonce3,titreDescription3);

        ArrayList<Evenement> eventList = new ArrayList<>();
        ArrayList<Service> serviceList = new ArrayList<>();
        ArrayList<Vente> venteList = new ArrayList<>();

        rechercherButton.setOnAction(event -> {
            String typeChoisi = filtreChoiceBox1.getValue().toString();
            switch (typeChoisi) {
                case "Evenement":
                    for (Evenement e : bd.getTabEvent()){
                        if ((e.getIntituleAnnonce().contains(recherche.getText())||e.getIntituleAnnonce().equals("")) && e.getVille().equals(currentUser.getProfil().getVille()))
                            eventList.add(e);
                    }
                    break;

                case "Service":
                    for (Service s : bd.getTabService()){
                        if ((s.getIntituleAnnonce().contains(recherche.getText())||s.getIntituleAnnonce().equals("")) && s.getVille().equals(currentUser.getProfil().getVille()))
                            serviceList.add(s);
                    }
                    break;


                case "Vente":
                    for (Vente v: bd.getTabVente()){
                        if ((v.getIntituleAnnonce().contains(recherche.getText())||v.getIntituleAnnonce().equals("")) && v.getVille().equals(currentUser.getProfil().getVille()))
                            venteList.add(v);
                    }
                    break;
            }

            Label infos1 = new Label();
            Label infos2 = new Label();
            Label infos3 = new Label();

            Image image1;

            ImageView imageView1 = null;
            
            ligneAnnonce1.getChildren().addAll(infos1);
            ligneAnnonce2.getChildren().add(infos2);
            ligneAnnonce3.getChildren().add(infos3);

            if (eventList.isEmpty() && serviceList.isEmpty() && venteList.isEmpty())
            {
                interfaceRechercherAnnonce();
            }

            switch (typeChoisi){
                case "Evenement":
                    if (eventList.size()>=1) {
                        titre1.setText(eventList.get(0).getIntituleAnnonce());
                        description1.setText(eventList.get(0).getActivite());
                        infos1.setText(eventList.get(0).getDate() + "\n" + eventList.get(0).getLieu());
                    }

                    if (eventList.size()>=2) {
                        titre2.setText(eventList.get(1).getIntituleAnnonce());
                        description2.setText(eventList.get(1).getActivite());
                        infos2.setText(eventList.get(1).getDate() + "\n" + eventList.get(1).getLieu());
                    }

                    if (eventList.size()>=3) {
                        titre3.setText(eventList.get(2).getIntituleAnnonce());
                        description3.setText(eventList.get(2).getActivite());
                        infos3.setText(eventList.get(2).getDate() + "\n" + eventList.get(2).getLieu());
                    }

                    break;

                case "Service":
                    if (serviceList.size()>=1) {
                        titre1.setText(serviceList.get(0).getIntituleAnnonce());
                        description1.setText(serviceList.get(0).getDescription());
                        infos1.setText((eventList.get(0).getDate() + "\n" + eventList.get(0).getLieu()));
                    }

                    if (serviceList.size()>=2) {
                        titre2.setText(serviceList.get(1).getIntituleAnnonce());
                        description2.setText(serviceList.get(1).getDescription());
                        infos2.setText((eventList.get(1).getDate() + "\n" + eventList.get(1).getLieu()));
                    }

                    if (serviceList.size()>=3) {
                        titre3.setText(serviceList.get(2).getIntituleAnnonce());
                        description3.setText(serviceList.get(2).getDescription());
                        infos3.setText((eventList.get(2).getDate() + "\n" + eventList.get(2).getLieu()));
                    }
                    break;

                case "Vente":
                    if (venteList.size()>=1) {
                        titre1.setText(venteList.get(0).getIntituleAnnonce());
                        description1.setText(venteList.get(0).getDescription());
                        infos1.setText((venteList.get(0).getPrixObjet() + "\n" + venteList.get(0).getTypeObjet()));
                        image1 = new Image(venteList.get(0).getPhotoObjet().toString());
                        imageView1 = new ImageView(image1);
                    }
                    if (venteList.size()>=2) {
                        titre2.setText(venteList.get(1).getIntituleAnnonce());
                        description2.setText(venteList.get(1).getDescription());
                        infos2.setText((venteList.get(1).getPrixObjet() + "\n" + venteList.get(1).getTypeObjet()));
                    }

                    if (venteList.size()>=3) {
                        titre3.setText(venteList.get(2).getIntituleAnnonce());
                        description3.setText(venteList.get(2).getDescription());
                        infos3.setText((venteList.get(2).getPrixObjet() + "\n" + venteList.get(2).getTypeObjet()));
                    }
                    break;
            }
            ligneAnnonce1.getChildren().addAll(imageView1);
            ligneAnnonce2.getChildren().add(infos2);
            ligneAnnonce3.getChildren().add(infos3);



        });

        root.setCenter(rechercherAnnonce);

    }

    // Affichage de l'interface du profil
    private void interfaceProfil() {
        initWebsite();
        VBox profilBox = new VBox();
        profilBox.setFillWidth(true);
        profilBox.setAlignment(Pos.CENTER);
        profilBox.setSpacing(50);

        //Rectangle qui contient le profil
        StackPane contenuProfil = new StackPane();
        contenuProfil.setAlignment(Pos.CENTER);
        contenuProfil.setStyle("-fx-border-color: black");
        contenuProfil.setPrefSize(700,400);
        profilBox.setMaxSize(700,400);

        //Vbox et Hbox qui contiennent le contenu du rectangle
        VBox contenuRectangleProfil = new VBox();
        contenuRectangleProfil.setAlignment(Pos.CENTER);
        HBox lignePhoto = setLine();
        HBox ligneNomEtPrenom = setLine();
        HBox lignebDay = setLine();
        HBox ligneAdresse = setLine();
        HBox ligneNumTel = setLine();
        HBox ligneBiographie = setLine();
        HBox ligneNoteEtChat = setLine();

        contenuRectangleProfil.setSpacing(20);


        Label nom = new Label    ("Nom                 ");
        Label nomUser = new Label(currentUser.getProfil().getNomUti());
        Label prenom = new Label ("Prénom              ");
        Label prenomUser = new Label(currentUser.getProfil().getPrenomUti());
        ligneNomEtPrenom.getChildren().addAll(nom, nomUser,prenom,prenomUser);

        Label bDay = new Label   ("Date de naissance   ");
        Label bDayUser = new Label(currentUser.getProfil().getDateNaissanceUti());
        lignebDay.getChildren().addAll(bDay,bDayUser);

        Label adresse = new Label("Adresse Postale     ");
        Label adresseUser = new Label(currentUser.getProfil().getAdressePostaleUti());
        ligneAdresse.getChildren().addAll(adresse,adresseUser);

        Label numero = new Label ("Numéro de téléphone ");
        Label numUser = new Label(currentUser.getProfil().getTelephoneUti());
        ligneNumTel.getChildren().addAll(numero,numUser);

        Label bio = new Label    ("Biographie          ");
        Label bioUser = new Label(currentUser.getProfil().getBioUti());
        ligneBiographie.getChildren().addAll(bio,bioUser);

        Label note = new Label   ("        Note        ");

        contenuRectangleProfil.getChildren().addAll(lignePhoto,ligneNomEtPrenom,lignebDay,
                ligneAdresse,ligneNumTel,ligneBiographie,ligneNoteEtChat);

        contenuProfil.getChildren().add(contenuRectangleProfil);
        profilBox.getChildren().addAll(contenuProfil);


        root.setCenter(profilBox);
    }

    private boolean areAllFieldsFilled2(TextField nomField, TextField prenomField, TextField mailField, TextField telephoneField, TextField adresseField, TextArea biographieField, LocalDate localDate) {
        return nomField.getText().isEmpty() || prenomField.getText().isEmpty() ||
                telephoneField.getText().isEmpty() || adresseField.getText().isEmpty() || biographieField.getText().isEmpty() ||
                mailField.getText().isEmpty() || localDate.toString().isEmpty();
    }

    //Fonction initialisant une ligne au bon format
    private HBox setLine() {
        HBox userNameLine = new HBox();
        userNameLine.setAlignment(Pos.CENTER);
        userNameLine.setSpacing(10);
        return userNameLine;
    }

    public static BaseDeDonnees getBD () {
        return bd;
    }
}