package udpclient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) throws IOException {

        // get a datagram socket
        DatagramSocket socket = new DatagramSocket();
        Scanner in = new Scanner(System.in);
        String str = "";
        
        while (!"q".equals(str)) {
            
            //User input
            System.out.println("Enter string: ");
            str = in.nextLine();
            System.out.println();
            
            // send request
            byte[] buf = new byte[256];
            buf = str.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);

            // get response
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            // display response
            String received = new String(packet.getData());
            System.out.println("Returned: " + received);
        }
        
        socket.close();
    }
}
