package money.funds.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import money.funds.model.MoneyFundDetails;

/**
 * <p>
 * <b> money funds service class </b>
 * </p>
 */
@Service
public class MoneyFundsService {
	
	public JSONObject allJsonObj =new JSONObject();
	
	@Autowired
	private MoneyFundDetails moneyFundDetails;

	/**
	 * <p>
	 * <b> get json format response</b>
	 * </p>
	 *
	 * @return
	 * @throws JSONException
	 */
	public ResponseEntity<?> processMoneyFundsData() throws SystemException {
		sendRequest(
				"http://cn.morningstar.com/handler/fundranking.ashx?date=2017-11-24&fund=&category=currency&rating=&company=&cust=&sort=ReturnYTD&direction=desc&tabindex=1&pageindex=1&pagesize=10000&randomid=0.8061648101957943");
		
		return new ResponseEntity(allJsonObj.toString(),HttpStatus.OK);
			
	}

	public void sendRequest(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			//System.out.println("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					try {
						 this.convertToJson(EntityUtils.toString(entity));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * <p><b>convert html to json</b></p>
	 * @param HTML
	 * @throws JSONException
	 */
	public void convertToJson(String HTML) throws JSONException {
		Document document = Jsoup.parse(HTML);
		// get table content
		Element tableContent = document.getElementsByClass("fr_tablecontent").first();
		
		JSONArray jsonArr = new JSONArray();

		Elements contentTrs = tableContent.select("tr");
		// JSONObject jo = new JSONObject();
		for (int i = 0; i < contentTrs.size()-1; i++) {
			JSONObject jsonObj = new JSONObject();
			
			Elements contents=contentTrs.get(i).select("td");
			String moneyFundCode=contents.get(1).text();
			String moenyFundName = contents.get(2).text();
			String returnYTD = contents.get(3).text();
			String return1Week = contents.get(4).text();
			String return1Month = contents.get(5).text();
			String return3Month = contents.get(6).text();
			String return6Month = contents.get(7).text();
			String return1Year = contents.get(8).text();
			String return2Year = contents.get(9).text();
			String return3Year = contents.get(10).text();
			String return5Year = contents.get(11).text();
			String return10Year = contents.get(12).text();
			String returnInception = contents.get(13).text();
			
		    jsonObj.put("fundCode", moneyFundCode);
		    jsonObj.put("fundName", moenyFundName);
			jsonObj.put("returnYTD", returnYTD);
			jsonObj.put("return1Week", return1Week);
			jsonObj.put("return1Month", return1Month);
			jsonObj.put("return3Month", return3Month);
			jsonObj.put("return6Month", return6Month);
			jsonObj.put("return1Year", return1Year);
		    
			jsonObj.put("return2Year", return2Year);
			jsonObj.put("return3Year", return3Year);
			jsonObj.put("return5Year", return5Year);
			jsonObj.put("return10Year", return10Year);
		    
			jsonObj.put("returnInception", returnInception);
			jsonArr.put(jsonObj);
		}
		allJsonObj.put("fundRecords", jsonArr);
	}
}
