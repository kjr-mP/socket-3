package thread;

// Runnable インターフェースを実装することで、このクラスのインスタンスはスレッドとして実行可能になります。
public class CountAZTenRunnable implements Runnable {

    String myAlfabetStr="NO ALFABET";
    // main メソッドはプログラムのエントリーポイントです。
    public static void main(String[] args){
        // 2つの文字を初期化します。
        char c1 = 97; // ASCII値 97 は 'a' です

        CountAZTenRunnable[] cts=new CountAZTenRunnable[26];
        for(int i=0; i<26; i++){
            cts[i] = new CountAZTenRunnable(); //CountTenBクラスのインスタンスとして
            cts[i].setAlfabet(c1+":");
            c1 = (char)(c1 + 1);
        }

        Thread[] ths=new Thread[26];
        for(int i=0; i<26; i++){
            ths[i] = new Thread(cts[i]);
        }
        for(int i=0; i<26; i++){
            ths[i].start();
        }
    }

    private void setAlfabet(String alfabet) {
        myAlfabetStr=alfabet;
    }

    // run メソッドは、新しいスレッドが実行するコードを含みます。
    public void run() {
        // この try-catch ブロックは、0 から 9 までの値を 1000 ミリ秒間隔で出力するループを実行します。
        try {
            for(int i = 1; i < 11; i++) {
                System.out.println(myAlfabetStr+" runnable time:i=" + i);

                // スレッドを 1000 ミリ秒間一時停止します。
                Thread.sleep(1000);  // ミリ秒単位のスリープ時間
            }
        }
        catch(InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }
}
