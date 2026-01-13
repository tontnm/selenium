package demo.lesson14;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JStream {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Abhijeet");
        names.add("Don");
        names.add("Alekhya");
        names.add("Adam");
        names.add("Ram");

        // đếm chữ có A ở đầu trong mảng
        int count = 0;
        for (int i = 0; i < names.size(); i++) {
            String actual = names.get(i);
            if (actual.startsWith("A")) {
                count++;
            }
        }
        System.out.println(count);

        streamFilter();
        streamMap();
    }

    //    @Test
    public static void streamFilter() {
        List<String> names = new ArrayList<>();
        names.add("Abhijeet");
        names.add("Don");
        names.add("Alekhya");
        names.add("Adam");
        names.add("Ram");

        Long c = names.stream().filter(s -> s.startsWith("A")).count();
        System.out.println(c);
        names.stream().filter(s -> s.length() > 4).forEach(s -> System.out.println(s));
        names.stream().filter(s -> s.length() > 4).limit(1).forEach(s -> System.out.println(s));
        System.out.println();
    }

    public static void streamMap() {
        Stream.of("Abhijeet", "Don", "Alekhya", "Adam", "Rama").
                filter(s -> s.endsWith("a")).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
        System.out.println();
        List<String> names = Arrays.asList("Abhijeet", "Don", "Alekhya", "Adam", "Rama");
        names.stream().filter(s -> s.startsWith("A")).sorted().
                map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));

        List<String> names2 = Arrays.asList("Ab", "Do", "Al", "Ad", "Ra");
        Stream<String> newStream = Stream.concat(names.stream(), names2.stream());
        boolean flag = newStream.anyMatch(s -> s.equalsIgnoreCase("Adam"));
        System.out.println(flag);
        Assert.assertTrue(flag);

        List<String> ls = Stream.of("Abhijeet", "Don", "Alekhya", "Adam", "Rama").
                filter(s -> s.endsWith("a")).map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(ls.get(0));

        List<Integer> values = Arrays.asList(3, 2, 2, 7, 5, 1, 9, 7);
//        values.stream().distinct().forEach(s -> System.out.print(s + " \n"));
        List<Integer> li = values.stream().sorted().collect(Collectors.toList());
        System.out.println(li.get(2));
    }
}
