package assignment3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Node<K, V>> {

    public static class Node<K, V> {
        private final K key;
        private final V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    private TreeNode root;
    private int size;

    private class TreeNode {
        private K key;
        private V val;
        private TreeNode left, right;

        TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public int size() {
        return size;
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private TreeNode put(TreeNode node, K key, V val) {
        if (node == null) {
            size++;
            return new TreeNode(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0)      node.left  = put(node.left,  key, val);
        else if (cmp > 0) node.right = put(node.right, key, val);
        else              node.val   = val;
        return node;
    }

    public V get(K key) {
        TreeNode node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if      (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else              return node.val;
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private TreeNode delete(TreeNode node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if      (cmp < 0) node.left  = delete(node.left,  key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            size--;
            if (node.right == null) return node.left;
            if (node.left  == null) return node.right;
            TreeNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.key = successor.key;
            node.val = successor.val;
            node.right = delete(node.right, successor.key);
            size++;
        }
        return node;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<Node<K, V>> {
        private final Stack<TreeNode> stack = new Stack<>();

        InOrderIterator() {
            pushLeft(root);
        }

        private void pushLeft(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node<K, V> next() {
            if (!hasNext()) throw new NoSuchElementException();
            TreeNode node = stack.pop();
            pushLeft(node.right);
            return new Node<>(node.key, node.val);
        }
    }
}
