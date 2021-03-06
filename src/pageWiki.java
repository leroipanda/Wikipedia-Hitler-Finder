
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class pageWiki {
    String link ;
    int profondeur = 0 ;
    public pageWiki pere ;
    public static ArrayList<String> pageVisite = new ArrayList<String>();
    public static ArrayList<pageWiki> pile = new ArrayList<pageWiki>();
    public static boolean found = false ;
    public static int pronfTrouve = 0 ;
    public static String addWiki = "https://fr.wikipedia.org" ;
    //pour les ban categorie
    public static boolean banActif =false ;
    public static ArrayList<String> ban = new ArrayList<String>();
    public pageWiki(String url ) {
        this.link = url ;

    }

    public pageWiki(String url , int pronf , pageWiki pere ) {
        this.link = url ;
        this.profondeur = pronf ;
        this.pere = pere ;
    }

    public void getLinks() throws IOException {
        Set<String> e = null;
        e = findLinks(this.link);
        for (String link :e) {
            //on traite les liens ,on regarde les quelle commence par /wiki
            if(link.startsWith("/wiki")) {
                System.out.println(link);
            }
        }

    }

    public void visite() throws IOException, Execption_dodolffound {
        pageWiki.pile.remove(this);
        Set<String> e = null;
        e = findLinks(this.link);
        //System.out.println(pageWiki.banActif);
        if(!pageWiki.banActif ||( !this.isBanned(e)  ) ||this.profondeur == 0) {
            for (String link : e) {
                if (link != null) {

                    //on traite les liens ,on regarde les quelle commence par /wiki
                    if (link.startsWith("/wiki")) {


                        if (link.equals("/wiki/Adolf_Hitler")) {
                            pageWiki.found = true;
                            pageWiki.pronfTrouve = this.profondeur;
                            throw new Execption_dodolffound(new pageWiki(pageWiki.addWiki + link, this.profondeur + 1, this));
                        }


                        //onregarde si il n a pas ete visité
                        if ((!link.startsWith("/wiki/Portail:")) && (!listStringContains(pageWiki.addWiki + link))) {
                            //on l'ajoute à la pile
                            pageWiki p = new pageWiki(pageWiki.addWiki + link, this.profondeur + 1, this);
                            pageWiki.pile.add(p);
                            pageWiki.pageVisite.add(pageWiki.addWiki + link);
                            System.out.println(this.profondeur + "--:" + pageWiki.addWiki + link);
                            //System.out.println(this.profondeur +":"+this.link );
                        }
                    }
                }
            }
        }
        else{
            //la page a été ignoré par la banlist
            System.out.println("--Page Ignorée :"+this.link  );
        }
    }

    public int recherche() throws IOException {
        try {
            this.visite();
        } catch (Execption_dodolffound execption_dodolffound) {
            execption_dodolffound.printStackTrace();
        }
        while(!pageWiki.found){
            ArrayList<pageWiki> copie = (ArrayList<pageWiki>) pageWiki.pile.clone();
            for(pageWiki page : copie ) {
                try {
                    page.visite();
                } catch (Execption_dodolffound execption_dodolffound) {
                    execption_dodolffound.afficher();
                    System.exit(0); ;
                }
            }

        }
        return pageWiki.pronfTrouve;
    }

    private static Set<String> findLinks(String url) throws IOException {
        Set<String> links = new HashSet<>();
        try {


            Document doc = Jsoup.connect(url)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .get();

            Elements elements = doc.select("a[href]");
            for (Element element : elements) {
                links.add(element.attr("href"));
            }

            return links;
        }
        catch (Exception e){
            try {
                System.out.println("!!!page wikipedia indisponible -- "+url);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return links ;

        }

    }

    private boolean listStringContains(String lien){
        for(String s :  pageWiki.pageVisite){
           if( s.equals(lien) ){
               return true;
           }
           // System.out.println(lien +"!="+ s );
        }

        return false ;
    }
    public boolean isBanned(Set<String> listLien){
        System.out.println("check ban");
        for(String pageBan : pageWiki.ban){
            if(listLien.contains(pageBan)){
                return true ;
            }
        }
        return false ;


    }
}

