package com.ding.study.datastructure.graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图Graph的存储：邻接矩阵(数组)Adjecency Matrix ;邻接表(链表)Adjecency List
 * 图Graph的搜索:DFS 深度优先 Depth first seach
 * 图Graph的搜索:BFS 广度优先 Breadth first seach
 *
 * 邻接表法
 BFS:ABDECFGHIJK
 DFS:ABCGEHFDIJK---分割线----
 A  B  C  D  E  F  G  H  I  J  K
 A  0  1  0  1  1  0  0  0  0  0  0
 B  1  0  1  0  0  1  0  0  0  0  0
 C  0  1  0  0  0  0  1  1  0  0  0
 D  1  0  0  0  0  1  0  0  0  0  0
 E  1  0  0  0  0  0  1  0  0  0  0
 F  0  1  0  1  0  0  0  0  0  0  0
 G  0  0  1  0  1  0  0  0  0  0  0
 H  0  0  1  0  0  0  0  0  0  0  0
 I  0  0  0  0  0  0  0  0  0  1  0
 J  0  0  0  0  0  0  0  0  1  0  0
 K  0  0  0  0  0  0  0  0  0  0  0
 ---分割线----

 */
public class GraphAdjecencyMatrixListDFSBFSTestMain {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //顶点个数
        int vertexNum = 11;
        //顶点个数
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        /**
         * 连通分量：分连通图的极大连通子图叫做连通分量。(里边的最多顶点数的连通图四个顶点围绕三个顶点)
         1.含有极大顶点数
         2.依附于这些顶点的所有边
         */
        // 此图包含三个连通分量:矩阵
        int[][] matrix = new int[][]{{0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        //邻接矩阵
        adjacencyMatrix(vertexNum, vertex, matrix);
        System.out.println("---分割线----");
        // 邻接表
        adjacencyList(vertexNum, vertex, matrix);
        System.out.println("---分割线----");
        printTest1(vertexNum, vertex, matrix);
    }

    /**
     * 打印二位数组
     * @param vertexNum
     * @param vertex
     * @param matrix
     */
    public static void printTest1(int vertexNum, char[] vertex, int[][] matrix) {
        System.out.print("   ");
        for (char c : vertex) {
            System.out.print(c + "  ");
        }
        System.out.println("");
        for (int i = 0; i < vertexNum; i++) {
            System.out.print(vertex[i] + "  ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println("");
        }


        System.out.println("---分割线----");
    }

    /**
     * 邻接矩阵
     *
     * @param vertexNum 顶点个数
     * @param vertex    顶点数组
     * @param matrix    矩阵二位数组
     */
    public static void adjacencyMatrix(int vertexNum, char[] vertex, int[][] matrix) {
        GraphAdjecencyMatrix g = new GraphAdjecencyMatrix(vertexNum, vertex, matrix);
        ArrayList<Integer> visitedSerials = traverseByBFS(g, 0); // 以下标为1的顶点开始BFS图g
        System.out.println("邻接矩阵法");
        System.out.print("BFS广度优先:");
        for (Integer visited : visitedSerials) {
            System.out.print(vertex[visited]);
        }
        System.out.println();
        Arrays.fill(g.visited, false); // 重置所有顶点均未被访问
        for (int distance : minDistance(g, 0)) {
            System.out.print(distance);
        }
        System.out.println();
        Arrays.fill(g.visited, false); // 重置所有顶点均未被访问
        System.out.print("DFS深度优先:");
        traverseByDFS(g, 0);
    }

    /**
     * 邻接表-链表表示
     *
     * @param vertexNum 顶点个数
     * @param vertexs   顶点数组
     * @param matrix    矩阵
     */
    public static void adjacencyList(int vertexNum, char[] vertexs, int[][] matrix) {
        GraphAdjecencyList g = new GraphAdjecencyList(vertexNum);
        GraphAdjecencyList[] g2 = g.create(vertexs, matrix);
        ArrayList<Integer> visitedSerials = traverseByBFS(g2, 0);
        System.out.println("邻接表法");
        System.out.print("BFS:");
        for (Integer visited : visitedSerials) {
            System.out.print(g2[visited].vertex);
        }
        Arrays.fill(GraphAdjecencyList.visited, false);
        System.out.print("\nDFS:");
        traverseByDFS(g2, 0);
    }


    // 以顶点i开始的图的广度优先搜索(邻接矩阵法)
    public static ArrayList<Integer> traverseByBFS(GraphAdjecencyMatrix g, int i) {
        ArrayList<Integer> visitedSerialOfAll = new ArrayList<Integer>();
        if (!g.visited[i]) {
            visitedSerialOfAll.addAll(BFS(g, i)); // 如果以下标为i的顶点未被访问，那么对从i开始的顶点进行BFS
        }
        for (int ii = 0; ii < g.vertexNum; ii++) { // 对图中所有顶点进行查看是否访问过(从第0号节点开始访问)，因为图可能是非连通的(由几个连通分量组成)，那么由某个顶点开始就不能将图所有节点遍历到
            if (!g.visited[ii]) {
                visitedSerialOfAll.addAll(BFS(g, ii)); // 如果下标为i的顶点未被访问，那么对从i开始的顶点进行BFS
            }
        }
        return visitedSerialOfAll;
    }

    // 以顶点i开始的连通图的广度优先搜索(邻接矩阵法)
    public static ArrayList<Integer> BFS(GraphAdjecencyMatrix g, int i) {
        ArrayList<Integer> visitedSerialOfPart = new ArrayList<Integer>(); // 存放某个连通分量顶点依次访问的序列
        LinkedList<Integer> l = new LinkedList<Integer>();
        visitedSerialOfPart.add(i);
        g.visited[i] = true; // 下标为i的顶点已访问
        l.addLast(i); // 将下标为i的顶点入队
        while (!l.isEmpty()) {
            int ii = l.removeFirst(); // 出队
            ArrayList<Integer> vertexs = g.getAllNeighbors(ii); // 得到ii顶点的所有邻接顶点
            while (!vertexs.isEmpty()) {
                int iii = vertexs.get(0); // 得到顶点ii的第一个邻接顶点
                vertexs.remove(0); // 从顶点ii的邻接序列中移除
                if (!g.visited[iii]) { // 未被访问则访问之
                    visitedSerialOfPart.add(iii);
                    g.visited[iii] = true;
                    l.addLast(iii); // 入队
                }
            }
        }
        return visitedSerialOfPart;
    }

    // 以顶点i开始的图的广度优先搜索(邻接表法)
    public static ArrayList<Integer> traverseByBFS(GraphAdjecencyList[] g, int i) {
        ArrayList<Integer> visitedSerialOfAll = new ArrayList<Integer>();
        if (!GraphAdjecencyList.visited[i]) {
            visitedSerialOfAll.addAll(BFS(g, i)); // 如果以下标为i的顶点未被访问，那么对从i开始的顶点进行BFS
        }
        for (int ii = 0; ii < GraphAdjecencyList.vertexNum; ii++) { // 对图中所有顶点进行查看是否访问过(从第0号节点开始访问)，因为图可能是非连通的(由几个连通分量组成)，那么由某个顶点开始就不能将图所有节点遍历到
            if (!GraphAdjecencyList.visited[ii]) {
                visitedSerialOfAll.addAll(BFS(g, ii)); // 如果下标为i的顶点未被访问，那么对从i开始的顶点进行BFS
            }
        }
        return visitedSerialOfAll;
    }

    // 以顶点i开始的连通图的广度优先搜索(邻接表法)
    public static ArrayList<Integer> BFS(GraphAdjecencyList[] g, int i) {
        ArrayList<Integer> visitedSerialOfPart = new ArrayList<Integer>(); // 存放某个连通分量顶点依次访问的序列
        LinkedList<Integer> l = new LinkedList<Integer>();
        visitedSerialOfPart.add(i);
        GraphAdjecencyList.visited[i] = true; // 下标为i的顶点已访问
        l.addLast(i); // 将下标为i的顶点入队
        while (!l.isEmpty()) {
            int ii = l.removeFirst(); // 出队
            GraphAdjecencyList cur = g[ii];
            while (cur.next != null) {
                cur = cur.next;
                int iii = GraphAdjecencyList.indexOf(g, cur.vertex);
                if (!GraphAdjecencyList.visited[iii]) {
                    visitedSerialOfPart.add(iii);
                    GraphAdjecencyList.visited[iii] = true;
                    l.addLast(iii);
                }
            }
        }
        return visitedSerialOfPart;
    }

    // 以顶点i开始的图的深度优先搜索(邻接矩阵法)
    public static void traverseByDFS(GraphAdjecencyMatrix g, int i) {
        if (!g.visited[i]) {
            DFS(g, i); // 如果以下标为i的顶点未被访问，那么对从i开始的顶点进行DFS
        }
        for (int ii = 0; ii < g.vertexNum; ii++) { // 对图中所有顶点进行查看是否访问过(从第0号节点开始访问)，因为图可能是非连通的(由几个连通分量组成)，那么由某个顶点开始就不能将图所有节点遍历到
            if (!g.visited[ii]) {
                DFS(g, ii); // 如果下标为i的顶点未被访问，那么对从i开始的顶点进行BFS
            }
        }
    }

    // 以顶点i开始的连通图的深度优先搜素(邻接矩阵法)
    public static void DFS(GraphAdjecencyMatrix g, int i) {
        System.out.print(g.vertex[i]);
        g.visited[i] = true; // 下标为i的顶点已访问
        ArrayList<Integer> vertexs = g.getAllNeighbors(i); // 得到i顶点的所有邻接顶点
        while (!vertexs.isEmpty()) {
            int iii = vertexs.get(0); // 得到顶点ii的第一个邻接顶点
            vertexs.remove(0); // 从顶点ii的邻接序列中移除
            if (!g.visited[iii]) { // 未被访问则访问之
                DFS(g, iii);
            }
        }
    }

    // 以顶点i开始的图的深度优先搜索(邻接表法)
    public static void traverseByDFS(GraphAdjecencyList[] g, int i) {
        if (!GraphAdjecencyList.visited[i]) {
            DFS(g, i); // 如果以下标为i的顶点未被访问，那么对从i开始的顶点进行BFS
        }
        for (int ii = 0; ii < GraphAdjecencyList.vertexNum; ii++) { // 对图中所有顶点进行查看是否访问过(从第0号节点开始访问)，因为图可能是非连通的(由几个连通分量组成)，那么由某个顶点开始就不能将图所有节点遍历到
            if (!GraphAdjecencyList.visited[ii]) {
                DFS(g, ii); // 如果下标为i的顶点未被访问，那么对从i开始的顶点进行BFS
            }
        }
    }

    // 以顶点i开始的连通图的深度优先搜素(邻接表法)
    public static void DFS(GraphAdjecencyList[] g, int i) {
        System.out.print(g[i].vertex);
        GraphAdjecencyList.visited[i] = true; // 下标为i的顶点已访问
        GraphAdjecencyList cur = g[i];
        while (cur.next != null) {
            cur = cur.next;
            int ii = GraphAdjecencyList.indexOf(g, cur.vertex);
            if (!GraphAdjecencyList.visited[ii]) {
                DFS(g, ii);
            }
        }
    }

    // 求解图的单源(i顶点)最短路径，如果从这个单源不可达，那么这些顶点就不会被访问，自然地距离也就会是默认的0
    public static int[] minDistance(GraphAdjecencyMatrix g, int i) {
        int[] minDistanceSerial = new int[g.vertexNum];
        LinkedList<Integer> l = new LinkedList<Integer>();
        minDistanceSerial[i] = 0;
        g.visited[i] = true; // 下标为i的顶点已访问
        l.addLast(i); // 将下标为i的顶点入队
        while (!l.isEmpty()) {
            int ii = l.removeFirst(); // 出队
            ArrayList<Integer> vertexs = g.getAllNeighbors(ii); // 得到ii顶点的所有邻接顶点
            while (!vertexs.isEmpty()) {
                int iii = vertexs.get(0); // 依次得到顶点ii邻接顶点
                vertexs.remove(0); // 从顶点ii的邻接序列中移除
                if (!g.visited[iii]) { // 未被访问则访问之
                    minDistanceSerial[iii] = minDistanceSerial[ii] + 1; // 在ii顶点基础上加1(因为BFS是按照距离由近到远来遍历图中每一个顶点的)
                    g.visited[iii] = true;
                    l.addLast(iii); // 入队
                }
            }
        }
        return minDistanceSerial;
    }


}