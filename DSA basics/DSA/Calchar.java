public class Calchar {
    public static void main(String[] args) {
        //string decalrtion
        String str = "bxydubcyklmxksxinasbxiu xcksdbcuyvydcnjdb";
        //to cal string length
        int[] freq  = new int[str.length()];
        int i, j;
         //method converts the given string into a sequence of characters
        char string[]  = str.toCharArray();
        
        for(i=0 ; i<str.length() ; i++)
        //cal each alphabet freq
        {
            freq[i] = 1;
            for(j=i+1 ; j<str.length() ; j++)
            {
             if(string[i]==string[j])
             {
                 freq[i]++;
                 string[j]='0';
             }
            }
    }
        System.out.println("character at frequencies");
        for(i=0; i<str.length(); i++)
        {
            //counting freq
            if(string[i]!=' ' && string[i]!='0')
                System.out.println(string[i] +"-"+ freq[i]);
        }
    }
}