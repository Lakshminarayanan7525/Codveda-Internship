package com.codveda.level3;

// 1. NODE REPRESENTATION
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

// 2. BST CORE LOGIC
class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    // --- Insertion Logic ---
    public void insert(int data) {
        root = insertRecursive(root, data);
    }

    private TreeNode insertRecursive(TreeNode current, int data) {
        if (current == null) {
            return new TreeNode(data);
        }
        if (data < current.data) {
            current.left = insertRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = insertRecursive(current.right, data);
        }
        return current;
    }

    // --- Search Logic ---
    public boolean search(int data) {
        return searchRecursive(root, data);
    }

    private boolean searchRecursive(TreeNode current, int data) {
        if (current == null) {
            return false;
        }
        if (data == current.data) {
            return true;
        }
        return data < current.data
                ? searchRecursive(current.left, data)
                : searchRecursive(current.right, data);
    }

    // --- Deletion Logic ---
    public void delete(int data) {
        root = deleteRecursive(root, data);
    }

    private TreeNode deleteRecursive(TreeNode current, int data) {
        if (current == null) {
            return null;
        }

        if (data < current.data) {
            current.left = deleteRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = deleteRecursive(current.right, data);
        } else {
            // Node found! Handle cases:
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            // Two children case
            current.data = findMinValue(current.right);
            current.right = deleteRecursive(current.right, current.data);
        }
        return current;
    }

    private int findMinValue(TreeNode current) {
        int minValue = current.data;
        while (current.left != null) {
            minValue = current.left.data;
            current = current.left;
        }
        return minValue;
    }

    // --- Traversal Logic ---
    public void inorder() { inorderRecursive(root); System.out.println(); }
    private void inorderRecursive(TreeNode root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.data + " ");
            inorderRecursive(root.right);
        }
    }

    public void preorder() { preorderRecursive(root); System.out.println(); }
    private void preorderRecursive(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRecursive(root.left);
            preorderRecursive(root.right);
        }
    }

    public void postorder() { postorderRecursive(root); System.out.println(); }
    private void postorderRecursive(TreeNode root) {
        if (root != null) {
            postorderRecursive(root.left);
            postorderRecursive(root.right);
            System.out.print(root.data + " ");
        }
    }
}

// 3. APPLICATION EXECUTION
public class BinarySearchTreeApp {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        System.out.println("--- BST Implementation ---");

        // Objectives: Insert nodes
        System.out.println("Inserting elements: 50, 30, 20, 40, 70, 60, 80");
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        // Objectives: Perform traversals
        System.out.print("In-order Traversal:   ");
        bst.inorder();

        System.out.print("Pre-order Traversal:  ");
        bst.preorder();

        System.out.print("Post-order Traversal: ");
        bst.postorder();

        // Objectives: Search nodes
        System.out.println("\n--- Testing Search ---");
        System.out.println("Is 40 in the tree? " + bst.search(40));
        System.out.println("Is 95 in the tree? " + bst.search(95));

        // Objectives: Delete nodes
        System.out.println("\n--- Testing Deletion ---");
        System.out.println("Deleting leaf node (20)...");
        bst.delete(20);
        System.out.print("In-order after deletion: ");
        bst.inorder();

        System.out.println("Deleting node with one child (30)...");
        bst.delete(30);
        System.out.print("In-order after deletion: ");
        bst.inorder();
    }
}