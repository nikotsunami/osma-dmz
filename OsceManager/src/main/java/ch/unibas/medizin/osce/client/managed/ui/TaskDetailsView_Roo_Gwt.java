// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.AdministratorProxy;
import ch.unibas.medizin.osce.client.managed.request.TaskProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyListView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class TaskDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<TaskProxy> {

    @UiField
    SpanElement name;

    @UiField
    SpanElement deadline;

    @UiField
    SpanElement isDone;

    @UiField
    SpanElement osce;

    @UiField
    SpanElement administrator;

    @UiField
    SpanElement id;

    @UiField
    SpanElement version;

    TaskProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(TaskProxy proxy) {
        this.proxy = proxy;
        name.setInnerText(proxy.getName() == null ? "" : String.valueOf(proxy.getName()));
        deadline.setInnerText(proxy.getDeadline() == null ? "" : DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(proxy.getDeadline()));
        isDone.setInnerText(proxy.getIsDone() == null ? "" : String.valueOf(proxy.getIsDone()));
        osce.setInnerText(proxy.getOsce() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.TaskProxyRenderer.instance().render(proxy.getOsce()));
        administrator.setInnerText(proxy.getAdministrator() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.AdministratorProxyRenderer.instance().render(proxy.getAdministrator()));
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        displayRenderer.setInnerText(ch.unibas.medizin.osce.client.managed.ui.TaskProxyRenderer.instance().render(proxy));
    }
}
