package com.gmail.egorovsonalexey.lesson1;

class MyTreeHashMap implements HashMap {

    private int count;
    private TreeNode root;

    // tree image is turned to the left by 90 degrees
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb = getTree(root, 0, sb);
        return sb.toString();
    }

    public void put(Object key, Object value) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        int hash = key.hashCode();
        TreeNode node = findKey(hash);
        if(node == null) {
            root = new TreeNode(null, hash);
            root.bucket.append(key, value);
            count++;
            return;
        }

        if (node.value == hash) {
            MyLinkedList.MyLinkedListItem item = node.bucket.search(key);
            if(item == null) {
                node.bucket.append(key, value);
                count++;
            }
            else {
                throw new IllegalArgumentException("The key " + key + " already exists.");
            }
            return;
        }

        TreeNode newNode = new TreeNode(node, hash);
        newNode.bucket.append(key, value);
        count++;
        if(node.value < hash) {
            node.right = newNode;
        }
        else {
            node.left = newNode;
        }
        //System.out.println(this);
        balanceAfterInsert(newNode);
    }

    public Object get(Object key) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        int hash = key.hashCode();
        TreeNode keyNode = findKey(hash);
        if(keyNode != null && keyNode.value == hash && keyNode.bucket != null) {
            MyLinkedList.MyLinkedListItem item = keyNode.bucket.search(key);
            if (item != null) {
                return item.getValue();
            }
        }
        return null;
    }

    public void set(Object key, Object value) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        int hash = key.hashCode();
        TreeNode node = findKey(hash);
        if(node.value == hash) {
            MyLinkedList.MyLinkedListItem item = node.bucket.search(key);
            if(item != null) {
                item.setValue(value);
                return;
            }
        }
        throw new IllegalArgumentException("The key " + key + " not found.");
    }

    public void delete(Object key) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        int hash = key.hashCode();
        TreeNode keyNode = findKey(hash);
        if(keyNode != null && keyNode.value == hash && keyNode.bucket != null) {
            boolean res = keyNode.bucket.remove(key);
            if (res) {
                count--;
                return;
            }
        }
        throw new IllegalArgumentException("The key " + key + " not found.");
    }

    public boolean containsKey(Object key) {

        if(key == null) {
            throw new IllegalArgumentException("Key mast be not null.");
        }

        int hash = key.hashCode();
        TreeNode keyNode = findKey(hash);
        if(keyNode != null && keyNode.value == hash) {
            MyLinkedList.MyLinkedListItem item = keyNode.bucket.search(key);
            return item != null;
        }
        else {
            return false;
        }
    }

    public int getCount() {
        return count;
    }

    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;
        return current;
    }

    // find the nearest node, not exactly the same
    private TreeNode findKey(int hash) {
        TreeNode node = root;
        TreeNode parentNode = null;
        while(node != null) {
            if(node.value == hash) {
                return node;
            }
            else if(node.value > hash) {
                parentNode = node;
                node = node.left;
            }
            else {
                parentNode = node;
                node = node.right;
            }
        }
        return parentNode;
    }

    //balanceAfterInsert code taken from wiki: https://en.wikipedia.org/wiki/AVL_tree
    private void balanceAfterInsert(TreeNode newNode) {
        TreeNode Z = newNode;
        TreeNode N;
        TreeNode G;

        for (TreeNode X = Z.parent; X != null; X = Z.parent) { // Loop (possibly up to the root)

            // BalanceFactor(X) has to be updated:
            if (Z == X.right) { // The right subtree increases
                if (X.balanceFactor > 0) { // X is right-heavy
                    // ===> the temporary BalanceFactor(X) == +2
                    // ===> rebalancing is required.
                    G = X.parent; // Save parent of X around rotations
                    if (Z.balanceFactor < 0) {    // Right Left Case     (see figure 5)
                        N = rotate_RightLeft(X, Z); // Double rotation: Right(Z) then Left(X)
                    } else {                        // Right Right Case    (see figure 4)
                        N = rotate_Left(X, Z);     // Single rotation Left(X)
                    }
                    // After rotation adapt parent link
                } else {
                    if (X.balanceFactor < 0) {
                        X.balanceFactor = 0; // Z’s height increase is absorbed at X.
                        break; // Leave the loop
                    }
                    X.balanceFactor = +1;
                    Z = X; // Height(Z) increases by 1
                    continue;
                }
            } else { // Z == left_child(X): the left subtree increases

                if (X.balanceFactor < 0) { // X is left-heavy
                    // ===> the temporary BalanceFactor(X) == –2
                    // ===> rebalancing is required.
                    G = X.parent; // Save parent of X around rotations
                    if (Z.balanceFactor > 0) {    // Left Right Case
                        N = rotate_LeftRight(X, Z); // Double rotation: Left(Z) then Right(X)
                    } else {                        // Left Left Case
                        N = rotate_Right(X, Z);    // Single rotation Right(X)
                    }
                    // After rotation adapt parent link
                } else {
                    if (X.balanceFactor > 0) {
                        X.balanceFactor = 0; // Z’s height increase is absorbed at X.
                        break; // Leave the loop
                    }
                    X.balanceFactor = -1;
                    Z = X; // Height(Z) increases by 1
                    continue;
                }
            }
            // After a rotation adapt parent link:
            // N is the new root of the rotated subtree
            // Height does not change: Height(N) == old Height(X)
            N.parent = G;
            if (G != null) {
                if (X == G.left) {
                    G.left = N;
                } else {
                    G.right = N;
                }
                break;
            } else {
                root = N; // N is the new root of the total tree
                break;
            }
            // There is no fall thru, only break; or continue;
        }
    }

    //rotate_Left code taken from wiki: https://en.wikipedia.org/wiki/AVL_tree
    private TreeNode rotate_Left(TreeNode X, TreeNode Z) {

        // Z is by 2 higher than its sibling
        TreeNode t23 = Z.left; // Inner child of Z
        X.right = t23;
        if (t23 != null) {
            t23.parent = X;
        }
        Z.left = X;
        X.parent = Z;

        // 1st case, BalanceFactor(Z) == 0, only happens with deletion, not insertion:
        if (Z.balanceFactor == 0) { // t23 has been of same height as t4
            X.balanceFactor = +1;   // t23 now higher
            Z.balanceFactor = -1;   // t4 now lower than X
        }
        else {
            // 2nd case happens with insertion or deletion:
            X.balanceFactor = 0;
            Z.balanceFactor = 0;
        }
        return Z; // return new root of rotated subtree
    }

    private TreeNode rotate_Right(TreeNode Z, TreeNode X) {

        TreeNode t23 = X.right;
        Z.left = t23;
        if (t23 != null) {
            t23.parent = Z;
        }
        X.right = Z;
        Z.parent = X;

        if (X.balanceFactor == 0) {
            Z.balanceFactor = +1;
            X.balanceFactor = -1;
        }
        else {
            Z.balanceFactor = 0;
            X.balanceFactor = 0;
        }
        return X;
    }

    //rotate_RightLeft code taken from wiki: https://en.wikipedia.org/wiki/AVL_tree
    private TreeNode rotate_RightLeft(TreeNode X, TreeNode Z) {

        // Z is by 2 higher than its sibling
        TreeNode Y = Z.left; // Inner child of Z

        // Y is by 1 higher than sibling
        TreeNode t3 = Y.right;
        Z.left = t3;
        if (t3 != null) {
            t3.parent = Z;
        }
        Y.right = Z;
        Z.parent = Y;
        TreeNode t2 = Y.left;
        X.right = t2;
        if (t2 != null) {
            t2.parent = X;
        }
        Y.left = X;
        X.parent = Y;

        if (Y.balanceFactor > 0) { // t3 was higher
            X.balanceFactor = -1;  // t1 now higher
            Z.balanceFactor = 0;
        } else

        if (Y.balanceFactor == 0) {
            X.balanceFactor = 0;
            Z.balanceFactor = 0;
        } else {

            // t2 was higher
            X.balanceFactor = 0;
            Z.balanceFactor = +1;  // t4 now higher
        }
        Y.balanceFactor = 0;
        return Y; // return new root of rotated subtree
    }

    private TreeNode rotate_LeftRight(TreeNode Z, TreeNode X) {

        TreeNode Y = X.right;

        TreeNode t2 = Y.left;
        X.right = t2;
        if (t2 != null) {
            t2.parent = X;
        }
        Y.left = X;
        X.parent = Y;
        TreeNode t3 = Y.right;
        Z.left = t3;
        if (t3 != null) {
            t3.parent = Z;
        }
        Y.right = Z;
        Z.parent = Y;

        if (Y.balanceFactor > 0) {
            Z.balanceFactor = -1;
            X.balanceFactor = 0;
        } else

        if (Y.balanceFactor == 0) {
            Z.balanceFactor = 0;
            X.balanceFactor = 0;
        }
        else {
            Z.balanceFactor = 0;
            X.balanceFactor = +1;
        }
        Y.balanceFactor = 0;
        return Y;
    }

    private StringBuilder getTree(TreeNode r, int l, StringBuilder sb)
    {
        if (r == null)
            return sb;

        getTree(r.right, l + 1, sb);
        for (int i = 0; i < l; ++i)
            sb.append("  ");

        sb.append(r.value);
        sb.append(System.lineSeparator());
        getTree(r.left, l + 1, sb);

        return sb;
    }

    class TreeNode {

            private TreeNode left;
            private TreeNode right;
            private TreeNode parent;
            private MyLinkedList bucket;
            private int value;
            int balanceFactor;

            TreeNode(TreeNode p, int v) {
                parent = p;
                bucket = new MyLinkedList();
                value = v;
                balanceFactor = 0;
            }
    }
}
