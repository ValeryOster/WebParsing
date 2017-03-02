package mainPack;

        import java.util.ArrayList;
import java.util.List;

import mainPack.Controller.AngebotElement;
        import mainPack.SiteParsers.PennyParser;


public class MainApp {

    public static void main(String[] args) {
        new MainApp();
    }

    private List<AngebotElement> lidlElements = new ArrayList<>();
    private List<AngebotElement> pennyElements = new ArrayList<>();
    private List<AngebotElement> nettoElements = new ArrayList<>();

    public MainApp() {

//        NettoParser nettoParser = new NettoParser();
//        nettoElements = nettoParser.getOffers();
        PennyParser pennyParser = new PennyParser();
        pennyElements = pennyParser.getOffers();
        String link = "";
        int i = 1;
        for (AngebotElement element : pennyElements) {
            System.out.println( i++ + " " + element.toString() );
            System.out.println();
        }


    }
}
