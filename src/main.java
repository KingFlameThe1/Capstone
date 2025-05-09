package src;
import src.Machines.*;
import java.util.ArrayList;

public class main {
    public main(){
        MainMenu menu = new MainMenu();//place holder to start main menu

        ArrayList<Computer> network = new ArrayList<Computer>();
        genNetwork(network, menu.getNetSize());


    }

    void genNetwork(ArrayList<Computer> network, int size){
        int i;
        Router router = new Router();
        for(i = 0; i < size; i++){
            Computer computer = new Computer(router.give_IP_addr());
        }

    }
}
