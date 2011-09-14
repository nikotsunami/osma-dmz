// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.LangSkillProxy;
import ch.unibas.medizin.osce.client.managed.request.SpokenLanguageProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedPatientProxy;
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

public abstract class LangSkillMobileDetailsView_Roo_Gwt extends Composite implements ProxyDetailsView<LangSkillProxy> {

    @UiField
    Element id;

    @UiField
    Element version;

    @UiField
    Element skill;

    @UiField
    Element standardizedpatient;

    @UiField
    Element spokenlanguage;

    LangSkillProxy proxy;

    public void setValue(LangSkillProxy proxy) {
        this.proxy = proxy;
        id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
        version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
        skill.setInnerText(proxy.getSkill() == null ? "" : String.valueOf(proxy.getSkill()));
        standardizedpatient.setInnerText(proxy.getStandardizedpatient() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.StandardizedPatientProxyRenderer.instance().render(proxy.getStandardizedpatient()));
        spokenlanguage.setInnerText(proxy.getSpokenlanguage() == null ? "" : ch.unibas.medizin.osce.client.managed.ui.SpokenLanguageProxyRenderer.instance().render(proxy.getSpokenlanguage()));
    }
}
