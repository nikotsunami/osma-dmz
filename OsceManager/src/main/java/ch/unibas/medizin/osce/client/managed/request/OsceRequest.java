// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.request;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;

@RooGwtMirroredFrom("ch.unibas.medizin.osce.domain.Osce")
@ServiceName("ch.unibas.medizin.osce.domain.Osce")
public interface OsceRequest extends RequestContext {

    abstract InstanceRequest<ch.unibas.medizin.osce.client.managed.request.OsceProxy, java.lang.Void> persist();

    abstract InstanceRequest<ch.unibas.medizin.osce.client.managed.request.OsceProxy, java.lang.Void> remove();

    abstract Request<java.lang.Long> countOsces();

    abstract Request<java.util.List<ch.unibas.medizin.osce.client.managed.request.OsceProxy>> findAllOsces();

    abstract Request<ch.unibas.medizin.osce.client.managed.request.OsceProxy> findOsce(Long id);

    abstract Request<java.util.List<ch.unibas.medizin.osce.client.managed.request.OsceProxy>> findOsceEntries(int firstResult, int maxResults);
}
