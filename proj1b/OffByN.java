public class OffByN implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return (y - x == N) || (y - x == -N);
    }

    public OffByN(int N) {
        this.N = N;
    }

    private final int N;
}
