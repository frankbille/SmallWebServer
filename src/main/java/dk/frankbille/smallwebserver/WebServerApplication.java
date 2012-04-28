package dk.frankbille.smallwebserver;

import org.eclipse.swt.widgets.Display;

public class WebServerApplication {

	protected ConfigurationDialog shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WebServerApplication window = new WebServerApplication();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		shell = new ConfigurationDialog(display, new WebServer());
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
