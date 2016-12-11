package crawler.example;

import com.github.abola.crawler.CrawlerPack;
import org.apache.commons.logging.impl.SimpleLog;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 爬蟲包程式的全貌，就只有這固定的模式
 * 
 * @author Abola Lee
 *
 */
public class PttExam {
	// commit test
	public static void main(String[] args) {

		// set to debug level
		//CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_DEBUG);

		// turn off logging
		CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_OFF);

		// 遠端資料路徑
		String uri = "https://www.ptt.cc/bbs/Beauty/M.1481359044.A.03C.html";
        Document element=CrawlerPack.start().addCookie("over18","1").getFromHtml(uri);
        for(Element ele:element.select( "#main-content *")) {
        ele.remove();
        }
        System.out.println(element.select("#main-content").text());
		System.out.println(
				"推數:"+CrawlerPack.start()
				
				// 參數設定
//			    .addCookie("key","value")	// 設定cookie
 				.addCookie("over18","1")	// 設定cookie
				//.setRemoteEncoding("big5")// 設定遠端資料文件編碼
				
				// 選擇資料格式 (三選一)推文數"+
//				.getFromJson(uri)
			    .getFromHtml(uri)
//			    .getFromXml(uri)
			    
			    // 這兒開始是 Jsoup Document 物件操作
//			    .select(".css .selector ")
				.select("[href$='.jpg']")
// 				.select("img[src$='.jpg']")
//				 .select("[span:contains(jpg)]")

                        +"\n"+"推數:"+CrawlerPack.start().addCookie("over18","1").getFromHtml(uri).select("span:containsOwn(推).push-tag").size()





		);
	}
}
