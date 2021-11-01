package adventofcode.calendar.year2018.day25;

public class DisjointSet {
    private int size;
    private int[] parent;
    private int[] height;

    public DisjointSet(int size) {
        this.size = size;
        parent = new int[size];
        height = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            height[i] = 1;
        }
    }

    public int size() {
        return size;
    }

    public int findRoot(int i) {
        int root = i;
        while (parent[root] != root) {
            root = parent[root];
        }
        while (parent[i] != root) {
            int oldParent = parent[i];
            parent[i] = root;
            i = oldParent;
        }
        return root;
    }

    public boolean merge(int i, int j) {
        int iRoot = findRoot(i);
        int jRoot = findRoot(j);
        if (iRoot == jRoot) return false;
        if (height[iRoot] < height[jRoot]) {
            parent[iRoot] = jRoot;
        } else if (height[jRoot] < height[iRoot]) {
            parent[jRoot] = iRoot;
        } else {
            parent[jRoot] = iRoot;
            height[iRoot]++;
        }
        size--;
        return true;
    }
}
