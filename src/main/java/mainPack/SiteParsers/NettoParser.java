package mainPack.SiteParsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mainPack.Controller.AngebotElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NettoParser implements ParserAll {
    private List <String> urlArray = new ArrayList<>() ;
    private List<AngebotElement> pennyOffers = new ArrayList<>();

    public NettoParser() {
        urlArray.add("https://www.netto-online.de/13084.ohtm/6594/Drogerie");
        urlArray.add("https://www.netto-online.de/13078.ohtm/6594/Fisch");
        urlArray.add("https://www.netto-online.de/13077.ohtm/6594/Fleisch-Wurst");
        urlArray.add("https://www.netto-online.de/13080.ohtm/6594/Frische");
        urlArray.add("https://www.netto-online.de/13081.ohtm/6594/Markenstars");
        urlArray.add("https://www.netto-online.de/13076.ohtm/6594/Obst-Gemuese");
        urlArray.add("https://www.netto-online.de/13094.ohtm/6594/Samstags-Kracher");
        urlArray.add("https://www.netto-online.de/13086.ohtm/6594/TV-Stars");
        urlArray.add("https://www.netto-online.de/-1143.ohtm/6594/TV-Stars");
        urlArray.add("https://www.netto-online.de/13083.ohtm/6594/Wein-Co.");
        urlArray.add("https://www.netto-online.de/13012.ohtm/6594/Wochen-Knueller");
        urlArray.add("https://www.netto-online.de/13087.ohtm/6594/Wochenend-Angebote");
        urlArray.add("https://www.netto-online.de/13082.ohtm/6594/XXL");
    }

    @Override
    public List<AngebotElement> getOffers() {

        for (String url : urlArray) {
            getAllOffersOfArray(url);
        }
        return null;
    }

    private void getAllOffersOfArray(String url) {

        Document doc;
        try {
            doc = Jsoup.connect(url).get();


            if (doc.select("div.box_article.clean").size() != 0) {
                writeElementsInArray(url,doc.select("div.box_article.clean"));

            }else if (doc.select("div.penny-themenwelt-product.pny_nonfood").size() != 0){
                writeElementsInArray(url,doc.select("div.penny-themenwelt-product.pny_nonfood"));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeElementsInArray(String url, Elements elemens) {
        System.out.println(url);
        int i = 0;
        for (Element e : elemens){
            try {
                System.out.println(i++ +" -- " + e.text());
            } catch (IndexOutOfBoundsException e1) {
                System.out.println("IndexOutOfBoundsException Hier ==> " + url);
                System.out.println("IndexOutOfBoundsException Hier ==> " + e.text());
            } catch (NullPointerException e2) {
                System.out.println("NullPointerExeption Hier ==> " + url);
                System.out.println("NullPointerExeption Hier ==> " + e.text());

            }
        }
    }
}
