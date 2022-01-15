/*
 * 
 Autor dieser Klasse: Lukas Keßler
 *
 */

package edu.thi.java.auftragsaggregation;

import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import edu.thi.jpa.beans.Cart;

public class AddAuftragToListe implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Cart auftrag = (Cart) execution.getVariable("current_auftrag");

		@SuppressWarnings("unchecked")
		ArrayList<Cart> auftragsliste = (ArrayList<Cart>) execution.getVariable("auftragsliste");
		if (auftragsliste == null) {
			auftragsliste = new ArrayList<Cart>();
		}

		auftragsliste.add(auftrag);
		execution.setVariable("auftragsliste", auftragsliste);

		if (auftragsliste.size() == 1) {
			Long auftragscounter = 0L;
			for (int i = 0; i < auftragsliste.size(); i++) {
				Cart auftr = auftragsliste.get(i);
				auftragscounter += auftr.getMenge();
			}

			execution.setVariable("auftragscounter", auftragscounter);
		} else {
			Long auftragscounter = (Long) execution.getVariable("auftragscounter");
			for (int i = 0; i < auftragsliste.size(); i++) {
				Cart auftr = auftragsliste.get(i);
				auftragscounter += auftr.getMenge();
			}

			execution.setVariable("auftragscounter", auftragscounter);
		}

	}
}
