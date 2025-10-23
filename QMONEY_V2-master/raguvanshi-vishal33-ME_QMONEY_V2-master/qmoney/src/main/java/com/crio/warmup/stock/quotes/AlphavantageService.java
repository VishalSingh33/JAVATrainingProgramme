
package com.crio.warmup.stock.quotes;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import com.crio.warmup.stock.dto.AlphavantageCandle;
import com.crio.warmup.stock.dto.AlphavantageDailyResponse;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.client.RestTemplate;

public class AlphavantageService implements StockQuotesService {

  private RestTemplate restTemplate;

  protected AlphavantageService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public static final String TOKEN = "0K4LCXEQZW4RSEE6";
  public static final String FUNCTION = "TIME_SERIES_DAILY";

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }

  @Override
  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws JsonProcessingException, StockQuoteServiceException {
    // Auto-generated method stub
    List<Candle> stocks = new ArrayList<>();
    Map<LocalDate, AlphavantageCandle> dailyResponses;

    try {
      String url = buildUri(symbol);
      String apiResponce = restTemplate.getForObject(url, String.class);
      // String response = restTemplate.getForObject(buildUri(symbol), String.class);
      System.out.println(apiResponce);
  
      ObjectMapper objectMapper = getObjectMapper();
      dailyResponses = objectMapper.readValue(apiResponce, AlphavantageDailyResponse.class).getCandles();
      System.out.println(dailyResponses.size());
      // if(dailyResponse == null){}
      
  
      for (LocalDate date = from; !date.isAfter(to); date = date.plusDays(1)) {
        AlphavantageCandle candle = dailyResponses.get(date);
  
        if (candle != null) {
          candle.setDate(date);
          stocks.add(candle);
  
        }
      }
    } catch (NullPointerException e) {
      // handle exception
      throw new StockQuoteServiceException("Alphavantage return invalid respose", e);
    }
    stocks.sort(Comparator.comparing(Candle::getDate));
    return stocks;
  }

  protected String buildUri(String symbol) {

    // String uriTemplate = String.format(
    // "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=AAPL&output=full&apikey=",
    // FUNCTION, symbol, TOKEN);

    return "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol
        + "&outputsize=full&apikey=" + TOKEN;

    // return uriTemplate;S
  }

  // CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  // Implement the StockQuoteService interface as per the contracts. Call Alphavantage service
  // to fetch daily adjusted data for last 20 years.
  // Refer to documentation here: https://www.alphavantage.co/documentation/
  // --
  // The implementation of this functions will be doing following tasks:
  // 1. Build the appropriate url to communicate with third-party.
  // The url should consider startDate and endDate if it is supported by the provider.
  // 2. Perform third-party communication with the url prepared in step#1
  // 3. Map the response and convert the same to List<Candle>
  // 4. If the provider does not support startDate and endDate, then the implementation
  // should also filter the dates based on startDate and endDate. Make sure that
  // result contains the records for for startDate and endDate after filtering.
  // 5. Return a sorted List<Candle> sorted ascending based on Candle#getDate
  // IMP: Do remember to write readable and maintainable code, There will be few functions like
  // Checking if given date falls within provided date range, etc.
  // Make sure that you write Unit tests for all such functions.
  // Note:
  // 1. Make sure you use {RestTemplate#getForObject(URI, String)} else the test will fail.
  // 2. Run the tests using command below and make sure it passes:
  // ./gradlew test --tests AlphavantageServiceTest
  // CHECKSTYLE:OFF
  // CHECKSTYLE:ON
  // TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  // 1. Write a method to create appropriate url to call Alphavantage service. The method should
  // be using configurations provided in the {@link @application.properties}.
  // 2. Use this method in #getStockQuote.

}



// AlphavantageDailyResponse alphadailyres;

// String getAPI(){
// return "0K4LCXEQZW4RSEE6";
// }

// String createUrl(String symbol){

// return
// "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+symbol+"&outputsize=full&apikey="+getAPI();
// }

// @Override
// public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
// throws JsonProcessingException {

// String url=createUrl(symbol);
// AlphavantageDailyResponse alphadailyres= restTemplate.getForObject(url,
// AlphavantageDailyResponse.class);

// System.out.println(alphadailyres);

// Map<LocalDate,AlphavantageCandle> iter = alphadailyres.getCandles();
// List<Candle> candlelist=new ArrayList<>();

// for (LocalDate date:iter.keySet()) {

// if(date.isAfter(from.minusDays(1)) && date.isBefore(to.plusDays(1))){

// AlphavantageCandle alphaobj=alphadailyres.getCandles().get(date);
// alphaobj.setDate(date);

// candlelist.add(alphaobj);
// }
// // AlphavantageCandle alphacandle=alphadailyres.getCandles();
// }

// return
// candlelist.stream().sorted(Comparator.comparing(Candle::getDate)).collect(Collectors.toList());

// }



// TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
// Implement the StockQuoteService interface as per the contracts. Call Alphavantage service
// to fetch daily adjusted data for last 20 years.
// Refer to documentation here: https://www.alphavantage.co/documentation/
// --
// The implementation of this functions will be doing following tasks:
// 1. Build the appropriate url to communicate with third-party.
// The url should consider startDate and endDate if it is supported by the provider.
// 2. Perform third-party communication with the url prepared in step#1
// 3. Map the response and convert the same to List<Candle>
// 4. If the provider does not support startDate and endDate, then the implementation
// should also filter the dates based on startDate and endDate. Make sure that
// result contains the records for for startDate and endDate after filtering.
// 5. Return a sorted List<Candle> sorted ascending based on Candle#getDate
// IMP: Do remember to write readable and maintainable code, There will be few functions like
// Checking if given date falls within provided date range, etc.
// Make sure that you write Unit tests for all such functions.
// Note:
// 1. Make sure you use {RestTemplate#getForObject(URI, String)} else the test will fail.
// 2. Run the tests using command below and make sure it passes:
// ./gradlew test --tests AlphavantageServiceTest
// CHECKSTYLE:OFF
// CHECKSTYLE:ON
// TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
// 1. Write a method to create appropriate url to call Alphavantage service. The method should
// be using configurations provided in the {@link @application.properties}.
// 2. Use this method in #getStockQuote.


  // TODO: CRIO_TASK_MODULE_EXCEPTIONS
  //   1. Update the method signature to match the signature change in the interface.
  //   2. Start throwing new StockQuoteServiceException when you get some invalid response from
  //      Alphavantage, or you encounter a runtime exception during Json parsing.
  //   3. Make sure that the exception propagates all the way from PortfolioManager, so that the
  //      external user's of our API are able to explicitly handle this exception upfront.
  //CHECKSTYLE:OFF


