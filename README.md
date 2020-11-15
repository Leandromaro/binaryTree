# Concepts

## Tree
Unlike Arrays, Linked Lists, Stack and queues, which are linear data structures, trees are hierarchical data structures.

## Tree Vocabulary 
The topmost node is called root of the tree. The elements that are directly under an element are called its children. The element directly above something is called its paren.
```
      tree
      ----
       j    <-- root
     /   \
    f      k  
  /   \      \
 a     h      z    <-- leaves
```

## Why Trees?

1. One reason to use trees might be because you want to store information that naturally forms a hierarchy. For example, the file system on a computer:
```
file system
-----------
     /    <-- root
  /      \
...       home
      /          \
   ugrad        course
    /       /      |     \
  ...      cs101  cs112  cs113  
```

2. Trees (with some ordering e.g., BST) provide moderate access/search (quicker than Linked List and slower than arrays).
3. Trees provide moderate insertion/deletion (quicker than Arrays and slower than Unordered Linked Lists).
4. Like Linked Lists and unlike Arrays, Trees don’t have an upper limit on number of nodes as nodes are linked using pointers.

#### Main applications of trees include:
1. Manipulate hierarchical data.
2. Make information easy to search (see tree traversal).
3. Manipulate sorted lists of data.
4. As a workflow for compositing digital images for visual effects.
5. Router algorithms
6. Form of a multi-stage decision-making (see business chess).


## Binary Tree: 
A tree whose elements have at most 2 children is called a binary tree. 
Since each element in a binary tree can have __only 2 children__, we typically name them the left and right child.

      /* Class containing left and right child of current 
         node and key value*/
      class Node 
      { 
          int key; 
          Node left, right; 

          public Node(int item) 
          { 
              key = item; 
              left = right = null; 
          } 
      } 
  
      // A Java program to introduce Binary Tree 
      class BinaryTree 
      { 
          // Root of Binary Tree 
          Node root; 

          // Constructors 
          BinaryTree(int key) 
          { 
              root = new Node(key); 
          } 

          BinaryTree() 
          { 
              root = null; 
          } 

          public static void main(String[] args) 
          { 
              BinaryTree tree = new BinaryTree(); 

              /*create root*/
              tree.root = new Node(1); 

              /* following is the tree after above statement 

                    1 
                  /   \ 
                null  null     */

              tree.root.left = new Node(2); 
              tree.root.right = new Node(3); 

              /* 2 and 3 become left and right children of 1 
                     1 
                   /   \ 
                  2      3 
                /    \    /  \ 
              null null null null  */


              tree.root.left.left = new Node(4); 
              /* 4 becomes left child of 2 
                          1 
                      /       \ 
                     2          3 
                   /   \       /  \ 
                  4    null  null  null 
                 /   \ 
                null null 
               */
          } 
      }

## Tree Properties

1) The maximum number of nodes at level ‘l’ of a binary tree is 2<sup>l</sup>. 
Here level is the number of nodes on the path from the root to the node (including root and node). Level of the root is 0.
2) The Maximum number of nodes in a binary tree of height ‘h’ is (2<sup>h</sup>) – 1. 
__Here the height of a tree is the maximum number of nodes counting from the root to leaf path__. Height of a tree with a single node is considered as 1
3) In a Binary Tree with N nodes, minimum possible height or the minimum number of levels is? Log<sub>2</sub>(N+1) ?   
This can be directly derived from point 2 above. If we consider the convention where the height of a leaf node is considered as 0, then above formula for minimum possible height becomes? Log<sub>2</sub>(N+1) ? – 1 
4) A Binary Tree with L leaves has at least? Log<sub>2</sub>L? + 1 levels. 
      A Binary tree has the maximum number of leaves (and a minimum number of levels) when all levels are fully filled. Let all leaves be at level l, then below is true for the number of leaves L. 
      ```
      L   <=  2<sup>l-1</sup>  [From Point 1]
      l =   ? Log<sub>2</sub>L ? + 1 
      ```
      where l is the minimum number of levels. 
5) In Binary tree where every node has 0 or 2 children, the number of leaf nodes is always one more than nodes with two children.
      ```
      L = T + 1
      Where L = Number of leaf nodes
      T = Number of internal nodes with two children
      ````

# Common Operations

## Inserting Elements

The first operation we're going to cover is the insertion of new nodes.

First, __we have to find the place where we want to add a new node in order to keep the tree sorted.__ We'll follow these rules starting from the root node:

 - if the new node's value is lower than the current node's, we go to the left child
 - if the new node's value is greater than the current node's, we go to the right child
 - when the current node is null, we've reached a leaf node and we can insert the new node in that position
 
 Recursive insertion method
```

