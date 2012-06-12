// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.OscePostProxy;
import ch.unibas.medizin.osce.client.managed.request.OscePostRoomProxy;
import ch.unibas.medizin.osce.client.managed.request.RoleTopicProxy;
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
import java.util.Set;

public abstract class OscePostMobileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<OscePostProxy> {

    @UiField
    Element isPossibleStart;

    @UiField
    Element roleTopic;

    @UiField
    Element nextPost;

    @UiField
    Element oscePostRooms;

    @UiField
    Element id;

    @UiField
    Element version;

    OscePostProxy proxy;

    public void setValue(OscePostProxy proxy) {
        this.proxy = proxy;
        isPossibleStart.setInnerText(proxy.getIsPossibleStart() == null ? "" : String.valueOf(proxy.getIsPossibleStart()));
        roleTopic.setInnerText(proxy.getRoleTopic() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.RoleTopicProxyRenderer.instance().render(proxy.getRoleTopic()));
        nextPost.setInnerText(proxy.getNextPost() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.OscePostProxyRenderer.instance().render(proxy.getNextPost()));
        oscePostRooms.setInnerText(proxy.getOscePostRooms() == null ? "" : ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.OscePostRoomProxyRenderer.instance()).render(proxy.getOscePostRooms()));
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
    }
}
