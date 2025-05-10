package src.Machines;

public class Router {
    private String IPv4_p1;
    private String IPv4_p2;

    public Router() {
        IPv4_p1 = "192.168.";
        IPv4_p2 = "0.";
    }

    public void setSubnet(int num) {
        this.IPv4_p2 = num + ".";
    }

    public String give_IP_addr() {
        int hostPart = (int)(Math.random() * 254 + 1); // Avoid .0 and .255
        return IPv4_p1 + IPv4_p2 + hostPart;
    }

    public String get_IP() {
        return IPv4_p1 + IPv4_p2 + "1";
    }
}
