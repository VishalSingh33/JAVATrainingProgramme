public class Words {
    public static void main(String[] args) {
        Words w1= new Words();
    
        System.out.println(w1.transform("i love programming"));
    }
    public String transform(String s)
    {
        // ArrayList<String> words = new ArrayList<>();
        String[] arrOfStr=s.split(" ");
        String result="";
        for(int i=0;i<arrOfStr.length;i++){
           result += arrOfStr[i].substring(0, 1).toUpperCase()
            + arrOfStr[i].substring(1,arrOfStr[i].length()) + " ";

        }
        
        return result.trim();
    }
}