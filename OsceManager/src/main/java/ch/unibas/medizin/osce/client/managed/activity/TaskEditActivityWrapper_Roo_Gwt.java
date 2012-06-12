// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.activity;

import ch.unibas.medizin.osce.client.managed.activity.TaskEditActivityWrapper.View;
import ch.unibas.medizin.osce.client.managed.request.AdministratorProxy;
import ch.unibas.medizin.osce.client.managed.request.ApplicationRequestFactory;
import ch.unibas.medizin.osce.client.managed.request.TaskProxy;
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

public abstract class TaskEditActivityWrapper_Roo_Gwt implements Activity, IsScaffoldMobileActivity {

    protected Activity wrapped;

    protected View<?> view;

    protected ApplicationRequestFactory requests;

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        view.setOscePickerValues(Collections.<TaskProxy>emptyList());
        requests.taskRequest().findTaskEntries(0, 50).with(ch.unibas.medizin.osce.client.managed.ui.TaskProxyRenderer.instance().getPaths()).fire(new Receiver<List<TaskProxy>>() {

            public void onSuccess(List<TaskProxy> response) {
                List<TaskProxy> values = new ArrayList<TaskProxy>();
                values.add(null);
                values.addAll(response);
                view.setOscePickerValues(values);
            }
        });
        view.setAdministratorPickerValues(Collections.<AdministratorProxy>emptyList());
        requests.administratorRequest().findAdministratorEntries(0, 50).with(ch.unibas.medizin.osce.client.managed.ui.AdministratorProxyRenderer.instance().getPaths()).fire(new Receiver<List<AdministratorProxy>>() {

            public void onSuccess(List<AdministratorProxy> response) {
                List<AdministratorProxy> values = new ArrayList<AdministratorProxy>();
                values.add(null);
                values.addAll(response);
                view.setAdministratorPickerValues(values);
            }
        });
        wrapped.start(display, eventBus);
    }

    public interface View_Roo_Gwt<V extends ch.unibas.medizin.osce.client.scaffold.place.ProxyEditView<ch.unibas.medizin.osce.client.managed.request.TaskProxy, V>> extends ProxyEditView<TaskProxy, V> {

        void setOscePickerValues(Collection<TaskProxy> values);

        void setAdministratorPickerValues(Collection<AdministratorProxy> values);
    }
}
