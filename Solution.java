package com.reputation.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class Solution {
	/**
	 * Definition for a binary tree node.
	 * 
	 */
	
	    public List<Integer> smallestValues(TreeNode root) {
	        Map<Integer, Integer> result = new TreeMap<>();
	        computeMin(result, root, 0);
	        return new ArrayList<Integer>(result.values());
	    }
	    
	    public void computeMin(Map<Integer, Integer> result, TreeNode node, int level){
	     if(node != null){
	         if(result.containsKey(level)) {
	             result.put(level, Math.min(node.val, result.get(level)));
	         }
	         else{
	             result.put(level, node.val);
	         }
	         computeMin(result, node.left, level+1);
	         computeMin(result, node.right, level+1);
	     }   
	    }



    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }
    
        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
    
        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }
    
    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }
    
        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }
    
    public static void main(String[] args) throws IOException {
    	String input_path = args[0];
    	File file = new File(input_path);
		// File file = new
		// File("/Users/swethakasireddy/eclipse-workspace/Test/src/input.txt");

		BufferedReader in = new BufferedReader(new FileReader(file));
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            
            List<Integer> ret = new Solution().smallestValues(root);
            
            String out = integerArrayListToString(ret);
            
            System.out.print(out);
        }
    }
    
}

