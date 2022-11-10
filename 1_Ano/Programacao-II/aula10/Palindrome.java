import p2utils.Stack;
import p2utils.Queue;
import java.util.Arrays;

public class Palindrome {
    public static void main(String[] args) {

        Stack<Character> stack = new Stack<Character>();
        Queue<Character> queue = new Queue<Character>();

        String a = "";

        for (int i = 0; i < args.length; i++){
            a+= args[i];
        }

        for (int i = 0; i < a.length(); i++) {
            char ch = a.charAt(i);
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                stack.push(Character.toLowerCase(ch));
                queue.in(Character.toLowerCase(ch));
            }
        }
        
        System.out.println(stack.size());
        System.out.println(queue.size());
        boolean result = true;

        while(!queue.isEmpty()){
            if (!queue.peek().equals(stack.top())){
                result = false;
                break;
            }
            queue.out();
            stack.pop();
        }
        System.out.println(result);
    }
}
