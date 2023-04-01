package com.joalvarez.wharehousecontrol.general.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.wharehousecontrol.general.data.dto.BaseDTO;
import org.springframework.core.GenericTypeResolver;

public class BaseMapper<DTO extends BaseDTO, ENT> {

    protected Class<DTO> dtoClass;
    protected Class<ENT> domainClass;
    protected ObjectMapper mapper;

    public BaseMapper(ObjectMapper mapper) {
        Class<?>[] arguments = GenericTypeResolver.resolveTypeArguments(this.getClass(), BaseMapper.class);
        if (arguments != null && arguments.length > 0) {
            this.dtoClass = (Class<DTO>) arguments[0];
            this.domainClass = (Class<ENT>) arguments[1];
        }
        this.mapper = mapper;
    }

    public ENT toEntity(DTO dto) {
        return this.mapper.convertValue(dto, this.domainClass);
    }

    public DTO toDTO(ENT ent) {
        return this.mapper.convertValue(ent, this.dtoClass);
    }

}
