package money.funds.utils;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import money.funds.model.MoneyFundDetails;


/**
 * 
 * Common utils for Funds.
 * 
 * @author Administrator
 *
 */

@Component
public class MoneyFundUtils {
	
	// Logger
	private static Logger logger = LoggerFactory.getLogger(MoneyFundUtils.class);
	
	/**
	 * Method to parse JSON to Object.
	 * 
	 * @param json String
	 * @param clazz Class<T>
	 * @return T
	 */
	public static <T> T parseJSON(String json, Class<T> clazz){
		
		logger.info("source json:" + json);
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			throw new RuntimeException("Unable to parse json data" + e);
		} catch (JsonMappingException e) {
			throw new RuntimeException("Unable to parse json data" + e);
		} catch (IOException e) {
			throw new RuntimeException("Unable to parse json data" + e);
		}
	}
	
	/**
	 * Method to convert Object to JSON.
	 * 
	 * @param obj Object
	 * @return String
	 */
	public String convertObjectToJSON(Object obj){
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Unable to converto JSON" + e);
		}
	}
	
	/**
	 * Sort Fund list by period in DESC sequence.
	 * 
	 * @param filter String
	 * @param fundList List
	 * @return fundList List
	 */
	public List<MoneyFundDetails> sortFundList(String period, List<MoneyFundDetails> fundList) {
		
		Collections.sort(fundList, new MoneyFundComparotor(period));

		return fundList;
	}

	/**
	 * 
	 * Money Fund comparator, sorted by period.
	 * 
	 * @author Administrator
	 *
	 */
	class MoneyFundComparotor implements Comparator {

		private String period;

		public MoneyFundComparotor(String period) {
			this.period = period;
		}

		@Override
		public int compare(Object o1, Object o2) {
			MoneyFundDetails f1 = (MoneyFundDetails) o1;
			MoneyFundDetails f2 = (MoneyFundDetails) o2;

			switch (period) {
			case "1week":
				return Double.compare(f2.getReturn1Week(), f1.getReturn1Week());
			case "1month":
				return Double.compare(f2.getReturn1Month(), f1.getReturn1Month());
			case "3month":
				return Double.compare(f2.getReturn3Month(), f1.getReturn3Month());
			case "6month":
				return Double.compare(f2.getReturn6Month(), f1.getReturn6Month());
			case "1year":
				return Double.compare(f2.getReturn1Year(), f1.getReturn1Year());

			default:
				return 0;
			}
		}
	}
}
