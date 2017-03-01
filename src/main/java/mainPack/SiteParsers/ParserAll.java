package mainPack.SiteParsers;

import java.util.Arrays;
import java.util.List;

import mainPack.Controller.AngebotElement;


public interface ParserAll {
    List<String> possibleWords = Arrays.asList(
        "Die", "DIE","Der","Das","Für", "Dr.", "DR.", "MON");
    List<AngebotElement> getOffers();
}
