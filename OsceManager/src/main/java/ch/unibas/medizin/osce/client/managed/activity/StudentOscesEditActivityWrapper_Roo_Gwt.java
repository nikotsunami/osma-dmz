// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.activity;

import ch.unibas.medizin.osce.client.managed.activity.StudentOscesEditActivityWrapper.View;
import ch.unibas.medizin.osce.client.managed.request.ApplicationRequestFactory;
import ch.unibas.medizin.osce.client.managed.request.OsceProxy;
import ch.unibas.medizin.osce.client.managed.request.StudentOscesProxy;
import ch.unibas.medizin.osce.client.managed.request.StudentProxy;
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

public abstract class StudentOscesEditActivityWrapper_Roo_Gwt implements Activity, IsScaffoldMobileActivity {

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
        view.setStudentPickerValues(Collections.<StudentProxy>emptyList());
        requests.studentRequest().findStudentEntries(0, 50).with(ch.unibas.medizin.osce.client.managed.ui.StudentProxyRenderer.instance().getPaths()).fire(new Receiver<List<StudentProxy>>() {

            public void onSuccess(List<StudentProxy> response) {
                List<StudentProxy> values = new ArrayList<StudentProxy>();
                values.add(null);
                values.addAll(response);
                view.setStudentPickerValues(values);
            }
        });
        wrapped.start(display, eventBus);
    }

    public interface View_Roo_Gwt<V extends ch.unibas.medizin.osce.client.scaffold.place.ProxyEditView<ch.unibas.medizin.osce.client.managed.request.StudentOscesProxy, V>> extends ProxyEditView<StudentOscesProxy, V> {

        void setOscePickerValues(Collection<OsceProxy> values);

        void setStudentPickerValues(Collection<StudentProxy> values);
    }
}
