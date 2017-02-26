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

    private List<AngebotElement> lidlElements = new ArrayList<>();
    private List<AngebotElement> pennyElements = new ArrayList<>();

    public MainApp() {

        PennyParser pennyParser = new PennyParser();
        pennyElements = pennyParser.getOffers();
        System.out.println("In List");
        String link = "";
//        for (AngebotElement element : pennyElements) {
//            if (!link.equals(element.getOffersLink())) {
//                link = element.getOffersLink();
//                System.out.println(link);
//            }
//            System.out.println(element.getOffersName() + " => " + element.getOffersImageLink());
//        }


    }
}
