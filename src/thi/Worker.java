/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thi;

import thi.Server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Worker implements Runnable {
    public String myName;
    private Socket socket;
    BufferedReader in;
    BufferedWriter out;
    public static ArrayList<UserEntity> arr_user = new ArrayList<>();

    public Worker(Socket s) throws IOException {
        this.socket = s;
//        this.myName = name;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
    
    public void run() {
        try {
            String input = "";
            String status;
            input = in.readLine();
            System.out.println(input);
            String[] temp = input.split(";");
            
            status = temp[1];
            setMyName(temp[0]);
            switch(status){
                case "CONNECT": {
                    Runnable runnable = new Runnable() {
                        public void run() {

                        }
                    };
                    UserEntity user = new UserEntity(getMyName(), status);
                    arr_user.add(user);
                    System.out.println("Server received Name: " + getMyName());
                    if(CheckNameExistence(getMyName())){
                        System.out.println("Nickname da ton tai");
                        out.write("RENAME");
                        out.newLine();
                        out.flush();
                        arr_user.remove(user);
                    }else{
                        arr_user.add(user);
                        System.out.println("Client " + socket.toString() + " accepted" + " # Client " + getMyName());
                        System.out.println("------------------------------------------------------------------------");
                    }
                    break;
                }
                case "MATCH":{
                    if(arr_user.size() == 1){
                        JOptionPane.showMessageDialog(null, "Bạn tới quá sớm nên đợi tí nhé!");
                        Server.arr_queue.add(arr_user.get(0));
                    }
                    System.out.println("alo");
//                    int reply = JOptionPane.showConfirmDialog(null, "Bạn có muốn ghép đôi ?", "???", JOptionPane.YES_NO_OPTION);
//                    if (reply == JOptionPane.YES_OPTION) {
//                        JOptionPane.showMessageDialog(null, "HELLO");
//                        
//                    } else {
//                        JOptionPane.showMessageDialog(null, "GOODBYE");
//                        Server.arr_workers.remove(socket);
//                    }
                    break;
                }
                case "SEND": {
                    while(true) {
                        System.out.println("Server received: " + input + " || from " + socket.toString() + " # Client " + getMyName());
                        
                        for(Worker worker : Server.arr_workers) {
                            if(!getMyName().equals(worker.getMyName())) {
                                worker.out.write(input + ';' + getMyName());
                                worker.out.flush();
                                System.out.println("Server write: " + input + " to " + worker.getMyName());
                                break;
                            }
                        }
                    }
                }
                case "RECEIVE":{
                    
                }
                default:{
                    System.out.println("Closed socket for client " + getMyName() + " " + socket.toString());
                }
            }
            
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public boolean CheckNameExistence(String name){
        for(int i = 1 ; i < arr_user.size(); i++){
            if(arr_user.size() == 1){
                return false;
            } 
            if(arr_user.get(i -1).getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
}