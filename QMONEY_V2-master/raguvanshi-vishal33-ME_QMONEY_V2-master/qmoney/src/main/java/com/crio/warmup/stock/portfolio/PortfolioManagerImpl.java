
package com.crio.warmup.stock.portfolio;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.dto.Candle;
import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.quotes.StockQuotesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
import org.springframework.web.client.RestTemplate;

public class PortfolioManagerImpl implements PortfolioManager {

 // Caution: Do not delete or modify the constructor, or else your build will break!
  // This is absolutely necessary for backward compatibility
  private StockQuotesService stockQuotesService;
  protected PortfolioManagerImpl(StockQuotesService stockQuotesService) {
    this.stockQuotesService = stockQuotesService;
  }

  private RestTemplate restTemplate;
  protected PortfolioManagerImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }


  // TODO: CRIO_TASK_MODULE_REFACTOR
  // 1. Now we want to convert our code into a module, so we will not call it from main anymore.
  // Copy your code from Module#3 PortfolioManagerApplication#calculateAnnualizedReturn
  // into #calculateAnnualizedReturn function here and ensure it follows the method signature.
  // 2. Logic to read Json file and convert them into Objects will not be required further as our
  // clients will take care of it, going forward.

  // Note:
  // Make sure to exercise the tests inside PortfolioManagerTest using command below:
  // ./gradlew test --tests PortfolioManagerTest

  // CHECKSTYLE:OFF

// ¶TODO: CRIO_TASK_MODULE_ADDITIONAL_REFACTOR
  //  Modify the function #getStockQuote and start delegating to calls to
  //  stockQuoteService provided via newly added constructor of the class.
  //  You also have a liberty to completely get rid of that function itself, however, make sure
  //  that you do not delete the #getStockQuote function.
  private Comparator<AnnualizedReturn> getComparator() {
    return Comparator.comparing(AnnualizedReturn::getAnnualizedReturn).reversed();
  }

  // CHECKSTYLE:OFF

  // CRIO_TASK_MODULE_REFACTOR
  // Extract the logic to call Tiingo third-party APIs to a separate function.
  // Remember to fill out the buildUri function and use that.


  public List<Candle> getStockQuote(String symbol, LocalDate from, LocalDate to)
      throws JsonProcessingException, StockQuoteServiceException {

    // if (from.compareTo(to) >= 0) {
    //   throw new RuntimeException();
    // }
    // String url = buildUri(symbol, from, to);
    // TiingoCandle[] stocksStartToEndDate = restTemplate.getForObject(url, TiingoCandle[].class);

    // if (stocksStartToEndDate == null) {
    //   return new ArrayList<Candle>();
    // } else {
    //   List<Candle> stock = Arrays.asList(stocksStartToEndDate);
    //   return stock;
    // }
    return stockQuotesService.getStockQuote(symbol, from, to);
  }

//   protected String buildUri(String symbol, LocalDate startDate, LocalDate endDate) {

//     // String token = "bcc73e5e027e97ba172c8537dcc8efd0e6c7f718";

//     // String uriTemplate = "https://api.tiingo.com/tiingo/daily/$SYMBOL/prices?"
//     //     + "startDate=$STARTDATE&endDate=$ENDDATE&token=$APIKEY";

//     // String url = uriTemplate.replace("$APIKEY", token).replace("$SYMBOL", symbol)
//     //     .replace("$STARTDATE", startDate.toString()).replace("$ENDDATE", endDate.toString());
//     // return url;

//     String uriTemplate = "https://api.tiingo.com/tiingo/daily/" + symbol + "/prices?" + "startDate="
//     + startDate + "&endDate=" + endDate + "&token=" + "bcc73e5e027e97ba172c8537dcc8efd0e6c7f718";
// return uriTemplate;

//   }


  @Override
  public List<AnnualizedReturn> calculateAnnualizedReturn(List<PortfolioTrade> portfolioTrades,
      LocalDate endDate) throws StockQuoteServiceException {
    // Auto-generated method stub
    AnnualizedReturn annualizedReturn;
    List<AnnualizedReturn> annualizedReturnList = new ArrayList<>();

    try {
      for(PortfolioTrade tradeList : portfolioTrades){
        annualizedReturn = getAnnualizedReturn(tradeList, endDate);
        annualizedReturnList.add(annualizedReturn);
      }
      Comparator<AnnualizedReturn> sortByComparator = Comparator.comparing(AnnualizedReturn::getAnnualizedReturn).reversed();
      Collections.sort(annualizedReturnList, sortByComparator);
    } catch (NullPointerException e) {
      // handle exception
      throw new StockQuoteServiceException("Inavalid Annual Calculation", e);
    }
    return annualizedReturnList;
  }


  public AnnualizedReturn getAnnualizedReturn(PortfolioTrade tradeList, LocalDate endDate) {
    AnnualizedReturn annualizedReturn;
    
    List<Candle> stocksStartToEndDate=new ArrayList<>();
    try {
        stocksStartToEndDate = getStockQuote(tradeList.getSymbol(), tradeList.getPurchaseDate(), endDate);
      
      
      System.out.println(stocksStartToEndDate);
      Candle stockStartDate = stocksStartToEndDate.get(0);
      Candle stockLatest = stocksStartToEndDate.get(stocksStartToEndDate.size() - 1);

      Double buyPrice = stockStartDate.getOpen();
      Double sellPrice = stockLatest.getClose();

      Double totalReturn = (sellPrice-buyPrice)/buyPrice;
      Double numYears = (double)ChronoUnit.DAYS.between(tradeList.getPurchaseDate(), endDate) / 365.24;
      Double annualizedReturns = Math.pow((1 + totalReturn), (1 / numYears)) - 1;
      annualizedReturn = new AnnualizedReturn(tradeList.getSymbol(), annualizedReturns, totalReturn);
    } 
    
    catch (StockQuoteServiceException e) {
      // Handle specific StockQuoteServiceException
      annualizedReturn = new AnnualizedReturn(tradeList.getSymbol(), Double.NaN, Double.NaN);
  } catch (JsonProcessingException e) {
      // Handle specific JsonProcessingException
      annualizedReturn = new AnnualizedReturn(tradeList.getSymbol(), Double.NaN, Double.NaN);
  } catch (IOException e) {
      // Handle specific IOException
      annualizedReturn = new AnnualizedReturn(tradeList.getSymbol(), Double.NaN, Double.NaN);
  }
    return annualizedReturn;
  }


}
