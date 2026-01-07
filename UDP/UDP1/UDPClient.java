import java.io.*;
import java.net.*;
public class UDPClient{
    public static void main(String[] args){
        DatagramSocket s=null;
        try{
            s=new DatagramSocket();
            int ServerPort=8440;
            InetAddress aHost=InetAddress.getByName(args[1]);
            byte[] m=args[0].getBytes();
            DatagramPacket request=new DatagramPacket(m,m.length,aHost,ServerPort);
            s.send(request);
            byte[] buffer=new byte[1000];
            DatagramPacket reply=new DatagramPacket(buffer,buffer.length);
            s.receive(reply);
            System.out.println("Recieved: "+new String(reply.getData(),0,reply.getLength()));
        }
        catch(SocketException e){
            System.out.println("Socket:"+e.getMessage());
        }
        catch(IOException e){
            System.out.println("IO: "+e.getMessage());
        }
        finally{
            if(s!=null){
                s.close();
            }
        }
    }
}