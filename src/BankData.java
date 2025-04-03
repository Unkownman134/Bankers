public class BankData {
    private int n; //进程的数量
    private int m; //资源种类
    private int[] available; //可利用资源向量
    private int[][] max; //最大需求矩阵
    private int[][] allocation; //分配矩阵
    private int[][] need; //还需资源矩阵

    public BankData(int n, int m) {
        this.n = n;
        this.m = m;
        available = new int[n];
        max = new int[n][m];
        allocation = new int[n][m];
        need = new int[n][m];
    }

    public int getProcessCount() {
        return n;
    }

    public int getResourceCount() {
        return m;
    }

    public int[] getAvailable() {
        return available;
    }

    public void setAvailable(int[] available) {
        if (available.length == m) {
            this.available = available;
        }
    }

    public int[][] getMax() {
        return max;
    }

    public void setMax(int[][] max) {
        this.max = max;
    }

    public int[][] getAllocation() {
        return allocation;
    }

    public void setAllocation(int[][] allocation) {
        this.allocation = allocation;
    }

    public int[][] getNeed() {
        return need;
    }

    //计算Need矩阵
    public void computeNeed() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }
}
