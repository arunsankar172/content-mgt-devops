package com.mea.contentmanagement.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.mea.contentmanagement.model.Control;
import com.mea.contentmanagement.model.Page;
import com.mea.contentmanagement.model.Task;


@Mapper(componentModel = "spring")
public interface ContentManagementMapper {
	
	ContentManagementMapper INSTANCE = Mappers.getMapper(ContentManagementMapper.class);
	
	
	Page mapPageToPageModel(com.mea.contentmanagement.domain.Page pages);

	@IterableMapping(elementTargetType = Task.class)
	List<Task> mapTaskToTaskModal(List<com.mea.contentmanagement.domain.Task> tasksList);

	@Mappings({@Mapping(target = "createdDatetime", source = "createdDatetime", dateFormat = "dd-MMM-yyyy HH:mm:ss"),
		@Mapping(target = "updateddateTime", source = "updateddateTime", dateFormat = "dd-MMM-yyyy HH:mm:ss") })
	Task mapTaskToTaskModel(com.mea.contentmanagement.domain.Task task);

	@IterableMapping(elementTargetType = Page.class)
	List<Page> mapPageToPageModelList(List<com.mea.contentmanagement.domain.Page> pagesDomain);

	@Mappings({@Mapping(target = "createdDateTime", source = "createdDateTime", dateFormat = "dd-MMM-yyyy HH:mm:ss"),
		@Mapping(target = "updatedDateTime", source = "updatedDateTime", dateFormat = "dd-MMM-yyyy HH:mm:ss") })
	Control mapControlToControlModel(com.mea.contentmanagement.domain.Control controlDomain);
	
	@IterableMapping(elementTargetType = Page.class)
	List<Control> mapControlToControlModel(List<com.mea.contentmanagement.domain.Control> controlDomain);
	
	@Mappings({ @Mapping(target = "createdDateTime", ignore = true), @Mapping(target = "updatedDateTime", ignore = true),
	})
	com.mea.contentmanagement.domain.Control mapControlToControlDomain(Control control);

	
//	Control mapControlToControlModel(com.mea.contentmanagement.domain.Control controlDomain);

	@Mappings({ @Mapping(target = "createdDatetime", ignore = true), @Mapping(target = "updateddateTime", ignore = true),
		@Mapping(target = "decisiondateTime", ignore = true)})
	com.mea.contentmanagement.domain.Task mapTaskTotaskDomain(Task task);

	@Mappings({ @Mapping(target = "createdDateTime", ignore = true), @Mapping(target = "updatedDateTime", ignore = true),
//		@Mapping(target = "localeValueEn", ignore = true),@Mapping(target = "localeValueAr", ignore = true),
	})
	com.mea.contentmanagement.domain.Control mapUpdateControlToControlDomain(Control control);

}
