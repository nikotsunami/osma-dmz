// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.AnamnesisCheckProxy;
import ch.unibas.medizin.osce.client.managed.request.EliminationCriterionProxy;
import ch.unibas.medizin.osce.client.managed.request.ScarProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.scaffold.place.AbstractProxyListView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import java.util.HashSet;
import java.util.Set;

public abstract class EliminationCriterionListView_Roo_Gwt extends AbstractProxyListView<EliminationCriterionProxy> {

    @UiField
    CellTable<EliminationCriterionProxy> table;

    protected Set<String> paths = new HashSet<String>();

    public void init() {
        paths.add("anamnesisCheckValue");
        table.addColumn(new TextColumn<EliminationCriterionProxy>() {

            Renderer<java.lang.Boolean> renderer = new AbstractRenderer<java.lang.Boolean>() {

                public String render(java.lang.Boolean obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(EliminationCriterionProxy object) {
                return renderer.render(object.getAnamnesisCheckValue());
            }
        }, "Anamnesis Check Value");
        paths.add("standardizedRole");
        table.addColumn(new TextColumn<EliminationCriterionProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.StandardizedRoleProxyRenderer.instance();

            @Override
            public String getValue(EliminationCriterionProxy object) {
                return renderer.render(object.getStandardizedRole());
            }
        }, "Standardized Role");
        paths.add("scar");
        table.addColumn(new TextColumn<EliminationCriterionProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.ScarProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.ScarProxyRenderer.instance();

            @Override
            public String getValue(EliminationCriterionProxy object) {
                return renderer.render(object.getScar());
            }
        }, "Scar");
        paths.add("anamnesisCheck");
        table.addColumn(new TextColumn<EliminationCriterionProxy>() {

            Renderer<ch.unibas.medizin.osce.client.managed.request.AnamnesisCheckProxy> renderer = ch.unibas.medizin.osce.client.managed.ui.AnamnesisCheckProxyRenderer.instance();

            @Override
            public String getValue(EliminationCriterionProxy object) {
                return renderer.render(object.getAnamnesisCheck());
            }
        }, "Anamnesis Check");
        paths.add("id");
        table.addColumn(new TextColumn<EliminationCriterionProxy>() {

            Renderer<java.lang.Long> renderer = new AbstractRenderer<java.lang.Long>() {

                public String render(java.lang.Long obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(EliminationCriterionProxy object) {
                return renderer.render(object.getId());
            }
        }, "Id");
        paths.add("version");
        table.addColumn(new TextColumn<EliminationCriterionProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(EliminationCriterionProxy object) {
                return renderer.render(object.getVersion());
            }
        }, "Version");
    }
}
