package Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class GetStreamTest {
    public static void main(String[] args) {
        Collection<String> list = new ArrayList<>();
        Stream<String> s1 = list.stream();
    }
}
