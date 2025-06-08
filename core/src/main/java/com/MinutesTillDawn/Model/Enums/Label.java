package com.MinutesTillDawn.Model.Enums;

import com.MinutesTillDawn.Model.GameSettings;

public enum Label {
    SETTINGS("Settings", "paramètres"),
    PROFILE("profile", "profil"),
    PREGAMEMENU("Pre-game menu", "Menu d'avant-match"),
    SCOREBOARD("Scoreboard", "Tableau de bord"),
    TALENTMENU("Hint menu", "Menu d'astuces"),
    BACK("back", "dos"),
    RESETPASSWORD("Reset password!", "Réinitialisez le mot de passe !"),
    FORGOTYOURPASSWORD("Forgot Your Password?", "Mot de passe oublié?"),
    ENTERYOURUSERNAME("Enter your username.", "Entrez votre nom d'utilisateur."),
    ENTERYOURPASSWORD("Enter your new password.", "Entrez votre nouveau mot de passe."),
    LOGINMENU("Login", "Se connecter"),
    REISTERMENU("Register", "Registre"),
    LOGINLABEL("-LOGIN-", "-SE CONNECTER-"),
    USERNAMEFIELDPLACEHOLDER("Username...", "Nom d'utilisateur..."),
    PASSWORDFIELDPLACEHOLDER("Password...", "Mot de passe..."),
    USERNAMEFIELD("Username:", "Nom d'utilisateur:"),
    PASSWORDFIELD("Password:", "Mot de passe:"),
    CHANGEMENU("Change menu", "Changer de menu"),
    RESUMELASTGAME("Resume last saved game", "«Reprendre la dernière partie sauvegardée»"),
    LOGOUT("Logout", "Déconnexion"),
    RESUME("Resume", "reprendre"),
    GIVEUP("Give up", "Abandonner"),
    SAVEANDLEAVE("save and leave", "sauvegarder et partir"),
    CHEATCODELABEL("-CHEAT CODES-", "-CODES DE TRICHE-"),
    LCHEAT("-L- Upgrades level", "-L- Niveau de mise à niveau"),
    RCHEAT("-R- Reduces 1 min", "-R- Réduit 1 min"),
    HCHEAT("-H- Adds health", "-H- Ajoute de la santé"),
    BCHEAT("-B- Boss fight", "-B- Combat de boss"),
    CCHEAT("-C- Clears enemies", "-C- Élimine les ennemis"),
    ABILITIESLABEL("-ABILITIES-", "-CAPACITÉS-"),
    BWDISPLAY("black and white display: ", "affichage noir et blanc :"),
    PLAY("PLAY", "JOUER"),
    GAMEDURATION("game duration :","durée du jeu :"),
    PLAYASGUEST("Play as guest", "Jouez en tant qu'invité"),
    ANSWERLABEL("Answer to security question", "Réponse à la question de sécurité"),
    CHANGEMUSIC("change music", "changer de musique"),
    MUSICVOLUME("music volume: ", "volume de la musique: "),
    AUTORELOAD("auto reload: ", "rechargement automatique: "),
    CONTROLLERSLABEL("keyboard controllers: ", "contrôleurs de clavier: "),
    CHOOSELABEL("choose your controllers", "choisissez vos contrôleurs"),
    ARROWS("Arrows", "Flèches"),
    LANGUAGE("english", "french"),
    CHANGEUSERNAME("change username", "changer de nom d'utilisateur"),
    ENTERNEWUSERNAME("enter your new username", "entrez votre nouveau nom d'utilisateur"),
    KILLSCOUNT("kills count: ", "nombre de victimes: "),
    LEVEL("level: ", "niveau: "),
    CHANGEPASSWORD("change password", "changer le mot de passe"),
    DELETEACCOUNT("delete account", "supprimer le compte"),
    WIN("YOU WON", "VOUS AVEZ GAGNÉ"),
    DEAD("YOU DIED", "VOUS ÊTES MORT"),
    CHANGEAVATAR("change avatar", "changer d'avatar"),
    SURVIVALTIME("survival time", "temps de survie")
    ;






    private final String englishText;
    private final String frenchText;
    Label(String english, String french) {
        this.englishText = english;
        this.frenchText = french;
    }

    public String getText() {
        if (GameSettings.language.equals("french")) return frenchText;
        return englishText;
    }
}
