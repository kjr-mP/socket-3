package thread;

public class ExThreadsMain{

    String myAlfabetStr="NO ALFABET";
    // main メソッドはプログラムのエントリーポイントです。
    public static void main(String[] args){
        Runnable task1 = task("仕事1", 2000); // 2秒かかる仕事
        Runnable task2 = task("仕事2", 3000); // 3秒かかる仕事
        Runnable task3 = task("仕事3", 1000); // 1秒かかる仕事
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("全ての仕事が完了しました。");
    }
    
// Runnable インターフェースを実装することで、このクラスのインスタンスはスレッドとして実行可能になります。
    private static Runnable task(final String taskName, final int duration) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(taskName + "が開始されました。");
                    Thread.sleep(duration);
                    System.out.println(taskName + "が終了しました。");
                } catch (InterruptedException e) {
                    System.err.println(taskName + "が中断されました。");
                }
            }
        };
    }
}
