package selenium.demo.lesson17;

public class Sublings {
    int a;

    public Sublings(int a) {
        this.a = a;
    }

    public int increment() {
        a += 1;
        return a;
    }

    public int decrement() {
        a -= 1;
        return a;
    }
}
