package org.dishes.facade;

import java.util.List;

import org.dishes.commons.InvokeResult;
import org.dishes.facade.command.CreateActiveCommand;
import org.dishes.facade.dto.ActivityDTO;

public interface ActiveFacade {
	
	InvokeResult<String> createActive(CreateActiveCommand command);

	InvokeResult<List<ActivityDTO>> listActivity();

	InvokeResult<String> deleteActivityById(String id);
}
