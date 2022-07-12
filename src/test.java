import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class test {
    static long ComparisonTimes = 0, MoveTimes = 0;//比较次数和移动次数
    // 交换数组中2个值的位置
    private static void swap(int index1, int index2, int[] array) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    //冒泡排序
    public static int[] BubbleSort(int[] sourceArray) {
        ComparisonTimes = 0;
        MoveTimes = 0;
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        if (arr.length < 2)
            return arr;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1, arr);
                    MoveTimes += 3;
                }
                ComparisonTimes++;
            }
        }
        return arr;
    }

    //选择排序
    public static int[] selectSort(int[] sourceArray) {
        ComparisonTimes = 0;
        MoveTimes = 0;
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        if (arr.length < 2)
            return arr;
        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i; j < arr.length; j++) {
                //寻找最小的数
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
                ComparisonTimes++;
            }
            // 判断第一个是不是最小值
            if (i != minIndex) {
                swap(i, minIndex, arr);
                MoveTimes += 3;
            }
        }
        return arr;
    }


    //插入排序
    public static int[] insertSort(int[] sourceArray) {
        ComparisonTimes = 0;
        MoveTimes = 0;
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        if (arr.length < 2)
            return arr;
        // 从第二项开始
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            MoveTimes++;
            int j = i;
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                ComparisonTimes++;
                MoveTimes++;
                j--;
            }
            if (j > 0 && temp >= arr[j - 1])
                ComparisonTimes++;
            arr[j] = temp;
            MoveTimes++;
        }
        return arr;
    }

    //希尔排序
    public static int[] shellSort(int[] sourceArray) {
        ComparisonTimes = 0;
        MoveTimes = 0;
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        if (arr.length < 2)
            return arr;
        int length = arr.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arr[i];
                MoveTimes++;
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                    ComparisonTimes++;
                    MoveTimes++;
                }
                if (j >= 0 && arr[j] > temp)
                    ComparisonTimes++;
                arr[j + step] = temp;
                MoveTimes++;
            }
        }
        return arr;
    }


    //归并排序
    public static int[] mergesort(int[] sourceArray) {
        ComparisonTimes = 0;
        MoveTimes = 0;
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        if (arr.length < 2)
            return arr;
        int middle = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(mergesort(left), mergesort(right));
    }

    protected static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
                MoveTimes++;
            } else if (j >= right.length) {
                result[index] = left[i++];
                MoveTimes++;
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
                MoveTimes++;
                ComparisonTimes++;
            } else {
                result[index] = left[i++];
                MoveTimes++;
                ComparisonTimes++;
            }
        }
        return result;
    }


    //快速排序
    public static int[] QuickSort(int[] sourceArray) {
        ComparisonTimes = 0;
        MoveTimes = 0;
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return quickSort(arr, 0, arr.length - 1);
    }

    private static int[] quickSort(int[] arr, int left, int right) {
        int partitionIndex = quick(arr, left, right);
//        if (left < right) {
//            quickSort(arr, left, partitionIndex - 1);
//            quickSort(arr, partitionIndex + 1, right);
//        }
        if(partitionIndex>left+1){
            quick(arr,left,partitionIndex-1);
        }
        if(partitionIndex<right-1){
            quick(arr,partitionIndex+1,right);
        }
        return arr;
    }

    private static int quick(int[] arr, int left, int right) {
        // 基准值（left）
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[left]) {
                swap(i, index, arr);
                index++;
                MoveTimes += 3;
            }
            ComparisonTimes++;
        }
        swap(left, index - 1, arr);
        MoveTimes += 3;
        return index - 1;
    }







    public static void quickSort(int []array) {
        ComparisonTimes = 0;
        MoveTimes = 0;
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
                ComparisonTimes++;
            }

            if(low >= high) {
                ComparisonTimes++;
                break;
            } else {
                array[low] = array[high];
                low++;
                MoveTimes++;
            }

            while(pivot > array[low] && low < high) {
                low++;
                ComparisonTimes++;
            }
            if(low >= high) {
                ComparisonTimes++;
                break;
            } else {
                array[high] = array[low];
                high--;
                MoveTimes++;
            }

        }
        array[low] = pivot;
        return low;
    }

    //堆排序
    public static int[] HeapSort(int[] sourceArray) {
        ComparisonTimes = 0;
        MoveTimes = 0;
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int len = arr.length;
        if (len < 2)
            return arr;
        buildMaxHeap(arr, len);
        for (int i = len - 1; i > 0; i--) {
            swap(0, i, arr);
            MoveTimes += 3;
            len--;
            heap(arr, 0, len);
        }
        return arr;
    }

    private static void buildMaxHeap(int[] arr, int len) {
        for (int i = len / 2; i >= 0; i--)
            heap(arr, i, len);
    }

    private static void heap(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
            ComparisonTimes++;
        }
        if (left < len && arr[left] <= arr[largest])
            ComparisonTimes++;

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
            ComparisonTimes++;
        }
        if (right < len && arr[right] <= arr[largest])
            ComparisonTimes++;
        if (largest != i) {
            swap(i, largest, arr);
            MoveTimes += 3;
            heap(arr, largest, len);
        }
    }


    //生成随机数组
    public static void random(String path, int n) throws IOException {
        File file = new File(path);
        if (file.exists())
            file.delete();
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file.getName(), true);
        for (int k = 0; k < n; k++) {
            int key = new Random().nextInt();
            String str = String.valueOf(key) + "\n";
            fileWriter.write(str);
        }
        fileWriter.close();
    }

    //储存数组
    public static void save(String path, int[] arr) throws IOException {
        File file = new File(path);
        if (file.exists())
            file.delete();
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file.getName(), true);
        for (int k = 0; k < arr.length; k++) {
            int key = arr[k];
            String str = String.valueOf(key) + "\n";
            fileWriter.write(str);
        }
        fileWriter.close();
    }


    //读取数据
    public static int[] read(String path, int k) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(path);
        int[] arr = new int[k];
        int i = 0;
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            int key = Integer.parseInt(scanner.next());
            arr[i] = key;
            i++;
        }
        scanner.close();
        return arr;
    }
    public static void main(String[] args) throws IOException {

        // random("./250000_2.txt",250000);
        int []array = read("./src/200000_1.txt", 250000);

        long begin = System.nanoTime();
          insertSort(array);//插入排序
        long end = System.nanoTime();
     //   System.out.println(begin+"\t"+end);
          System.out.print("插入排序time:" + (end - begin) / 1000000 + " ms\t");
          System.out.println("Comtimes:" + ComparisonTimes + "\tMoveTimes:" + MoveTimes);
        begin = System.nanoTime();
        shellSort(array);//希尔排序
        end = System.nanoTime();
      //  System.out.println(begin+"\t"+end);
        System.out.print("希尔排序time:" + (end - begin) / 1000000 + " ms\t");
        System.out.println("Comtimes:" + ComparisonTimes + "\tMoveTimes:" + MoveTimes);
        begin = System.nanoTime();
      //  QuickSort(array); //快速排序
        quickSort(array);
        end = System.nanoTime();
       // System.out.println(begin+"\t"+end);
        System.out.print("快速排序time:" + (end - begin) / 1000000 + " ms\t ");
        System.out.println("Comtimes:" + ComparisonTimes + "\tMoveTimes:" + MoveTimes);
        begin = System.nanoTime();
        HeapSort(array);//堆排序
        end = System.nanoTime();
       // System.out.println(begin+"\t"+end);
        System.out.print("堆排序time:" + (end - begin) / 1000000 + " ms\t");
        System.out.println("Comtimes:" + ComparisonTimes + "\tMoveTimes:" + MoveTimes);
        begin = System.nanoTime();
         BubbleSort(array);//冒泡排序
        end = System.nanoTime();
      //  System.out.println(begin+"\t"+end);
          System.out.print("冒泡排序time:" + (end - begin) / 1000000 + " ms\t");
          System.out.println("Comtimes:" + ComparisonTimes + "\tMoveTimes:" + MoveTimes);
        begin = System.nanoTime();
          selectSort(array);//选择排序
        end = System.nanoTime();
          System.out.print("选择排序time:" + (end - begin) / 1000000 + " ms\t");
          System.out.println("Comtimes:" + ComparisonTimes + "\tMoveTimes:" + MoveTimes);
        begin = System.nanoTime();
        mergesort(array);//归并排序
        end = System.nanoTime();
        System.out.print("归并排序time:" + (end - begin) / 1000000 + " ms\t");
        System.out.println("Comtimes:" + ComparisonTimes + "\tMoveTimes:" + MoveTimes);

    }
}
