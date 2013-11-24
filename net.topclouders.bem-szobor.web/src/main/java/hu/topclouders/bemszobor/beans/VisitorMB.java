package hu.topclouders.bemszobor.beans;

import hu.topclouders.bemszobor.domain.Visitor;
import hu.topclouders.bemszobor.enums.ActionType;
import hu.topclouders.bemszobor.service.VisitorService;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@SessionScoped
public class VisitorMB implements Serializable {

	private static final long serialVersionUID = -1971724386429818793L;

	private Visitor visitor;

	@Autowired
	private VisitorService registrationService;

	public void visit() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String param = params.get("protestId");

		if (visitor == null) {
			visitor = registrationService.createVisitor(Long.valueOf(param));
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

	public Boolean getDemonstrator() {
		return visitor != null
				&& visitor.getActionType().equals(ActionType.DEMONSTRATOR);
	}

	public Boolean getCounterDemonstrator() {
		return visitor != null
				&& visitor.getActionType().equals(
						ActionType.COUNTER_DEMONSTRATOR);
	}
}
