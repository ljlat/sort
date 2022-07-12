public class quik {
    public static void quickSort(int []array) {
        sort(array,0,array.length-1);
    }
    public static void sort(int[] array,int low,int high) {
        int par = partition(array,low,high);
        if(par > low+1) {
            sort(array,low,par-1);
        }
        if(par < high-1) {
            sort(array,par+1,high);
        }
    }
    public static int partition(int[] array,int low,int high) {
        int pivot = array[low];
        while(low < high) {
            while(pivot < array[high] && low < high) {
                high--;
            }
            if(low >= high) {
                break;
            } else {
                array[low] = array[high];
                low++;
            }
            while(pivot > array[low] && low < high) {
                low++;
            }
            if(low >= high) {
                break;
            } else {
                array[high] = array[low];
                high--;
            }
        }
        array[low] = pivot;
        return low;
    }


    public static void main(String[] args) {
        int arr[]={2,3,65,5,4,87,9,52,45,4,5,5};
        quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"\t");
        }
    }



}
