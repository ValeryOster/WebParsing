package mainPack.SiteParsers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mainPack.Controller.AngebotElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

    @Override
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
                writeElementsInArray(link,doc.select("div.penny-themenwelt-product.pny_nonfood"));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeElementsInArray(String url, Elements elemens) {


        for (Element e : elemens){
            try {
                String[] hersteller = e.child(1).child(0).ownText().split(" ",2);
                System.out.println(hersteller[0]);
                System.out.println(hersteller[1]);
                if(hersteller[1].contains("*")) {
                    hersteller[1] = hersteller[1].replace("*", "");
                    System.out.println(hersteller[1]);
                }

                LocalDate date = LocalDate.now();
//                pennyOffers.add(new AngebotElement(
//                        hersteller[1],
//                        e.child(0).text(),
//                        url,
//                        e.child(0).getElementsByTag("img")
//                            .first().absUrl("data-src-retina"),
//                        date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
//                        "Penny Markt",
//                        hersteller[0],
//
//                ));
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
