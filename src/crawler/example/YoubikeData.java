package crawler.example;

import com.github.abola.crawler.CrawlerPack;
import org.apache.commons.logging.impl.SimpleLog;

/**
 * YouBike 資料抓取
 *
 * @author Abola Lee
 *
 */
public class YoubikeData {

	public static void main(String[] args) {

		// turn off logging
		CrawlerPack.setLoggerLevel(SimpleLog.LOG_LEVEL_OFF);

	    // 簡址無法成功爬取資料
        //String uri = "http://data.taipei/youbike";

        // 遠端真實路徑，但資料有gzip壓縮過
        //String uri = "https://tcgbusfs.blob.core.windows.net/blobyoubike/YouBikeTP.gz";
		//https://tcgbusfs.blob.core.windows.net/blobyoubike/YouBikeTP.gz
		// 遠端真實路徑，使用gzip解壓縮
		String uri = "gz:https://tcgbusfs.blob.core.windows.net/blobyoubike/YouBikeTP.gz";

		System.out.println(
				CrawlerPack.start()
				
				// Json 的資料格式
				.getFromJson(uri)
			    
			    // 取得大安區的所有 YouBike 站
			    .select("retVal > *:contains(大安區)")
			    
		);
	}
}
