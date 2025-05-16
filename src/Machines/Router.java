package src.Machines;

import java.util.ArrayList;

public class Router {
    //for now, this just coordinates the generation of IP addresses
    private String IPv4_p1;//first 2 sections of IPv4 address. Can be varies for different networks in the future
    private String IPv4_p2;//computer's subnet
    private String IPv4_p3;//specific address in subnet -- assumed that one subnet covers range 0 to 255 and all subnets are uniform.
    private ArrayList<Integer> usedIPs = new ArrayList<Integer>();
    
    public Router(){
        IPv4_p1 = "192.168.";
        IPv4_p2 = "0.";
        IPv4_p3 = "0";
    }

    public void set_Subnet(int num){
        this.IPv4_p2 = String.valueOf(num);
    }//has no use right now -- for later implementation

    public String give_IP_addr(){ 
        int exactAddr = (int) (Math.random()*255 + 1);
        while (true){
            if (!usedIPs.contains(exactAddr)){
                usedIPs.add(exactAddr);
                return IPv4_p1+IPv4_p2+String.valueOf(exactAddr);
            }
            else{
                exactAddr = (int) Math.random()*255 + 1;
            }
        }
        
    }
    public String get_IP(){return IPv4_p1+IPv4_p2+IPv4_p3;}
}
