package com.fengbangleasing.leasertradeservice.feature;

import com.fengbangleasing.leasertradeservice.service.base.StatisticService;
import org.junit.Test;

import java.util.*;

/**
 * @description: 排序算法
 * @author: lifeng
 * @create: 2021-02-02 14:29:58
 **/
public class Sort {

    public  int a0[] = {4,5,6,9,1,3,2};
    public  int []a1 = {4,5,6,9,1,3,2};

    //冒泡排序(交换排序，空间复杂度O(1),时间复杂度O(n^2))
    public static void bubbleSort1(int []a,int n){
        //最大的元素往下沉，小元素向上冒，外层循环控制待排元素，内层循环控制比较和排序（下沉的最大元素就不管了，所以要-i）
        int i,j;//(冒泡排序--i从0开始，j从1开始，)
        for (i = 0; i < n; i++) {
            for (j = 1; j < n-i; j++) {
                if (a[j-1] > a[j]){
                    int temp;
                    temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public static void bs(int []a){
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n-i; j++) {
                if(a[j-1]>a[j]){
                    int temp;
                    temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }


    //快速排序(交换排序的一种，空间复杂度（最好O(log2^n),最坏n-1，平均O(log2^n)）,时间复杂度最差为O(n^2)内部排序中最优的算法)
    public static void quickSort(int []a,int low,int high){
        int start = low;
        int end = high;
        int key = a[low];
        while(end > start){
            //从后往前比较
            while(end > start && a[end] >= key) {
                //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，
                end--;
            }
            if (a[end] <= key){
                int temp= a[end];
                a[end] = a[start];
                a[start] = temp;
            }

            //从前往后比较
            while(end>start && a[start] <= key){
                //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            }
            if (a[start] >= key){
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了，左边的值都比关键值小，右边的 值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        if (start>low){ quickSort(a,low,start-1);}//左边序列。第一个索引位置到关键值索引-1
        if (end<high){ quickSort(a,end+1,high);}//右边序列。从关键值索引+1 到最后一个
    }

    //快速排序的分治思想
    public static void quickSortkkk(int [] a,int left ,int right){
        int n = a.length;
        if (left>right){
            return;
        }
        int start = left;
        int end = right;
        int temp = a[left];
        while(start != end){
            while(a[end]>=temp && end >start){
                end --;
            }
            while(a[start]<=temp && end >start){
                start++;
            }
            if (start < end ){
                int k = a[end];
                a[end] = a[start];
                a[start] = k;
            }
        }
        a[left] = a[start];
        a[start] = temp;
        if (start>left) quickSortkkk(a,left,start-1);
        if (right>start)quickSortkkk(a,start+1,right);
    }



    //希尔排序（插入排序的一种，空间复杂度O(1),时间复杂度O(n^2)）--不稳定算法

    private static void shellSort(int[]a){
        int dk = a.length/2;
        while(dk>=1){
            ShellInsertSort(a,dk);
            dk= dk/2;
        }
    }
    private static  void ShellInsertSort(int []a,int dk){
        //类似插入排序，插入排序时的排序增量为1，这里的增量是dk，dk=1时就是直接插入排序
        int n = a.length;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < a[i-dk]){
                int j;
                int insertData = a[i];
                a[i] = a[i-dk];
                for (j=i-dk;j>0 && insertData<a[j];j=j-dk ) {
                    a[j+dk] = a[j];
                }
                a[j+dk] = insertData;
            }
        }
    }
    //二分法排序（插入排序的一种，时间复杂度O(n^2)--与初始序列的顺序有关）--稳定排序算法
    public static void twoDivideSort(int[] a) {
        int i;
        int j;
        int low;
        int high;
        int mid;
        int temp;
        for (i = 1; i < a.length; i++) {
            System.out.println(Arrays.toString(a));
            temp = a[i];//依次插入前面的已排序序列
            low = 0;//设置折半查找的范围
            high = i - 1;
            while (low <= high) {//折半查找
                mid = (low + high) / 2;//取中间点
                if (a[mid] > temp) {
                    high = mid - 1;//查找左半子表
                } else {
                    low = mid + 1;//查找右半子表
                }
            }
            for (j = i - 1; j >= high + 1; j--) {
                a[j + 1] = a[j];//统一后移元素，空出插入位置
            }
            a[high + 1] = temp;//插入操作
        }
    }
    //选择排序实现（选择排序的一种 空间复杂度O(1),时间复杂度(O(n^2))）--不稳定算法
    public static void selectSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            System.out.println("待排序元素：" + a[j]);
            for (j = i + 1; j < n; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
    //插入排序
    public static void insertSort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j >0; j--) {
                if (a[j] < a[j-1]) {
                    int temp = a[j-1];
                    a[j-1] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
    //插入排序优化版（空间复杂度-常量O(1)，时间复杂度-(最好O(n),总体时间复杂度O(n^2))）--稳定算法
    public static void insertSortopt(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int temp = a[i];//记录每次需要插入的值
            int j;
            for (j = i; j >0 && temp<a[j-1]; j--) {//增加循环进入的条件，
                a[j] = a[j-1];//大于待插入的数往后移动留出一个空位放待插入数据
            }
            //跳出循环后，j变化，对应的位置空缺，放入待插入元素
            a[j] = temp;//待插入数据入坑
        }
    }

    public static void insertSortWhile(int[] a){
        for (int i = 0; i < a.length; i++) {
            int insertdata = a[i];//待插入数据
            int index = i-1;//待插入位置
            while (index>=0 && insertdata < a[index]){
                a[index+1] = a[index];//比较的数据比待插入数据大，向后移动
                index--;//需要向前移动取下一个参与对比的数据
            }
            a[index+1] = insertdata;
        }
    }
    //插入排序(内层为while循环)
    public static void InsertSort(int arr[]){
        for (int i = 0; i < arr.length; i++) {
            //插入的数(理解为从i位置取出，i位置为空)
            int insertVal = arr[i];
            //被插入的位置（准备和前一个数比较）
            int index = i-1;
            //如果插入的数据比被插入的数小
            while(index>=0 && insertVal <arr[index]){
                //把arr[index] 向后移动
                arr[index+1] = arr[index];
                //让index向前移动
                index--;
            }
            arr[index+1] = insertVal;
        }
    }

    public static void insertWhileSort(int arr[]){
        for (int i = 0; i < arr.length; i++) {
            int insertVal = arr[i];
            int index = i-1;
            while(index >=0 && insertVal< arr[index]){
                arr[index+1] = arr[index];
                index--;
            }
            arr[index+1] = insertVal;
        }
    }
//总结-直接插入排序内层循环控制待插入值和有序序列中从后往前的元素作对比，找到合适的位置插入，外层循环则是从无序待排序列中获取待插入的数据
    //基数排序(空间复杂度（O(r)-r个队列，r个头尾指针），时间复杂度，进行d趟分配和收集，分配需要O(n),收集需要O(r),所以时间复杂度为O(d(n+r)),稳定排序)
    public static  void radixSort(int []a){
        int max = a[0];
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (a[i]>max){
                max = a[i];
            }
        }
        int time = 0;
        //判断位数
        while(max>0){
            max/=10;
            time++;
        }
        //建立10个队列
        List<ArrayList> queue = new ArrayList<ArrayList>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<>();
            queue.add(queue1);
        }
        //进行time次分配和收集
        for (int i = 0; i < time; i++) {
            System.out.println(Arrays.toString(a));
            //分配数组元素
            for (int j = 0; j < n; j++) {
                //得到数字的第time+1位数
                int x = a[j] % (int)Math.pow(10,i+1) / (int)Math.pow(10,i);
                ArrayList<Integer> queue2 = queue.get(x);//确定加入的队列
                queue2.add(a[j]);//加入队列
                queue.set(x,queue2);//再设置回去
            }
            int count = 0;//元素计数器
            //收集队列元素
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size()>0){
                    ArrayList<Integer> queue3 = queue.get(k);
                    a[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }

    }

    public void insertSortkkk(int [] a){
       //回顾插入排序(i从1开始，选定待插元素为哨兵值temp，j=i起始开始比较j-1的数组值和i的值对比，a[j-1]大则大的值后移，跳出循环后，哨兵值赋值)
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int temp  = a[i];
            int j;
            for (j = i; j < n && temp<a[j-1]; j--) {
                    a[j] = a[j-1];
            }
            a[j] = temp;
        }
    }

    public static void selectsortkkk(int []a){
        //(i从第一个值开始，小于n-1倒数第二个元素，开始逐一对比后续元素，j=i+1，当前者比后者大则交换两者)
        int n = a.length;
        for (int i = 0; i <n-1 ; i++) {
            for (int j = i+1; j <n ; j++) {
                if (a[i]>a[j]){
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
 public void bubbleSortkkk(int []a){
        //冒泡排序-项，相邻的两个元素比较，
        int n = a.length;
     for (int i = 0; i < n; i++) {
         for (int j = 1; j <n-i ; j++) {
             if (a[j-1]>a[j] ) {
                 int temp = a[j - 1];
                 a[j - 1] = a[j];
                 a[j] = temp;
             }
         }
     }
 }



    public static void main(String[] args) {
        int []a = {49,38,65,97,76,13,27,49};
        //selectSort(a);
        //insertSort(a);
        //insertSortopt(a);
        //InsertSort(a);
        //bubbleSort1(a,a.length);


 /*       int k = 56789;
        System.out.println(k % (int)Math.pow(10,1));
        System.out.println(k % (int)Math.pow(10,2));
        System.out.println(k % (int)Math.pow(10,3));
        System.out.println(k % (int)Math.pow(10,1)/ (int)Math.pow(10,0));
        System.out.println(k % (int)Math.pow(10,2)/ (int)Math.pow(10,1));
        System.out.println(k % (int)Math.pow(10,3)/ (int)Math.pow(10,2));  */      ;
        //int [] b ={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,101,56,17,18,23,34,15,35,25,53,51};
        //radixSort(b);
        //twoDivideSort(a);
        //System.out.println(Arrays.toString(b));
        //selectsortkkk(a);
        int [] b = {6,1,2,7,9,3,4,5,10,8};
        //quickSortkkk(b,0,b.length-1);
        //quickSort(b,0,b.length-1);
        qs(b,0,b.length-1);
        System.out.println(Arrays.toString(b));
        //Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        //map.containsKey()
        //int [] c = {1,3,5,2,2};
        //findKth(c,5,3);
    }
//获取数组中最大的K值
    public static  int findKth(int[] a, int n, int K) {
        // write code here
        if(K<0|| K>=n){
            return -1;
        }
        quicksort(a,0,n-1);
        System.out.println(Arrays.toString(a));
        int valueK = a[n-K];
        System.out.println("valueK:"+valueK);
        return valueK;
    }
    public static void quicksort(int[]a,int left,int right){
        if(a==null || a.length==0){
            return;
        }
        int n = a.length;
        if(left <0 || right>=n ||left>right){
            return ;
        }

        int start = left;
        int end = right;
        int key = a[left];
        while(start<end){
            while(a[end]>=key && end > start){
                end--;
            }
            if(a[end]<key){
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            while(a[start]<=key && end > start){
                start++;
            }
            if(a[start]>key){
                int temp = a[end];
                a[end] =a[start];
                a[start] = temp;
            }
        }
        if(start>left){quicksort(a,left,start-1);}
        if(end<right){quicksort(a,end+1,right);}
    }

    public static void qs(int[] nums,int left ,int right){
        int start = left;
        int end = right;
        int key = nums[left];
        while(start<end){
            while(nums[end]>=key && start<end){
                end--;
            }
            if(nums[end]<key) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
            }
            while(nums[start]<=key && start<end){
                start++;
            }
            if(nums[start]>key){
                int temp = nums[end];
                nums[end] = nums[start];
                nums[start] = temp;
            }
        }
        if(start>left) qs(nums,left,start-1);
        if(end<right) qs(nums,end+1,right);
    }



/*    public class HeapSort1 {

        public static void main(String[] args) {
            int[] a = new int[]{16, 25, 7, 32, 6, 9};
            heapSort(a);
            System.out.println(Arrays.toString(a));
        }

        *//**
         * 构造大顶堆
         * @param arr 待调整数组
         * @param size 调整多少
         * @param index 调整哪一个 最后一个叶子节点的父节点开始调整
         *//*
        public static void maxHeap(int arr[], int size, int index) {

            //左子节点
            int leftNode = 2 * index + 1;
            //右子节点
            int rightNode = 2 * index + 2;

            int max = index;//假设自己最大

            //分别比较左右叶子节点找出最大
            if(leftNode < size && arr[leftNode] > arr[max]) {//如果左侧叶子节点大于max则将最大位置换成leftNode并且递归需要限定范围为数组长度，
                max = leftNode;//将最大位置改为左子节点
            }

            if(rightNode < size && arr[rightNode] > arr[max]) {//如果左侧叶子节点大于max则将最大位置换成rightNode
                max = rightNode;//将最大位置改为右子节点
            }

            //如果不相等就需要交换
            if(max != index) {
                int tem = arr[index];
                arr[index] = arr[max];
                arr[max] = tem;
                //如果下边还有叶子节点并且破坏了原有的堆。需要重新调整
                maxHeap(arr, size, max);//位置为刚才改动的位置；
            }
        }

        *//**
         * 需要将最大的顶部与最后一个交换
         * @param arr
         *//*
        public static void heapSort(int arr[]) {
            int start = (arr.length - 1)/2;//开始位置最后一个非叶子节点，最后一个叶子节点的父节点
            for(int i = start; i>=0; i--) {
                maxHeap(arr, arr.length, i);
            }

            //最后一个跟第一个进行调整
            for(int i = arr.length-1; i>0; i--) {//因为数组从零开始的，所以最后一个是数组长度减一
                int temp = arr[0];//最前面的一个
                arr[0] = arr[i];//最后一个
                arr[i] = temp;
                //调整后再进行大顶堆调整
                maxHeap(arr, i, 0);
            }
        }
    }*/
/*    public class HeapSortMin {
        public static void main(String[] args) {
            int[] a = new int[]{16, 25, 7, 32, 6, 9};
            heapSort(a);//小顶堆
            System.out.println(Arrays.toString(a));
        }

        *//**
         * 构造小顶堆
         * @param arr 待调整数组
         * @param size 调整多少
         * @param index 调整哪一个 最后一个叶子节点的父节点开始调整
         *//*
        public static void minHeap(int arr[], int size, int index) {

            //左子节点
            int leftNode = 2 * index + 1;
            //右子节点
            int rightNode = 2 * index + 2;

            int min = index;//假设自己最小

            //分别比较左右叶子节点找出最小
            if(leftNode < size && arr[leftNode] < arr[min]) {//如果左侧叶子节点小于min则将最小位置换成leftNode并且递归需要限定范围为数组长度，
                min = leftNode;//将最小位置改为左子节点
            }
            if(rightNode < size && arr[rightNode] < arr[min]) {//如果左侧叶子节点小于min则将最小位置换成rightNode
                min = rightNode;//将最小位置改为右子节点
            }
            //如果不相等就需要交换
            if(min != index) {
                int tem = arr[index];
                arr[index] = arr[min];
                arr[min] = tem;
                //如果下边还有叶子节点并且破坏了原有的堆。需要重新调整
                minHeap(arr, size, min);//位置为刚才改动的位置；
            }
        }
        *//**
         * 需要将最小的顶部与最后一个交换
         * @param arr
         *//*
        public static void heapSort(int arr[]) {
            int start = (arr.length - 1)/2;//开始位置最后一个非叶子节点，最后一个叶子节点的父节点
            for(int i = start; i>=0; i--) {
                minHeap(arr, arr.length, i);
            }

            //最后一个跟第一个进行调整
            for(int i = arr.length-1; i > 0; i--) {
                int temp = arr[0];//最前面的一个
                arr[0] = arr[i];//最后一个
                arr[i] = temp;
                //调整后再进行小顶堆调整
                minHeap(arr, i, 0);
            }
        }
    }*/

    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }


    @Test
    public void TestMergeSort(){
        int [] a = {6,8,1,2,4,3,5,0,7};
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }

    public void mergeSort(int[] arr){
        int[] temp = new int [arr.length];
        mergeSort(arr,0,arr.length-1,temp);
    }
    public void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid = (left+right)/2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }

    }
    public void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;
        int j = mid+1;
        int t = 0;
        while(i<=mid && j<right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){
            temp[t++] = arr[i++];
        }
        while(j<=right){
            temp[t++] = arr[j++];
        }
        t=0;
        while(left<=right){
            arr[left++] = temp[t++];
        }

    }





    //基本排序方法-练习逻辑
    //1、冒泡排序
    public void bubbleSort(int[]a){
        int n = a.length;
        //i从0开始，就从第二个元素开始，j小于n-i，已经排序的元素为i个，下沉
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <n-i ; j++) {
                if(a[j-1]>a[j]){//相邻两个元素对比大小交换
                    int temp = a[j];
                    a[j] = a[j-1];
                    a[j] = temp;
                }
            }
        }
    }
    //2、插入排序
    public void inserts(int[]a){
        int n = a.length;
        //第一个元素一定是有序的，从第二个开始
        for (int i = 1; i < n; i++) {
            int temp = a[i];
            int j = i;
            for (; j >0 && temp<a[j-1]; j--) {
                a[j] = a[j-1];//前边的元素后移，移除空位
            }
            a[j] = temp;
        }
        System.out.println(Arrays.toString(a));
    }
    //3、选择排序
    public void selectS(int []a){
        int n = a.length;
        for (int i = 0; i <n-1 ; i++) {
            int j=i+1;
            for(;j<n;j++){
                if(a[i] >a[j]){
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
    //4、快速排序
    public void quicks(int []a ,int left,int right){
        int start = left ,end = right;
        int key = a[left];
        while(start<end){
            while(start<end && a[end]>=key){
                end--;
            }
            if(a[end]<key){
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            while (start < end && a[start]<=key){
                start++;
            }
            if(a[start]>key){
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
        }
        if(left<start){quicks(a,left,start-1);}
        if(end<right){quicks(a,end+1,right);}
    }
    //5、归并排序
    public void mergeS(int []a){
        int[] temp = new int[a.length];
        //mergeS();
    }
    public void mergeS(int []a,int left,int right,int[] temp){
        int mid = (right-left)/2+left;
        mergeS(a,left,mid,temp);
        mergeS(a,mid+1,right,temp);
        merge1(a,left,mid,right,temp);
    }
    public void merge1(int []a,int left,int mid,int right,int[] temp){
        int i = left,j = mid+1,t=0;
        while(i<=mid && j<right){
            if(a[i] <= a[j]){
                temp[t++] = a[i++];
            }else{
                temp[t++] = a[j++];
            }
        }
        while(i<=mid){
            temp[t++] = a[i++];
        }
        while(j<=right){
            temp[t++] = a[j++];
        }
        t=0;
        while(left<=right){
            a[left++] = temp[t++];
        }
    }






}
