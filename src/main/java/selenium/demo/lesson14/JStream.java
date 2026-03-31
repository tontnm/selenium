package selenium.demo.lesson14;

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

        //names.stream() : tạo một luồng (stream) từ danh sách names.
        //.filter(s -> s.startsWith("A")) : lọc các phần tử trong stream sao cho chỉ giữ lại những chuỗi bắt đầu bằng chữ "A".
        //.count() : đếm số phần tử thỏa điều kiện.
        //Kết quả được gán vào biến c (kiểu Long) và in ra màn hình.
        //👉 Ví dụ: nếu names = ["An", "Bao", "Anh", "Minh"] thì c = 2 (vì có "An" và "Anh" bắt đầu bằng "A").
        Long c = names.stream().filter(s -> s.startsWith("A")).count();
        System.out.println(c);

        //.filter(s -> s.length() > 4) : lọc các chuỗi có độ dài lớn hơn 4 ký tự.
        //.forEach(s -> System.out.println(s)) : duyệt qua từng phần tử còn lại và in ra màn hình.
        //👉 Ví dụ: nếu names = ["An", "Bao", "Anh", "Minh", "Thanh"] thì sẽ in "Thanh" (vì chỉ "Thanh" có độ dài > 4).
        names.stream().filter(s -> s.length() > 4).forEach(s -> System.out.println(s));

        //.filter(s -> s.length() > 4) : lọc chuỗi có độ dài > 4.
        //.limit(1) : chỉ lấy 1 phần tử đầu tiên trong stream sau khi lọc.
        //.forEach(...) : in phần tử đó ra màn hình.
        //👉 Ví dụ: với names = ["An", "Bao", "Anh", "Minh", "Thanh", "Hoang"] thì sẽ in "Thanh" (vì "Thanh" là phần tử đầu tiên
        // thỏa điều kiện độ dài > 4).
        names.stream().filter(s -> s.length() > 4).limit(1).forEach(s -> System.out.println(s));
        System.out.println();
    }

    public static void streamMap() {
        //Stream.of(...) : tạo một stream từ danh sách các chuỗi.
        //.filter(s -> s.endsWith("a")) : lọc các chuỗi kết thúc bằng "a".
        //→ Kết quả: "Alekhya", "Rama".
        //.map(s -> s.toUpperCase()) : chuyển các chuỗi còn lại thành chữ hoa.
        //→ Kết quả: "ALEKHYA", "RAMA".
        //.forEach(...) : in từng phần tử ra màn hình.
        Stream.of("Abhijeet", "Don", "Alekhya", "Adam", "Rama").
                filter(s -> s.endsWith("a")).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
        System.out.println();

        List<String> names = Arrays.asList("Abhijeet", "Don", "Alekhya", "Adam", "Rama");
        //.filter(s -> s.startsWith("A")) : lọc các chuỗi bắt đầu bằng "A".
        //→ Kết quả: "Abhijeet", "Alekhya", "Adam".
        //.sorted() : sắp xếp theo thứ tự từ điển (alphabet).
        //→ "Abhijeet", "Adam", "Alekhya".
        //.map(...) : chuyển sang chữ hoa.
        //→ "ABHIJEET", "ADAM", "ALEKHYA".
        //.forEach(...) : in ra màn hình.
        names.stream().filter(s -> s.startsWith("A")).sorted().
                map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));

        List<String> names2 = Arrays.asList("Ab", "Do", "Al", "Ad", "Ra");
        //Stream.concat(...) : nối hai stream (names và names2) thành một stream mới.
        //.anyMatch(s -> s.equalsIgnoreCase("Adam")) : kiểm tra xem có phần tử nào bằng "Adam" (không phân biệt hoa thường).
        //→ Vì "Adam" có trong names, nên kết quả là true.
        //System.out.println(flag) : in true.
        //Assert.assertTrue(flag) : kiểm tra bằng JUnit, xác nhận rằng flag phải đúng. Nếu sai sẽ báo lỗi test.
        Stream<String> newStream = Stream.concat(names.stream(), names2.stream());
        boolean flag = newStream.anyMatch(s -> s.equalsIgnoreCase("Adam"));
        System.out.println(flag);
        Assert.assertTrue(flag);

        //Giống đoạn đầu tiên, nhưng thay vì forEach, ta dùng .collect(Collectors.toList()) để thu kết quả vào một List.
        //Kết quả: ls = ["ALEKHYA", "RAMA"].
        //ls.get(0) : lấy phần tử đầu tiên trong danh sách → "ALEKHYA".
        List<String> ls = Stream.of("Abhijeet", "Don", "Alekhya", "Adam", "Rama").
                filter(s -> s.endsWith("a")).map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(ls.get(0));

        //values là danh sách số nguyên có phần tử trùng lặp.
        //.sorted() : sắp xếp tăng dần.
        //→ Kết quả: [1, 2, 2, 3, 5, 7, 7, 9].
        //.collect(Collectors.toList()) : thu kết quả vào li.
        //li.get(2) : lấy phần tử ở vị trí index = 2 (tức phần tử thứ 3).
        //→ Kết quả: 2.
        List<Integer> values = Arrays.asList(3, 2, 2, 7, 5, 1, 9, 7);
