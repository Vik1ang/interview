# Sorting

- [Sorting](#sorting)
    - [Selection Sort 选择排序](#selection-sort-选择排序)
    - [Bubble Sort 冒泡排序](#bubble-sort-冒泡排序)
    - [Insertion Sort 插入排序](#insertion-sort-插入排序)
    - [希尔排序](#希尔排序)
    - [归并排序](#归并排序)

### Selection Sort 选择排序

![](https://camo.githubusercontent.com/3077738d14d2fc08943fa953616639a1e7828f37c5928299f4af1ebc89a66881/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f62633662653264302d656435652d346465662d383965352d3361646139616661383131612e676966)

```java
public class Selection {
    public void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                minIndex = nums[j] < nums[minIndex] ? j : minIndex;
            }
            swap(nums, i, minIndex);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### Bubble Sort 冒泡排序

![](https://camo.githubusercontent.com/1672cc63dfbfa9f3173fb2b94a5042d64076afd01158fce906b6b70fc21232c3/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f30663864313738622d353264382d343931622d396466642d3431653035613935323537382e676966)

```java
public class Bubble {
    public void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### Insertion Sort 插入排序

![](https://camo.githubusercontent.com/9dc4032a0ee029d5e0549dd9129bead2f02b99db6b3c9d06ae44ec69a04697b1/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f33353235336661342d663630612d346533622d616165632d3866633833356161626461632e676966)

```java
public class Insertion {
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 从已经排序的序列最右边的开始比较, 找到比其小的数
            for (int j = i; j > 0 && nums[j] < nums[j - 1]; j--) {
                swap(nums, j, j - 1);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### 希尔排序

### 归并排序

![](https://www.runoob.com/wp-content/uploads/2019/03/mergeSort.gif)

```java

```
