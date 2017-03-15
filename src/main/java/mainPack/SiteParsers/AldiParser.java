package mainPack.SiteParsers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import mainPack.Controller.AngebotElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AldiParser implements ParserAll {
    private List <String> urlArray = new ArrayList<>() ;
    private String mainUrl = "https://www.aldi-nord.de/";

    //All Urls that not a offers
    private List<String> zeroUrls = new ArrayList<>();

    public AldiParser() {
        getAllArrayUrl();
        zeroUrls.add(mainUrl + "");
        zeroUrls.add(mainUrl + "index.html");
        zeroUrls.add(mainUrl + "unsere_angebote.html");
        zeroUrls.add(mainUrl + "index.html");
        zeroUrls.add(mainUrl + "#top");
        zeroUrls.addAll(urlArray);
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
                        urlArray.add(mainUrl+element.attr("href").toString() );

                    }
                    zahler++;
                }
            }else
                System.out.println("**** AldiParser **** Problem with Navigation Parsing !!!");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AngebotElement> getOffers() {

        for (String url : urlArray) {
            collectAllOffersUrl(url);
        }

        return offers;
    }

    //Gathering all urls of offer, to parse it in getAllOffersByUrl()
    private void collectAllOffersUrl(String url) {
        Document doc;
        HashSet<String> allOffersUrls = new HashSet<>();
        try {
            doc = Jsoup.connect(url).get();
            Elements mainEl = doc.getElementById("content_").select("a");

            for (Element element :
                    mainEl) {
                String linkUrl = mainUrl+"" + element.attr("href");

                if (!zeroUrls.contains(linkUrl) && !linkUrl.contains(".php") && !allOffersUrls.contains(linkUrl)) {
                   getAllOffersByUrl(linkUrl);
                   allOffersUrls.add(linkUrl);
                }

            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getAllOffersByUrl(String url) {
        Document doc;

        String offersName = "";
        String offersPrice = "";
        String offersManuf = "";
        String offersProp = "";
        String offersImgUrl = "";
        try {
            doc = Jsoup.connect(url).get();
            Element mainElement = doc.getElementsByClass("product-content-left").first();
            offersName = mainElement.getElementsByAttribute("itemprop").first().text();
            offersPrice = doc.getElementsByClass("price-tag").first().
                            getElementsByAttribute("itemprop").first().
                            child(0).child(1).text().replace("*","");
            offersManuf = mainElement.getElementsByClass("manufacturer").first()
                            .text().replace("®"," ");
            offersProp = mainElement.getElementsByClass("richtext").first()
                    .text().replace("®","");
            offersImgUrl = doc.getElementsByClass("stage-product").first()
                            .getElementsByClass("stage-item").first().select("a").first().absUrl("href");
            LocalDate date = LocalDate.now();

            offers.add(new AngebotElement(offersName,
                                        offersPrice,
                                        url,
                                        offersImgUrl,
                                        "Aldi-Nord",
                                        offersManuf,
                                        date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                                        offersProp ));


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
