package udpclient;

import java.io.*;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) throws IOException {

        // get a datagram socket
        DatagramSocket socket = new DatagramSocket();
        Scanner in = new Scanner(System.in);

        //User input
        System.out.println("Enter string: ");
        String str = in.nextLine();

        while (!"q".equals(str)) {

            //Send request to server
            byte[] buf = new byte[256];
            buf = str.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);

            String received;
            do {
                //Receive response from server
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                //Display response from server
                received = new String(packet.getData());
                System.out.println("Recieved: " + received);
            } while (!received.substring(0, 4).equals(("STOP")));
            System.out.println();

            // sleep for a while
            try {
                sleep(5000L);
            } catch (InterruptedException e) {
            }
        }
        socket.close();
    }
}
