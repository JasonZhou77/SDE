import java.util.*;

public class DecompressStringII {
    /*
    [3|ABC] => ABCABCABC
    HG[3|B[2|CA]]F => HGBCACABCACABCACAF
    */

    /*
    input = H G [ 3 | B [ 2 | C A ] ] F
              i
    stack = [ F BCACABCACABCACA G H ] <->
    when meet |
        1. go left in input to grab the number
        2. pop the stack until non-capitalized character and construct to string
        3. build such decompressed string and push it back to stack
    when meet ]
        1. push into stack
    when meet [
        1. pop stack to grab the string
        2. pop stack to remove the corresponding [
        3. push string back to stack
    when meet capitalized letter
        1. push into stack
    when meet number
        1. shouldn't have such case
    
    after i reaches the start, all should be done, just pop the stack and build the final decompressed string
    */
    public String decompress(String input) {
        Deque<String> stack = new ArrayDeque<>();
        int i = input.length() - 1;
        while (i >= 0) {
            char c = input.charAt(i);
            if (c == ']') {
                stack.offerFirst(Character.toString(c));
            } else if (c == '[') {
                // grab the string inside [STRING]
                String back = stack.pollFirst();
                // remove the ]
                stack.pollFirst();
                // push STRING back to the stack
                stack.offerFirst(back);
            } else if (c >= 'A' && c <= 'Z') {
                stack.offerFirst(Character.toString(c));
            } else if (c == '|') {
                // go left to grab the number
                int count = 0;
                int dec = 0;
                i--;
                while (input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                    count = count + (int)((input.charAt(i) - '0') * Math.pow(10, dec));
                    dec++;
                    i--;
                }
                // reset it back to the position of first digit of the number
                i++;
                // pop the stack to construct to string for recurrence
                StringBuilder recurrence = new StringBuilder();
                while (stack.peekFirst().matches("[A-Z]*")) {
                    recurrence.append(stack.pollFirst());
                }
                // use count and recurrence to rebuild the string
                StringBuilder decompressed = new StringBuilder();
                for (int j = 0; j < count; j++) {
                    decompressed.append(recurrence);
                }
                // put it back to the stack
                stack.offerFirst(decompressed.toString());
            } else {
                // case when meet a number
                // shouldn't reach this case
                return null;
            }
            // move the pointer to left
            i--;
        }
        // in here, all strings in the stack should be decompressed
        // just append them together to rebuild the original string
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pollFirst());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        DecompressStringII solution = new DecompressStringII();
        System.out.printf("Test Case 1: %s, Result: %s%n", "[3|ABC]", solution.decompress("[3|ABC]").equals("ABCABCABC") ? "PASSED" : "FAILED");
        System.out.printf("Test Case 2: %s, Result: %s%n", "HG[3|B[2|CA]]F", solution.decompress("HG[3|B[2|CA]]F").equals("HGBCACABCACABCACAF") ? "PASSED" : "FAILED");
    }
}