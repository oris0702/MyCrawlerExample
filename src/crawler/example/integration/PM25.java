package crawler.example.integration;

import com.github.abola.crawler.CrawlerPack;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 整合練習：PM2.5 資料取得
 *
 * @author Abola Lee
 */
public class PM25 {

    public static void main(String[] args) {

        // 遠端資料路徑
        String uri = "http://opendata2.epa.gov.tw/AQX.xml";
//        data sample
//        ---
//        <AQX>
//            <Data>
//                <SiteName>麥寮</SiteName>
//                <County>雲林縣</County>
//                <PSI>32</PSI>
//                <MajorPollutant></MajorPollutant>
//                <Status>良好</Status>
//                <SO2>1.7</SO2>
//                <CO>0.06</CO>
//                <O3>22</O3>
//                <PM10>25</PM10>
//                <PM2.5>3</PM2.5>
//                <NO2>1.3</NO2>
//                <WindSpeed>5.4</WindSpeed>
//                <WindDirec>240</WindDirec>
//                <FPMI>1</FPMI>
//                <NOx>3.82</NOx>
//                <NO>2.5</NO>
//                <PublishTime>2016-07-03 14:00</PublishTime>
//            </Data>
//        <AQX>
        Document jsoupDoc = CrawlerPack.start().getFromXml(uri);


        for(Element elem: jsoupDoc.select("Data")){
//            System.out.println(elem.toString());
//            System.out.print( "SiteName:"+elem.getElementsByTag("SiteName").text()+" " );
            System.out.print( elem.getElementsByTag("SiteName").text()+" " );
//            System.out.print( "County:"+elem.getElementsByTag("County").text() +" ");
            System.out.println( "PM2.5:"+elem.getElementsByTag("pm2.5").text() );

        }
    }
}
