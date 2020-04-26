public class Execption_dodolffound extends Exception
{
    // Parameterless Constructor


    // Constructor that accepts a message
    pageWiki dodolf ;
    public Execption_dodolffound(pageWiki p)
    {

        this.dodolf = p ;
    }
    public void afficher(){
        System.out.println("distance :"+dodolf.profondeur+ " : ");
        pageWiki p = dodolf ;
        System.out.println(p.link);
        while(p != null ){
            if(p.pere != null ) {
                System.out.println(p.pere.link);
            }
            p = p.pere ;
        }
        System.out.println("Pages parcourues :"+ pageWiki.pageVisite.size());
    }
}
