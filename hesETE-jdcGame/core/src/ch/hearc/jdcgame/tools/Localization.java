package ch.hearc.jdcgame.tools;

/**
 *
 */
public class Localization {
    
    public static String START_BTN ="JOUER";
    public static String QUIT_BTN  ="QUITTER";
    public static String REPLAY_BTN ="RECOMMENCER";
    public static String LEVEL_BTN ="NIVEAU";
    public static String BACK_BTN ="RETOUR";
    
    public static String LIFE_LAB ="Vie";
    public static String LEVEL_LAB ="Niveau";
    public static String Time_LAB ="Temps";
    
    public static String MESSAGE_1 = "Touche [ESC] pour revenir au jeu.";
    public static String MESSAGE_2 = "PAUSE";

    public static void setStartBtn(String name){
        START_BTN = name;
    }
    public static void setQuitBtn(String name){
        QUIT_BTN = name;
    }
    public static void setReplayBtn(String name){
        REPLAY_BTN = name;
    }
    public static void setLevelBtn(String name){
        LEVEL_BTN = name;
    }
    public static void setBackBtn(String name){
        BACK_BTN = name;
    }
    public static void setLifeLab(String name){
        LIFE_LAB = name;
    }

    public static void setLevelLab(String name) {
        LEVEL_LAB = name;
    }

    public static void setTimeLab(String name) {
        Time_LAB = name;
    }
    
    public static void setMessage(String name) {
        MESSAGE_1 = name;
    }
    public static void setMessage2(String name) {
        MESSAGE_2 = name;
    }
}
