package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Marcel on 21.04.2015.
 */
public class PathFinder {

    private Map map;

    private Array<Node> open = new Array<PathFinder.Node>();
    private Array<Node> closed = new Array<PathFinder.Node>();
    private Deque<Vector2> path = new LinkedList<Vector2>();

    private Node[][] nodes;

    private final int mapWidth;
    private final int mapHeight;

    public PathFinder(Map map) {
        this.map = map;
        mapWidth = map.getWidth();
        mapHeight = map.getHeight();
        nodes = new Node[mapWidth][mapHeight];
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                nodes[x][y] = new Node(x, y);
            }
        }
    }

    public boolean findPath(int sx, int sy, int tx, int ty) {
        Gdx.app.log("Pathfinder", "Finding path from " + sx + " " + sy + " to " + tx + " " + ty);

        path.clear();
        closed.clear();
        open.clear();
        open.add(nodes[sx][sy]);

        nodes[sx][sy].depth = 0;
        nodes[sx][sy].cost = 0f;
        nodes[tx][ty].parent = null;
        while (open.size > 0) {
            Node current = open.get(0);

            if (current == nodes[tx][ty]) {
                break;
            }

            open.removeValue(current, false);
            closed.add(current);

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0 || (Math.abs(x) + Math.abs(y) == 2))
                        continue;

                    int xp = current.x + x;
                    int yp = current.y + y;

                    if (isBlocked(xp, yp)) {
                        continue;
                    }

                    float nextStepCost = current.cost + 1;
                    Node neighbor = nodes[xp][yp];

                    if (nextStepCost < neighbor.cost) {
                        open.removeValue(neighbor, false);
                        closed.removeValue(neighbor, false);
                    }

                    if (!open.contains(neighbor, false)
                            && !closed.contains(neighbor, false)
                            && !isBlocked(x + current.x, current.y)
                            && !isBlocked(current.x, y + current.y)) {

                        neighbor.cost = nextStepCost;
                        neighbor.heuristic = getHeuristicCost(xp, yp, tx, ty);
                        neighbor.setParent(current);
                        open.add(neighbor);
                        open.sort();
                    }
                }
            }
        }


        if (nodes[tx][ty].parent == null) {
            Gdx.app.log("Pathfinder", "No path found..");
            return false;
        }

        Node target = nodes[tx][ty];
        while (nodes[sx][sy] != target) {
            path.addFirst(new Vector2((target.x - mapWidth / 2) * Map.CELL_SIZE + 0.5f, (target.y - mapHeight / 2) * Map.CELL_SIZE + 0.5f));
            target = target.parent;
        }
        Gdx.app.log("Pathfinder", "Path found!");
        return true;
    }

    public Queue<Vector2> getPath() {
        return path;
    }

    private float getHeuristicCost(int x, int y, int tx, int ty) {
        int dx = tx - x;
        int dy = ty - y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    private boolean isBlocked(int x, int y) {
        if (x < 0 || y < 0 || x >= mapWidth || y >= mapHeight) {
            return true;
        }

        Cell cell = map.getCell(x, y);

        return cell.isBlocked();
    }

    private class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int depth;
        private float cost;
        private float heuristic;
        private Node parent;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setParent(Node parent) {
            depth = parent.depth + 1;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node o) {
            float f = heuristic + cost;
            float of = o.heuristic + o.cost;

            if (f > of) {
                return 1;
            } else if (f < of) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
