package com.sort;

public class TreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<Integer>();
		tree.buildTree();
		System.out.println("中序遍历");
		tree.inOrderTraverse();
		tree.nrInOrderTraverse();
		System.out.println("后续遍历");
		//tree.nrPostOrderTraverse();
		tree.postOrderTraverse();
		tree.nrPostOrderTraverse();
		System.out.println("先序遍历");
		tree.preOrderTraverse();
		tree.nrPreOrderTraverse();
		
//
	}

}