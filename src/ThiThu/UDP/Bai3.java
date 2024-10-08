
package ThiThu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Bai3 {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int port = 2209;
            String messSend = ";B21DCCN703;TJn89URK";
            DatagramPacket sendData = new DatagramPacket(messSend.getBytes(), messSend.length(), serverAddress, port);
            socket.send(sendData);

            byte[] receivedByte = new byte[2048];
            DatagramPacket receivedPacket = new DatagramPacket(receivedByte, receivedByte.length);
            socket.receive(receivedPacket);

            String requestId = new String(receivedPacket.getData(), 0, 8);

            ByteArrayInputStream byteStream = new ByteArrayInputStream(receivedPacket.getData(), 8, receivedPacket.getLength() - 8);
            ObjectInputStream ojs = new ObjectInputStream(byteStream);
            Product product = (Product) ojs.readObject();

            String[] nameArr = product.getName().trim().split(" ");
            if (nameArr.length > 1) {
                String tmp = nameArr[0];
                nameArr[0] = nameArr[nameArr.length - 1];
                nameArr[nameArr.length - 1] = tmp;
            }
            String newName = "";
            for (String x : nameArr) newName += (x + " ");
            product.setName(newName.trim());

            int Quantity = product.getQuantity();
            StringBuilder sb = new StringBuilder(Quantity + "");
            product.setQuantity(Integer.parseInt(sb.reverse() + ""));

            System.out.println(product.getName());
            System.out.println(product.getQuantity());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(product);
            oos.flush();

            byte[] sendDataa = new byte[8 + baos.size()];
            System.arraycopy(requestId.getBytes(), 0, sendDataa, 0, 8);
            System.arraycopy(baos.toByteArray(), 0, sendDataa, 8, baos.size());
            DatagramPacket resultPacket = new DatagramPacket(sendDataa, sendDataa.length, serverAddress, port);
            socket.send(resultPacket);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
