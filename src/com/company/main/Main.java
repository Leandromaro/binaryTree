package com.company.main;

public class Main {

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        bt.add(3);
        bt.add(5);
        bt.add(7);

        bt.add(6);

        System.out.println(bt.containsNode(9));

    }
}
