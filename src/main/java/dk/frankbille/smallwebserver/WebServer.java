package dk.frankbille.smallwebserver;

import java.net.URL;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.resource.FileResource;

public class WebServer {

	private String documentRoot = System.getProperty("user.home");

	private int port = 8888;

	private Server server;

	public String getDocumentRoot() {
		return documentRoot;
	}

	public void setDocumentRoot(String documentRoot) {
		this.documentRoot = documentRoot;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isStarted() {
		return server != null && server.isRunning();
	}

	public void start() throws Exception {
		server = new Server(port);
		ResourceHandler handler = new ResourceHandler();
		handler.setBaseResource(new FileResource(new URL("file://"+documentRoot)));
		server.addHandler(handler);
		server.start();
	}

	public void stop() throws Exception {
		if (isStarted()) {
			server.stop();
			server = null;
		}
	}

}
