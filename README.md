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
4) A Binary Tree with L leaves has at least? Log<sub>2</sub>L? + 1   levels 
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

