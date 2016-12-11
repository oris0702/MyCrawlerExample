package crawler.example.integration;

import com.github.abola.crawler.CrawlerPack;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Longs;
import com.mashape.unirest.http.Unirest;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 整合練習：PM2.5 資料取得
 * + ElasticSearch 輸入
 *
 * @author Abola Lee
 */
public class PM25ElasticImport {
    static String elasticHost = "192.168.33.130" ;
    static String elasticPort = "9200" ;
    static String elasticIndex = "pm25";
    static String elasticIndexType = "data";


    public static void main(String[] args) {

        // 遠端資料路徑
        String uri = "http://opendata2.epa.gov.tw/AQX.xml";

        Document jsoupDoc = CrawlerPack.start().getFromXml(uri);


        for(Element elem: jsoupDoc.select("Data")){
            // 格式資料解析
            Double  co      = Doubles.tryParse( elem.getElementsByTag("CO").text() );
            String  county  = elem.getElementsByTag("County").text();
            Long    fpmi    = Longs.tryParse( elem.getElementsByTag("FPMI").text() );
            String  majorpollutant
                            = elem.getElementsByTag("MajorPollutant").text();
            Double  no      = Doubles.tryParse( elem.getElementsByTag("NO").text() );
            Long    no2     = Longs.tryParse( elem.getElementsByTag("NO2").text() );
            Double  nox     = Doubles.tryParse( elem.getElementsByTag("NOx").text() );
            Long    o3      = Longs.tryParse( elem.getElementsByTag("O3").text() );
            Long    pm10    = Longs.tryParse( elem.getElementsByTag("PM10").text() );
            Long    pm25    = Longs.tryParse( elem.getElementsByTag("PM2.5").text() );
            Long    psi     = Longs.tryParse( elem.getElementsByTag("PSI").text() );
            String  publishtime
                            = elem.getElementsByTag("PublishTime").text()
                              .replace(' ', 'T') + ":00+0800";
            String  sitename= elem.getElementsByTag("SiteName").text();
            Long    so2     = Longs.tryParse( elem.getElementsByTag("Status").text() );
            String  status  = elem.getElementsByTag("Status").text();
            String  windspeed
                            = elem.getElementsByTag("WindSpeed").text();
            Double  winddirec
                            = Doubles.tryParse( elem.getElementsByTag("WindDirec").text() );

            // Elasticsearch data format
            String elasticJson = "{" +
                    "\"co\":" + co +
                    ",\"county\":\"" + county + "\"" +
                    ",\"fpmi\":" + fpmi +
                    ",\"majorpollutant\":\"" + majorpollutant + "\"" +
                    ",\"no\":" + no +
                    ",\"no2\":" + no2 +
                    ",\"nox\":" + nox +
                    ",\"o3\":" + o3 +
                    ",\"pm10\":" + pm10 +
                    ",\"pm25\":" + pm25 +
                    ",\"psi\":" + psi +
                    ",\"publishtime\":\"" + publishtime + "\"" +
                    ",\"sitename\":\"" + sitename + "\"" +
                    ",\"so2\":" + so2 +
                    ",\"status\":\"" + status + "\"" +
                    ",\"windspeed\":\"" + windspeed + "\"" +
                    ",\"winddirec\":" + winddirec +
                    "}";



            System.out.println(
            // curl -XPOST http://localhost:9200/pm25/data -d '{...}'
            sendPost("http://" + elasticHost + ":" + elasticPort
                    + "/" + elasticIndex + "/" + elasticIndexType
                    , elasticJson));
        }
    }


    /***
     * NVL
     * if arg0 isNull then return arg1
     */
    static public <T> T nvl(T arg0, T arg1) {
        return (arg0 == null)?arg1:arg0;
    }

    static String sendPost(String url, String body){
        try{
         return Unirest.post(url)
                .header("content-type", "text/plain")
                .header("cache-control", "no-cache")
                .body(body)
                .asString().getBody();

        }catch(Exception e){return "Error:" + e.getMessage();}
    }
}
