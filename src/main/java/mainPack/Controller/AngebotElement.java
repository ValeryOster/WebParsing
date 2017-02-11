package mainPack.Controller;


public class AngebotElement {
    private String offersName;
    private String offersLink;
    private String offersImageLink;

    public AngebotElement() {
    }

    public AngebotElement(String offersName, String offersLink) {

        this.offersName = offersName;
        this.offersLink = offersLink;
    }
    public AngebotElement(String offersName, String offersLink, String offersImageLink) {

        this.offersName = offersName;
        this.offersLink = offersLink;
        this.offersImageLink = offersImageLink;
    }


    public String getOffersName() {
        return offersName;
    }

    public void setOffersName(String offersName) {
        this.offersName = offersName;
    }

    public String getOffersLink() {
        return offersLink;
    }

    public void setOffersLink(String offersLink) {
        this.offersLink = offersLink;
    }

    public String getOffersImageLink() {
        return offersImageLink;
    }

    public void setOffersImageLink(String offersImageLink) {
        this.offersImageLink = offersImageLink;
    }
}
