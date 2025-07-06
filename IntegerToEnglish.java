/**
 * Leetcode 273. Integer to English Words
 * Link: https://leetcode.com/problems/integer-to-english-words/description/
 */
public class IntegerToEnglish {
    /**
     * process num in triplets, at thousand, million and billion separators. num inside each triplet is essentially the same
     * except adding thousand, million or billion at the end. Build language dictionary using string array with all needed
     * words below twenty, all tens etc and process number from behind and keep on building the result string.
     *
     * TC: O(1) only 3 iterations and calculations are finite with all possible int value
     * SC: O(1)
     */
    String[] belowTwenty = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen" , "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        String word = "";
        int i = 0;
        while(num > 0) {
            if (num % 1000 != 0) {
                word = helper(num % 1000) + thousands[i] + " " + word;
            }

            num = num / 1000;
            i++;
        }
        return word.trim();
    }

    private String helper(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return belowTwenty[num] + " ";
        } else if (num < 100) {
            return tens[num / 10] + " " + helper(num % 10);
        } else {
            return belowTwenty[num / 100] + " Hundred " + helper(num % 100);
        }
    }
}
