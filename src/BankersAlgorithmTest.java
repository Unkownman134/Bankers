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

        BankManager manager = new BankManager(bankData);

        //T0初始状态安全检查
        System.out.println("初始系统状态检测：");
        if (manager.isSafe()){
            System.out.println("系统状态安全！");
        } else {
            System.out.println("系统状态不安全！");
        }

        //请求测试
        char choice = 0;
        do {
            System.out.println("请输入申请进程：");
            int p = scanner.nextInt();
            if (p<0 || p>=n) {
                System.out.println("没有这个进程！");
                continue;
            }

            int[] req=new int[m];
            System.out.println("请输入进程"+p+"的请求资源数：");
            for (int i = 0; i < m; i++){
                req[i]=scanner.nextInt();
            }
            manager.requestResource(p,req);
            System.out.print("是否继续申请？ Y/N");
            choice=scanner.next().charAt(0);
        } while (choice=='Y');

        scanner.close();
    }
}
