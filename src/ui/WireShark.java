package src.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import src.Machines.*;

public class WireShark {
    private static ArrayList<Computer> network;
    private static JTextArea terminalOutput;
    private static JTextArea packetDetails;
    private static JTextField commandInput;
    private static JPanel computerPanel;
    private static JFrame frame;
    private static List<JButton> computerButtons;
    private static Random random;
    private static DefaultTableModel tableModel;
    private static JTable packetTable;
    private static int messageCount = 1;

    public static void main(String[] args) {
        new MainMenu(); // Initialize the main menu UI
    }

    public WireShark(){
        random = new Random();
        frame = new JFrame("Network Simulation - Wireshark Style");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Terminal output (bottom logs)
        terminalOutput = new JTextArea(6, 80);
        terminalOutput.setEditable(false);
        terminalOutput.setBackground(Color.BLACK);
        terminalOutput.setForeground(Color.GREEN);
        terminalOutput.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane terminalScrollPane = new JScrollPane(terminalOutput);

        // Command input (bottom)
        commandInput = new JTextField();
        commandInput.setBackground(Color.BLACK);
        commandInput.setForeground(Color.WHITE);
        commandInput.setFont(new Font("Monospaced", Font.PLAIN, 13));

        // Table to show simulated packets (top)
        String[] columnNames = {"No.", "Time", "Source", "Destination", "Protocol", "Length", "Info"};
        tableModel = new DefaultTableModel(columnNames, 0);
        packetTable = new JTable(tableModel);
        JScrollPane packetScrollPane = new JScrollPane(packetTable);
        packetScrollPane.setPreferredSize(new Dimension(1000, 200));

        // Packet details (middle)
        packetDetails = new JTextArea(5, 80);
        packetDetails.setEditable(false);
        packetDetails.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane detailsScrollPane = new JScrollPane(packetDetails);

        // On packet table row select, show details
        packetTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && packetTable.getSelectedRow() != -1) {
                    int row = packetTable.getSelectedRow();
                    String details = "Packet #" + tableModel.getValueAt(row, 0) + "\n"
                            + "Time: " + tableModel.getValueAt(row, 1) + "\n"
                            + "Source: " + tableModel.getValueAt(row, 2) + "\n"
                            + "Destination: " + tableModel.getValueAt(row, 3) + "\n"
                            + "Protocol: " + tableModel.getValueAt(row, 4) + "\n"
                            + "Length: " + tableModel.getValueAt(row, 5) + "\n"
                            + "Info: " + tableModel.getValueAt(row, 6);
                    packetDetails.setText(details);
                }
            }
        });

        // Left computer panel
        computerPanel = new JPanel(new GridLayout(0, 1));
        JScrollPane computerScrollPane = new JScrollPane(computerPanel);
        computerScrollPane.setPreferredSize(new Dimension(250, 0));

        // Controls (top)
        JPanel controlPanel = new JPanel();
        JButton startButton = new JButton("Start Simulation");
        JButton clearButton = new JButton("Clear");
        JButton sendButton = new JButton("Send Message");
        JButton attackButton = new JButton("Attack");
        controlPanel.add(startButton);
        controlPanel.add(sendButton);
        controlPanel.add(attackButton);
        controlPanel.add(clearButton);

        // Add listeners
        startButton.addActionListener(e -> startSimulation());
        clearButton.addActionListener(e -> terminalOutput.setText(""));
        sendButton.addActionListener(e -> sendMessage());
        attackButton.addActionListener(e -> attackComputer());

        // Combine center panels
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(packetScrollPane, BorderLayout.NORTH);
        centerPanel.add(detailsScrollPane, BorderLayout.CENTER);
        centerPanel.add(terminalScrollPane, BorderLayout.SOUTH);

        // Layout frame
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(computerScrollPane, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(commandInput, BorderLayout.SOUTH);

        // Command handler
        commandInput.addActionListener(e -> {
            String command = commandInput.getText().trim();
            terminalOutput.append("> " + command + "\n");
            commandInput.setText("");
            switch (command.toLowerCase()) {
                case "help" -> terminalOutput.append("Available commands: help, clear, start, exit\n");
                case "exit" -> System.exit(0);
                default -> terminalOutput.append("Unknown command: " + command + "\n");
            }
        });

        frame.setVisible(true);
    }

    public static void setNetwork(ArrayList<Computer> net) {
        network = net;
        displayComputers();
    }

    private static void displayComputers() {
        computerPanel.removeAll();
        computerButtons = new ArrayList<>();
        for (Computer c : network) {
            JButton b = new JButton("IP: " + c.getIPv4() + " Status: " + (c.isCompromised() ? "Compromised" : "Secure"));
            b.setBackground(c.isCompromised() ? Color.RED : Color.GREEN);
            b.addActionListener(e -> showComputerDetails(c));
            computerButtons.add(b);
            computerPanel.add(b);
        }
        computerPanel.revalidate();
        computerPanel.repaint();
    }

    private static void showComputerDetails(Computer c) {
        terminalOutput.append("\nSelected Computer: " + c.getIPv4() + "\n");
        terminalOutput.append("Vulnerabilities:\n");
        for (Vulnerabilities.VulnerabilityType v : Vulnerabilities.VulnerabilityType.values()) {
            if (c.isVulnerableTo(v)) {
                terminalOutput.append("- " + v.name() + "\n");
            }
        }
    }

    private static void startSimulation() {
        terminalOutput.append("\nStarting simulation...\n");
        simulateRandomEvents();
    }

    private static void simulateRandomEvents() {
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(random.nextInt(5000) + 5000);
                    if (random.nextBoolean()) {
                        attackComputer();
                    } else {
                        sendMessage();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void sendMessage() {
        Computer sender = network.get(random.nextInt(network.size()));
        Computer receiver = network.get(random.nextInt(network.size()));
        String protocol = "TCP";
        int length = random.nextInt(200) + 60;
        String info = "Message: 'Hello from " + sender.getIPv4() + "'";
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

        Object[] row = {messageCount++, time, sender.getIPv4(), receiver.getIPv4(), protocol, length, info};
        tableModel.addRow(row);
        terminalOutput.append("Message sent from " + sender.getIPv4() + " to " + receiver.getIPv4() + "\n");
    }

    private static void attackComputer() {
        Computer target = network.get(random.nextInt(network.size()));
        terminalOutput.append("\n[ALERT] Attack on " + target.getIPv4() + "!\n");
        //target.compromise();
        target.addVulnerability(Vulnerabilities.VulnerabilityType.OPEN_HTTP_PORT);//can add a function to randomise the vulnerability
        displayComputers();
    }
}
