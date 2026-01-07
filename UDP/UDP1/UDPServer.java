import java.io.*;
import java.net.*;
public class UDPServer{
    public static void main(String[] args){
        DatagramSocket s=null;
        try{
            s=new DatagramSocket(8440);
            byte[] buffer=new byte[1000];
            while(true){
            DatagramPacket request=new DatagramPacket(buffer,buffer.length);
            s.receive(request);
            DatagramPacket reply=new DatagramPacket(request.getData(),request.getLength(),request.getAddress(),request.getPort());
            s.send(reply);
            System.out.println("reply:"+new String(reply.getData()));
            }
        }
        catch(SocketException e){
            System.out.println("Socket: "+e.getMessage());
        }
        catch(IOException e){
            System.out.println("IO: "+e.getMessage());
        }
        finally{
            s.close();
        }
    }
}