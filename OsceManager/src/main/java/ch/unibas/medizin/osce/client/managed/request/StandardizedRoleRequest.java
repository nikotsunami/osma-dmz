// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.request;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;

@RooGwtMirroredFrom("ch.unibas.medizin.osce.domain.StandardizedRole")
@ServiceName("ch.unibas.medizin.osce.domain.StandardizedRole")
public interface StandardizedRoleRequest extends RequestContext {

    abstract InstanceRequest<ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy, java.lang.Void> persist();

    abstract InstanceRequest<ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy, java.lang.Void> remove();

    abstract Request<java.lang.Long> countStandardizedRoles();

    abstract Request<ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy> findStandardizedRole(Long id);

    abstract Request<java.util.List<ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy>> findAllStandardizedRoles();

    abstract Request<java.util.List<ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy>> findStandardizedRoleEntries(int firstResult, int maxResults);
}
