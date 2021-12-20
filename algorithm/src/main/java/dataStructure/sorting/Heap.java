package dataStructure.sorting;

public class Heap {
    // 利用数组建立一个大根堆
    // 把堆顶元素和堆尾元素互换
    // 把堆(无序区)的尺寸缩小为1, 并调用shiftDown(arr,0,len-1)从新的堆顶元素开始进行堆调整
    // 重复步骤直到堆的大小为1
    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            shiftUp(arr, i);
        }
        int size = arr.length - 1;
        swap(arr, 0, size);
        while (size > 0) {
            shiftDown(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    // 下沉的过程 -> 这个函数就是一个数变小了往下沉, 改变的数为index, 目前的自己指定的堆的大小为heapSize
    // 左孩子: 2*i+1 右孩子: 2*i+2
    private void shiftDown(int[] arr, int i, int heapSize) {
        int L = 2 * i + 1; // 左孩子的下标
        while (L < heapSize) { // 下方还有子的时候
            // 比较两个孩子之间
            // 右孩子Index: L + 1
            int maxIndex = L + 1 < heapSize && arr[L + 1] > arr[L] ? L + 1 : L;
            // 比较父节点和子节点中最大的比较
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
            if (maxIndex == i) {
                // 说明当前已经是大根堆节点
                break;
            }
            swap(arr, i, maxIndex);
            i = maxIndex;
            L = 2 * i + 1; // 继续往下
        }
    }

    // 上浮的过程 -> 新插入的数调整为大根堆的过程
    // 父节点: (i - 1)/2
    private void shiftUp(int[] arr, int i) {
        while (arr[i] > arr[(i - 1) / 2]) {
            swap(arr, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
