package src.Machines;

public class Message {
    public enum Protocol {
        TCP, UDP
    }

    String source;
    String destination;
    String protocol;
    String msg;

    public Message(String source, String destination, String protocol, String msg){
        this.source = source;
        this.destination = destination;
        this.protocol = protocol;
        this.msg = msg;
    }

    
}
