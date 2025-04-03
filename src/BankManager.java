public class BankManager {
    private BankData data;

    public BankManager(BankData data) {
        this.data = data;
    }

    //安全检查算法
    public boolean isSafe() {
        int m = data.getResourceCount();
        int n = data.getProcessCount();
        int[] work = data.getAvailable().clone(); //初始赋值
        boolean[] finish = new boolean[n];

        boolean found; //循环找满足未分配的进程

        do {
            found = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    boolean canFinish = true; //当前进程可以全部被满足
                    for (int j = 0; j < m; j++) {
                        if (data.getNeed()[i][j] > work[j]) {
                            canFinish = false;
                            break;
                        }
                    }
                    if (canFinish) {
                        for (int j = 0; j < m; j++) {
                            work[j] += data.getAllocation()[i][j];
                        }
                        finish[i] = true;
                        found = true;
                    }
                }
            }
        } while (found);

        for (int i = 0; i < n; i++) {
            if (!finish[i]) {
                return false;
            }
        }
        return true;
    }

    //银行家算法
    public boolean requestResource(int p, int[] request) {
        int m = data.getResourceCount();

        System.out.print("进程" + p + "请求资源(");
        for (int i = 0; i < m; i++) {
            System.out.print(request[i] + (i < m - 1 ? ", " : ""));
        }
        System.out.println(")");

        //请求不能超过进程的need
        for (int i = 0; i < m; i++) {
            if (request[i] > data.getNeed()[p][i]) {
                System.out.println("请求超过了进程" + p + " 的最大需求");
                return false;
            }
        }

        //请求不能超过系统可用资源
        for (int i = 0; i < m; i++) {
            if (request[i] > data.getAvailable()[i]) {
                System.out.println("当前系统资源无法满足进程" + p + "的需求");
                return false;
            }
        }

        //尝试分配资源
        int[] oldAvailable = data.getAvailable().clone();
        int[][] allocation = data.getAllocation();
        int[][] need = data.getNeed();
        int[] available = data.getAvailable();

        int[] oldAilocationRow = allocation[p].clone();
        int[] oldNeedRow = need[p].clone();

        for (int i = 0; i < m; i++) {
            available[i] -= request[i];
            allocation[p][i] += request[i];
            need[p][i] -= request[i];
        }

        if (isSafe()) {
            System.out.println("请求成功！");
            return true;
        } else {
            for (int i = 0; i < m; i++) {
                available[i] = oldAvailable[i];
                allocation[p][i] = oldAilocationRow[i];
                need[p][i] = oldNeedRow[i];
            }
            System.out.println("请求失败！");
            return false;
        }
    }
}
