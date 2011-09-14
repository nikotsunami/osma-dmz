// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.activity.AnamnesisChecksValueEditActivityWrapper;
import ch.unibas.medizin.osce.client.managed.activity.AnamnesisChecksValueEditActivityWrapper.View;
import ch.unibas.medizin.osce.client.managed.request.AnamnesisCheckProxy;
import ch.unibas.medizin.osce.client.managed.request.AnamnesisChecksValueProxy;
import ch.unibas.medizin.osce.client.managed.request.AnamnesisFormProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyEditView;
import ch.unibas.medizin.osce.client.scaffold.ui.*;
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

public abstract class AnamnesisChecksValueMobileEditView_Roo_Gwt extends Composite implements View<AnamnesisChecksValueMobileEditView> {

    @UiField(provided = true)
    CheckBox truth = new CheckBox() {

        public void setValue(Boolean value) {
            super.setValue(value == null ? Boolean.FALSE : value);
        }
    };

    @UiField
    TextBox comment;

    @UiField
    IntegerBox anamnesisChecksValue;

    @UiField(provided = true)
    ValueListBox<AnamnesisFormProxy> anamnesisform = new ValueListBox<AnamnesisFormProxy>(ch.unibas.medizin.osce.client.managed.ui.AnamnesisFormProxyRenderer.instance(), new com.google.gwt.requestfactory.ui.client.EntityProxyKeyProvider<ch.unibas.medizin.osce.client.managed.request.AnamnesisFormProxy>());

    @UiField(provided = true)
    ValueListBox<AnamnesisCheckProxy> anamnesischeck = new ValueListBox<AnamnesisCheckProxy>(ch.unibas.medizin.osce.client.managed.ui.AnamnesisCheckProxyRenderer.instance(), new com.google.gwt.requestfactory.ui.client.EntityProxyKeyProvider<ch.unibas.medizin.osce.client.managed.request.AnamnesisCheckProxy>());

    public void setAnamnesischeckPickerValues(Collection<AnamnesisCheckProxy> values) {
        anamnesischeck.setAcceptableValues(values);
    }

    public void setAnamnesisformPickerValues(Collection<AnamnesisFormProxy> values) {
        anamnesisform.setAcceptableValues(values);
    }
}
