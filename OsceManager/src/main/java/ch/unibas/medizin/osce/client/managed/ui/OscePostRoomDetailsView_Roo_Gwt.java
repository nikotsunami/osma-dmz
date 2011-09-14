// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.AssignmentProxy;
import ch.unibas.medizin.osce.client.managed.request.CourseProxy;
import ch.unibas.medizin.osce.client.managed.request.OscePostProxy;
import ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy;
import ch.unibas.medizin.osce.client.managed.request.RoomProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyListView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.Set;

public abstract class OscePostRoomDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<OscePostRoomProxy> {

    @UiField
    SpanElement id;

    @UiField
    SpanElement version;

    @UiField
    SpanElement room;

    @UiField
    SpanElement oscePost;

    @UiField
    SpanElement course;

    @UiField
    SpanElement assignments;

    OscePostRoomProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(OscePostRoomProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        room.setInnerText(proxy.getRoom() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.RoomProxyRenderer.instance().render(proxy.getRoom()));
        oscePost.setInnerText(proxy.getOscePost() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.OscePostProxyRenderer.instance().render(proxy.getOscePost()));
        course.setInnerText(proxy.getCourse() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.CourseProxyRenderer.instance().render(proxy.getCourse()));
        assignments.setInnerText(proxy.getAssignments() == null ? "" : ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.AssignmentProxyRenderer.instance()).render(proxy.getAssignments()));
        displayRenderer.setInnerText(OscePostRoomProxyRenderer.instance().render(proxy));
    }
}
