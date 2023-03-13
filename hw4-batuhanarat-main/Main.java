import java.io.IOException;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
public class Main {
    static int getRandomNumber(int min, int max) {
        // random generator
        Random rd = new Random();
        double rand = rd.nextDouble();
        return (int) (rand * (max - min) + min);
    }
    static int[] copyArray(int[] arr) { // Helper for array copying
        int[] array_cpy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array_cpy[i] = arr[i];
        }
        return array_cpy;
    }
    static int[] randArray(int size, int min,int max) { // Helper for generating rand Array
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            int rand = getRandomNumber(min, max);
            array[i] = rand;
        }
        return array;
    }
    static int[] test_array(String file) throws IOException {
        int[] arr = new int[100000];
        BufferedReader bf = new BufferedReader(new FileReader(file));
        // read entire line as string
        String line = bf.readLine();
        int c = 0;
        while (line != null) {
            int in = Integer.parseInt(line);
            arr[c] = in;
            line = bf.readLine();
            c++;
        }
        bf.close();
        return arr;
    }
    static void print_head(int[] array) {
        for (int j : array) {
            System.out.println(j);
        }
    }



    public static void main(String[] args) throws Exception {
        int LENGTH = (int) 5; // equivalent of 10^(Ex)
        int maximum = 100; // maximum integer in your array
        int minimum = -maximum; // minimum integer in your array
        int k = 10; // topK "K" value

        //autograder spesific
        boolean test = false; // make it true for autograder
        // you have to make it false to generate custom sized arrays for time analysis !
        int k_test = 3;; // do not change it
        int [] input = test_array("input.txt"); // length 1e5 you can comment out until submission
        //

        int[] array = test ? input : randArray(LENGTH,minimum,maximum);
        Sort sort = new Sort();

        int [] t_array = copyArray(array); // to avoid time to copy, generate new one before the timing operations
      //  long start = System.nanoTime();

       long start = System.currentTimeMillis();
        sort.sort(t_array,true,"insertionsort");
        long finish = System.currentTimeMillis();
        //long finish = System.nanoTime();

        float time = (float) (finish-start);
        System.out.println("insertion sort took "+time+" ms.");
        System.out.println("Insertion Sort Largest");
        print_head(sort.vals);
        sort.flush();
        sort.sort(copyArray(array),false,"insertionsort");
        System.out.println("Insertion Sort Smallest");
        print_head(sort.vals);

        t_array = copyArray(array); // to avoid time to copy, generate new one before the timing operations
        //start = System.nanoTime();

        start = System.currentTimeMillis();
        sort.sort(t_array,true,"heapsort");
        //finish = System.nanoTime();

        finish = System.currentTimeMillis();
        time = (float) (finish-start);
        System.out.println("heap sort took "+time+" ms.");
        System.out.println("Insertion HeapSort Largest");
        print_head(sort.vals);
        sort.flush();
        sort.sort(copyArray(array),false,"heapsort");
        System.out.println("Insertion Sort Smallest");
        print_head(sort.vals);

        t_array = copyArray(array);
       start = System.currentTimeMillis();
        //start = System.nanoTime();


        sort.topk(t_array,k_test,true,"heapsort");
        finish = System.currentTimeMillis();
      //  finish = System.nanoTime();
        time = (float) (finish-start);
        System.out.println("TopK took "+time+" ms.");
        System.out.println(sort.inds.length + sort.vals.length);
        System.out.println("TOPK HeapSort Largest");
        print_head(sort.vals);
        System.out.println("TOPK HeapSort Largest Indexes");
        print_head(sort.inds);
        sort.topk(copyArray(array),k_test,false,"heapsort");
        System.out.println(sort.inds.length + sort.vals.length);
        System.out.println("TOPK HeapSort Smallest");
        print_head(sort.vals);
        System.out.println("TOPK HeapSort Smallest Indexes");

        print_head(sort.inds);
/*
        t_array = copyArray(array);
        start = System.currentTimeMillis();
        sort.fast_topk(t_array,k_test,true,"heapsort");
        finish = System.currentTimeMillis();
        time = (float) (finish-start);
        System.out.println("Fast-TopK took "+time+" ms.");
        System.out.println(sort.inds.length + sort.vals.length);
        print_head(sort.vals);
        print_head(sort.inds);
        sort.fast_topk(copyArray(array),k_test,false,"heapsort");
        System.out.println(sort.inds.length + sort.vals.length);
        print_head(sort.vals);
        print_head(sort.inds);
*/
    }

}