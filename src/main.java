
import java.io.IOException;


public class main {




    public static void main(String[] args) throws IOException {
        pageWiki.banActif = false ;
        pageWiki start = new pageWiki(args[0]);
        if(args.length > 1 ){
            //on traite les arguments
            for(String arg : args){
                if(arg.equals("-ban")){
                    System.out.println("---ban actif---");
                    pageWiki.banActif = true ;
                    pageWiki.ban.add("/wiki/Coordonnées_géographiques");
                    pageWiki.ban.add("/wiki/Modèle:Infobox_Langue");
                    pageWiki.ban.add("/wiki/Mod%C3%A8le:Infobox_Territoire") ;
                    pageWiki.ban.add("/wiki/Coordonn%C3%A9es_g%C3%A9ographiques");
                    pageWiki.ban.add("/wiki/Modèle:Infobox_Éphéméride");
                    pageWiki.ban.add("/wiki/%C3%89ph%C3%A9m%C3%A9ride_(calendrier)");
                    pageWiki.ban.add("/wiki/Recensement_de_la_population");
                    //pageWiki.ban.add("/wiki/Wikip%C3%A9dia:Accueil_principal");
                }
            }

        }

        //test.getLinks();
        System.out.println(start.recherche());
        //System.out.println("DONE");


    }

}
