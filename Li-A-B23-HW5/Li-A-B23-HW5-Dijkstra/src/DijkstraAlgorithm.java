import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DijkstraAlgorithm {

    private static int calculatePathLength(int[][] graph, int[] path) {
        int length = 0;
        for (int i = 0; i < path.length - 1; i++) {
            int from = path[i];
            int to = path[i + 1];
            length += graph[from][to];
        }
        return length;
    }

    private static int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private static int[][] readGraph(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int size = Integer.parseInt(reader.readLine());
        int[][] graph = new int[size][size];

        for (int i = 0; i < size; i++) {
            String[] values = reader.readLine().split(" "); //https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
            for (int j = 0; j < size; j++) {
                graph[i][j] = Integer.parseInt(values[j]);
            }
        }

        reader.close();
        return graph;
    }

    private static int[] dijkstra(int[][] graph, int start, int destination, int size) {
        boolean[] visited = new boolean[size];
        int[] distances = new int[size];
        int[] previousNodes = new int[size];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previousNodes, -1);

        distances[start] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        priorityQueue.add(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            int current = currentNode.node;

            if (visited[current]) {
                continue;
            }
            visited[current] = true;
            for (int i = 0; i < size; i++) {
                if (!visited[i] && graph[current][i] != 0) {
                    int newDistance = distances[current] + graph[current][i];

                    if (newDistance < distances[i]) {
                        distances[i] = newDistance;
                        previousNodes[i] = current;
                        priorityQueue.add(new Node(i, newDistance));
                    }
                }
            }
        }

        List<Integer> pathList = new ArrayList<>();
        int current = destination;
        while (current != -1) {
            pathList.add(current);
            current = previousNodes[current];
        }

        Collections.reverse(pathList);
        int[] pathArray = new int[pathList.size()];
        for (int i = 0; i < pathList.size(); i++) {
            pathArray[i] = pathList.get(i);
        }

        return pathArray;
    }

    static class Node {
        int node;
        int distance;

        Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        try {
            int[][] graph = readGraph("src/DijkstraMatrix.txt");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the start node[Enter a number between 0 and 9, inclusive]: ");
            int startNode = scanner.nextInt();

            System.out.print("Enter the destination node[Enter a number between 0 and 9, inclusive]: ");
            int destinationNode = scanner.nextInt();

            scanner.close();

            int size = graph[0].length; // Number of columns, representing the number of nodes
            int[] shortestPath = dijkstra(graph, startNode, destinationNode, size);

            int destinationIndex = indexOf(shortestPath, destinationNode);

            if (destinationIndex != -1) {
                int pathLength = calculatePathLength(graph, shortestPath);
                System.out.println("Shortest Path Length: " + pathLength);
                System.out.println("Shortest Path[An array of nodes]: " + Arrays.toString(shortestPath));
            } else {
                System.out.println("Invalid destination node, Rerun and try again with valid destination node");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
