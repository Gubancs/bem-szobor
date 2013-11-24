package hu.topclouders.bemszobor.beans;

import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class VisitorMB implements Serializable {

	private static final long serialVersionUID = -1971724386429818793L;

	private Long protestId;

	private Visitor visitor;

	public void visit() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String protestId = params.get("protestId");

		this.protestId = Long.valueOf(protestId);

		if (visitor != null) {
			System.out.println("visitor: " + visitor.getActionType());
		} else {

			visitor = new Visitor();
			visitor.setActionType(ActionType.VISITOR);
			System.out.println("new visitor created: "
					+ visitor.getActionType());
		}
	}

	public void createDemonstrator() {

		visitor.setActionType(ActionType.DEMONSTRATOR);
		System.out.println("demonstrator created");
	}

	public void createCounterDemonstrator() {

		visitor.setActionType(ActionType.COUNTER_DEMONSTRATOR);

		System.out.println("counter demonstrator created");
	}

	public boolean isDemonstrator() {
		return visitor != null
				&& visitor.getActionType().equals(ActionType.DEMONSTRATOR);
	}

	public boolean isCounterDemonstrator() {
		return visitor != null
				&& visitor.getActionType().equals(
						ActionType.COUNTER_DEMONSTRATOR);
	}
}
