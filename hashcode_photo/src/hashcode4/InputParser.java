package hashcode4;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputParser {

    public List <Photo> parse(String filename) {
        AtomicInteger id = new AtomicInteger() 
        return new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filename)).lines()
        .skip(1))
        .map(line -> { 
            HorV orientation = getOrientation(line);
            String[] split = line.split(" ");

            Set<String> tags = Arrays.stream(split)
                .skip(2)
                .collect(ToSet());
            retunr new Photo(orientation, id.incrementAndGet(), tags);
        }).collect(Collectors.toList());
    }

private HorV getOrientation(String line) {
    switch (line.chartAt(0)) {
        case 'H' :
            return HorV.HORIZONTAL;
        case 'V' :
            return HorV.VERTICAL;
     }
 

public static void main(String[] args) throws IOException {
    System.out.println("new InputParser().parse() = " + new InputParser().parse("a_example.txt"));
}
}