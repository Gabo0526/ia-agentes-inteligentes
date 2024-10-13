package comunicacion.contenedor;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class Contenedor {

    static AgentContainer agentContainer;

    public static void configurarContenedor() throws StaleProxyException {
        jade.core.Runtime runtime = jade.core.Runtime.instance();

        Profile profile = new ProfileImpl(null, 1099, null);

        agentContainer = runtime.createMainContainer(profile);

        agregarAgentes();
    }

    private static void agregarAgentes() throws StaleProxyException {
        agentContainer.createNewAgent("Ag5", comunicacion.agentes.Agente5.class.getName(), null).start();
        agentContainer.createNewAgent("Ag4", comunicacion.agentes.Agente4.class.getName(), null).start();
        agentContainer.createNewAgent("Ag3", comunicacion.agentes.Agente3.class.getName(), null).start();
        agentContainer.createNewAgent("Ag2", comunicacion.agentes.Agente2.class.getName(), null).start();
        agentContainer.createNewAgent("Ag1", comunicacion.agentes.Agente1.class.getName(), null).start();
    }
}
