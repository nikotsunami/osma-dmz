// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.activity;

import ch.unibas.medizin.osce.client.managed.activity.LangSkillEditActivityWrapper.View;
import ch.unibas.medizin.osce.client.managed.request.ApplicationRequestFactory;
import ch.unibas.medizin.osce.client.managed.request.LangSkillProxy;
import ch.unibas.medizin.osce.client.managed.request.SpokenLanguageProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedPatientProxy;
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

public abstract class LangSkillEditActivityWrapper_Roo_Gwt implements Activity, IsScaffoldMobileActivity {

    protected Activity wrapped;

    protected View<?> view;

    protected ApplicationRequestFactory requests;

    @Override
    public void start(AcceptsOneWidget display, EventBus eventBus) {
        view.setStandardizedpatientPickerValues(Collections.<StandardizedPatientProxy>emptyList());
        requests.standardizedPatientRequest().findStandardizedPatientEntries(0, 50).with(ch.unibas.medizin.osce.client.managed.ui.StandardizedPatientProxyRenderer.instance().getPaths()).fire(new Receiver<List<StandardizedPatientProxy>>() {

            public void onSuccess(List<StandardizedPatientProxy> response) {
                List<StandardizedPatientProxy> values = new ArrayList<StandardizedPatientProxy>();
                values.add(null);
                values.addAll(response);
                view.setStandardizedpatientPickerValues(values);
            }
        });
        view.setSpokenlanguagePickerValues(Collections.<SpokenLanguageProxy>emptyList());
        requests.spokenLanguageRequest().findSpokenLanguageEntries(0, 50).with(ch.unibas.medizin.osce.client.managed.ui.SpokenLanguageProxyRenderer.instance().getPaths()).fire(new Receiver<List<SpokenLanguageProxy>>() {

            public void onSuccess(List<SpokenLanguageProxy> response) {
                List<SpokenLanguageProxy> values = new ArrayList<SpokenLanguageProxy>();
                values.add(null);
                values.addAll(response);
                view.setSpokenlanguagePickerValues(values);
            }
        });
        wrapped.start(display, eventBus);
    }

    public interface View_Roo_Gwt<V extends ch.unibas.medizin.osce.client.scaffold.place.ProxyEditView<ch.unibas.medizin.osce.client.managed.request.LangSkillProxy, V>> extends ProxyEditView<LangSkillProxy, V> {

        void setStandardizedpatientPickerValues(Collection<StandardizedPatientProxy> values);

        void setSpokenlanguagePickerValues(Collection<SpokenLanguageProxy> values);
    }
}
