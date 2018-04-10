import java.util.ArrayList;

public class HelloWorld {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        var list = new ArrayList<Integer>();
        list.add(2);
        boolean isP = true;
        for (int i = 3; i < 1000000; i += 2) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) * list.get(j) > i) {
                    isP = true;
                    break;
                } else if (i % list.get(j) == 0) {
                    isP = false;
                    break;
                }
            }
            if (isP)
                list.add(i);
        }
        long endTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ", ");
            if ((i + 1) % 10 == 0)
                System.out.println("");
        }
        System.out.println("");
        System.out.println("cost: " + (endTime - startTime) + "ms");
    }
}
