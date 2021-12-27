package dataStructure.binaryTree.redBlackTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RedBlackTreeTest {
    public static void main(String[] args) {
        insertOpt();
    }

    public static void insertOpt() {
        Scanner scanner = new Scanner(System.in);

        RBTree<String, Object> rbt = new RBTree<String, Object>();
        while (true) {
            System.out.println("请输入key：");
            String key = scanner.next();
            System.out.println();
            rbt.insert(key, null);


            TreeOperation.show(rbt.getRoot());

        }
    }
}