public void add(int value) {
    root = addRecursive(root, value);
}

private Node addRecursive(Node current, int value) {
    if (current == null) {
        return new Node(value);
    }
 
    if (value < current.value) {
        current.left = addRecursive(current.left, value);
    } else if (value > current.value) {
        current.right = addRecursive(current.right, value);
    } else {
        // value already exists
        return current;
    }
 
    return current;
}
```

## Finding element

First create a recursive method that traverses the tree:

```
public boolean containsNode(int value) {
    return containsNodeRecursive(root, value);
}


private boolean containsNodeRecursive(Node current, int value) {
    if (current == null) {
        return false;
    } 
    if (value == current.value) {
        return true;
    } 
    return value < current.value
      ? containsNodeRecursive(current.left, value)
      : containsNodeRecursive(current.right, value);
}
```

## Deleting an Element

First, we have to find the node to delete in a similar way as we did before:

Once we find the node to delete, there are 3 main different cases:

 - __a node has no children__ – this is the simplest case; we just need to replace this node with null in its parent node
 - __a node has exactly one child__ – in the parent node, we replace this node with its only child.
 - __a node has two children__ – this is the most complex case because it requires a tree reorganization

### Leaf (No childern)

      if (current.left == null && current.right == null) {
           return null;
      }
      
### One Children      

      if (current.right == null) {
          return current.left;
      }

      if (current.left == null) {
          return current.right;
      }

### Two children

To handle the case where the node has two children.

First, we need to find the node that will replace the deleted node. 
__We'll use the smallest node of the node to be deleted's right sub-tree__:

      private int findSmallestValue(Node root) {
          return root.left == null ? root.value : findSmallestValue(root.left);
      }
Then, we assign the smallest value to the node to delete and after that, we'll delete it from the right subtree:

      int smallestValue = findSmallestValue(current.right);
      current.value = smallestValue;
      current.right = deleteRecursive(current.right, smallestValue);
      return current;

Finally, let's create the public method that starts the deletion from the root:

      public void delete(int value) {
          root = deleteRecursive(root, value);
      }

#### Complete methods
      public void delete(int value) {
          root = deleteRecursive(root, value);
      }    
         
       private Node deleteRecursive(Node current, int value) {

       if (current == null) {
            return null;
       }

       if (value == current.value) {
            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: only 1 child
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            // Case 3: 2 children
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
       }

       private int findSmallestValue(Node root) {
         return root.left == null ? root.value : findSmallestValue(root.left);
       }

![treeDelition](https://github.com/Leandromaro/binaryTree/blob/main/treeRemove.png)

# Traversing the Tree

## Depth-First Search

__Depth-first search is a type of traversal that goes deep as much as possible in every child before exploring the next sibling.__

There are several ways to perform a depth-first search: 
 - in-order 
 - pre-order
 - post-order
 
 ### In Order
 
__The in-order traversal consists of first visiting the left sub-tree, then the root node, and finally the right sub-tree:__

      public void traverseInOrder(Node node) {
          if (node != null) {
              traverseInOrder(node.left);
              System.out.print(" " + node.value);
              traverseInOrder(node.right);
          }
      }

### Pre Order

__Pre-order traversal visits first the root node, then the left subtree, and finally the right subtree:__

      public void traversePreOrder(Node node) {
          if (node != null) {
              System.out.print(" " + node.value);
              traversePreOrder(node.left);
              traversePreOrder(node.right);
          }
      }

### Post Order

__Post-order traversal visits the left subtree, the right subtree, and the root node at the end:__

      public void traversePostOrder(Node node) {
          if (node != null) {
              traversePostOrder(node.left);
              traversePostOrder(node.right);
              System.out.print(" " + node.value);
          }
      }

## Breadth-First Search

This is another common type of traversal that __visits all the nodes of a level before going to the next level.__

This kind of traversal is also called level-order and visits all the levels of the tree starting from the root, and from left to right.

For the implementation, we'll use a Queue to hold the nodes from each level in order. We'll extract each node from the list, print its values, then add its children to the queue:

      public void traverseLevelOrder() {
          if (root == null) {
              return;
          }

          Queue<Node> nodes = new LinkedList<>();
          nodes.add(root);

          while (!nodes.isEmpty()) {

              Node node = nodes.remove();

              System.out.print(" " + node.value);

              if (node.left != null) {
                  nodes.add(node.left);
              }

              if (node.right != null) {
                  nodes.add(node.right);
              }
          }
      }

# References
[geeksforgeeks](https://www.geeksforgeeks.org/binary-tree-set-1-introduction/)
[Baeldung](https://www.baeldung.com/java-binary-tree)
[Lalitha Natraj](https://www.youtube.com/watch?v=g0mjZwYRErM&t=2s)
