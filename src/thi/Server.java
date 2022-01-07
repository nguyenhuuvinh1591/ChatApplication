/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */
public class Server {
    public static int port = 9901;
    public static int numThread = 2;
    private static ServerSocket server = null;
    
    Socket socket = null;
    BufferedReader in;
    BufferedWriter out;
    
    public static ArrayList<Worker> arr_workers = new ArrayList<>();
    public static ArrayList<UserEntity> arr_queue = new ArrayList<>();

    public Server() {
        
    }
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        try {
            server = new ServerSocket(port);
            System.out.println("Server binding at port " + port);
            System.out.println("Waiting for client...");
            while(true) {
                Socket socket = server.accept();
                
                Worker client = new Worker(socket);
                arr_workers.add(client);
                
                executor.execute(client);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if(server!=null)
                try {
                    server.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
