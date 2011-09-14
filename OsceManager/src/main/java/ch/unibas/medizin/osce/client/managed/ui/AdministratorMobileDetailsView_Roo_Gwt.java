// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.AdministratorProxy;
import ch.unibas.medizin.osce.client.managed.request.SemesterProxy;
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
import java.util.Set;

public abstract class AdministratorMobileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<AdministratorProxy> {

    @UiField
    Element id;

    @UiField
    Element version;

    @UiField
    Element email;

    @UiField
    Element name;

    @UiField
    Element preName;

    @UiField
    Element semesters;

    AdministratorProxy proxy;

    public void setValue(AdministratorProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        email.setInnerText(proxy.getEmail() == null ? "" : String.valueOf(proxy.getEmail()));
        name.setInnerText(proxy.getName() == null ? "" : String.valueOf(proxy.getName()));
        preName.setInnerText(proxy.getPreName() == null ? "" : String.valueOf(proxy.getPreName()));
        semesters.setInnerText(proxy.getSemesters() == null ? "" : ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.SemesterProxyRenderer.instance()).render(proxy.getSemesters()));
    }
}
