package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import Message.*;
public class Signup {
    public Button backBT;
    public Button signBT;
    public TextField userTF;
    public TextField emailTF;
    public TextField passTF;
    public static String name;
    //Listener function of Button back
    public void ListenerbBT(ActionEvent actionEvent){
        System.out.println("BACK");
        Parent root=null;
        Stage stage = (Stage) backBT.getScene().getWindow();
        try{
            root = FXMLLoader.load(getClass().getResource("./sample.fxml"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,300, 275));
    }
    //Listener function of Button signup
    public void ListenersBT(ActionEvent actionEvent){
        System.out.println("Signup");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("localhost",5402);//ip adress and port
                    ObjectOutputStream op = new ObjectOutputStream(socket.getOutputStream());
                    op.writeObject(new Message_Music(userTF.getText(),6));
                    op.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
                    Socket socket = new Socket("localhost",5400);//ip adress and port
                    name = userTF.getText();
                    String password = passTF.getText();
                    String email = emailTF.getText();
                    ObjectOutputStream op = new ObjectOutputStream(socket.getOutputStream());
                    sendMessage(socket,in,name,password,email,op);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("BACK");
                            Parent root=null;
                            Stage stage = (Stage) signBT.getScene().getWindow();
                            try{
                                root = FXMLLoader.load(getClass().getResource("./signselect.fxml"));
                            }
                            catch (IOException e){
                                e.printStackTrace();
                            }
                            stage.setScene(new Scene(root,300, 275));
                        }
                    });
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            public void sendMessage(Socket socket, BufferedReader in, String name, String password,String email, ObjectOutputStream op)throws IOException{
                op.writeObject(new Message(name,password,email,1,0));
                op.flush();
                System.out.println("Client closed");
            }
        }).start();
    }
    public static String getname(){
        return name;
    }
}
