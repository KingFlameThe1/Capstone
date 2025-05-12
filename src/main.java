package src;
import src.Machines.*;
import src.ui.*;
import java.util.ArrayList;

public class main {
    public main(){
        MainMenu menu = new MainMenu(); // Place holder to start main menu

        /*// Get the network size from the main menu (assuming MainMenu handles this input)
        int networkSize = menu.getNetSize();

        // Initialize network and generate computers
        ArrayList<Computer> network = new ArrayList<Computer>();
        genNetwork(network, networkSize);

        // Start the simulation by interacting with the network
        startSimulation(network);*/
    }

    // Generate the network of computers
    void genNetwork(ArrayList<Computer> network, int size){
        Router router = new Router();
        for (int i = 0; i < size; i++) {
            Computer computer = new Computer(router.give_IP_addr());
            network.add(computer);  // Add computer to the network
        }
    }

     // Start the network simulation
    void startSimulation(ArrayList<Computer> network) {
        // Example of vulnerability scanning and attack simulation
        for (Computer computer : network) {
            System.out.println("Scanning computer with IP: " + computer.getIPv4());

            // Simulate vulnerability checking
            if (computer.isVulnerableTo(Vulnerabilities.VulnerabilityType.OPEN_HTTP_PORT)) {
                System.out.println("Vulnerability found on " + computer.getIPv4() + ": Open HTTP Port");
                // You could simulate an attack here, like exploiting this vulnerability.
            }
            // Example of communication (this could be extended for actual message passing or attack)
            //Message message = new Message("Attacker_IP", computer.getIPv4(), Message.Protocol.TCP.name(), "Hello, computer!");
            //System.out.println("Message sent to " + computer.getIPv4() + ": " + message.getMessage());
        }
    }

    public static void main(String[] args) {
        new main();  // Start the simulation when the application runs
    }
}
