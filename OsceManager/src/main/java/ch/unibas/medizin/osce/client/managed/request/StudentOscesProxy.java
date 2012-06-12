// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.request;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyForName;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;

@RooGwtMirroredFrom("ch.unibas.medizin.osce.domain.StudentOsces")
@ProxyForName("ch.unibas.medizin.osce.domain.StudentOsces")
public interface StudentOscesProxy extends EntityProxy {

    abstract Boolean getIsEnrolled();

    abstract void setIsEnrolled(Boolean isEnrolled);

    abstract OsceProxy getOsce();

    abstract void setOsce(OsceProxy osce);

    abstract StudentProxy getStudent();

    abstract void setStudent(StudentProxy student);

    abstract Long getId();

    abstract void setId(Long id);

    abstract Integer getVersion();

    abstract void setVersion(Integer version);
}
