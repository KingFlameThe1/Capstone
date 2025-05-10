package src.Machines;
//import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//import java.util.ArrayList;
//import java.util.random.*;
//import Math;


public class Computer{

    private String ipv4 = "";
    private String MAC = "";
    private String Subnet_Mask= "";
    private String Gateway = "";
    private ArrayList<Integer> ports = new ArrayList<Integer>();
    //private ArrayList<String> vulns = new ArrayList<String>();//vulnerabilities will be handeled in a different class for easy coordination
    private Vulnerabilities vulns;

    public Computer(String IPv4_addr) {
        Random rand = new Random();
        ipv4 = IPv4_addr;
        MAC = genMac(rand);
        Subnet_Mask = "255.255.255.0";//can be changed later to simulate multiple subnets 
        Gateway = "192.168.0.1";//can be made into a variable to allow for simulation of multiple networks the user can attempt to access (maybe using a simulated proxy)
        openPorts();
        vulns = new Vulnerabilities();
    }

    private String genMac(Random rand){
        StringBuilder macAddress = new StringBuilder();
        
        for (int i = 0; i < 6; i++) {
            int byteValue = rand.nextInt(256);  // Generates a random number between 0 and 255
            macAddress.append(String.format("%02X", byteValue));  // Convert to two-digit hex
            if (i < 5) {
                macAddress.append(":");  // Add colon separator between bytes
            }
        }
        return macAddress.toString();
    }
    private void openPorts(){
        Integer[] availPorts = {20, 21, 22, 25, 53, 443};
        Collections.addAll(this.ports, availPorts);
        //generate open ports in this function
    }
    public void addOpenPort(int port){
        if (!ports.contains(port)) {
            ports.add(port);
        }
    }

    public String getIPv4(){
        return this.ipv4; 
    }
    public String getMAC(){
        return this.MAC; 
    }
    public String getSubnet(){
        return this.Subnet_Mask; 
    }
    public String getDefault(){
        return this.Gateway; 
    }

    public void addVulnerability(Vulnerabilities.VulnerabilityType type) {
        vulns.add(type);
    }

    public boolean isVulnerableTo(Vulnerabilities.VulnerabilityType type) {
        return vulns.has(type);
    }

}