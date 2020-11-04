import com.nutrymaco.parser.job.GJParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        GJParser parser = new GJParser(24*3600);
        parser.start();
    }
}
