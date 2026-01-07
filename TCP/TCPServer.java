import java.net.*;
import java.io.*;
public class TCPServer{
    public static void main(String[] args){
        ServerSocket s;
        try{
            s=new ServerSocket(8440);
            while(true){
                Socket clientSocket=s.accept();
                Connection c=new Connection(clientSocket);
            }
        }
        catch(IOException e){
            System.out.println("IO: "+e.getMessage());
        }
    }
}
class Connection extends Thread{
    DataInputStream in;
    DataOutputStream out;
    Socket s=null;
    public Connection(Socket aClientSocket){
        try{
        s=aClientSocket;
        in=new DataInputStream(s.getInputStream());
        out=new DataOutputStream(s.getOutputStream());
        this.start();
        }
        catch(IOException e){
            System.out.println("IO: "+e.getMessage());
        }
    }
    public void run(){
        try{
            String data=in.readUTF();
            out.writeUTF(data);
            System.out.println("Recieved:"+data);
        }
        catch(EOFException e){
            System.out.println("EOF "+e.getMessage());
        }
        catch(IOException e){
            System.out.println("IO:"+e.getMessage());
        }
        finally{
            if(s!=null){
                try{
                    s.close();
                }
                catch(IOException e){
                    System.out.println("IO: "+e.getMessage());
                }
            }
        }
    }
}