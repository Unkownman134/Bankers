import java.util.Scanner;

public class BankersAlgorithmTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入进程数：");
        int n = scanner.nextInt();
        System.out.println("请输入资源种类数数：");
        int m = scanner.nextInt();

        //初始化数据类
        BankData bankData = new BankData(n, m);

        System.out.println("请输入系统"+m+"个资源可用数量：");
        int[] available = new int[m];
        for(int i=0;i<m;i++){
            available[i]=scanner.nextInt();
        }
        bankData.setAvailable(available);

        // 输入各进程的最大需求矩阵
        System.out.println("请输入各进程的最大需求"+n+"*"+m+"矩阵:");
        int[][] max = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                max[i][j] = scanner.nextInt();
            }
        }
        bankData.setMax(max);

        // 输入各进程的资源分配矩阵
        System.out.println("请输入各进程的资源分配"+n+"*"+m+"矩阵:");
        int[][] allocation = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                allocation[i][j] = scanner.nextInt();
            }
        }
        bankData.setAllocation(allocation);

        // 计算 Need 矩阵
        bankData.computeNeed();
    }
}
