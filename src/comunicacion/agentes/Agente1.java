package comunicacion.agentes;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import comunicacion.utilidades.Mensaje;

import java.util.UUID;

public class Agente1 extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    class Comportamiento extends CyclicBehaviour {
        @Override
        public void action() { // Nunca llamar este metodo
            Mensaje.sendMensaje(ACLMessage.INFORM, "Ag2", getAgent(), UUID.randomUUID().toString(), String.format("Hola, Ag2, soy el agente %s, necesito las credenciales para conectarme a la base de datos", getAgent().getName()), null);

            ACLMessage aclMessage = blockingReceive();

            Mensaje.recibirMensaje(aclMessage);

            System.out.println("He recibido las credenciales");
            doDelete();
        }
    }
}
