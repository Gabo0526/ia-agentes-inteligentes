package comunicacion;

import comunicacion.contenedor.Contenedor;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class Agentes {
    AgentContainer agentContainer;
    public static void main(String[] args) throws StaleProxyException {
        Contenedor.configurarContenedor();
    }
}
