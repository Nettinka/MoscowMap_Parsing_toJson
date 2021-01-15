import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class WEBParser {

    private static String url = "https://www.moscowmap.ru/metro.html#lines";


    public static void linesParsing(ArrayList<Lines> lines) throws IOException {

        Document doc = Jsoup.connect(url).maxBodySize(0)
                .userAgent("Mozilla").timeout(1000 * 10)
                .get();
        Elements h1elements = doc.select("#metrodata");  //выделение куска кода с данными о станциях, линиях и пересадках

        Elements linesNames = h1elements.select("[data-line]");

        String dataLine = "data-line";
        int k = dataLine.length();
        for (Element e : linesNames) {
            if (e.select("span[data-line]").toString().length() > 1) {   //
                int i;
                i = e.select("span[data-line]").toString().lastIndexOf(dataLine);  //поиск последнего индекса вхождения data-line
                String key = e.select("span[data-line]").toString().substring(i + k, i + k + 5).replaceAll("[^\\w]", ""); //выделение номера линии
                String value = e.ownText(); //название линии
                Lines line = new Lines(value, key);
                lines.add(line);
            }
        }
    }

    public static void stationsParsing(ArrayList<Stations> stations) throws IOException {
        Document doc = Jsoup.connect(url).maxBodySize(0)
                .userAgent("Mozilla").timeout(1000 * 10)
                .get();
        String dataLine = "data-line";
        int k = dataLine.length();
        Elements h1elements = doc.select("#metrodata").select("div[data-line]");  //выделение куска кода с данными о станциях
        for (Element el : h1elements) {
            String stationNameWithNum = el.select("span").text();
            String stationName = stationNameWithNum.replaceAll("\\.", ",").replaceAll("[0-9]", "").substring(1).replaceAll("\\s{2,}", "").trim();
            int i;
            i = el.toString().lastIndexOf(dataLine);
            String stationNum = el.toString().substring(i + k, i + k + 5).replaceAll("[^\\w]", "");
            Stations stat = new Stations(stationName, stationNum);
            stations.add(stat);
        }
    }

    public static void connectionParsingtoSet(HashSet<Connections> connectionsSet) throws IOException {

        Document doc = Jsoup.connect(url).maxBodySize(0)
                .userAgent("Mozilla").timeout(1000 * 10)
                .get();
        String dataLine = "data-line";
        int k = dataLine.length();
        Elements h1elements = doc.select("#metrodata").select("div[data-line]");  //выделение куска кода с данными о станциях

        for (Element el : h1elements) {
            int i;
            i = el.toString().lastIndexOf(dataLine);
            String line1Num = el.toString().substring(i + k, i + k + 5).replaceAll("[^\\w]", "");
            Elements h2elements = el.select("a");  //все элементы, которые содержат данные о имени станции и переходах

            for (Element h2 : h2elements) {

                Connections connection = new Connections();
                connection.setFirstLineNumber(line1Num);
                Elements search = h2.select("[title]");
                String s = search.toString();
                if (s.isEmpty()) {   // проверка на наличие записи о переходах
                } else {
                    String station1Name = h2.select("span.name").text(); //здесь ещё всё ок. По 1 названию станций
                    connection.setFirstStationName(station1Name);
                    String h2str = h2.select("[title]").toString();
                    int r1 = h2str.lastIndexOf("«");
                    int r2 = h2str.lastIndexOf("»");
                    String secondStName = h2str.substring(r1 + 1, r2);
                    int s1 = h2str.lastIndexOf("ln-");
                    String secondLineNum = h2str.substring(s1 + 3, s1 + 6).replaceAll("[^0-9a-zA-Z]", "");
                    connection.setSecondLineNumber(secondLineNum);
                    connection.setSecondStationName(secondStName);
                    if (!connection.getFirstLineNumber().equals(connection.getSecondLineNumber()) &&
                            !connection.getFirstStationName().equals(connection.getSecondStationName()))
                        connectionsSet.add(connection);

                }
            }

        }

    }
}

