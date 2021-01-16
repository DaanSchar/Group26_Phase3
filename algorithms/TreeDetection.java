package algorithms;

import graph.ColEdge;
import graph.Graph;
import logging.Log;

import java.util.LinkedList;

/**
 * Tree detection class that contains various modular methods for detecting whether
 * a graph is a tree.
 */
public class TreeDetection {

    /**
     * Checks whether the current loaded graph is acyclic by traversing the
     * graph using a DFS algorithm.
     *
     * @return true if the graph is acyclic, false otherwise.
     */

    public static boolean isAcyclic() {
        //Bookkeeping
        LinkedList<Integer> visited = new LinkedList<>();
        LinkedList<ParentChild> relation = new LinkedList<>();
        LinkedList<Integer> queue = new LinkedList<>();

        //Tree rooted at vertex 0
        queue.push(0);

        //Traverse the tree
        while (!queue.isEmpty()) {
            int vertex = queue.pop();
            if (!listContainsVertex(visited, vertex)) {
                visited.push(vertex);
            }
            LinkedList<Integer> neighbours = getNeighbours(vertex);

            //Update bookkeeping
            for (int neighbour: neighbours) {
                if (isNotParent(relation, neighbour, vertex)) {
                    relation.push(new ParentChild(vertex, neighbour));
                    if (!listContainsVertex(queue, neighbour)) {
                        queue.push(neighbour);
                    }
                }


                //check to see if we have seen this vertex before
                if (listContainsVertex(visited, neighbour)) {
                    //check to see if the this node only has only one parent
                    if (isNotParent(relation, neighbour, vertex)) {
                        return false;
                    }

                }


            }
        }

        //check for chains
        for (int vertex: visited) {
            if (!oneParent(relation, vertex)) {
                return false;
            }
        }

        //found no counter example, graph is acyclic
        return true;
    }


    /**
     * Check if the the given nodes have a parent-child relationship.
     *
     * @param list - relation list.
     *
     * @param parent - vertex index of the parent .
     *
     * @param child - vertex index of the child.
     *
     * @return true if the relation exists, false otherwise.
     */

    private static boolean isNotParent(LinkedList<ParentChild> list, int parent, int child) {
        for (ParentChild relation : list) {
            if (relation.child == child && relation.parent == parent) {
                return false;
            }
        }
        return true;
    }


    /**
     * Returns the list of neighbours of the specified vertex from the currently loaded graph,
     * assumption is that graphs are undirected.
     *
     * @param vertexIndex - index of the vertex whose neighbours need to be returned.
     *
     * @return - list of vertex indexes directly connected to the specified vertex.
     */

    private static LinkedList<Integer> getNeighbours(int vertexIndex) {
        LinkedList<Integer> neighbours = new LinkedList<>();

        for (ColEdge edge: Graph.getE()) {
            if (edge.v == vertexIndex) {
                if (!listContainsVertex(neighbours, edge.u) && edge.u != vertexIndex) {
                    neighbours.add(edge.u);
                }
                continue;
            }

            if (edge.u == vertexIndex) {
                if (!listContainsVertex(neighbours, edge.v) && edge.v != vertexIndex) {
                    neighbours.add(edge.v);
                }
            }
        }

        return neighbours;
    }

    /**
     * Checks whether the specified vertex has only one traversal parent based on a child-parent
     * graph traversal relations lists that is provided.
     *
     * @param list - child-parent graph traversal relations list against which the specified
     *             child vertex will be checked.
     *
     * @param child - the child vertex whose child-parent relation needs to be checked to be unique.
     *
     * @return true if only the vertex is the child in only one relation, false otherwise.
     */

    private static boolean oneParent(LinkedList<ParentChild> list, int child) {
        if (child == 0) {
            return true;
        }

        boolean parentFound = false;

        for (ParentChild relation: list) {
            if (!parentFound) {
                //no parents found yet
                if (relation.getChild() == child) {
                    parentFound = true;
                }

            } else {
                //parent already found, search for another one
                if (relation.getChild() == child) {
                    return false;
                }
            }
        }

        return parentFound;
    }

    /**
     * Checks if the provided list of vertexes contains the specified vertex,
     * needed to bypass comparison failure due to object wrapping.
     *
     * @param list - list against whose contents the vertex will be checked.
     *
     * @param vertex - the vertex whose presence in the provided list will be checked.
     *
     * @return true if the list contains the specified index, false otherwise.
     */

    private static boolean listContainsVertex(LinkedList<Integer> list, int vertex) {
        for (Integer integer : list) {
            if (integer == vertex) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks that the graph is fully connected.
     *
     * @return true if graph is connected, false otherwise.
     */

    public static boolean isConnected() {
        LinkedList<Integer> visited = new LinkedList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(0);

        while (!queue.isEmpty()) {
            int currentVertex = queue.pop();
            visited.push(currentVertex);

            for (int neighbour: getNeighbours(currentVertex)) {
                if (!listContainsVertex(visited, neighbour) && !listContainsVertex(queue, neighbour)) {
                    queue.push(neighbour);
                }
            }
        }
        return visited.size() == Graph.getN();
    }

    /**
     * Checks that the current graph meets the requirements for being a tree.
     *
     * @return true if the graph is a tree, false otherwise.
     */

    public static boolean isTree() {
        Log.startTimer();
        System.out.println("TreeDetection:      Running...");
        isConnected();
        isAcyclic();
        System.out.println("TreeDetection:      Finished running.");
        Log.endTimer("Tree",(isConnected() && isAcyclic()));

        return isConnected() && isAcyclic();
    }

    /**
     * Returns the chromatic number of a tree graph, graph must be
     * prechecked to be a tree before this method is called, otherwise
     * results will not be reliable.
     *
     * @return chromatic number of the current graph
     */

    public static int getChromaticNumberOfTree() {
        if (Graph.getN() == 1 || Graph.getN() == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Struct for bookkeeping child-parent relations of vertexes
     * based on graph traversal order.
     */

    private static class ParentChild {

        private final int parent;
        private final int child;

        public ParentChild(int parent, int child) {
            this.parent = parent;
            this.child = child;
        }

        public int getParent() {
            return parent;
        }

        public int getChild() {
            return child;
        }
    }

}
