import java.io.*;
import java.net.*;

public class ServerImp {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(2831);   // Tạo 1 ServerSocket trên port 2831 (> 1024)
        System.out.println("Server is listening on port 2831...");

        while (true) {
            Socket client = server.accept();    // Chấp nhận kết nối từ client

            ClientHandler clientHandler = new ClientHandler(client);    // Tạo 1 luồng mới để xử lý kết nối với client
            new Thread(clientHandler).start();
        }
    }
}

// Thực thi Runnable để chạy trong 1 luồng mới
class ClientHandler implements Runnable {
    private Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {

            BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
            for(int i = 0 ; i < 100 ; i++){
                System.out.println("Wrote - " + i);
                String s = "dmah ";
                out.write(s.getBytes());
                out.flush();
            }

            out.write("END".getBytes());
            out.flush();

            // Đọc từ input tổng trả về từ client
            BufferedInputStream in = new BufferedInputStream(client.getInputStream());
            byte[] data = new byte[1024];
            int bytesRead;
            String res = "";
            while((bytesRead = in.read(data)) != -1){
                res += new String(data, 0, bytesRead);
            }
            System.out.println(res);

            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException("Client handler error: " + e.getMessage(), e);
        }
    }
}