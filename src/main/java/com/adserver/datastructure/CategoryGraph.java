package com.adserver.datastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import com.adserver.entities.AdCategory;

/**
 * Graph to maintain category details. It would be cumbersome to run recursive queries on backend
 * (lot of resource usage could be avoided)
 */
public class CategoryGraph {

    private static Graph graph;

    /*
     * vertex of a graph
     */
    private static class Vertex {

        private int categoryId;
        private boolean wasVisited;
        private String categoryName;

        public Vertex(int categoryId, String categoryName) {
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.wasVisited = false;
        }
    }

    /*
     * Graph which will hold category and its relation with other categories.
     */
    private static class Graph {

        private Vertex vertexList[];
        private int adjMat[][];
        private Stack<Integer> theStack;
        private List<Integer> decendants = new LinkedList<Integer>();

        public Graph(int maxVertices) {
            vertexList = new Vertex[maxVertices];
            adjMat = new int[maxVertices][maxVertices];
            theStack = new Stack<Integer>();
        }

        private void addVertex(int categoryId, String name) {
            vertexList[categoryId] = new Vertex(categoryId, name);
        }

        private void addEdge(int start, int end) {
            adjMat[end][start] = 1;
        }

        public void dfs(int start) {
            if (start <= 0 || start >= vertexList.length) {
                return;
            }
            int vertex;
            if (!vertexList[start].wasVisited) {
                vertexList[start].wasVisited = true;
                theStack.push(start);
                while (!theStack.isEmpty()) {
                    vertex = getAdjUnvisitedVertex(theStack.peek());
                    if (vertex == -1) {
                        decendants.add(theStack.pop());
                    } else {
                        vertexList[vertex].wasVisited = true;
                        theStack.push(vertex);
                    }
                }
            }
        }

        private void clearVisitedFlag() {
            for (int j = 1; j < vertexList.length; j++) {
                vertexList[j].wasVisited = false;
            }
            decendants.clear();
        }

        private int getAdjUnvisitedVertex(int v) {
            for (int j = 1; j < vertexList.length; j++)
                if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
                    return j;
            return -1;
        }

        private List<AdCategory> getfinalCategories() {
            final List<AdCategory> categories = new ArrayList<AdCategory>(decendants.size());
            Vertex vertex;
            for (Integer decendant : decendants) {
                vertex = vertexList[decendant];
                categories.add(new AdCategory(vertex.categoryId, vertex.categoryName, 0));
            }
            return categories;
        }
    }

    /**
     * Build the graph for the categories.
     * 
     * @param adCategories
     */
    public void initGraph(List<AdCategory> adCategories) {
        final Set<AdCategory> set = new HashSet<AdCategory>();
        graph = new Graph(adCategories.size() + 1);
        int currentCategory, parentCategory;
        for (AdCategory adCategory : adCategories) {
            currentCategory = adCategory.getCategoryId();
            parentCategory = adCategory.getParentCategoryId();
            graph.addEdge(currentCategory, parentCategory);
            set.add(adCategory);
        }
        for (AdCategory adCategory : set) {
            graph.addVertex(adCategory.getCategoryId(), adCategory.getCategoryName());
        }
    }

    /**
     * Get all the descendant categories for the parent node given as input parameter.
     * 
     * @param node
     * @return
     */
    public List<AdCategory> getDescendantCategories(int node) {
        graph.dfs(node);
        final List<AdCategory> categories = graph.getfinalCategories();
        graph.clearVisitedFlag();
        return categories;
    }
}
