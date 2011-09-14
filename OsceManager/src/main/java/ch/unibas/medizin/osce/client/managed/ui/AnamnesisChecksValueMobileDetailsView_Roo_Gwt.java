// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.AnamnesisCheckProxy;
import ch.unibas.medizin.osce.client.managed.request.AnamnesisChecksValueProxy;
import ch.unibas.medizin.osce.client.managed.request.AnamnesisFormProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
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

public abstract class AnamnesisChecksValueMobileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<AnamnesisChecksValueProxy> {

    @UiField
    Element id;

    @UiField
    Element version;

    @UiField
    Element truth;

    @UiField
    Element comment;

    @UiField
    Element anamnesisChecksValue;

    @UiField
    Element anamnesisform;

    @UiField
    Element anamnesischeck;

    AnamnesisChecksValueProxy proxy;

    public void setValue(AnamnesisChecksValueProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        truth.setInnerText(proxy.getTruth() == null ? "" : String.valueOf(proxy.getTruth()));
        comment.setInnerText(proxy.getComment() == null ? "" : String.valueOf(proxy.getComment()));
        anamnesisChecksValue.setInnerText(proxy.getAnamnesisChecksValue() == null ? "" : String.valueOf(proxy.getAnamnesisChecksValue()));
        anamnesisform.setInnerText(proxy.getAnamnesisform() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.AnamnesisFormProxyRenderer.instance().render(proxy.getAnamnesisform()));
        anamnesischeck.setInnerText(proxy.getAnamnesischeck() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.AnamnesisCheckProxyRenderer.instance().render(proxy.getAnamnesischeck()));
    }
}
