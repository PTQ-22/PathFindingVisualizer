import java.awt.*;
import java.util.*;

public class Algorithm implements Runnable {
    private final Grid grid;

    private final Point[] moves = {
            new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)
    };
    private final HashMap<Node, Integer> distance = new HashMap<>();
    private final HashMap<Node, Node> predecessors = new HashMap<>();
    private final Node start;
    private final Node end;

    public Algorithm(Grid grid, Node start, Node end) {
        this.grid = grid;
        this.start = start;
        this.end = end;
        for (Node[] row : grid.getNodes()) {
            for (Node node : row) {
                distance.put(node, -1);
                predecessors.put(node, null);
            }
        }
    }

    @Override
    public void run() {
        if (bfs()) {
            LinkedList<Node> path = new LinkedList<>();
            Node c = end;
            while (predecessors.get(c) != null) {
                path.add(predecessors.get(c));
                c = predecessors.get(c);
            }
            Collections.reverse(path);
            path.pop();
            for (Node node : path) {
                node.setType(Node.PATH);
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private boolean bfs() {
        Node[][] arr = grid.getNodes();

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(start);
        distance.put(start, 0);
        while (queue.size() != 0) {
            Node v = queue.poll();
            for (Point move : moves) {
                int x = v.getGridPosition().x + move.x;
                int y = v.getGridPosition().y + move.y;
                if (x < 0 || y < 0 || x >= arr.length || y >= arr[0].length) {
                    continue;
                }
                if (arr[x][y].getType() == Node.BORDER) {
                    continue;
                }
                if (distance.get(arr[x][y]) == -1) {
                    distance.put(arr[x][y], distance.get(v) + 1);
                    predecessors.put(arr[x][y], v);
                    queue.add(arr[x][y]);
                    if (arr[x][y].getType() == Node.END) {
                        return true;
                    }
                    arr[x][y].setType(Node.WAVE);
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return false;
    }
}
