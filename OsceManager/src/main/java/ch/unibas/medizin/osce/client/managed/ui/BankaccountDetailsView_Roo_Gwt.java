// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.BankaccountProxy;
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

public abstract class BankaccountDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<BankaccountProxy> {

    @UiField
    SpanElement id;

    @UiField
    SpanElement version;

    @UiField
    SpanElement bankName;

    @UiField
    SpanElement iBAN;

    @UiField
    SpanElement bIC;

    BankaccountProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(BankaccountProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        bankName.setInnerText(proxy.getBankName() == null ? "" : String.valueOf(proxy.getBankName()));
        iBAN.setInnerText(proxy.getIBAN() == null ? "" : String.valueOf(proxy.getIBAN()));
        bIC.setInnerText(proxy.getBIC() == null ? "" : String.valueOf(proxy.getBIC()));
        displayRenderer.setInnerText(BankaccountProxyRenderer.instance().render(proxy));
    }
}
