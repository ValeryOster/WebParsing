package mainPack.SiteParsers;

import java.util.ArrayList;
import java.util.List;

import mainPack.Controller.AngebotElement;

public class AldiParser implements ParserAll {
    private List <String> urlArray = new ArrayList<>() ;
    private List<AngebotElement> aldiOffers = new ArrayList<>();

    @Override
    public List<AngebotElement> getOffers() {
        return null;
    }
}
