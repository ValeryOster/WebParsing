package mainPack.SiteParsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mainPack.Controller.AngebotElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AldiParser implements ParserAll {
    private List <String> urlArray = new ArrayList<>() ;
    private List<AngebotElement> aldiOffers = new ArrayList<>();
    private String mainUrl = "https://www.aldi-nord.de/";

    public AldiParser() {
        getAllArrayUrl();
    }

    private void getAllArrayUrl() {
        Document doc;
        try {
            doc = Jsoup.connect(mainUrl).get();
            Elements root = doc.getElementById("ul_menu_3906").getElementsByTag("ul").first().child(0).getElementsByTag("li");

            if (root.select("a[href]").size() != 0) {
                Elements elements = root.select("a[href]");
                int zahler = 0;
                for (Element element : elements) {
                    if (zahler != 0)
                    {
                        urlArray.add( mainUrl+element.attr("href").toString() );

                    }
                    zahler++;
                }
                System.out.println(zahler);
            }else
                System.out.println("Problem with Navigation Parsing !!!");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AngebotElement> getOffers() {

        for (String url : urlArray) {
            getAllOffersByUrl(url);
        }

        return aldiOffers;
    }

    private void getAllOffersByUrl(String url) {
        Document doc;
        System.out.println(url);
        String offersName = "";
        String offersPrice = "";
        String offersManuf = "";
        String offersProp = "";
        String offersImgUrl = "";
        try {
            doc = Jsoup.connect(url).get();
            Elements elements1 = doc.getElementsByClass("offer");
            Elements elements2 = doc.getElementsByClass("product-tile");

            int i = 1;
            System.out.println("****************ELEMENTS Offer***************");
            for (Element element : elements1) {

                //Erste Element ist zerteilt worden, in einem liegt Bild in anderem Info
                if (element.getElementsByClass("detaillink").first() != null) {

                    offersName = element.getElementsByClass("detaillink").first().text();
                    offersPrice = element.getElementsByClass("main clearfix").first().
                                getElementsByTag("strong").first().text().replace("*","");

                }else {
                    Element el = element.parent().getElementsByClass("offer-1-content").first();

                    offersName = el.getElementsByClass("detaillink").first().text();
                    offersPrice = el.getElementsByClass("main clearfix").first().
                            getElementsByTag("strong").first().text().replace("*","");
                }

                System.out.println(i++ +" ----------------------");
                System.out.println("Name: " + offersName);
                System.out.println("Price: " + offersPrice);
                System.out.println("------------------------");
            }
            System.out.println("**************ELEMENTS product-tile Red****************");
            for (Element element : elements2) {
                if (element.getElementsByClass("detaillink").first() != null) {

                    offersName = element.getElementsByClass("detaillink").first().text();
                }else {

                }
                System.out.println(i++ +" ----------------------");
                System.out.println(element.text());
                System.out.println("------------------------");
            }




        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
