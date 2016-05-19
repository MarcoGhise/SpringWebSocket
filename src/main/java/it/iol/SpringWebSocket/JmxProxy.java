package it.iol.SpringWebSocket;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxProxy {

	private JMXConnector jmxc;
	
	public JmxProxy(String url) throws IOException, AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException
	{
		JMXServiceURL jmxUrl = new JMXServiceURL(url);
		jmxc = JMXConnectorFactory.connect(jmxUrl);		
	}
	
	public String getHeapMemoryUsage() throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException
	{
		Object o = jmxc.getMBeanServerConnection().getAttribute(
				new ObjectName("java.lang:type=Memory"), "HeapMemoryUsage");
		CompositeData cd = (CompositeData) o;
		
		return cd.get("used").toString();
	}
}
