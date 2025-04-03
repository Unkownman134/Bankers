public class BankManager {
    private BankData data;

    public BankManager(BankData data) {
        this.data=data;
    }

    //安全检查算法
    public boolean isSafe() {
        int m= data.getResourceCount();
        int n= data.getProcessCount();
        int[] work = data.getAvailable().clone(); //初始赋值
        boolean[] finish=new boolean[n];

        boolean found; //循环找满足未分配的进程

        do {
            found=false;
            for(int i=0;i<n;i++) {
                if (!finish[i]) {
                    boolean canFinish=true;
                    for(int j=0;j<m;j++) {
                        if (data.getNeed()[i][j]>work[j]) {
                            canFinish=false;
                            break;
                        }
                    }
                    if (canFinish) {
                        for(int j=0;j<m;j++) {
                            work[j]+=data.getAllocation()[i][j];
                        }
                        finish[i]=true;
                        found=true;
                    }
                }
            }
        } while(found);

        for (int i=0;i<n;i++) {
            if (!finish[i]) {
                return false;
            }
        }
        return true;
    }

    //银行家算法
    public boolean requestResource(int p,int[] request) {
        return true;
    }
}
