package it.iol.SpringWebSocket;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller("monitorController")
public class MonitorController {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private JmxProxy proxy;

	public void fireHeapMemoryUsage() throws MessagingException,
			AttributeNotFoundException, InstanceNotFoundException,
			MalformedObjectNameException, MBeanException, ReflectionException,
			IOException {

		System.out.println("Fire!");

		this.template.convertAndSend("/topic/HeapMemoryUsage",
				new JvmAttribute(proxy.getHeapMemoryUsage()));
	}
}