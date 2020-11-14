import com.nutrymaco.parser.job.GJParser;


public class Main {
    public static void main(String[] args) throws InterruptedException {

        while (true){
            GJParser parser = new GJParser(24 * 3600);
            parser.start();
        }
    }
}
