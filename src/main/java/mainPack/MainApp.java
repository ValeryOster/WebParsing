package mainPack;

import mainPack.SiteParsers.LidlParser;


public class MainApp {
    public static void main(String[] args) {
        LidlParser lidlParser = new LidlParser();
        lidlParser.getOffers();
    }
}
