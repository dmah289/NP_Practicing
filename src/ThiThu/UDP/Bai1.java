
package THQuanThuy.B2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;

public class Bai1 {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int port = 2208;
            String messSend = ";B21DCCN703;jRrRuoIR";
            DatagramPacket sendData = new DatagramPacket(messSend.getBytes(), messSend.length(), serverAddress, port);
            socket.send(sendData);

            byte[] receivedData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
            socket.receive(receivePacket);

            String receivedMess = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] arrMess = receivedMess.split(";");
            String input = arrMess[1].trim().toLowerCase();
            input = input.substring(0, 1).toUpperCase() + input.substring(1);

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ' ') {
                    String tmp = input.charAt(i + 1) + "";
                    input = input.substring(0, i + 1) + tmp.toUpperCase() + input.substring(i + 2);
                }
            }

            String result = String.format("%s;%s", arrMess[0], input);
            System.out.println(result);
            DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, port);
            socket.send(resultPacket);
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
