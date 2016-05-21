package com.wendy.algorithm.tree;

import java.util.ArrayDeque;
import java.util.Stack;
/**
 * 
 * 				13
 * 			 /       \
 * 			65		  5
 * 		  /	   \     /  \
 *       
 * 
 * 
 * 
 * @author tony
 *
 */
public class BinaryTree {

	TreeNode root;

	public BinaryTree(int[] array) {
		this.root = makeBinaryTreeByArray(array, 1);
	}

	public TreeNode makeBinaryTreeByArray(int[] array, int index) {
		if (index < array.length) {
			int value = array[index];
			if (value != 0) {
				TreeNode treeNode = new TreeNode(value);
				array[index] = 0;
				treeNode.left = makeBinaryTreeByArray(array, index * 2);
				treeNode.right = makeBinaryTreeByArray(array, index * 2 + 1);
				return treeNode;
			}
		}

		return null;
	}



	public void depth() {
		if (root == null) {
			System.out.println("empty tree");
			return;
		}

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			System.out.print(node.value);
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}

			System.out.println();

		}

	}
	public void level(){
		if(root == null){
			return;
		}
		
		
		ArrayDeque<TreeNode> deque = new ArrayDeque<>();
		deque.add(root);
		while(!deque.isEmpty()){
			TreeNode node = deque.remove();
			System.out.println(node.value);
			if(node.left!=null){
				deque.add(node.left);
			}
			if(node.right !=null){
				deque.add(node.right);
			}
			System.out.println();
		}
		
		
		
		
	}

	public static void main(String[] args) {
		int[] arr = { 0, 13, 65, 5, 97, 25, 0, 37, 22, 0, 4, 28, 0, 0, 32, 0 };
		BinaryTree tree = new BinaryTree(arr);
		tree.depth();
		
		tree.level();
	}
}
