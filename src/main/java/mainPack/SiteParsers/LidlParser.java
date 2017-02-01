package mainPack.SiteParsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LidlParser implements ParserAll{
    private String mainUrl = "https://www.lidl.de/de/angebote";

    private Map<String,String> lildArrays = new HashMap<String,String >();

    public List<String> getOffers() {

        getAllOffersSectors(getOfferForNow());
        for (String mEntry : lildArrays.keySet())
        {
            //System.out.println(mEntry+" , "+lildArrays.get(mEntry));
            if(mEntry.contains("Montag")){
                getAllOffersOfArray(mainUrl+"/"+lildArrays.get(mEntry));
                break;
            }
        }
        return null;
    }

    //Bekommen den Link für alle Angebotten diese Wochen
    private String getOfferForNow(){
        Document doc;
        String linkOffersNow = "";
        try {
            doc = Jsoup.connect(mainUrl).get();
            Elements elements =  doc.select("body > div.shifter-page > div > section.main > div.wrapper.content > div > div > div.space.p-lr > ul");
            if(!elements.isEmpty())
            {
                for (Element e :
                        elements) {
                    //Finden wir element mit Tag "diese Woche"
                    List<Element> list = e.children();
                    for(Element el: list){
                        if(el.text().contains("diese Woche"))
                            linkOffersNow = mainUrl + el.child(0).tagName("href").attr("href");
                    }
                }
            }else
                System.out.println("Array ist leer!");

        }catch (IOException e) {
            e.printStackTrace();
        }
        return linkOffersNow;
    }
    //Finden specifische Abteilungen für Lidl
    private void getAllOffersSectors(String link) {
        Document doc;
        try {
            doc = Jsoup.connect(link).get();

/*            BufferedWriter htmlWriter =
                    new BufferedWriter(new OutputStreamWriter(new FileOutputStream("meinAngebot.html"), "UTF-8"));
            htmlWriter.write(doc.toString());*/

            Elements elements =  doc.select("body > div.shifter-page > div > section.main > div.wrapper.content > div > div > div.space.p-l > div > div");
            if(!elements.isEmpty())
            {
                Elements es = elements.first().children();

                for (Element e : es)
                {
                    Element elem = e.child(0).tagName("href");
//                    lildArrays.add();
                    lildArrays.put(e.text(), elem.child(0).tagName("href").attr("href"));
                }
            }else
                System.out.println("Array ist leer!");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get All Offers of the Array
    private void getAllOffersOfArray(String link) {
        Document doc;
        try {
            doc = Jsoup.connect(link).get();
            Elements elemens = doc.select("#theme1 > div:nth-child(1)");
            for (Element e: elemens) System.out.println(e.child(0).text());

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    //get All imposible Array for Offer of this weck
    public Map<String,String > getLildArrays() {
        return lildArrays;
    }
}
