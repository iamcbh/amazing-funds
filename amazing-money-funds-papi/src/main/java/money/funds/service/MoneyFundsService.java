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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * <p>
 * <b> money funds service class </b>
 * </p>
 */
@Service
public class MoneyFundsService {
	
	public JSONObject allJsonObj =new JSONObject();

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
				"http://cn.morningstar.com/handler/fundranking.ashx?date=2017-11-24&fund=&category=stock&rating=&company=&cust=&sort=ReturnYTD&direction=desc&tabindex=1&pageindex=1&pagesize=10&randomid=0.15476893225741128");
		
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
		// get table header
		Element tableHeader = document.getElementsByClass("fr_tableheader").first();
		// get table content
		Element tableContent = document.getElementsByClass("fr_tablecontent").first();
		// get first header to be json key
		Elements header = tableHeader.select("tr").first().select("td");
		String moenyFundNumKey = header.get(0).text();
		String moneyFundCodeKey = header.get(1).text();
		String moneyFundNameKey=header.get(2).text();
		String totalReturnPercentKey = header.get(3).text();
		String annualRateOfReturnKey = header.get(4).text();
		String totalReturnRateKey = header.get(5).text()+" ";
		//get subheader to be json key
		Elements subHeaders = tableHeader.select("tr").get(1).select("td");
		String returnYTDKey = subHeaders.get(0).text();
		String return1WeekKey = subHeaders.get(1).text();
		String return1MonthKey = subHeaders.get(2).text();
		String return3MonthKey = subHeaders.get(3).text();
		String return6MonthKey = subHeaders.get(4).text();
		String return1YearKey = subHeaders.get(5).text();
		String return2YearKey = subHeaders.get(6).text();
		String return3YearKey = subHeaders.get(7).text();
		String return5YearKey = subHeaders.get(8).text();
		String return10YearKey = subHeaders.get(9).text();
		String returnInceptionKey = subHeaders.get(10).text();

		

		JSONArray jsonArr = new JSONArray();
		JSONArray totalReturnPercentArr = new JSONArray();
		JSONArray annualRateOfReturnArr = new JSONArray();
		JSONArray totalReturnRateArr = new JSONArray();

		Elements contentTrs = tableContent.select("tr");
		// JSONObject jo = new JSONObject();
		for (int i = 0; i < contentTrs.size()-1; i++) {
			JSONObject jsonObj = new JSONObject();
			JSONObject totalReturnPercentObj = new JSONObject();
			JSONObject annualRateOfReturnObj = new JSONObject();
			JSONObject totalReturnRateObj = new JSONObject();
			
			Elements contents=contentTrs.get(i).select("td");
			String moenyFundNum = contents.get(0).text();
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
		    
		    totalReturnPercentObj.put(returnYTDKey, returnYTD);
		    totalReturnPercentObj.put(return1WeekKey, return1Week);
		    totalReturnPercentObj.put(return1MonthKey, return1Month);
		    totalReturnPercentObj.put(return3MonthKey, return3Month);
		    totalReturnPercentObj.put(return6MonthKey, return6Month);
		    totalReturnPercentObj.put(return1YearKey, return1Year);
		    
		    annualRateOfReturnObj.put(return2YearKey, return2Year);
		    annualRateOfReturnObj.put(return3YearKey, return3Year);
		    annualRateOfReturnObj.put(return5YearKey, return5Year);
		    annualRateOfReturnObj.put(return10YearKey, return10Year);
		    
		    totalReturnRateObj.put(returnInceptionKey, returnInception);
		    
		    jsonObj.put(moenyFundNumKey, moenyFundNum);
		    jsonObj.put(moneyFundCodeKey, moneyFundCode);
		    jsonObj.put(moneyFundNameKey, moenyFundName);
		    jsonObj.put(totalReturnPercentKey, totalReturnPercentObj);
		    jsonObj.put(annualRateOfReturnKey, annualRateOfReturnObj);
		    jsonObj.put(totalReturnRateKey, totalReturnRateObj);
		    
		    allJsonObj.put(String.valueOf(i), jsonObj);
		}
		//System.out.println(allJsonObj); 
	}

}
