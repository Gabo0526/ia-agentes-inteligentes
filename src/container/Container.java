package container;

import agent.*;
import jade.util.Logger;
import jade.wrapper.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;

import java.util.logging.Level;

public class Container {
    AgentContainer agentContainer;

    public void configureContainer() throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        runtime.setCloseVM(true);
        Profile profile = new ProfileImpl(null, 1099, null);
        agentContainer = runtime.createMainContainer(profile);
        addAgents();
    }

    public void addAgents() throws StaleProxyException {
        agentContainer.createNewAgent("Ag5", Agent5.class.getName(), null).start();
        agentContainer.createNewAgent("Ag4", Agent4.class.getName(), null).start();
        agentContainer.createNewAgent("Ag3", Agent3.class.getName(), new Object[]{this}).start();
        agentContainer.createNewAgent("Ag2", Agent2.class.getName(), null).start();
        agentContainer.createNewAgent("Ag1", Agent1.class.getName(), null).start();
    }

    public void addChild(String nickname, Object[] knowledge) {
        try {
            agentContainer.createNewAgent(nickname, ChildAgent.class.getName(), knowledge).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Container.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
