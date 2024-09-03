import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {
    public static void main(String[] args) {
        
        int[] nums = {3,4,-1,1};

        Set<Integer> set = new HashSet<>();
        
        for (int num : nums) {
            set.add(num);
        }
        for(int i =1; i <= set.size(); i++){
            if(!set.contains(i))
            // return i;
            System.out.println(i);
        }
        System.out.println(set.size() + 1);
        // return set.size() + 1;
    }
    
}
