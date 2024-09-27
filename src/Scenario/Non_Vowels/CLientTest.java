package Scenario.Non_Vowels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CLientTest {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 2831);

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        String line;
        while((line = in.readLine()) != null){
            if(line.equals("EOF")) break;

            System.out.println("Received: " + line);

            String[] arr = line.trim().split(",");
            StringBuilder sb = new StringBuilder("");
            for(String s : arr){

                if(!s.trim().matches(".*[ueoaiUEOAI].*"))
                {
                    if(sb.length() > 0) sb.append(", ");
                    sb.append(s.trim());
                }
            }

            out.println(sb.toString());
            System.out.println("Sent: " + sb.toString());
        }

        client.close();
    }
}
