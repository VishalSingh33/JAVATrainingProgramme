
package com.crio.warmup.stock.quotes;

import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.client.RestTemplate;

public class TiingoService implements StockQuotesService {

  private RestTemplate restTemplate;
  protected TiingoService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws JsonProcessingException, StockQuoteServiceException {
    // Auto-generated method stub

    List<Candle> stock;

    
      if (from.compareTo(to) >= 0) {
        throw new RuntimeException();
      }
      String url = buildUri(symbol, from, to);
      try {
      ObjectMapper objectMapper = new ObjectMapper();
      // This registers objectMapper with the time to convert the input String time to LocalDate
      objectMapper.registerModule(new JavaTimeModule());
      // TiingoCandle[] stocksStartToEndDateArray = restTemplate.getForObject(url, TiingoCandle[].class);
      String response = restTemplate.getForObject(url, String.class);
      TiingoCandle[] stocksStartToEndDateArray = objectMapper.readValue(response, TiingoCandle[].class);
  
      if (stocksStartToEndDateArray == null) {
        // return new ArrayList<Candle>();
        stock = Arrays.asList(new TiingoCandle[0]);
      } else {
        stock = Arrays.asList(stocksStartToEndDateArray);
      }
    } catch (NullPointerException e) {
      // handle exception
      throw new StockQuoteServiceException("Tiingo return invalid respose", e.getCause());
    }
    return stock;
  }

  protected String buildUri(String symbol, LocalDate startDate, LocalDate endDate) {

    // String uriTemplate = "https://api.tiingo.com/tiingo/daily/$SYMBOL/prices?"
    //     + "startDate=$STARTDATE&endDate=$ENDDATE&token=$APIKEY";

    // String url = uriTemplate.replace("$APIKEY", getToken()).replace("$SYMBOL", symbol)
    //     .replace("$STARTDATE", startDate.toString()).replace("$ENDDATE", endDate.toString());
    // return url;

    String uriTemplate = "https://api.tiingo.com/tiingo/daily/" + symbol + "/prices?" + "startDate="
    + startDate + "&endDate=" + endDate + "&token=" + getToken();
     return uriTemplate;

  }

  public static String getToken() {
    return "b2aa9f126dcee21d707aadf68430de853d958c4b";
  }


  // CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  //  Implement getStockQuote method below that was also declared in the interface.

  // Note:
  // 1. You can move the code from PortfolioManagerImpl#getStockQuote inside newly created method.
  // 2. Run the tests using command below and make sure it passes.
  //    ./gradlew test --tests TiingoServiceTest


  //CHECKSTYLE:OFF

  // CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  //  Write a method to create appropriate url to call the Tiingo API.





  // TODO: CRIO_TASK_MODULE_EXCEPTIONS
  //  1. Update the method signature to match the signature change in the interface.
  //     Start throwing new StockQuoteServiceException when you get some invalid response from
  //     Tiingo, or if Tiingo returns empty results for whatever reason, or you encounter
  //     a runtime exception during Json parsing.
  //  2. Make sure that the exception propagates all the way from
  //     PortfolioManager#calculateAnnualisedReturns so that the external user's of our API
  //     are able to explicitly handle this exception upfront.

  //CHECKSTYLE:OFF


}
