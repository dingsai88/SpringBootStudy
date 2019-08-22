package com.ding.study.datastructure;

import com.ding.study.util.JsonUtils;

/**
 * 归并排序；二路归并排序
 * @see <a href="https://blog.csdn.net/qq_36442947/article/details/81612870">归并排序</a>
 *  @see <a href="https://www.cnblogs.com/wuyepeng/p/9819827.html">归并排序</a>
 * @author daniel 2019-8-14 0014.
 */
public class HG68MergeSort {

    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println("归并排序：排序前:" + JsonUtils.convertObjToJsonString(arr));
        sort(arr,0,arr.length-1);
        System.out.println("归并排序：排序后:" + JsonUtils.convertObjToJsonString(arr));
    }

    public static int[] sort(int[] a,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            sort(a,low,mid);
            sort(a,mid+1,high);
            //左右归并
            merge(a,low,mid,high);
        }
        return a;
    }

    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i= low;
        int j = mid+1;
        int k=0;
        // 把较小的数先移到新数组中
        while(i<=mid && j<=high){
            if(a[i]<a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while(i<=mid){
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while(j<=high){
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for(int x=0;x<temp.length;x++){
            a[x+low] = temp[x];
        }
    }
}
