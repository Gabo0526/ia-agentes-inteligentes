import container.Container;
import jade.wrapper.StaleProxyException;

public class Main {

    public static void main(String[] args) throws StaleProxyException {
        Container c = new Container();
        c.configureContainer();
    }
}
