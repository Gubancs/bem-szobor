package hu.topclouders.bemszobor.beans;

import hu.topclouders.bemszobor.domain.Visitor;
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
	private VisitorService visitorService;

	public void visit() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String param = params.get("protestId");

		Long demonstrationId = Long.valueOf(param);
		try {
			if (visitor == null) {
				visitor = visitorService.createVisitor(demonstrationId);
			}

			if (!demonstrationId.equals(visitor.getDemonstration().getId())) {

				visitor = visitorService.registerVisitor(visitor,
						demonstrationId);
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Ivalid protest id format: "
					+ param);
		}

	}

	public void createDemonstrator() {
		visitor = visitorService.registerDemonstrator(visitor);
	}

	public void createCounterDemonstrator() {
		visitor = visitorService.registerCounterDemonstrator(visitor);
	}

	public Boolean getDemonstrator() {
		return visitor != null && visitor.getActionType().isDemonstrator();
	}

}
