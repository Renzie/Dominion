package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by Renzie on 14/04/2016.
 */
public class Actiekaart {

    public void speelactiekaart(String naam, Speler speler, Spel spel) {
        switch (naam) {
            case "Heks":
                heks(spel, speler);
                break;
            case "Kelder":
                kelder(spel, speler);
                break;
            case "Kerk":
                kerk(spel, speler);
                break;
            case "Gracht":
                gracht(speler);
                break;
            case "Kanselier":
                kanselier(speler);
                break;
            case "Dorps":
                dorps(speler);
                break;
            case "Houthakker":
                houthakker(speler);
                break;
            case "Werkplaats":
                werkplaats(spel,speler);
                break;
            case "Bureaucraat":
                bureaucraat(spel, speler);
                break;
            case "Feest":
                feest(spel, speler);
                break;
            case "Schutterij":
                schutterij(spel, speler);
                break;
            case "Geldverlener":
                geldverlener(spel, speler);
                break;
            case "Ombouwen":
                ombouwen(spel,speler);
                break;
            case "Smederij":
                smederij(speler);
                break;
            case "Spion":
                spion(spel, speler);
                break;
            case "Dief":
                dief(spel, speler);
                break;
            case "Troonzaal":
                troonzaal(spel, speler);
                break;
            case "Raadzaal":
                raadzaal(spel, speler);
                break;
            case "Festival":
                festival(speler);
                break;
            case "Laboratorium":
                laboratorium(speler);
                break;
            case "Bibliotheek":
                bibliotheek(speler);
                break;
            case "Markt":
                markt(speler);
                break;
            case "Mijn":
                mijn(spel, speler);
                break;
            case "Avonturier":
                avonturier(speler);
                break;
            case "Tuinen":
                tuinen(speler);
                break;
        }
    }

    public Kaart kiesKaart(Speler speler, String input){
        Scanner keyboard = new Scanner(System.in);
        int i = 0;
        for (Kaart k : speler.getHand()) {
            System.out.println(k.getNaam() + " | " + i);
            i++;
        }
        input = keyboard.nextLine();
        Kaart gekozenkaart = speler.getHand().get(Integer.parseInt(input));
        return gekozenkaart;
    }

    public boolean heeftReactiekaart(Speler s){
        for(Kaart k : s.getHand()){
            if(Objects.equals(k.getType(), "Actie-Reactie")){
                return true;
            }
        }
        return false;
    }

    public Kaart duidSpecifiekeKaartAan(String naam, Spel spel) {
        for (Kaart k : spel.getAlleKaarten()) {
            if (Objects.equals(k.getNaam(), naam)) {
                return k;
            }
        }
        return null;
    }

    public Kaart kiesKaartMetSoort(String waarde, String soort, List<Kaart> bron){
        String input;
        Scanner keyboard = new Scanner(System.in);
        List<Kaart> koopopties = new ArrayList<>();
        int i = 0;

        for(Kaart k: bron){
            if(!koopopties.contains(k) && checkKaart(soort, waarde, k)){
                System.out.println(k.getNaam() + " | " + i);
                koopopties.add(k);
                i++;
            }
        }

        input = keyboard.nextLine();
        return koopopties.get(Integer.parseInt(input));
    }

    public boolean checkKaart(String soort, String waarde, Kaart k){
        if(Objects.equals(soort, "type")){
            if(Objects.equals(waarde, "Actie")){
                return Objects.equals(k.getType(), waarde) || Objects.equals(k.getType(), "Actie-Reactie") || Objects.equals(k.getType(), "Actie-Aanval");
            } else {
                return Objects.equals(k.getType(), waarde);
            }
        } else if (Objects.equals(soort, "kost")){
            return k.getKost()<= Integer.parseInt(waarde);
        }
        return false;
    }

