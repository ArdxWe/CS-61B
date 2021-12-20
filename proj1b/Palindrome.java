public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }

        Deque<Character> res = new LinkedListDeque<>();

        for (int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }

        return res;
    }

    private boolean dequeIsPalindrome(Deque<Character> deque) {
        if (deque == null || deque.size() < 2) {
            return true;
        }

        Character first = deque.removeFirst();
        Character last = deque.removeLast();
        if (first == last) {
            return dequeIsPalindrome(deque);
        }

        return false;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> letters = wordToDeque(word);
        return dequeIsPalindrome(letters);
    }

    private boolean dequeIsPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque == null || deque.size() < 2) {
            return true;
        }

        Character first = deque.removeFirst();
        Character last = deque.removeLast();
        if (cc.equalChars(first, last)) {
            return dequeIsPalindrome(deque, cc);
        }

        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> letters = wordToDeque(word);
        return dequeIsPalindrome(letters, cc);
    }
}
