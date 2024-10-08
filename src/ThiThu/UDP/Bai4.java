package ThiThu;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bai4 {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int port = 2207;
            String messSend = ";B21DCCN703;DGIiLTmZ";
            DatagramPacket sendData = new DatagramPacket(messSend.getBytes(), messSend.length(), serverAddress, port);
            socket.send(sendData);

            byte[] receivedData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
            socket.receive(receivePacket);

            String receivedMess = new String(receivePacket.getData(), 0, receivePacket.getLength());
            receivedMess = receivedMess.replace(";", " ");
            receivedMess = receivedMess.replace(",", " ");
            String[] arrMess = receivedMess.split(" ");
            ArrayList<Integer> arl = new ArrayList<>();
            for (int i = 1; i < arrMess.length; i++) {
                arl.add(Integer.parseInt(arrMess[i]));
            }
            Collections.sort(arl);
            String result = arrMess[0] + ";" + arl.get(arl.size() - 2) + "," + arl.get(1);
            System.out.println(result);
            DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, port);
            socket.send(resultPacket);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}