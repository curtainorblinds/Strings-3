import java.util.Stack;

/**
 * Leetcode 227. Basic Calculator II
 * Link: https://leetcode.com/problems/basic-calculator-ii/description/
 */
//------------------------------------ Solution 1 -----------------------------------
public class BasicCalculatorII {
    /**
     * Stack solution - we want to give precedence to multiply and division
     * over addition and subtraction. Idea is to leave +/- for later processing
     * by keeping them in the stack along with the sign attached to the num going inside
     * the stack. For multiply and division we want to process them as and when they occur by
     * taking the latest num out of stack and process it with the current num.
     *
     * TC: O(n) SC: O(n)
     */
    public int calculate(String s) {
        if (s == null) {
            return 0;
        }

        s = s.trim();
        if (s.isEmpty()) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int curr = 0;
        char lastSign = '+';

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curr = curr*10 + c - '0';
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (lastSign == '+') {
                    stack.push(curr);
                } else if (lastSign == '-') {
                    stack.push(-curr);
                } else if (lastSign == '*') {
                    stack.push(stack.pop() * curr);
                } else {
                    stack.push(stack.pop() / curr);
                }
                curr = 0;
                lastSign = c;
            }
        }

        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}

//------------------------------------ Solution 2 -----------------------------------
class BasicCalculatorII2 {
    /**
     * forward processing solution using tail concept where tail keeps track of just the
     * last calculated intermediate calculated value rather than the full result till that
     * point. In case of multiply/division tail keeps track of calculation till previous plus/minus.
     *
     * TC: O(n) SC: O(1)
     */
    public int calculate(String s) {
        if (s == null) {
            return 0;
        }

        s = s.trim();
        if (s.isEmpty()) {
            return 0;
        }

        int curr = 0;
        char lastSign = '+';
        int result = 0;
        int tail = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                curr = curr*10 + c - '0';
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (lastSign == '+') {
                    result = result + curr;
                    tail = curr;
                } else if (lastSign == '-') {
                    result = result - curr;
                    tail = -curr;
                } else if (lastSign == '*') {
                    result = result - tail + tail * curr;
                    tail = tail * curr;
                } else {
                    result = result - tail + tail / curr;
                    tail = tail / curr;
                }
                curr = 0;
                lastSign = c;
            }
        }

        return result;
    }
}