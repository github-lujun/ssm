import java.util.*;

@SuppressWarnings("all")
public class MyCollection {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list = new LinkedList<>();
        //过时
        /*list = new Vector<>();
        list = new Stack<>();*/

        Queue<Integer> queue = new LinkedList<>();
        Deque<Integer> deque = new LinkedList<>();

        Set<Integer> set = new HashSet<>();
        set = new TreeSet<>();
        set = new LinkedHashSet<>();

        Map<String,Integer> map = new HashMap<>();
        map = new TreeMap<>();
        map = new LinkedHashMap<>();
        //过时
        /*map = new Hashtable<>();*/

        /**
         * 1.顺序查找树
         * 2.B-树
         * 3.B+树
         * 4.红黑树
         * */
    }
}
