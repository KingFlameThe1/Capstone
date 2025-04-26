package src.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Command_Line{
    public static void main(String[] args) {
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
                    case "help":
                        terminalOutput.append("Available commands:\n");
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