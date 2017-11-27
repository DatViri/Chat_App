package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Object: a command interpreter that recognizes the specified commands
public class CI implements Runnable,ChatObserver {
    private ChatHistory a;
    private InputStream inputStream;
    private PrintStream outputStream;
    private ArrayList<ChatObserver> observerList = new ArrayList<>();

    public CI(InputStream inputStream, PrintStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        a = ChatHistory.getInstance();
        a.register(this);
    }

    public void run() {

        Scanner sc = new Scanner(inputStream);
        boolean done = false;
        Users client = null;

        while (!done) {
            String cmd = sc.nextLine();
            //Search for first character
            if (cmd != null && !cmd.equals(" ")) {
            if (cmd.charAt(0) != ':') {
                if (client != null) {
                    ChatMessage newMessage = new ChatMessage(cmd, client);
                    ChatHistory.getInstance().insert(newMessage);
                    ChatConsole chatConsole = new ChatConsole();
                    chatConsole.update();
                } else {
                    outputStream.println(":users command for setting new user." + "\n");
                }
            } else {
                StringTokenizer stringTokenizer = new StringTokenizer(cmd, " ");
                String firstToken = stringTokenizer.nextToken();
                //Search for second character, jump into switch statement
                switch (firstToken) {
                    case ":users":
                        //If :users + username
                        if (!stringTokenizer.hasMoreTokens()) {
                            outputStream.println("Users:");
                            for (Users user : Users.getUsersList()) {
                                outputStream.println(user.toString());
                            }
                        }
                        //If :users + already named username
                        else {
                            String secondToken = stringTokenizer.nextToken();
                            client = new Users(secondToken);
                        //Change username
                            if (!UserNameList.getInstance().usersCheck(client)) {
                                outputStream.println("Username is " + client.toString());
                                UserNameList.getInstance().insert(client);
                            } else {
                                outputStream.println("Username already exist, changing" + "\n");
                            }
                        }
                        break;
                    //:messages print the chat history
                    case ":messages":
                        outputStream.println("History is :");
                        outputStream.println(ChatHistory.getInstance().toString());
                        break;
                    //:quit end message
                    case ":quit":
                        outputStream.println("Quit");
                        done = true;
                        break;
                    default:
                        //other wrong command, enter again
                        outputStream.println("Error command, Please enter again !");
                        break;
                }
            }
            }
            }

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void update () {
            outputStream.println(ChatHistory.getInstance().getLatestMessage());
        }
    }
