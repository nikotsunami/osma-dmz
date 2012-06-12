// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.MediaContentProxy;
import ch.unibas.medizin.osce.client.managed.request.MediaContentTypeProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedPatientProxy;
import ch.unibas.medizin.osce.client.scaffold.place.ProxyDetailsView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
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

public abstract class MediaContentMobileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<MediaContentProxy> {

    @UiField
    Element link;

    @UiField
    Element comment;

    @UiField
    Element standardizedPatient;

    @UiField
    Element contentType;

    @UiField
    Element id;

    @UiField
    Element version;

    MediaContentProxy proxy;

    public void setValue(MediaContentProxy proxy) {
        this.proxy = proxy;
        link.setInnerText(proxy.getLink() == null ? "" : String.valueOf(proxy.getLink()));
        comment.setInnerText(proxy.getComment() == null ? "" : String.valueOf(proxy.getComment()));
        standardizedPatient.setInnerText(proxy.getStandardizedPatient() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.StandardizedPatientProxyRenderer.instance().render(proxy.getStandardizedPatient()));
        contentType.setInnerText(proxy.getContentType() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.MediaContentTypeProxyRenderer.instance().render(proxy.getContentType()));
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
    }
}
