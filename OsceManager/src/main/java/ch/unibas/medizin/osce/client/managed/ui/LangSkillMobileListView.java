package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.LangSkillProxy;
import ch.unibas.medizin.osce.client.managed.request.SpokenLanguageProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedPatientProxy;
import ch.unibas.medizin.osce.client.scaffold.ScaffoldMobileApp;
import ch.unibas.medizin.osce.client.scaffold.ui.MobileProxyListView;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.text.shared.Renderer;
import java.util.HashSet;
import java.util.Set;

public class LangSkillMobileListView extends LangSkillMobileListView_Roo_Gwt {

    private static ch.unibas.medizin.osce.client.managed.ui.LangSkillMobileListView instance;

    public LangSkillMobileListView() {
        super("New LangSkill", new CellRenderer());
        init();
    }

    public static ch.unibas.medizin.osce.client.managed.ui.LangSkillMobileListView instance() {
        if (instance == null) {
            instance = new LangSkillMobileListView();
        }
        return instance;
    }

    public String[] getPaths() {
        return paths.toArray(new String[paths.size()]);
    }

    private static class CellRenderer extends AbstractSafeHtmlRenderer<LangSkillProxy> {

        private final String dateStyle = ScaffoldMobileApp.getMobileListResources().cellListStyle().dateProp();

        private final String secondaryStyle = ScaffoldMobileApp.getMobileListResources().cellListStyle().secondaryProp();

        private final Renderer<String> primaryRenderer = new AbstractRenderer<String>() {

            public String render(java.lang.String obj) {
                return obj == null ? "" : String.valueOf(obj);
            }
        };

        private final Renderer<Long> secondaryRenderer = new AbstractRenderer<Long>() {

            public String render(java.lang.Long obj) {
                return obj == null ? "" : String.valueOf(obj);
            }
        };

        @Override
        public SafeHtml render(LangSkillProxy value) {
            if (value == null) {
                return SafeHtmlUtils.EMPTY_SAFE_HTML;
            }
            SafeHtmlBuilder sb = new SafeHtmlBuilder();
            if (value.getSkill() != null) {
                //sb.appendEscaped(primaryRenderer.render(value.getSkill()));
            }
            sb.appendHtmlConstant("<div style=\"position:relative;\">");
            sb.appendHtmlConstant("<div class=\"" + secondaryStyle + "\">");
            if (value.getId() != null) {
                sb.appendEscaped(secondaryRenderer.render(value.getId()));
            }
            sb.appendHtmlConstant("</div>");
            sb.appendHtmlConstant("<div class=\"" + dateStyle + "\">");
            sb.appendHtmlConstant("</div>");
            sb.appendHtmlConstant("</div>");
            return sb.toSafeHtml();
        }
    }
}
