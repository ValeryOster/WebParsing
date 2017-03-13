package mainPack;

import java.util.ArrayList;
import java.util.List;

import mainPack.Controller.AngebotElement;
import mainPack.SiteParsers.AldiParser;


public class MainApp {

    public static void main(String[] args) {
        new MainApp();
    }

    private List<AngebotElement> lidlElements = new ArrayList<>();
    private List<AngebotElement> pennyElements = new ArrayList<>();
    private List<AngebotElement> nettoElements = new ArrayList<>();
    private List<AngebotElement> aldiElements = new ArrayList<>();

    public MainApp() {

        AldiParser aldiParser = new AldiParser();
        aldiParser.getOffers();

//        String link = "";
//        int i = 1;
//        for (AngebotElement element : pennyElements) {
//            System.out.println( i++ + " " + element.toString() );
//            System.out.println();
//        }


    }
}
