package src.Machines;

public class Message {
    public enum Protocol {
        TCP, UDP
    }

    Computer source;
    Computer destination;
    Protocol protocol;
    String msg;
    int stage; //current stage of TCP handshake

    public Message(Computer source, Computer destination, Protocol protocol, String msg){
        this.source = source;
        this.destination = destination;
        this.protocol = protocol;
        this.msg = msg;
        stage = 0;
    }

    public Computer getSource() { return source; }
    public Computer getDestination() { return destination; }
    public Protocol getProtocol() { return protocol; }
    public String getMessage() { return msg; }
    public int getStage() { return stage; }

    public void nextStage() {
        if (protocol == Protocol.TCP && stage < 3) {
            stage++; // SYN -> SYN-ACK -> ACK
        } else if (protocol == Protocol.UDP) {
            stage = 1; // One-step delivery
        }
    }

    public void communicate(){
        if (protocol == Protocol.TCP && !isComplete()){
            //logic for TCP handshake
        }
        else if (protocol == Protocol.TCP && !isComplete()){
            //send UDP message
        }
    }

    public boolean isComplete() {
        return (protocol == Protocol.TCP && stage >= 3) || (protocol == Protocol.UDP && stage >= 1);
    }
}
