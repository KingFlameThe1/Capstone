package src.ui;

import src.Machines.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private JFrame frame;
    private JTextField numMachinesField;
    private JTextField numVulnerabilitiesField;
    private JButton startButton;
    private JTextArea statusArea;

    public MainMenu() {
        // Initialize frame
        frame = new JFrame("Network Simulation");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Status area to show the progress
        statusArea = new JTextArea();
        statusArea.setEditable(false);
        statusArea.setBackground(Color.BLACK);
        statusArea.setForeground(Color.GREEN);
        statusArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(statusArea);

        // Input fields for number of machines and vulnerabilities
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel numMachinesLabel = new JLabel("Number of Machines:");
        numMachinesField = new JTextField();

        JLabel numVulnerabilitiesLabel = new JLabel("Number of Vulnerabilities:");
        numVulnerabilitiesField = new JTextField();

        inputPanel.add(numMachinesLabel);
        inputPanel.add(numMachinesField);
        inputPanel.add(numVulnerabilitiesLabel);
        inputPanel.add(numVulnerabilitiesField);

        // Start button
        startButton = new JButton("Start Simulation");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });

        // Add components to the frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);

        // Show the frame
        frame.setVisible(true);
    }

    // Method to handle simulation logic when the start button is clicked
    private void startSimulation() {
        String numMachinesText = numMachinesField.getText();
        String numVulnerabilitiesText = numVulnerabilitiesField.getText();

        try {
            int numMachines = Integer.parseInt(numMachinesText);
            int numVulnerabilities = Integer.parseInt(numVulnerabilitiesText);

            // Show the network generation process
            statusArea.append("Starting simulation with " + numMachines + " machines and " + numVulnerabilities + " vulnerabilities...\n");

            // Generate network and vulnerabilities here

            // logic for network generation
            ArrayList<Computer> network = new ArrayList<>();
            Router router = new Router();
            for (int i = 0; i < numMachines; i++) {
                network.add(new Computer(router.give_IP_addr()));
            }

            // For each machine, assign vulnerabilities (can be customized)
            for (int i = 0; i < numMachines; i++) {
                Computer computer = network.get(i);
                // Example: Assign random vulnerabilities
                for (int j = 0; j < numVulnerabilities; j++) {
                    computer.addVulnerability(Vulnerabilities.VulnerabilityType.values()[(int) (Math.random() * Vulnerabilities.VulnerabilityType.values().length)]);
                }
                statusArea.append("Machine " + (i + 1) + " has " + computer.getIPv4() + " with vulnerabilities: " + computer.getVulnerabilities() + "\n");
            }

            CommandLine cmd = new CommandLine(network);


        } catch (NumberFormatException ex) {
            statusArea.append("Invalid input! Please enter valid numbers.\n");
        }
    }

    public static void main(String[] args) {
        new MainMenu(); // Initialize the main menu UI
    }
}
