package mainPack.SiteParsers;

import mainPack.Controller.AngebotElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PennyParser implements ParserAll {
    private List <String> urlArray ;
    private List<AngebotElement> pennyOffers = new ArrayList<>();

    public PennyParser() {
        this.urlArray = new ArrayList<>();
        urlArray.add("http://www.penny.de/angebote/aktuell//liste/Ab-Montag/");
        urlArray.add("http://www.penny.de/angebote/aktuell//liste/Ab-Donnerstag/");
        urlArray.add("http://www.penny.de/angebote/aktuell//liste/Ab-Freitag/");
        urlArray.add("http://www.penny.de/angebote/aktuell//liste/Non-Food/");
        urlArray.add("http://www.penny.de/angebote/vorschau/");
    }

    public List<AngebotElement> getOffers() {

        for(String url: urlArray){
                getAllOffersOfArray(url);
        }

        return pennyOffers;
    }

    //get All Offers of the Array
    private void getAllOffersOfArray(String link) {
        Document doc;
        try {
            doc = Jsoup.connect(link).get();


            if (doc.select("div.penny-themenwelt-product.pny_angebot").size() != 0) {

                writeElementsInArray(link,doc.select("div.penny-themenwelt-product.pny_angebot"));

            }else if (doc.select("div.penny-themenwelt-product.pny_nonfood").size() != 0){
                System.out.println(link);
                writeElementsInArray(link,doc.select("div.penny-themenwelt-product.pny_nonfood"));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeElementsInArray(String url, Elements elemens) {


        for (Element e : elemens){
            try {
                String[] hersteller = e.child(1).text().split(" ",2);
                LocalDate date = LocalDate.now();
                System.out.println(hersteller[0]);
                System.out.println(hersteller[1]);
                pennyOffers.add(new AngebotElement(
                        e.child(0).text(),
                        e.child(1).text(),
                        url,
                        e.child(0).getElementsByTag("img")
                            .first().absUrl("data-src-retina"),
                        date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        "Penny Markt",
                        hersteller[0]

                ));
            } catch (IndexOutOfBoundsException e1) {
                System.out.println(url);
            } catch (NullPointerException e2) {
                System.out.println("NullPointerExeption Hier ==> " + url);
                System.out.println("NullPointerExeption Hier ==> " + e.text());

            }
        }
    }
}
