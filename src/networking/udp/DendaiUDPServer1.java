package networking.udp;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DendaiUDPServer1 {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            // 受信パケットの準備
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("サーバーはクライアントからのメッセージを待っています...");

            // クライアントからのデータを受信
            socket.receive(receivePacket);
            String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("受信: " + clientMessage);

            // サーバーから大文字に変換させる
            System.out.println("大文字に変換します");
            String serverResponse=clientMessage.toUpperCase();
            sendData = serverResponse.getBytes();
            System.out.println("送信: " + serverResponse);

            // クライアントのアドレスとポートを取得
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            // 返信をクライアントに送信
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
