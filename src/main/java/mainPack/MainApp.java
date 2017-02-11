package mainPack;

        import mainPack.Controller.AngebotElement;
        import mainPack.SiteParsers.LidlParser;

        import java.util.ArrayList;
        import java.util.List;


public class MainApp {

    public static void main(String[] args) {
        new MainApp();
    }

    private List<AngebotElement> lidlElements = new ArrayList<AngebotElement>();

    public MainApp() {

        LidlParser lidlParser = new LidlParser();
        lidlElements = lidlParser.getOffers();

        for (AngebotElement element :
                lidlElements) {
            System.out.println(element.getOffersName() + " => " + element.getOffersLink());
        }
    }
}