    public void kaartAfleggen(Speler speler, int aantal){
        String input = "";
        Scanner keyboard = new Scanner(System.in);

        boolean besloten = false;
        while(!besloten){
            input = keyboard.nextLine();
            if(Objects.equals(input, "J") || Objects.equals(input, "j")){
                speler.voegKaartToe(aantal, speler.getDeck(), speler.getAflegstapel());
                besloten = true;
            } else if (!Objects.equals(input, "N") || !Objects.equals(input, "n")){
                System.out.println("Ongeldige invoer, probeer opnieuw.");
            } else {
                besloten = true;
            }
        }
    }

    public void heks(Spel spel, Speler speler) {
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand()); //+2 kaarten
        for (Speler s : spel.getSpelers()) { //geef de andere spelers een vloekkaart
            if (!Objects.equals(s.getNaam(), speler.getNaam()) && !heeftReactiekaart(s)) {
                spel.koopKaart(duidSpecifiekeKaartAan("Vloek", spel), s.getAflegstapel());
            }
        }
    }

    public void kelder(Spel spel, Speler speler) {
        //+1 actie
        speler.addActie(1);
        //selecteer de kaarten die je wilt afleggen
        String input = "";
        int aantalkaarten = 0;

        System.out.println("Kies de kaarten die je wilt afleggen, typ 'OK' om door te gaan: \n");
        while (!Objects.equals(input, "OK")) {
            spel.voegKaartToe(1, kiesKaart(speler, input), speler.getHand(), speler.getAflegstapel());
            aantalkaarten++;
        }
        //trek x nieuwe kaarten
        speler.voegKaartToe(aantalkaarten, speler.getDeck(), speler.getHand());
    }

    public void kerk(Spel spel, Speler speler) {
        //plaats tot 4 kaarten in de vuilbak
        int aantalkaarten = 0;
        String input = "";

        System.out.println("Kies max 4 kaarten die je wilt wegsmijten, typ 'OK' om door te gaan: ");
        while (!Objects.equals(input, "OK") || aantalkaarten<4) {
            spel.voegKaartToe(1, kiesKaart(speler, input), speler.getHand(), speler.getVuilbak());
            aantalkaarten++;
        }
    }

    public void gracht(Speler speler) {
        //trek 2 kaarten
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand());
    }

    public void kanselier(Speler speler) {
        speler.addGeld(2);//+2 geld
        //je mag je deck in de aflegstapel gooien
        System.out.println("Wil je je deck in de aflegstapel gooien? J/N");
        kaartAfleggen(speler, speler.getDeck().size());
    }

    public void dorps(Speler speler) {
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        speler.addActie(2);
    }

    public void houthakker(Speler speler) {
        speler.addKoop(1);
        speler.addGeld(2);
    }

    public void werkplaats(Spel spel, Speler speler) {
        //neem een kaart met <=4 kost
        System.out.println("Kies een kaart met een kost van maximum 4: \n");
        spel.koopKaart(kiesKaartMetSoort("4", "kost", spel.getAlleKaarten()), speler.getAflegstapel());
    }

    public void bureaucraat(Spel spel, Speler speler) {
        //+1 actiekaart, plaats deze op je deck
        System.out.println("Kies een actiekaart: ");
        kiesKaartMetSoort("Actie", "type", spel.getAlleKaarten());

        //elke andere speler toont een overwinningskaart en plaatst het op zijn deck (of toont een hand zonder overwinningskaarten)
        List<Kaart> koopopties = new ArrayList();
        int i = 0;
        String input = "";
        Scanner keyboard = new Scanner(System.in);

        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam()) && !heeftReactiekaart(s)) {
                System.out.println(s.getNaam() + "\n");
                i = 0;
                for (Kaart k : s.getHand()){
                    if (Objects.equals(k.getType(), "Overwinning")){
                        System.out.println(k.getNaam() + "|" + i);
                        i++;
                    }
                }
                input = keyboard.nextLine();
                Kaart teVerplaatsenKaart = koopopties.get(Integer.parseInt(input));
                spel.voegKaartToe(1, teVerplaatsenKaart, s.getHand(), s.getDeck());
                if(i == 0){
                    for(Kaart k2 : s.getHand()){
                        System.out.println(k2.getNaam());
                    }
                }
            }
        }
    }

    public void feest(Spel spel, Speler speler) {
        //deze kaart naar trash
        spel.voegKaartToe(1, duidSpecifiekeKaartAan("Feest", spel), speler.getHand(), speler.getVuilbak());

        //neem kaart die max 5 geld kost
        System.out.println("Kies een kaart met een kost van maximum 5: \n");
        kiesKaartMetSoort("5", "kost", spel.getAlleKaarten());
    }

    public void schutterij(Spel spel, Speler speler) {
        //+2Geld
        speler.addGeld(2);
        //leg kaarten af tot alle spelers 3 kaarten over heeft
        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam()) && !heeftReactiekaart(s)) {
                while (s.getHand().size() > 3) {
                    String input = "";

                    System.out.println("Kies de kaarten die je wilt verwijderen: \n");
                    spel.voegKaartToe(1, kiesKaart(speler, input), s.getHand(), s.getAflegstapel());
                }
            }
        }
    }

    public void geldverlener(Spel spel, Speler speler) {
        //thrash koper
        //krijg +3 geld
        String input = "";
        System.out.println("Kies de koperkaart die je wilt verwijderen: \n");
        Kaart teVerwijderenKaart = kiesKaart(speler, input);

        if (Objects.equals(teVerwijderenKaart.getNaam(), "Koper")) {
            spel.voegKaartToe(1, teVerwijderenKaart, speler.getHand(), speler.getVuilbak());
            speler.addGeld(3);
        } else {
            System.out.println("Dit is geen koperkaart, probeer opnieuw.");
            geldverlener(spel, speler);
        }
    }

    public void ombouwen(Spel spel, Speler speler) {
        //select kaart -> thrash
        String input = "";
        System.out.println("Kies een kaart om weg te smijten: ");
        Kaart gekozenkaart = kiesKaart(speler, input);
        int trashkaartwaarde = gekozenkaart.getWaarde();
        spel.voegKaartToe(1, gekozenkaart, speler.getHand(), speler.getVuilbak());

        //krijg een kaart die tot 2 meer geld kost
        System.out.println("Kies de kaart die je wilt nemen: \n");
        kiesKaartMetSoort(trashkaartwaarde + "2", "kost", spel.getAlleKaarten());
    }

    public void smederij(Speler speler) {
        speler.voegKaartToe(3, speler.getDeck(), speler.getHand());
    }

    public void spion(Spel spel, Speler speler) {
        //+1 kaart
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        //+1 actie
        speler.addActie(1);
        //elke speler bekijkt de bovenste kaart van zijn deck en de speler kan beslissen of het naar de aflegstapel gaat
        for(Speler s : spel.getSpelers()){
            if(!heeftReactiekaart(s) || Objects.equals(s.getNaam(), speler.getNaam())){
                Kaart k = s.getDeck().get(0);
                System.out.println(speler.getNaam() + ", wil je " + k.getNaam() + " van " + s.getNaam() + " wegleggen? J/N");
                kaartAfleggen(speler, 1);
            }
        }
    }

    public void dief(Spel spel, Speler speler) {

        /*Each other player reveals the top 2 cards of his deck.
        If they revealed any Treasure cards, they trash one of them that you choose.
        You may gain any or all of these trashed cards.
        They discard the other revealed cards.*/

        for (Speler s : spel.getSpelers()) {
            if (!Objects.equals(s.getNaam(), speler.getNaam()) && !heeftReactiekaart(s)) {
                for(int i =0;i<2;i++){
                    Kaart k = s.getDeck().get(i);
                    System.out.println(k.getNaam());
                    if (Objects.equals(k.getType(), "Geld")){
                        System.out.println("Wil je deze kaart stelen en op je aflegstapel plaatsen? J/N\n");
                        kaartAfleggen(speler, 1);
                    }
                }
            }
        }
    }

    public void troonzaal(Spel spel, Speler speler) {
        //kies een actiekaart
        String naamvangekozenkaart = kiesKaartMetSoort("Actie", "type", speler.getHand()).getNaam();

        //effect gekozen actiekaart*2
        speelactiekaart(naamvangekozenkaart, speler, spel);
        speelactiekaart(naamvangekozenkaart, speler, spel);

    }

    public void raadzaal(Spel spel, Speler speler) {
        //+4 kaarten
        speler.voegKaartToe(4, speler.getDeck(), speler.getHand());
        //+1 koop
        speler.addKoop(1);
        //andere spelers trekken 1 kaart
        for(Speler s : spel.getSpelers()){
            if(!Objects.equals(s.getNaam(), speler.getNaam())){
                s.voegKaartToe(1, s.getDeck(), s.getHand());
            }
        }
    }

    public void festival(Speler speler) {
        //+2 acties +1 kaart +2geld
        speler.addActie(2);
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        speler.addGeld(2);
    }

    public void laboratorium(Speler speler) {
        //+2 kaarten
        speler.voegKaartToe(2, speler.getDeck(), speler.getHand());
        //+1actie
        speler.addActie(1);
    }

    public void bibliotheek(Speler speler) {
        while (speler.getHand().size() < 7) {
            if(Objects.equals(speler.getDeck().get(0).getType(), "Actie") || Objects.equals(speler.getDeck().get(0).getType(), "Actie-Reactie") || Objects.equals(speler.getDeck().get(0).getType(), "Actie-Aanval")){
                System.out.println("Wil je " + speler.getDeck().get(0).getNaam() + " aan de kant leggen? J/N");
                int oldhandsize = speler.getHand().size();
                kaartAfleggen(speler, 1);
                int newhandsize = speler.getHand().size();
                if(oldhandsize == newhandsize){
                    speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
                }
            } else {
                speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
            }
        }
    }

    public void markt(Speler speler) {
        //+1 kaart
        speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
        //+1geld +1 koop +1actie
        speler.addGeld(1);
        speler.addKoop(1);
        speler.addActie(1);
    }

    public void mijn(Spel spel, Speler speler) {
        //thrash een geldkaart en geef de geldkaart met 1 waarde meer
        String input = "";
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Kies de kaart die je wilt verwijderen: \n");
        Kaart gekozenkaart = kiesKaartMetSoort("Geld", "type", speler.getHand());
        int kaartkost = gekozenkaart.getKost();
        spel.voegKaartToe(1, gekozenkaart, speler.getHand(), speler.getVuilbak());

        System.out.println("Kies een kaart om te kopen: \n");
        Kaart tekopenkaart = kiesKaartMetSoort(kaartkost + "3", "kost", speler.getHand());

        while(!Objects.equals(tekopenkaart.getType(), "Geld")){
            System.out.println("Dit is geen geldkaart");
            tekopenkaart = kiesKaartMetSoort(kaartkost + "3", "kost", speler.getHand());
        }

        spel.koopKaart(tekopenkaart, speler.getHand());
    }

    public void avonturier(Speler speler) {
        //blijf kaarten trekken tot je 2 geldkaarten krijgt
        int maxGeldKaarten = 2;
        int i = 0;
        while (i < maxGeldKaarten) {
            Kaart bovenliggendeKaart = speler.getDeck().get(0);
            if (!Objects.equals(bovenliggendeKaart.getType(), "Geld")) {
                speler.voegKaartToe(1, speler.getDeck(), speler.getAflegstapel());
            } else {
                speler.voegKaartToe(1, speler.getDeck(), speler.getHand());
                i++;
            }
        }
    }

    public void tuinen(Speler speler){
        //+1 overwinningspunten voor elke 10 kaarten
        int aantalkaarten = speler.getDeck().size()+speler.getAflegstapel().size()+speler.getHand().size();
        aantalkaarten = aantalkaarten/10;
        speler.addOverwinningspunten(aantalkaarten);
    }
}