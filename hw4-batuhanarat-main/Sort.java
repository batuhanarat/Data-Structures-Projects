import java.lang.*;
import java.util.Arrays;
import java.util.Collections;

public class Sort {
    public int[] vals = null;
    public int[] inds = null;
    public void flush()
    {
        // for restarting the class variables
        this.vals = null;
        this.inds = null;
    }
    private void swap(int[] arr, int i, int j)
    {
        /// You should implement a swap function between the 2 indices
        int temp;
        temp= arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
        // you may delete this function if not needed
    }

    public int[] getIndices(int size){
            /*Returns array of indices of the
            unsorted array input
            */
        int [] inds ;
        inds = new int[size];
        for(int i=0; i<size; i++) {
            inds[i]=i;
        }
        ///  Generate indices
        return inds;
    }

    private int[] reverseArray(int[] array) {
        int size = array.length;
        for(int i = 0; i < size / 2; i++)
        {
            int temp = array[i];
            array[i] = array[size - i - 1];
            array[size - i - 1] = temp;
        }
        return array;
    }
//using max heap it sorts the array
    private void heapsort(int[] array,int[] inds, boolean largest) {
        /*
        Implement heap sort, you  can use Java heap or you can implement your own helper function
         */
        int n = array.length;

        for (int i = (n / 2) - 1; i >= 0; i--)
            heapify(array, n, i, inds);

        // removing and collecting data from the heap
        for (int i = n - 1; i > 0; i--) {

            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // call max heapify on the reduced heap
            heapify(array, i, 0, inds);
        }
        if(largest) {
            reverseArray(array);
            reverseArray(inds);

        }

        this.vals = array; // you can change this
        this.inds = inds; // change this if you sort indices as well
    }

    // heapify function fixes the order of the heap
    void heapify(int arr[], int n, int i,int[] inds)
    {
        int largest = i;
        // left = 2*i + 1
        int l = 2 * i + 1;
        // right = 2*i + 2
        int r = 2 * i + 2;


        //check left child is bigger
        if (l < n && arr[l] > arr[largest])
            largest = l;

         //check right child is bigger
        if (r < n && arr[r] > arr[largest])
            largest = r;

        //reorder heap
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            swap(inds,i,largest);
            arr[largest] = temp;

            heapify(arr, n, largest,inds);
        }
    }

    private void insertionSort (int[] arr,int[] inds,boolean largest) {
        //This is according to min heap
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int a = i - 1;

            while (a >= 0 && arr[a] > key) {
                arr[a + 1] = arr[a];
                swap(inds,a+1,a);
                a = a - 1;
            }
            arr[a + 1] = key;
        }
        if(largest) {
            reverseArray(arr);
            reverseArray(inds);

        }

        this.vals = arr; // you can change this
        this.inds = inds; // change this if you sort indices as well
    }
    public void sort(int[] array,boolean largest, String alg) throws Exception{
        /*
        You do not need to change this method
        but you are free to do so.
         */

        int[] inds;
        if (this.inds == null) {
            inds = getIndices(array.length);
        }
        else {
            inds = this.inds;
        }
        if (alg.equals("heapsort")) {
            heapsort(array, inds, largest);
        }
        else if(alg.equals("insertionsort")) {
            insertionSort(array,inds,largest);
        }
        this.vals = array;
        this.inds = inds;
    }

    public void topk(int[] array,int k,boolean largest, String alg){
        flush();
        inds = getIndices(array.length);
        if (alg.equals("heapsort")) {
            heapsort(array, inds, largest);
        }
        else if(alg.equals("insertionsort")) {
            insertionSort(array,inds,largest);
        }

        int[] topkarray = new int[k];
        int[] topkindices = new int[k];

        for(int i=0 ; i<k ; i++) {
            if(largest) {
                topkarray[i]=this.vals[i];
                topkindices[i]=this.inds[i];
            } else {
                topkarray[i]=this.vals[i];
                topkindices[i]=this.inds[i];

//                topkarray[i]=this.vals[this.vals.length-(i+1)];
  //              topkindices[i]=this.inds[this.inds.length-(i+1)];

            }
        }

        /*
        implement topk method
         */
        this.vals = topkarray; // you can change this
        this.inds = topkindices; // change this
    }


    public void fast_topk(int[] array,int k, boolean largest,String alg){
        flush();
        /*
        Assume that, k <= array.length / 50
        for every fast_topk operation
        */
        this.vals = array; // you can change this
        this.inds = null; // change this
    }

}