//        values.stream().distinct().forEach(s -> System.out.print(s + " \n"));
        List<Integer> li = values.stream().sorted().collect(Collectors.toList());
        System.out.println(li.get(2));
    }
}

/*
=============================
JAVA STREAM API - HÀM THÔNG DỤNG
=============================

1. filter(Predicate)
-----------------------------
Input: ["An", "Bao", "Anh", "Minh"]
Code: names.stream().filter(s -> s.startsWith("A")).toList()
Output: ["An", "Anh"]

2. map(Function)
-----------------------------
Input: ["An", "Bao", "Anh"]
Code: names.stream().map(String::toUpperCase).toList()
Output: ["AN", "BAO", "ANH"]

3. flatMap(Function)
-----------------------------
Input: [["A", "B"], ["C", "D"]]
Code: list.stream().flatMap(Collection::stream).toList()
Output: ["A", "B", "C", "D"]

4. distinct()
-----------------------------
Input: [3, 2, 2, 7, 5, 1, 9, 7]
Code: values.stream().distinct().toList()
Output: [3, 2, 7, 5, 1, 9]

5. sorted()
-----------------------------
Input: ["Ban", "An", "Cuong"]
Code: names.stream().sorted().toList()
Output: ["An", "Ban", "Cuong"]

Code: names.stream().sorted(Comparator.reverseOrder())
Output: ["Cuong","Ban","An"]

6. limit(n)
-----------------------------
Input: ["A", "B", "C", "D"]
Code: names.stream().limit(2).toList()
Output: ["A", "B"]

7. skip(n)
-----------------------------
Input: ["A", "B", "C", "D"]
Code: names.stream().skip(2).toList()
Output: ["C", "D"]

8. peek(Consumer)
-----------------------------
Input: ["A", "B"]
Code: names.stream().peek(System.out::println).toList()
Output: (in ra "A", "B") và trả về ["A", "B"]

9. forEach(Consumer)
-----------------------------
Input: ["A", "B", "C"]
Code: names.stream().forEach(System.out::println)
Output: In ra:
A
B
C

10. collect(Collectors.toList())
-----------------------------
Input: ["A", "B", "C"]
Code: names.stream().collect(Collectors.toList())
Output: List ["A", "B", "C"]

11. toArray()
-----------------------------
Input: ["A", "B", "C"]
Code: names.stream().toArray(String[]::new)
Output: Array ["A", "B", "C"]

12. reduce(BinaryOperator)
-----------------------------
Input: [1, 2, 3, 4]
Code: values.stream().reduce(0, Integer::sum)
Output: 10

13. count()
-----------------------------
Input: ["A", "B", "C"]
Code: names.stream().count()
Output: 3

14. anyMatch(Predicate)
-----------------------------
Input: ["An", "Bao", "Anh"]
Code: names.stream().anyMatch(s -> s.equals("Bao"))
Output: true

15. allMatch(Predicate)
-----------------------------
Input: ["An", "Anh"]
Code: names.stream().allMatch(s -> s.startsWith("A"))
Output: true

16. noneMatch(Predicate)
-----------------------------
Input: ["An", "Bao"]
Code: names.stream().noneMatch(s -> s.equals("Cuong"))
Output: true

17. findFirst()
-----------------------------
Input: ["A", "B", "C"]
Code: names.stream().findFirst().get()
Output: "A"

18. findAny()
-----------------------------
Input: ["A", "B", "C"]
Code: names.stream().findAny().get()
Output: "A" (hoặc "B" hoặc "C" tùy runtime)

* */