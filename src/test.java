import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class test {
    public static void main(String[] args) throws IOException {
        GetDataByTimeOut();
        GetDataByNoBytes();
    }

    private static void GetDataByNoBytes() {

    }

    private static void GetDataByTimeOut() throws IOException {
        Socket client = new Socket("localhost", 2831);
        System.out.println("Connected to port 2831");
        BufferedInputStream in = new BufferedInputStream(client.getInputStream());
        BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());

        byte[] data = new byte[1024];
        int bytesRead;
        String s = "";
        client.setSoTimeout(5000);

        try {
            while ((bytesRead = in.read(data)) != -1) {
                String tmp = new String(data, 0, bytesRead);
                System.out.println(tmp);
                s += tmp;
            }
        } catch (SocketTimeoutException e) {
            System.out.println(s);
            String[] arr = s.trim().split(" ");
            String res = "";
            for(int i = 0 ; i < arr.length-1 ; i++){
                res += arr[i] + i + ", ";
            }

            out.write(res.getBytes());
            out.flush();

            in.close();
            out.close();
            client.close();
            System.out.println("Disconnected");
        }
    }
}
