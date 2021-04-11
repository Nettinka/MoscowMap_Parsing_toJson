import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        String strURL = "http://www.cbr.ru/scripts/XML_daily.asp";

        Document document = Jsoup
                .connect(strURL)
                .userAgent("Mozilla")
                .timeout(10 * 1000)
                .get();
        Elements elements = document.select("valute");
        String text = "Гонконгских долларов";
        Pattern pattern = Pattern.compile("Гонконгских долларов");
        String value = "Value";
        for(Element el : elements){
            String str = el.toString();
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()){
                int ind1 = str.indexOf(value)+6;
                int ind2 = str.lastIndexOf(value)-2;

                System.out.println("1 Гонконгский доллар - " + str.substring(ind1, ind2).trim() + " руб.");
            }
        }
    }
}
