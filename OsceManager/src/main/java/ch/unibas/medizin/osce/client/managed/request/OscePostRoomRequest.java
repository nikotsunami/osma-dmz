// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.request;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.ServiceName;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;

@RooGwtMirroredFrom("ch.unibas.medizin.osce.domain.OscePostRoom")
@ServiceName("ch.unibas.medizin.osce.domain.OscePostRoom")
public interface OscePostRoomRequest extends RequestContext {

    abstract InstanceRequest<ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy, java.lang.Void> persist();

    abstract InstanceRequest<ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy, java.lang.Void> remove();

    abstract Request<java.lang.Long> countOscePostRooms();

    abstract Request<ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy> findOscePostRoom(Long id);

    abstract Request<java.util.List<ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy>> findAllOscePostRooms();

    abstract Request<java.util.List<ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy>> findOscePostRoomEntries(int firstResult, int maxResults);
}
