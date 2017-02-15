package mainPack.SiteParsers;

import mainPack.Controller.AngebotElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by voster on 09.02.2017.
 */
public class PennyParser implements ParserAll {
    private List <String> urlArray ;
    private List<AngebotElement> pennyOffers;

    public PennyParser() {
        this.urlArray = new ArrayList<String>();
        urlArray.add("http://www.penny.de/angebote/aktuell//liste/Ab-Montag/");
        urlArray.add("http://www.penny.de/angebote/aktuell//liste/Ab-Donnerstag/");
        urlArray.add("http://www.penny.de/angebote/aktuell//liste/Ab-Freitag/");
        urlArray.add("http://www.penny.de/angebote/aktuell//liste/Non-Food/");
        urlArray.add("http://www.penny.de/angebote/vorschau/");
    }

    public List<AngebotElement> getOffers() {

        for(String url: urlArray) getAllOffersOfArray(url);

        return pennyOffers;
    }
    //get All Offers of the Array
    private void getAllOffersOfArray(String link) {
        Document doc;
        Element elm;
        try {
            doc = Jsoup.connect(link).get();
            System.out.println(link);
            Elements elemens = doc.select("div.penny-themenwelt-product.pny_angebot");

            int agbnummer = 0;
            if (elemens.size() != 0) {
                for (Element e : elemens){
                    try {
//                        System.out.println("Angebot Nr: " + (++agbnummer));
//                        System.out.println(e.child(1).text());
//                        System.out.println(e.child(0).text());

                        pennyOffers.add(new AngebotElement(
                                e.child(0).text(),
                                e.child(1).text(),
                                link));
                    } catch (IndexOutOfBoundsException e1) {
                        System.out.println(link);
                    } catch (NullPointerException e2) {
                        System.out.println("NullPointerExeption Hier ==> " + link);
                        System.out.println("NullPointerExeption Hier ==> " + e.text());

                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
