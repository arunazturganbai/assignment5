import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    private Node root;
    private int size;

    private static class Node<K, V> {
        private K key;
        private V val;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node<K, V> put(Node<K, V> node, K key, V val) {
        if (node == null) {
            size++;
            return new Node<>(key, val);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }

        return node;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            } else {
                Node minNode = findMin(node.right);
                node.key = minNode.key;
                node.val = minNode.val;
                node.right = deleteMin(node.right);
            }
        }

        return node;
    }

    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    public int size() {
        return size;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Entry<K, V>> {
        private Stack<Node> stack;

        public BSTIterator() {
            stack = new Stack<>();
            pushLeft(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node node = stack.pop();
            Entry<K, V> entry = new Entry<>(node.key, node.val);

            if (node.right != null) {
                pushLeft(node.right);
            }

            return entry;
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
    public class Count(K key, V value){
        private K key;
        private V value;

        public Count(K key, V value){
            this.key = key;
            this.value = value;
        }
        public K getkey(){
            return key;
        }

        public V getvalue(){
            return value;
        }
    }
}