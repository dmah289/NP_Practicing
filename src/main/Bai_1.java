package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Bai_1 {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218", 1605);

        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("B21DCCN511;pv0Etr1");
        dos.flush();

        DataInputStream dis = new DataInputStream(client.getInputStream());
        int res1 = 0, res2 = 0;
        if (dis.available() > 0) {
            int a = dis.readInt();
            int b = dis.readInt();
            System.out.println("Received: a = " + a + ", b = " + b);

            res1 = gcd(a, b);
            res2 = lcm(a, b);

            dos.writeInt(res1);
            dos.writeInt(res2);
            dos.flush();
        } else {
            System.out.println("No data received from server.");
        }


        dos.writeInt(res1);
        dos.writeInt(res2);
        dos.flush();

        dos.close();
        dis.close();
        client.close();
    }

    private static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    private static int gcd(int a, int b) {
        while(a * b != 0){
            if(a > b) a %= b;
            else b %= a;
        }
        return a + b;
    }
}
