
/**
 * @filename - StringAndNumber.java
 * @description - Perform Operations on String And Number
 * @author - Arman Agrawal
 */

public class StringAndNumber {

    private String value = null;
    private Integer number = null;

    public StringAndNumber(String value) {
        if (value != null && !value.isEmpty())
            this.value = value;
    }

    public StringAndNumber(int number) {
        this.number = number;
    }

    // Counts unique palindromic substrings in a given string
    public int countUniquePalindrome() {
        if (this.value == null || this.value.isEmpty())
            return -1;
        String[] palindrome = new String[1000];
        int count = 0;
        // expand around every center for both odd and even length
        for (int center = 0; center < this.value.length(); center++) {
            count = expandAroundCenter(this.value, palindrome, center, center, count);
            count = expandAroundCenter(this.value, palindrome, center, center + 1, count);
        }
        return count;
    }

    // Expands around a given center and finds palindromes
    public static int expandAroundCenter(String value, String[] palindrome, int left, int right, int count) {
        while (left >= 0 && right < value.length() && value.charAt(left) == value.charAt(right)) {
            if (right - left + 1 > 1) {
                String temp = "";
                for (int i = left; i <= right; i++) {
                    temp += value.charAt(i);
                }
                boolean exist = false;

                for (int j = 0; j < count; j++) {
                    if (palindrome[j].equals(temp)) {
                        exist = true;
                        break;
                    }
                }

                if (!exist) {
                    palindrome[count++] = temp;
                }
            }
            left--;
            right++;
        }
        return count;
    }

    // Returns the Nth Fibonacci number
    public int getFibonacciNumber() {
        if (this.number == null)
            return -1;
        if (this.number < 0) {
            return 0;
        }
        if (this.number == 0)
            return 0;
        if (this.number == 1)
            return 1;
        int first = 0, second = 1;
        for (int i = 2; i <= this.number; i++) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }

    // Converts a snake_case string to camelCase
    public String snakeToCamel() {
        if (this.value == null || this.value.isEmpty())
            return "Invalid Input";
        String result = "";
        Boolean nextCapital = false;
        for (int i = 0; i < this.value.length(); i++) {
            if (this.value.charAt(i) == '_') {
                nextCapital = true;
            } else if (nextCapital) {
                result += Character.toUpperCase(this.value.charAt(i));
                nextCapital = false;
            } else {
                result += this.value.charAt(i);
            }
        }
        return result;
    }

    // Converts a binary string to decimal number
    public int binaryToDecimal() {

        if (this.value == null || this.value.length() == 0) {
            return -1;
        }
        for (int i = 0; i < this.value.length(); i++) {
            char c = this.value.charAt(i);
            if (c != '0' && c != '1') {
                System.out.println("Invalid input");
                return -1;
            }
        }
        int decimal = 0;
        int length = this.value.length();
        for (int i = length - 1; i >= 0; i--) {
            if (this.value.charAt(i) == '1') {
                decimal += Math.pow(2, length - i - 1);
            }
        }
        return decimal;
    }

    // Counts consonants in a given string
    public int countConsonants() {
        if (this.value == null || this.value.isEmpty())
            return -1;
        int count = 0;
        String temperory = this.value.toLowerCase();

        for (int i = 0; i < temperory.length(); i++) {
            char currentCharacter = temperory.charAt(i);
            if (currentCharacter >= 'a' && currentCharacter <= 'z') {
                if (currentCharacter != 'a' && currentCharacter != 'e' && currentCharacter != 'i'
                        && currentCharacter != 'o' && currentCharacter != 'u') {
                    count++;
                }
            }
        }
        return count;
    }

    // Expands a compressed string
    public String expandCharacters() {
        if (this.value == null || this.value.isEmpty())
            return "Invalid Input";
        String result = "";
        int i = 0;

        while (i < this.value.length()) {
            char current = this.value.charAt(i);
            if (i + 1 >= this.value.length() || !Character.isDigit(this.value.charAt(i + 1))) {
                return "Invalid Input";
            }
            i++;
            String number = "";
            while (i < this.value.length() && Character.isDigit(this.value.charAt(i))) {
                number += this.value.charAt(i);
                i++;
            }

            int repeat = Integer.parseInt(number);
            for (int j = 0; j < repeat; j++) {
                result += current;
            }
        }
        return result;
    }

    // Returns the frequency of each consecutive character
    public String characterFrequency() {
        if (this.value == null || this.value.isEmpty())
            return "Invalid Input";
        String result = "";
        for (int i = 0; i < this.value.length();) {
            char currentCharacter = this.value.charAt(i);
            int count = 0;
            while (i < this.value.length() && this.value.charAt(i) == currentCharacter) {
                count++;
                i++;
            }
            result += currentCharacter;
            result += count;
        }
        return result;
    }

    // Checks if a number is prime or not
    public String isPrime() {
        if (this.number == null)
            return "Invalid Input";
        if (this.number <= 1)
            return "The number is NOT prime";
        for (int i = 2; i * i <= this.number; i++) {
            if (this.number % i == 0)
                return "The number is NOT prime";
        }
        return "The number is PRIME";
    }

    // Finds length of the longest substring with all unique characters
    public int longestUniqueSubstring() {
        if (this.value == null || this.value.isEmpty())
            return -1;
        int[] lastIndex = new int[256];
        for (int i = 0; i < 256; i++)
            lastIndex[i] = -1;

        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < this.value.length(); right++) {
            char c = this.value.charAt(right);
            if (lastIndex[c] >= left) {
                left = lastIndex[c] + 1;
            }
            lastIndex[c] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    // Arrays for converting numbers to words
    static String[] ones = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };

    static String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty",
            "Sixty", "Seventy", "Eighty", "Ninety" };

    // Converts two-digit number to words
    static String convertTwoDigits(int number) {
        if (number < 20)
            return ones[number];
        else {
            return tens[number / 10] + (number % 10 != 0 ? " " + ones[number % 10] : "");
        }
    }

    public String numberToWords() {
        if (this.number == null)
            return "Invalid Input";

        String result = "";
        int num = this.number;
        int crore = (int) (num / 10000000);
        num %= 10000000;

        int lakh = (int) (num / 100000);
        num %= 100000;

        int thousand = (int) (num / 1000);
        num %= 1000;

        int hundred = (int) (num);

        // Handle crores
        if (crore > 0) {
            result += convertTwoDigits(crore);
            result += " crore ";
        }
        // Handle lakhs
        if (lakh > 0) {
            result += convertTwoDigits(lakh);
            result += " lakh ";
        }
        // Handle thousands
        if (thousand > 0) {
            result += convertTwoDigits(thousand);
            result += " thousand ";
        }

        // Handle hundreds
        if (hundred > 0) {
            String temp = "";
            if (hundred > 99) {
                temp += ones[hundred / 100] + " hundred ";
                if (hundred % 100 != 0) {
                    temp += " ";
                }
            }
            if (hundred % 100 != 0) {
                hundred = hundred % 100;
                temp += convertTwoDigits(hundred);
            }
            result += temp;
        }
        return result;
    }
}