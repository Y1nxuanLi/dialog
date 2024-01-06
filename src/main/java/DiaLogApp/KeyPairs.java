package DiaLogApp;

public class KeyPairs<A, B> {
    private A first;
    private B second;

    public KeyPairs(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

}
