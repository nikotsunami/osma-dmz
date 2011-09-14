// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.activity.AssignmentEditActivityWrapper;
import ch.unibas.medizin.osce.client.managed.activity.AssignmentEditActivityWrapper.View;
import ch.unibas.medizin.osce.client.managed.request.AssignmentProxy;
import ch.unibas.medizin.osce.client.managed.request.DoctorProxy;
import ch.unibas.medizin.osce.client.managed.request.OsceDayProxy;
import ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy;
import ch.unibas.medizin.osce.client.managed.request.PatientInRoleProxy;
import ch.unibas.medizin.osce.client.managed.request.StudentProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyEditView;
import ch.unibas.medizin.osce.client.scaffold.ui.*;
import ch.unibas.medizin.osce.shared.AssignmentTypes;
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

public abstract class AssignmentMobileEditView_Roo_Gwt extends Composite implements View<AssignmentMobileEditView> {

    @UiField(provided = true)
    ValueListBox<AssignmentTypes> type = new ValueListBox<AssignmentTypes>(new AbstractRenderer<ch.unibas.medizin.osce.shared.AssignmentTypes>() {

        public String render(ch.unibas.medizin.osce.shared.AssignmentTypes obj) {
            return obj == null ? "" : String.valueOf(obj);
        }
    });

    @UiField
    IntegerBox slotNumber;

    @UiField
    DateBox timeStart;

    @UiField
    DateBox timeEnd;

    @UiField(provided = true)
    ValueListBox<OsceDayProxy> osceDay = new ValueListBox<OsceDayProxy>(ch.unibas.medizin.osce.client.managed.ui.OsceDayProxyRenderer.instance(), new com.google.gwt.requestfactory.ui.client.EntityProxyKeyProvider<ch.unibas.medizin.osce.client.managed.request.OsceDayProxy>());

    @UiField(provided = true)
    ValueListBox<OscePostRoomProxy> oscePostRoom = new ValueListBox<OscePostRoomProxy>(ch.unibas.medizin.osce.client.managed.ui.OscePostRoomProxyRenderer.instance(), new com.google.gwt.requestfactory.ui.client.EntityProxyKeyProvider<ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy>());

    @UiField(provided = true)
    ValueListBox<StudentProxy> student = new ValueListBox<StudentProxy>(ch.unibas.medizin.osce.client.managed.ui.StudentProxyRenderer.instance(), new com.google.gwt.requestfactory.ui.client.EntityProxyKeyProvider<ch.unibas.medizin.osce.client.managed.request.StudentProxy>());

    @UiField(provided = true)
    ValueListBox<PatientInRoleProxy> patientInRole = new ValueListBox<PatientInRoleProxy>(ch.unibas.medizin.osce.client.managed.ui.PatientInRoleProxyRenderer.instance(), new com.google.gwt.requestfactory.ui.client.EntityProxyKeyProvider<ch.unibas.medizin.osce.client.managed.request.PatientInRoleProxy>());

    @UiField(provided = true)
    ValueListBox<DoctorProxy> examiner = new ValueListBox<DoctorProxy>(ch.unibas.medizin.osce.client.managed.ui.DoctorProxyRenderer.instance(), new com.google.gwt.requestfactory.ui.client.EntityProxyKeyProvider<ch.unibas.medizin.osce.client.managed.request.DoctorProxy>());

    public void setOsceDayPickerValues(Collection<OsceDayProxy> values) {
        osceDay.setAcceptableValues(values);
    }

    public void setExaminerPickerValues(Collection<DoctorProxy> values) {
        examiner.setAcceptableValues(values);
    }

    public void setStudentPickerValues(Collection<StudentProxy> values) {
        student.setAcceptableValues(values);
    }

    public void setPatientInRolePickerValues(Collection<PatientInRoleProxy> values) {
        patientInRole.setAcceptableValues(values);
    }

    public void setOscePostRoomPickerValues(Collection<OscePostRoomProxy> values) {
        oscePostRoom.setAcceptableValues(values);
    }

    public void setTypePickerValues(Collection<AssignmentTypes> values) {
        type.setAcceptableValues(values);
    }
}
