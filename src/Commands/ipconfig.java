package src.Commands;
import src.Machines.*;
import java.util.ArrayList;

public class ipconfig{
    public String arg; //argument
    private String ipv4 = "";
    private String MAC = "";
    private String Subnet_Mask= "";
    private String Gateway = "";
    private ArrayList<Computer> computers;
    private Computer computer;

    public ipconfig(){
        
    }

    public void all(){
        
    }

    public String ipv4(Computer computer){
        this.computer = computer;
        return computer[1];
        //return this.ipv4; 
    }
    public String mac(){
        return this.MAC; 
    }
    public String subnet(){
        return this.Subnet_Mask; 
    }
    public String getDefault(){
        return this.Gateway; 
    }

}

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