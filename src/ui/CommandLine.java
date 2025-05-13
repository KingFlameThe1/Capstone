package src.ui;

import src.Machines.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Commands.ifconfig;


public class CommandLine {
    ArrayList<Computer> network;
    
    
    public CommandLine(ArrayList<Computer> network){
        this.network = network;
        // Create JFrame
        JFrame frame = new JFrame("Command Line Simulator");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create JTextArea for terminal output
        JTextArea terminalOutput = new JTextArea();
        terminalOutput.setEditable(false);
        terminalOutput.setBackground(Color.BLACK);
        terminalOutput.setForeground(Color.GREEN);
        terminalOutput.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(terminalOutput);

        // Create JTextField for user input
        JTextField commandInput = new JTextField();
        commandInput.setBackground(Color.BLACK);
        commandInput.setForeground(Color.WHITE);
        commandInput.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Add components to JFrame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(commandInput, BorderLayout.SOUTH);

        // Action Listener for command input
        commandInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = commandInput.getText();
                terminalOutput.append("> " + command + "\n"); // Simulate command log
                commandInput.setText(""); // Clear input field

                // Process the command
                switch (command.toLowerCase()) {
                    case "wireshark":
                        WireShark wireShark = new WireShark();
                        break;
                    case "ifconfig":
                        //demonstration --- will swap for a call to a "Commands" object as per MVC architecture
                        
                        terminalOutput.append("eth0:\tflags=4163<UP,BORADCASTING,RUNNING,MULTICAST>\tmtu\t1500\n");
                        //terminalOutput.append("\tinet: "+network.get(0).getIPv4()+"\tnetmask 255.255.255.0\tbroadcast \n");
                        terminalOutput.append("\tinet: "+ifconfig.ipv4(network.get(0))+"\tnetmask 255.255.255.0\tbroadcast \n");
                        terminalOutput.append("\tinet6:\t\tprefixlen\t\tscopeid\n");
                        terminalOutput.append("\tphysical\t"+ifconfig.mac(network.get(0))+"\n");
                        break;
                    case "help":
                        terminalOutput.append("Available commands:\n");
                        terminalOutput.append(" - wireshark: starts up emulated wireshark for network activity simulation\n");
                        terminalOutput.append(" - help: Show available commands\n");
                        terminalOutput.append(" - clear: Clear the terminal\n");
                        terminalOutput.append(" - exit: Close the application\n");
                        break;

                    case "clear":
                        terminalOutput.setText(""); // Clear terminal output
                        break;

                    case "exit":
                        System.exit(0); // Close the application
                        break;

                    default:
                        terminalOutput.append("Unknown command: " + command + "\n");
                        break;
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
}
