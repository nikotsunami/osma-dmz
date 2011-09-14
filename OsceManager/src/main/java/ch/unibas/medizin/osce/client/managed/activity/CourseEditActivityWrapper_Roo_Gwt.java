// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.activity;

import ch.unibas.medizin.osce.client.managed.activity.CourseEditActivityWrapper.View;
import ch.unibas.medizin.osce.client.managed.request.ApplicationRequestFactory;
import ch.unibas.medizin.osce.client.managed.request.CourseProxy;
import ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy;
import ch.unibas.medizin.osce.client.managed.request.OsceProxy;
import ch.unibas.medizin.osce.client.managed.ui.OscePostRoomSetEditor;
import ch.unibas.medizin.osce.client.scaffold.activity.IsScaffoldMobileActivity;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyEditView;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyListPlace;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class CourseEditActivityWrapper_Roo_Gwt implements Activity, IsScaffoldMobileActivity {

    protected Activity wrapped;

    protected View<?> view;

    protected ApplicationRequestFactory requests;

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        view.setOscePickerValues(Collections.<OsceProxy>emptyList());
        requests.osceRequest().findOsceEntries(0, 50).with(ch.unibas.medizin.osce.client.managed.ui.OsceProxyRenderer.instance().getPaths()).fire(new Receiver<List<OsceProxy>>() {

            public void onSuccess(List<OsceProxy> response) {
                List<OsceProxy> values = new ArrayList<OsceProxy>();
                values.add(null);
                values.addAll(response);
                view.setOscePickerValues(values);
            }
        });
        view.setOscePostRoomsPickerValues(Collections.<OscePostRoomProxy>emptyList());
        requests.oscePostRoomRequest().findOscePostRoomEntries(0, 50).with(ch.unibas.medizin.osce.client.managed.ui.OscePostRoomProxyRenderer.instance().getPaths()).fire(new Receiver<List<OscePostRoomProxy>>() {

            public void onSuccess(List<OscePostRoomProxy> response) {
                List<OscePostRoomProxy> values = new ArrayList<OscePostRoomProxy>();
                values.add(null);
                values.addAll(response);
                view.setOscePostRoomsPickerValues(values);
            }
        });
        wrapped.start(display, eventBus);
    }

    public interface View_Roo_Gwt<V extends ch.unibas.medizin.osce.client.scaffold.place.ProxyEditView<ch.unibas.medizin.osce.client.managed.request.CourseProxy, V>> extends ProxyEditView<CourseProxy, V> {

        void setOscePickerValues(Collection<OsceProxy> values);

        void setOscePostRoomsPickerValues(Collection<OscePostRoomProxy> values);
    }
}
