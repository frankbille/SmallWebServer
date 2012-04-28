package dk.frankbille.smallwebserver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;

public class ConfigurationDialog extends Shell {

	protected WebServer result;
	private Text documentRootField;
	private Button showDocumentRootChooserButton;
	private Spinner portField;
	private Button startStopButton;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ConfigurationDialog(Display display, WebServer webServer) {
		super(display);
		setImage(SWTResourceManager.getImage(ConfigurationDialog.class, "/dk/frankbille/smallwebserver/3globe_128.png")); //$NON-NLS-1$
		this.result = webServer;
		createContents();
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		setText(Messages.getString("ConfigurationDialog.smallWebServer")); //$NON-NLS-1$
		setSize(500, 175);

		Label documentRootLabel = new Label(this, SWT.NONE);
		documentRootLabel.setText(Messages.getString("ConfigurationDialog.documentRoot")); //$NON-NLS-1$

		documentRootField = new Text(this, SWT.BORDER);
		documentRootField.setText(result.getDocumentRoot());
		documentRootField.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				result.setDocumentRoot(documentRootField.getText());
			}
		});

		Label portLabel = new Label(this, SWT.NONE);
		portLabel.setText(Messages.getString("ConfigurationDialog.port")); //$NON-NLS-1$

		portField = new Spinner(this, SWT.BORDER);
		portField.setMaximum(65536);
		portField.setMinimum(1);
		portField.setSelection(result.getPort());
		portField.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				result.setPort(portField.getSelection());
			}
		});

		startStopButton = new Button(this, SWT.NONE);
		startStopButton.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent event) {
				startStopButton.setImage(SWTResourceManager.getImage(ConfigurationDialog.class, "/dk/frankbille/smallwebserver/control_"+(result.isStarted()?"stop":"play")+"_blue.png")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}

			@Override
			public void mouseExit(MouseEvent event) {
				startStopButton.setImage(SWTResourceManager.getImage(ConfigurationDialog.class, "/dk/frankbille/smallwebserver/control_"+(result.isStarted()?"stop":"play")+".png")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			}
		});
		startStopButton.setImage(SWTResourceManager.getImage(ConfigurationDialog.class, "/dk/frankbille/smallwebserver/control_play.png")); //$NON-NLS-1$
		startStopButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				try {
					if (result.isStarted()) {
						result.stop();
					} else {
						result.start();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				updateComponents();
			}
		});
		startStopButton.setText(Messages.getString("ConfigurationDialog.start")); //$NON-NLS-1$

		showDocumentRootChooserButton = new Button(this, SWT.NONE);
		showDocumentRootChooserButton.setText("..."); //$NON-NLS-1$
		GroupLayout gl_shlSmallWebServer = new GroupLayout(this);
		gl_shlSmallWebServer.setHorizontalGroup(
			gl_shlSmallWebServer.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_shlSmallWebServer.createSequentialGroup()
					.addContainerGap()
					.add(gl_shlSmallWebServer.createParallelGroup(GroupLayout.LEADING)
						.add(gl_shlSmallWebServer.createSequentialGroup()
							.add(gl_shlSmallWebServer.createParallelGroup(GroupLayout.LEADING)
								.add(documentRootLabel)
								.add(portLabel))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(gl_shlSmallWebServer.createParallelGroup(GroupLayout.TRAILING)
								.add(gl_shlSmallWebServer.createSequentialGroup()
									.add(documentRootField, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(showDocumentRootChooserButton))
								.add(portField, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)))
						.add(GroupLayout.TRAILING, startStopButton))
					.addContainerGap())
		);
		gl_shlSmallWebServer.setVerticalGroup(
			gl_shlSmallWebServer.createParallelGroup(GroupLayout.LEADING)
				.add(gl_shlSmallWebServer.createSequentialGroup()
					.addContainerGap()
					.add(gl_shlSmallWebServer.createParallelGroup(GroupLayout.BASELINE)
						.add(documentRootLabel)
						.add(documentRootField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(showDocumentRootChooserButton))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_shlSmallWebServer.createParallelGroup(GroupLayout.BASELINE)
						.add(portLabel)
						.add(portField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED, 70, Short.MAX_VALUE)
					.add(startStopButton)
					.addContainerGap())
		);
		this.setLayout(gl_shlSmallWebServer);

		updateComponents();
	}

	private void updateComponents() {
		if (result.isStarted()) {
			startStopButton.setText(Messages.getString("ConfigurationDialog.stop")); //$NON-NLS-1$
			startStopButton.setImage(SWTResourceManager.getImage(ConfigurationDialog.class, "/dk/frankbille/smallwebserver/control_stop.png")); //$NON-NLS-1$
		} else {
			startStopButton.setText(Messages.getString("ConfigurationDialog.start")); //$NON-NLS-1$
			startStopButton.setImage(SWTResourceManager.getImage(ConfigurationDialog.class, "/dk/frankbille/smallwebserver/control_play.png")); //$NON-NLS-1$
		}
		documentRootField.setEnabled(false == result.isStarted());
		portField.setEnabled(false == result.isStarted());
		showDocumentRootChooserButton.setEnabled(false == result.isStarted());
	}

	@Override
	protected void checkSubclass() {
	}
}
