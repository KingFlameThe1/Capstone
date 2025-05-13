package src.Commands;
import java.util.ArrayList;
import src.Machines.*;

public class ping{

    public void ping(){}

    public static String ping(Computer c1){
        int i = 0;
        String top = "PING " + c1 + " 56 data bytes \n";
        String ans = top;
        
        while(i<5) {
            String nums = "64 bytes from " + c1 + ": icmp_seq =" + i + "ttl=118" + (int)((Math.random() * 100)/4) + "." + (int)(Math.random() * 101) + " ms\n";
            ans += nums;
            i+=1;

        }
        
        return(ans);
    }


}

//int randomNum = (int)(Math.random() * 101);