package org.eclipse.nebula.cwt.v;

import org.eclipse.nebula.cwt.test.VTestCase;
import org.eclipse.nebula.cwt.test.VTestComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class DisposalTests extends VTestCase {

	private VTestComposite comp;
	
	@Override
	protected void setUp() throws Exception {
		comp = new VTestComposite(getShell(), SWT.BORDER);
	}
	
	public void testDisposeWithListeners() throws Exception {
		asyncExec(new Runnable() {
			public void run() {
				comp.addListener(SWT.Dispose, new Listener() {
					public void handleEvent(Event event) {
						assertFalse(comp.isDisposed());
					}
				});
			}
		});
		
		asyncExec(new Runnable() {
			public void run() {
				getShell().dispose();
			}
		});
		
		while(getDisplay() != null && !getDisplay().isDisposed()) {
			Thread.sleep(100);
		}
	}
	
}
