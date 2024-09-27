package main;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class TenMienDung {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("172.188.19.218", 1606);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        out.write("B21DCCN055;WlVsU2o"); out.newLine();
        out.flush();

        String s = in.readLine();
        String[] arr = s.trim().split("\\, ");

        StringBuilder sb = new StringBuilder("");
        for(String x : arr){
            if(x.endsWith(".edu")){
                if(sb.length() > 0){
                    sb.append(", ");
                }
                sb.append(x);
            }
        }
        String res = sb.toString();

        out.write(res);
        out.flush();

        in.close();
        out.close();
        client.close();
    }
}
