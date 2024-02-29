import java.util.*;

public class Pratice {

  public static void main(String[] args) {

    Map<String, Map<String, Integer>> trainARoute = new HashMap<>();

    // Add city details and distances to the inner Maps
    trainARoute.put("CHN", createCityMap("CHENNAI", 0));
    trainARoute.put("SLM", createCityMap("SALEM", 350));
    trainARoute.put("BLR", createCityMap("BANGALORE", 550));
    trainARoute.put("KRN", createCityMap("KURNOOL", 900));
    trainARoute.put("HYB", createCityMap("HYDERABAD", 1200));
    trainARoute.put("NGP", createCityMap("NAGPUR", 1600));
    trainARoute.put("ITJ", createCityMap("ITARSI", 1900));
    trainARoute.put("BPL", createCityMap("BHOPAL", 2000));
    trainARoute.put("AGA", createCityMap("AGRA", 2500));
    trainARoute.put("NDL", createCityMap("NEW DELHI", 2700));

    // Print the Train A route Map
    for (Map.Entry<String, Map<String, Integer>> entry : trainARoute.entrySet()) {
        String cityCode = entry.getKey();
        Map<String, Integer> cityMap = entry.getValue();
        for (Map.Entry<String, Integer> innerEntry : cityMap.entrySet()) {
            String cityName = innerEntry.getKey();
            Integer distance = innerEntry.getValue();
            System.out.println(cityName + " (" + cityCode + ") - " + distance);
        }
    }
}
// Helper method to create inner city Maps
private static Map<String, Integer> createCityMap(String cityName, int distance) {
    Map<String, Integer> cityMap = new HashMap<>();
    cityMap.put(cityName, distance);
    return cityMap;
}

  }