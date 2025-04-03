import java.util.Scanner;

public class BankersAlgorithmTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入进程数：");
        int n = sc.nextInt();
        System.out.println("请输入资源种类数数：");
        int m = sc.nextInt();

        //初始化数据类
        BankData bankData = new BankData(n, m);

        System.out.println("请输入系统"+m+"个资源可用数量：");
        int[] available = new int[m];
        for(int i=0;i<m;i++){
            available[i]=sc.nextInt();
        }
        bankData.setAvailable(available);

    }
}
