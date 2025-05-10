package src.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.Machines.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Command_Line {
    private static ArrayList<Computer> network; // Network of computers
    private static JTextArea terminalOutput;   // Terminal output
    private static JPanel computerPanel; // Panel to display the network computers
    private static JFrame frame; // Main window frame
    private static List<JButton> computerButtons; // Store buttons for computers
    private static Random random; // For simulating random events

    public static void main(String[] args) {
        // Initialize random event generator
        random = new Random();

        // Initialize main frame
        frame = new JFrame("Network Simulation");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Terminal Output area (Simulation logs)
        terminalOutput = new JTextArea();
        terminalOutput.setEditable(false);
        terminalOutput.setBackground(Color.BLACK);
        terminalOutput.setForeground(Color.GREEN);
        terminalOutput.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(terminalOutput);

        // Command Input Field
        JTextField commandInput = new JTextField();
        commandInput.setBackground(Color.BLACK);
        commandInput.setForeground(Color.WHITE);
        commandInput.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Panel to display computers in the network
        computerPanel = new JPanel();
        computerPanel.setLayout(new GridLayout(0, 1)); // Vertical list of computers
        JScrollPane computerScrollPane = new JScrollPane(computerPanel);

        // Button to Start Simulation
        JButton startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });

        // Button to Clear Terminal Output
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminalOutput.setText(""); // Clear terminal output
            }
        });

        // Button to Send Message Between Computers
        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Button to Attack a Computer
        JButton attackButton = new JButton("Attack Computer");
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attackComputer();
            }
        });

        // Add components to frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(commandInput, BorderLayout.SOUTH);
        frame.add(computerScrollPane, BorderLayout.WEST);

        JPanel controlPanel = new JPanel();
        controlPanel.add(startSimulationButton);
        controlPanel.add(clearButton);
        controlPanel.add(sendMessageButton);
        controlPanel.add(attackButton);
        frame.add(controlPanel, BorderLayout.NORTH);

        // Add action listener to process commands
        commandInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = commandInput.getText();
                terminalOutput.append("> " + command + "\n");
                commandInput.setText(""); // Clear input field

                // Process commands
                switch (command.toLowerCase()) {
                    case "help":
                        terminalOutput.append("Available commands:\n");
                        terminalOutput.append(" - help: Show available commands\n");
                        terminalOutput.append(" - clear: Clear the terminal\n");
                        terminalOutput.append(" - start: Start the network simulation\n");
                        terminalOutput.append(" - exit: Close the application\n");
                        terminalOutput.append(" - attack [IP]: Attack a computer by IP\n");
                        terminalOutput.append(" - send [message]: Send a message across the network\n");
                        break;

                    case "exit":
                        System.exit(0); // Exit application
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

    // Set the network for this command line interface
    public static void setNetwork(ArrayList<Computer> network) {
        Command_Line.network = network;
        displayComputers(); // Update the UI with computers in the network
    }

    // Display computers in the network in the left panel
    private static void displayComputers() {
        computerPanel.removeAll(); // Clear existing components
        computerButtons = new ArrayList<>();
        for (Computer computer : network) {
            JButton computerButton = new JButton("IP: " + computer.getIPv4() + " Status: " + (computer.isCompromised() ? "Compromised" : "Secure"));
            computerButton.setBackground(computer.isCompromised() ? Color.RED : Color.GREEN);
            computerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showComputerDetails(computer);
                }
            });
            computerButtons.add(computerButton);
            computerPanel.add(computerButton);
        }
        computerPanel.revalidate(); // Refresh the panel
        computerPanel.repaint();
    }

    // Show details of a computer when clicked
    private static void showComputerDetails(Computer computer) {
        terminalOutput.append("\nSelected Computer: " + computer.getIPv4() + "\n");
        terminalOutput.append("Vulnerabilities:\n");
        for (Vulnerabilities.VulnerabilityType v : Vulnerabilities.VulnerabilityType.values()) {
            if (computer.isVulnerableTo(v)) {
                terminalOutput.append("- " + v.name() + "\n");
            }
        }
    }

    // Start simulation (trigger network interaction)
    private static void startSimulation() {
        terminalOutput.append("\nStarting network simulation...\n");
        for (Computer computer : network) {
            terminalOutput.append("Scanning computer: " + computer.getIPv4() + "\n");
            // Example vulnerability check
            if (computer.isVulnerableTo(Vulnerabilities.VulnerabilityType.OPEN_HTTP_PORT)) {
                terminalOutput.append("Vulnerability found: Open HTTP Port on " + computer.getIPv4() + "\n");
            }
        }
        // Simulate random network events
        simulateRandomEvents();
    }

    // Simulate random events in the network
    private static void simulateRandomEvents() {
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(random.nextInt(5000) + 5000); // Random delay (between 5 to 10 seconds)
                    if (random.nextBoolean()) {
                        // Random event: Attack
                        Computer target = network.get(random.nextInt(network.size()));
                        terminalOutput.append("\nRandom Event: Attack on " + target.getIPv4() + "!\n");
                        target.compromise();
                    } else {
                        // Random event: Send Message
                        String message = "Random message from network!";
                        terminalOutput.append("\nRandom Event: Sending message: " + message + "\n");
                        sendMessage();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Send a message between computers
    private static void sendMessage() {
        Computer sender = network.get(random.nextInt(network.size()));
        Computer receiver = network.get(random.nextInt(network.size()));
        terminalOutput.append("\nSending message from " + sender.getIPv4() + " to " + receiver.getIPv4() + "...\n");
        // Example message content
        terminalOutput.append("Message content: 'Network status update: Everything looks normal.'\n");
    }

    // Attack a computer in the network
    private static void attackComputer() {
        Computer target = network.get(random.nextInt(network.size()));
        terminalOutput.append("\nAttacking computer: " + target.getIPv4() + "...\n");
        target.compromise(); // Simulate a compromise attack
    }
}
