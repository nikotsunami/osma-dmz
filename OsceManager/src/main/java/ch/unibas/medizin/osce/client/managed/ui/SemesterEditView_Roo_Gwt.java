// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.activity.SemesterEditActivityWrapper;
import ch.unibas.medizin.osce.client.managed.activity.SemesterEditActivityWrapper.View;
import ch.unibas.medizin.osce.client.managed.request.AdministratorProxy;
import ch.unibas.medizin.osce.client.managed.request.OsceProxy;
import ch.unibas.medizin.osce.client.managed.request.PatientInSemesterProxy;
import ch.unibas.medizin.osce.client.managed.request.SemesterProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyEditView;
import ch.unibas.medizin.osce.client.scaffold.ui.*;
import ch.unibas.medizin.osce.shared.Semesters;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.RequestFactory;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public abstract class SemesterEditView_Roo_Gwt extends Composite implements View<SemesterEditView> {

    @UiField(provided = true)
    ValueListBox<Semesters> semester = new ValueListBox<Semesters>(new AbstractRenderer<ch.unibas.medizin.osce.shared.Semesters>() {

        public String render(ch.unibas.medizin.osce.shared.Semesters obj) {
            return obj == null ? "" : String.valueOf(obj);
        }
    });

    @UiField
    IntegerBox calYear;

    @UiField
    AdministratorSetEditor administrators;

    @UiField
    OsceSetEditor osces;

    @UiField
    PatientInSemesterSetEditor patientsInSemester;

    public void setAdministratorsPickerValues(Collection<AdministratorProxy> values) {
        administrators.setAcceptableValues(values);
    }

    public void setSemesterPickerValues(Collection<Semesters> values) {
        semester.setAcceptableValues(values);
    }

    public void setPatientsInSemesterPickerValues(Collection<PatientInSemesterProxy> values) {
        patientsInSemester.setAcceptableValues(values);
    }

    public void setOscesPickerValues(Collection<OsceProxy> values) {
        osces.setAcceptableValues(values);
    }
}
