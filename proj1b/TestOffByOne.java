import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne1() {
        assertTrue(offByOne.equalChars('a', 'b'));
    }

    @Test
    public void testOffByOne2() {
        assertFalse(offByOne.equalChars('a', 'z'));
    }

    @Test
    public void testOffByOne3() {
        assertTrue(offByOne.equalChars('e', 'f'));
    }

    @Test
    public void testOffByOne4() {
        assertTrue(offByOne.equalChars('%', '&'));
    }

    @Test
    public void testOffByOne5() {
        assertFalse(offByOne.equalChars('x', 'Y'));
    }
}
