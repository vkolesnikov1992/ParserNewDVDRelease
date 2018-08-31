import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Parser {

    private static Document getPage() throws IOException {
        String url = "https://www.kinopoisk.ru/comingsoon/dvd/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;

    }
//\^НД Плэй\$\^ , !\$
    public static void main(String[] args) throws IOException {
        Document page = getPage();
        Element tableKinopoisk = page.select("table[class=filmList]").first();
        Elements names = tableKinopoisk.select("tr");
        System.out.println(names.select("td[class=news]").text());

        for (Element name : names) {
            String films = name.select("td[valign=top]").text();
            if (!films.isEmpty()) {
                String firstRegex = films.replaceAll("[a-zA-Zôê\\s]{2,}"," ");
                String secondRegex = firstRegex.replaceAll("(   НД Плэй)","");
                String finalResult = secondRegex.replaceAll(" , !","");
                System.out.println(finalResult);
            }
        }




    }
}
