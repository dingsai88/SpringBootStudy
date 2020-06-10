package com.ding.study.datastructure.graph;

import java.util.ArrayList;

/**邻接矩阵
 * @author daniel 2020-6-10 0010.
 */
public class GraphAdjecencyMatrix {

    /**
     * 图中顶点的个数
     */
    public int vertexNum;
    /**
     * 顶点信息
     */
    public char[] vertex = null;
    /**
     * 顶点之间的边信息
     */
    private int[][] matrix = null;
    /**
     * 标记相应顶点是否访问
     */
    public boolean[] visited = null;

    public GraphAdjecencyMatrix(int vertexNum, char[] vertex, int[][] matrix) {
        this.vertexNum = vertexNum;
        this.vertex = vertex;
        this.matrix = matrix;
        /**
         * 初始化所有顶点均未被访问
         */
        visited = new boolean[vertexNum];
    }

    /**
     * 得到i顶点的所有邻接顶点
     * @param i
     * @return
     */
    public ArrayList<Integer> getAllNeighbors(int i) {
        ArrayList<Integer> vertexs = new ArrayList<Integer>();
        for (int ii = 0; ii < vertexNum; ii++) {
            //与i顶点是邻接顶点
            if (matrix[i][ii] == 1) {
                vertexs.add(ii);
            }
        }
        return vertexs;
    }
}
