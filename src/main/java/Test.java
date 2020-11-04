import com.nutrymaco.parser.config.DBConfiguration;
import com.nutrymaco.parser.config.JobConfig;
import com.nutrymaco.parser.job.GJParser;
import com.nutrymaco.parser.model.Tables;
import com.nutrymaco.parser.model.tables.pojos.Vacancy;
import com.nutrymaco.parser.pojo.GJSalary;
import org.jooq.DSLContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.SynchronousQueue;

import static com.nutrymaco.parser.model.Tables.VACANCY;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        GJParser parser = new GJParser(24*3600);
        parser.start();
    }
}
