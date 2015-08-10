package com.jwyming.common.web;

import javax.el.ELContext;
import javax.el.ELException;
import javax.faces.context.FacesContext;

import org.springframework.context.MessageSource;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

public class SpringMessageSourceAwareFacesELResolver extends
        SpringBeanFacesELResolver {

    @Override
    public Object getValue(ELContext elContext, Object base, Object property)
            throws ELException {
        if ((base instanceof MessageSource) && (property instanceof String)) {
            MessageSource messageSource = (MessageSource) base;
            elContext.setPropertyResolved(true);
            return messageSource
                    .getMessage(property.toString(), null, null, FacesContext
                            .getCurrentInstance().getViewRoot().getLocale());
        } else {
            return super.getValue(elContext, base, property);
        }
    }
}
