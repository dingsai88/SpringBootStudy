package com.ding.study.datastructure.graph;

/**邻接表
 * @author daniel 2020-6-10 0010.
 */
public class GraphAdjecencyList {

    public char vertex;
    public GraphAdjecencyList next;
    //顶点数
    public static int vertexNum;
    public static boolean visited[] = null;

    public GraphAdjecencyList(char vertex) {
        this.vertex = vertex;
    }

    public GraphAdjecencyList(int vertexNum) {
        GraphAdjecencyList.vertexNum = vertexNum;
        GraphAdjecencyList.visited = new boolean[GraphAdjecencyList.vertexNum];
    }

    public GraphAdjecencyList[] create(char[] vertexs, int[][] matrix) {
        GraphAdjecencyList[] g2 = new GraphAdjecencyList[vertexNum];
        for (int i = 0; i < matrix.length; i++) {
            g2[i] = new GraphAdjecencyList(vertexs[i]); // 创建顶点表
            GraphAdjecencyList cur = g2[i];
            for (int ii = 0; ii < matrix[i].length; ii++) {
                if (matrix[i][ii] != 0) { // 顶点i的邻接点
                    GraphAdjecencyList tmp = new GraphAdjecencyList(vertexs[ii]); // 创建边表
                    cur.next = tmp; // 将其作为上一个顶点的后继
                    cur = tmp;
                }
            }
        }
        return g2;
    }

    // 得到图中顶点为vertex的索引
    public static int indexOf(GraphAdjecencyList[] g, char vertex) {
        for (int i = 0; i < vertexNum; i++) {
            if (g[i].vertex == vertex) {
                return i;
            }
        }
        return -1; // 未找到
    }
}
