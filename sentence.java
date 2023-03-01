import java.util.HashMap;
import java.util.*;

public class sentence {
    public static void main(String[] args) {
       
       Scanner sc =new Scanner(System.in);
       
       String str = sc.nextLine() ;
        String[] words = str.split(" ");

        HashMap<String, Integer> sentence = new HashMap<String, Integer>();

        for (String word : words) {
            if (sentence.containsKey(word)) {
                sentence.put(word, sentence.get(word) + 1);
            } else {
                sentence.put(word, 1);
            }
        }

        for (String word : sentence.keySet()) {
            System.out.println(word + " : " + sentence.get(word));
        }
    }
}


