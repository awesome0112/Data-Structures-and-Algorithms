package RelatedToGraphDSA;

import java.util.ArrayList;
import java.util.Collections;

public class Bst <Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;  /* Left: smaller   Right: larger */

        public Node (Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;

    private int size(Node node) {
        if(node == null) return 0;
        else return 1 + size(node.left) + size(node.right);
    }
    public int size() {
        return size(root);
    }

    public Value get(Key key) {
        Node tmp = root;
        while(tmp != null) {
            int cmp = tmp.key.compareTo(key);
            if(cmp > 0) tmp = tmp.left;
            else if(cmp < 0) tmp = tmp.right;
            else return tmp.value;
        }
        return null;
    }

    /* Put using recursion
//    private Node put(Node node, Key key, Value value) {
//        if(node == null) return new Node(key, value);
//        int cmp = node.key.compareTo(key);
//        if(cmp > 0) node.left = put(node.left, key, value);
//        else if(cmp < 0) node.right = put(node.right, key, value);
//        else node.value = value;
//        return node;
//    }
//    public void put(Key key, Value value) {
//        root = put(root, key, value);
//    }
     */

    public void put(Key key, Value value) {
        if(root == null) root = new Node(key, value);
        else {
            Node tmp = root;
            while(true) {
                int cmp = tmp.key.compareTo(key);
                if(cmp > 0) {
                    if(tmp.left == null) {
                        tmp.left = new Node(key, value);
                        return;
                    } else tmp = tmp.left;
                }
                else if(cmp < 0) {
                    if(tmp.right == null) {
                        tmp.right = new Node(key, value);
                        return;
                    } else tmp = tmp.right;
                }
                else {
                    tmp.value = value;
                    return;
                }
            }
        }
    }

    private Key minKey(Node node) {
        if(node.left == null) return node.key;
        else return minKey(node.left);
    }
    public Key minKey() {
        if(root == null) return null;
        return minKey(root);
    }

    private Key maxKey(Node node) {
        if(node.right == null) return node.key;
        else return maxKey(node.right);
    }
    public Key maxKey() {
        if(root == null) return null;
        return maxKey(root);
    }

    private void calHeight(Node node, ArrayList<Integer> heights, Integer count) {
        if(node.left == null && node.right == null) {
            heights.add(count);
            return;
        }
        if(node.left == null) {
            if(node.right != null) calHeight(node.right, heights, count + 1);
        }
        else if(node.right == null) {
            if(node.left != null) calHeight(node.left , heights, count + 1);
        } else if(node.right != null && node.left != null) {
            calHeight(node.right, heights, count + 1);
            calHeight(node.left , heights, count + 1);
        }
    }
    public int height() {
        if(root == null) return 0;
        Integer count = 0;
        ArrayList<Integer> heights = new ArrayList<>();
        calHeight(root, heights, count);
        return Collections.max(heights);
    }

    private int tmp(Node node) {
        if(node == null) return 0;
        return Math.max(1 + tmp(node.right) , 1 + tmp(node.left));
    }
    public int height1() {
        if(root == null) return 0;
        return Math.max(tmp(root.right) , tmp(root.left));
    }

    private Key floor(Key key, Node node) {
        if(node ==  null) return null;
        int cmp = key.compareTo(node.key);
        if(cmp < 0) return floor(key, node.left);
        else if(cmp > 0) {
            Key subRes = floor(key, node.right);
            if(subRes == null) return node.key;
            else return subRes;
        } else return key;
    }
    public Key floor(Key key) {  // Largest Key <= given Key.
        return floor(key, root);
    }

    private Key ceiling(Key key, Node node) {
        if(node == null) return null;
        int cmp = key.compareTo(node.key);
        if(cmp > 0) return ceiling(key, node.right);
        else if(cmp < 0) {
            Key subRes = ceiling(key, node.left);
            if(subRes == null) return node.key;
            else return subRes;
        } else return key;
    }
    public Key ceiling(Key key) {  // Smallest Key >= given Key.
        return ceiling(key, root);
    }

    private int rank(Key key, Node node) {  // How many Keys smaller than a given Key.
        if(node == null) return 0;
        int cmp = key.compareTo(node.key);
        if(cmp == 0) return size(node.left);
        else if(cmp < 0) return rank(key, node.left);
        else return 1 + size(node.left) + rank(key, node.right);
    }
    public int rank(Key key) {
        return rank(key, root);
    }

    public void deleteMin() {
        root = deleteMinNode(root);
    }
    private Node deleteMinNode(Node from) {
        if(from.left == null) return from.right;
        from.left = deleteMinNode(from.left);
        return from;
    }

    public void deleteMax() {
        root = deleteMaxNode(root);
    }
    private Node deleteMaxNode(Node from) {
        if(from.right == null) return from.left;
        from.right = deleteMaxNode(from.right);
        return from;
    }
    public void delete(Key key) {
        root = deleteNode(key, root);
    }
    private Node minNode(Node from) {
        if(from.left != null) return minNode(from.left);
        else return from;
    }
    private Node deleteNode(Key key, Node node) {
        if(node == null) return null;
        int cmp = key.compareTo(node.key);
        if(cmp < 0) node.left = deleteNode(key, node.left);
        else if(cmp > 0) node.right = deleteNode(key, node.right);
        else {

            if(node.right == null) return node.left; // Trường hợp nút chỉ có nút con trái
            if(node.left == null) return node.right; // Trường hợp nút chỉ có nút con phải
            if(node.right != null && node.left != null) {
                // Lưu TMP để khi thay đổi node thì vẫn có vết để truy cập đến node.right.
                Node tmp = node;
                // Gắn node vào kế vị của nó (node nhỏ nhất nằm bên phải).
                node = minNode(tmp.right);
                // Xóa node nhỏ nhất bên phải đó
                node.right = deleteMinNode(tmp.right);
                // Gắn cho lại cho node.left gias trị của tmp.left đã lưu vết.
                node.left = tmp.left;
            }
            if(node.right == null && node.left == null) return null;
        }
        return node;
    }

    private String toString(Node node) {
        if(node.left != null && node.right != null) {
            return toString(node.left) + ", " + node.key + "=" + node.value + ", " + toString(node.right);
        } else {
            if(node.left == null && node.right == null) {
                return node.key + "=" + node.value;
            } else if(node.left == null) {
                return node.key + "=" + node.value + ", " + toString(node.right);
            } else {
                return toString(node.left) + ", " + node.key + "=" + node.value;
            }
        }
    }
    @Override
    public String toString() {
        if(root == null) return "{}";
        return "{" + toString(root) + "}";
    }

    public boolean containsKey(Key key) {
        return get(key) != null;
    }

    public void clear() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
