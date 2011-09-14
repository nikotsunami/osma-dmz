// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.ui;

import ch.unibas.medizin.osce.client.managed.request.RoleTopicProxy;
import ch.unibas.medizin.osce.client.managed.request.StandardizedRoleProxy;
import ch.unibas.medizin.osce.client.scaffold.place.AbstractProxyListView;
import ch.unibas.medizin.osce.shared.StudyYears;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
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

public abstract class RoleTopicListView_Roo_Gwt extends AbstractProxyListView<RoleTopicProxy> {

    @UiField
    CellTable<RoleTopicProxy> table;

    protected Set<String> paths = new HashSet<String>();

    public void init() {
        paths.add("id");
        table.addColumn(new TextColumn<RoleTopicProxy>() {

            Renderer<java.lang.Long> renderer = new AbstractRenderer<java.lang.Long>() {

                public String render(java.lang.Long obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(RoleTopicProxy object) {
                return renderer.render(object.getId());
            }
        }, "Id");
        paths.add("version");
        table.addColumn(new TextColumn<RoleTopicProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(RoleTopicProxy object) {
                return renderer.render(object.getVersion());
            }
        }, "Version");
        paths.add("name");
        table.addColumn(new TextColumn<RoleTopicProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(RoleTopicProxy object) {
                return renderer.render(object.getName());
            }
        }, "Name");
        paths.add("description");
        table.addColumn(new TextColumn<RoleTopicProxy>() {

            Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {

                public String render(java.lang.String obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(RoleTopicProxy object) {
                return renderer.render(object.getDescription());
            }
        }, "Description");
        paths.add("studyYear");
        table.addColumn(new TextColumn<RoleTopicProxy>() {

            Renderer<ch.unibas.medizin.osce.shared.StudyYears> renderer = new AbstractRenderer<ch.unibas.medizin.osce.shared.StudyYears>() {

                public String render(ch.unibas.medizin.osce.shared.StudyYears obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(RoleTopicProxy object) {
                return renderer.render(object.getStudyYear());
            }
        }, "Study Year");
        paths.add("slotsUntilChange");
        table.addColumn(new TextColumn<RoleTopicProxy>() {

            Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {

                public String render(java.lang.Integer obj) {
                    return obj == null ? "" : String.valueOf(obj);
                }
            };

            @Override
            public String getValue(RoleTopicProxy object) {
                return renderer.render(object.getSlotsUntilChange());
            }
        }, "Slots Until Change");
        paths.add("standardizedRoles");
        table.addColumn(new TextColumn<RoleTopicProxy>() {

            Renderer<java.util.Set> renderer = ch.unibas.medizin.osce.client.scaffold.place.CollectionRenderer.of(ch.unibas.medizin.osce.client.managed.ui.StandardizedRoleProxyRenderer.instance());

            @Override
            public String getValue(RoleTopicProxy object) {
                return renderer.render(object.getStandardizedRoles());
            }
        }, "Standardized Roles");
    }
}
