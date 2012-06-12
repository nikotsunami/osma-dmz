// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.AssignmentProxy;
import ch.unibas.medizin.osce.client.managed.request.OsceDayProxy;
import ch.unibas.medizin.osce.client.managed.request.OsceProxy;
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
import java.util.Set;

public abstract class OsceDayDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<OsceDayProxy> {

    @UiField
    SpanElement id;

    @UiField
    SpanElement version;

    @UiField
    SpanElement timeStart;

    @UiField
    SpanElement timeEnd;

    @UiField
    SpanElement osce;

    @UiField
    SpanElement assignments;

    OsceDayProxy proxy;

    @UiField
    SpanElement displayRenderer;

    public void setValue(OsceDayProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        timeStart.setInnerText(proxy.getTimeStart() == null ? "" : DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(proxy.getTimeStart()));
        timeEnd.setInnerText(proxy.getTimeEnd() == null ? "" : DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT).format(proxy.getTimeEnd()));
        osce.setInnerText(proxy.getOsce() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.OsceProxyRenderer.instance().render(proxy.getOsce()));
        assignments.setInnerText(proxy.getAssignments() == null ? "" : ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.AssignmentProxyRenderer.instance()).render(proxy.getAssignments()));
        displayRenderer.setInnerText(ch.unibas.medizin.osce.client.managed.ui.OsceDayProxyRenderer.instance().render(proxy));
    }
}
