package sjq.bitcoin.command;

public class Command {

    private Command(){
        
    }

    public static Command parse() {
        return new Command();
    }
}
