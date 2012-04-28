package dk.frankbille.smallwebserver;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class TestWebServer {

	@Test
	public void startStopServer() throws Exception {
		File docRoot = new File("src/test/resources/docroot1");
		WebServer webServer = new WebServer();
		webServer.setDocumentRoot(docRoot.getAbsolutePath());
		webServer.setPort(58888);
		Assert.assertFalse(webServer.isStarted());
		webServer.start();
		Assert.assertTrue(webServer.isStarted());
		webServer.stop();
		Assert.assertFalse(webServer.isStarted());
	}

}
