package api_test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author fatKarin
 * @date 2020/4/9 10:52
 */
public class TreeTest {

    public static void main(String[] args) {

        Tree<Integer> integerTree = new Tree<>();

        for (int i = 0; i < 100; i++) {
            integerTree.addElement(new Random().nextInt(500));
        }
        integerTree.dfs();

    }
}

// 二叉树,
class Tree<T extends Comparable<T>> {

    private PriorityQueue<T> queue;

    private List<Node<T>> nodes;

    // 根节点
    private Node<T> root;

    private int depth;
    // 元素个数
    private int elementNum;

    public Tree () {
        this.queue = new PriorityQueue<>();
        this.elementNum = 0;
        this.depth = 0;
        nodes = new ArrayList<>();
    }


    class Node<T> {

        T element;

        Node<T> left;

        Node<T> right;

        boolean hasChild;
    }
    //新增节点
    void addElement(T T) {
        if(queue.size() == 0) {
            Node<T> rootNode = new Node<>();
            rootNode.element = T;
            this.root = rootNode;
            this.nodes.add(rootNode);
        }
        else {
            compareAndAdd(this.root, T);
        }
        this.queue.add(T);

    }

    // 插入节点
    private void compareAndAdd(Node<T> node, T T) {
        // 小于位于左节点
        if(T.compareTo(node.element) <= 0) {
            if(node.left != null) {
                compareAndAdd(node.left,T);
            } else {
                Node<T> childNode = new Node<>();
                childNode.element = T;
                node.left = childNode;
                this.elementNum++;
                this.nodes.add(childNode);
            }

        } else { // 大于位于右节点
            if(node.right != null) {
                compareAndAdd(node.right,T);
            } else {
                Node<T> childNode = new Node<>();
                childNode.element = T;
                node.right = childNode;
                this.elementNum++;
                this.nodes.add(childNode);
            }


        }
    }

    //深度优先遍历
    void dfs() {
        System.out.println("插入顺序：");
        this.nodes.forEach(x -> System.out.println(x.element));
        if(this.root == null)
            return;
        System.out.println("元素个数：" + this.elementNum);
        System.out.println("dfs顺序：");
        dfs(this.root);
    }

    private void dfs(Node<T> node) {
        System.out.println(node.element);
        if(node.left != null)
            dfs(node.left);
        if(node.right != null)
            dfs(node.right);
    }

    //广度优先遍历,待完成
    void bfs() {
        if(this.root == null)
            return;
        dfs(this.root);
    }

    private void bfs(Node<T> node) {
    }
}
