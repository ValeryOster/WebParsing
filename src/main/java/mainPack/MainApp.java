package mainPack;

        import mainPack.Controller.AngebotElement;
        import mainPack.SiteParsers.LidlParser;
        import mainPack.SiteParsers.PennyParser;

        import java.util.ArrayList;
        import java.util.List;


public class MainApp {

    public static void main(String[] args) {
        new MainApp();
    }

    private List<AngebotElement> lidlElements = new ArrayList<AngebotElement>();
    private List<AngebotElement> pennyElements = new ArrayList<AngebotElement>();

    public MainApp() {

        PennyParser pennyParser = new PennyParser();
        pennyElements = pennyParser.getOffers();
        String link = "";
        for (AngebotElement element : pennyElements) {
            if (!link.equals(element.getOffersLink())) {
                link = element.getOffersLink();
                System.out.println(link);
            }
            System.out.println(element.getOffersName() + " => " + element.getOffersPrice());
        }


    }
}
