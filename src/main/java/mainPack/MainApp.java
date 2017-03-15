package mainPack;

import java.util.ArrayList;
import java.util.List;

import mainPack.Controller.AngebotElement;
import mainPack.SiteParsers.*;


public class MainApp {

    public static void main(String[] args) {
        new MainApp();
    }

    private List<AngebotElement> elements = new ArrayList<>();

    public MainApp() {
        getAllOffers(new AldiParser());
        getAllOffers(new LidlParser());
        getAllOffers(new NettoParser());
        getAllOffers(new PennyParser());
        getAllOffers(new ReweParser());

        int i = 1;
        String dialer = "";

        for (AngebotElement element : elements) {
            if (dialer.equals(element.getOffersDialer())){
                dialer = element.getOffersDialer();
                System.out.println("***************** - " + dialer + " - ******************");
            }
            System.out.println( i++ + " " + element.toString() );

        }
    }
    private void getAllOffers(ParserAll parser){
        elements = parser.getOffers();
    }
}
