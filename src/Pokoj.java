/**
 * Created by Mamel on 11.10.2016.
 */
public class Pokoj {
    public String nazwa;
    public double szerokosc;
    public double wysokosc;
    public double dlugosc;

    public Pokoj(String nazwa, double szerokosc, double wysokosc, double dlugosc){
        this.nazwa = nazwa;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.dlugosc = dlugosc;
    }

    public double getObjetosc(){
        return szerokosc*wysokosc*dlugosc;
    }
    public double getPowierzchniaScian()
    {
        return szerokosc*wysokosc*2 + dlugosc*wysokosc*2;
    }
    public double getKosztMalowania(double kosztFarby)
    {
        return getPowierzchniaScian() * kosztFarby;
    }
    public double getKosztPodlogi(double kosztMaterialu)
    {
        return szerokosc*dlugosc * kosztMaterialu;
    }
}
