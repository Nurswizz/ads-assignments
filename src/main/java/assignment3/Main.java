package assignment3;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        testHashTable();
        testBST();
    }

    private static void testHashTable() {
        System.out.println("=== MyHashTable Test ===");
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(11);
        Random rand = new Random();
        String[] names = {"Alice", "Bob", "Charlie", "Diana", "Eve",
                          "Frank", "Grace", "Hank", "Ivy", "Jack"};

        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(100000);
            String name = names[rand.nextInt(names.length)] + id;
            table.put(new MyTestingClass(id, name), new Student(name, 18 + rand.nextInt(10)));
        }

        table.printBucketSizes();
        System.out.println();
    }

    private static void testBST() {
        System.out.println("=== BST Test ===");
        BST<Integer, String> tree = new BST<>();
        tree.put(5, "five");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(1, "one");
        tree.put(4, "four");
        tree.put(6, "six");
        tree.put(9, "nine");

        System.out.println("Size: " + tree.size());
        System.out.println("get(3): " + tree.get(3));
        System.out.println("get(9): " + tree.get(9));

        System.out.println("In-order traversal:");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

        tree.delete(3);
        System.out.println("After deleting 3, size: " + tree.size());
        System.out.println("In-order traversal:");
        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
