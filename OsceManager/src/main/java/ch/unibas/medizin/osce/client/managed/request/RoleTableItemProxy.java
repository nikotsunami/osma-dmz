// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.

package ch.unibas.medizin.osce.client.managed.request;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyForName;
import java.util.Set;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;

@RooGwtMirroredFrom("ch.unibas.medizin.osce.domain.RoleTableItem")
@ProxyForName("ch.unibas.medizin.osce.domain.RoleTableItem")
public interface RoleTableItemProxy extends EntityProxy {

    abstract Long getId();

    abstract void setId(Long id);

    abstract Integer getVersion();

    abstract void setVersion(Integer version);

    abstract String getItemName();

    abstract void setItemName(String itemName);

    abstract RoleBaseItemProxy getRoleBaseItem();

    abstract void setRoleBaseItem(RoleBaseItemProxy roleBaseItem);

    abstract Integer getSort_order();

    abstract void setSort_order(Integer sort_order);

    abstract Set<ch.unibas.medizin.osce.client.managed.request.RoleTableItemValueProxy> getRoleTableItemValue();

    abstract void setRoleTableItemValue(Set<RoleTableItemValueProxy> roleTableItemValue);
}
