// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.FileProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
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

public abstract class FileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<FileProxy> {

    @UiField
    SpanElement id;

    @UiField
    SpanElement version;

    @UiField
    SpanElement path;

    @UiField
    SpanElement sortOrder;

    @UiField
    SpanElement description;

    @UiField
    SpanElement standardizedRole;

    FileProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(FileProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        path.setInnerText(proxy.getPath() == null ? "" : String.valueOf(proxy.getPath()));
        sortOrder.setInnerText(proxy.getSortOrder() == null ? "" : String.valueOf(proxy.getSortOrder()));
        description.setInnerText(proxy.getDescription() == null ? "" : String.valueOf(proxy.getDescription()));
        standardizedRole.setInnerText(proxy.getStandardizedRole() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.StandardizedRoleProxyRenderer.instance().render(proxy.getStandardizedRole()));
        displayRenderer.setInnerText(FileProxyRenderer.instance().render(proxy));
    }
}
