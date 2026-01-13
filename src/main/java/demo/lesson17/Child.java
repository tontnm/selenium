package demo.lesson17;

import org.testng.annotations.Test;

public class Child extends Parent {


    @Test
    public void ChildMethod() {
        parentMethod();
        Sublings p = new Sublings(3);
        System.out.println(p.increment());
        System.out.println(p.decrement());
    }
}
