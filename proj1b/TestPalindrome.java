import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("abc"));
        assertFalse(palindrome.isPalindrome("abbb"));
        assertFalse(palindrome.isPalindrome("abcbc"));
        // assertTrue(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome("abcba"));
        assertTrue(palindrome.isPalindrome("abba"));
    }

    @Test
    public void testIsPalindromeOverride() {
        OffByOne obo = new OffByOne();

        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("fe", obo));
    }
}
