
import java.io.IOException;


public class main {




    public static void main(String[] args) throws IOException {

        pageWiki test = new pageWiki(args[0]);
        //test.getLinks();
        System.out.println(test.recherche());
        System.out.println("DONE");


    }

}
