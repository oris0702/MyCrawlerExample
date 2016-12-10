package crawler.example.exam;

import com.github.abola.crawler.CrawlerPack;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 練習：取得任一篇或多篇文章的 Reactions 總數
 *
 *
 * 重點
 * 1. 先利用Graph Api調整出需要的資料
 * 2. 修改程式，使用爬蟲包取得資料
 * 3. 上傳至GitHub
 * 
 * @author Abola Lee
 *
 */
public class FacebookExam {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑

		String uri = 
				"https://graph.facebook.com/v2.7"
				+ "/crazyck101/posts?fields=id,link,message,created_time,reactions.type(LIKE).limit(0).summary(total_count)&since=1480849200&until=1480856400"
				+ "&access_token=EAACEdEose0cBAP6vSQCIM13F4ax1dZCcySGvvu7R2E6NZCw9rLdOwrlZC34HCFl7GZBK7JX98f4NFDIn6p9s2fzTroM4EdpT47NFEUC1iKYmdOIjmrTZAVnv8K2kPVLBs5KkMHzhnvgRs2niVl9xPCsBSoblDlEn1gSf8UxoUpgZDZD";


		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");
		
		String output = "message,reactions:\n";

		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("message").text();

			// FIXIT
			String reactions = data.select("reactions").text();


			output += id + "," + reactions + "\n";
		}

		System.out.println( output );
	} 
}
