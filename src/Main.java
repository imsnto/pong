import java.util.*;

class Pair{
    int first;
    int second;

    public Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}
public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static boolean isSorted(int[] arr, int n){
        for(int i=1; i<n; i++){
            if(arr[i-1] > arr[i]) return false;
        }
        return true;
    }

    public static List<Pair> seq = new ArrayList<>();
    public static boolean[][] vis;
    public static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    public static int[] dy = {0, 0, 1, -1, 1, -1, -1, 1};


    public static void main(String[] args) {
        String[] s = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        List<String> ar = new ArrayList<>(Arrays.asList(s));

        String ss = sc.next();

        System.out.println(5 - ar.indexOf(ss));

    }
}
/*
        public static Scanner sc = new Scanner(System.in);

        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(key, value);
        int getValue = mp.getOrDefault(key, defaultValue);

        String str = "main-class";
        StringBuilder sb = new StringBuilder(str);
        String reverseStr = sb.reverse().toString();
        char ch = str.charAt(index);

        String[] s = {'abc', "def", "fd"};
        List<String> ar = new ArrayList<>(Arrays.asList(s));

        Collections.reverse(arr);

        List<Integer> ar = new ArrayList<>();
        arr.add(val);
        int leftMostIndex = ar.indexOf(val);
        int rightMostIndex = ar.lastIndexOf(val);

        List<List<Integer>> ar = new ArrayList<>(); //2D List

        boolean isUpper = Character.isUpperCase(s.charAt(i));
 */