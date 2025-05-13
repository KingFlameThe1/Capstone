package src.Commands;
import java.util.ArrayList;
import src.Machines.*;

public class ifconfig{
    public String arg; //argument
    ArrayList<Computer> computers;
    Computer computer;
    ArrayList<Computer> network;
    
    public ifconfig(){
        
    }

    public String all(){
        return("\tinet: "+network.get(0).getIPv4()+"\tnetmask 255.255.255.0\tbroadcast \n");
    }

    public static String ipv4(Computer computer){
        //return computer[1];
        //return this.ipv4;
        return(computer.getIPv4());
    }
    public static String mac(Computer computer){
        computer.getMAC(); 
    }
    public static String subnet(){
         
    }
    public static String getDefault(){
         
    }
    public static String help(){
        String discription = "WIP";
        return(discription);
    }

}

/*
                        terminalOutput.append("eth0:\tflags=4163<UP,BORADCASTING,RUNNING,MULTICAST>\tmtu\t1500\n");
                        terminalOutput.append("\tinet: "+network.get(0).getIPv4()+"\tnetmask 255.255.255.0\tbroadcast \n");
                        terminalOutput.append("\tinet6:\t\tprefixlen\t\tscopeid\n");
                        terminalOutput.append("\tphysical\t"+network.get(0).getMAC()+"\n");
*/

/*
System.out.println("Windows IP Configuration");
        System.out.println();
        System.out.println("Ethernet adapter Ethernet:");
        System.out.println("   Connection-specific DNS Suffix  . : " + machineName.toLowerCase() + ".local");
        System.out.println("   IPv4 Address. . . . . . . . . . . : " + ipAddress);
        System.out.println("   Subnet Mask . . . . . . . . . . . : " + subnetMask);
        System.out.println("   Default Gateway . . . . . . . . . : " + defaultGateway);
        System.out.println("--------------------------------------------------");
        System.out.println();
*/