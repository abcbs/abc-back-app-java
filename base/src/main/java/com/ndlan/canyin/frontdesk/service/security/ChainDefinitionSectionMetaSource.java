package com.ndlan.canyin.frontdesk.service.security;

import com.ndlan.canyin.frontdesk.service.ctzh.AuthorityModuleService;
import com.ndlan.canyin.frontdesk.service.ctzh.EmployeeService;
import com.ndlan.canyin.base.entity.ctzh.AuthorityModule;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ChainDefinitionSectionMetaSource
  implements FactoryBean<Ini.Section>
{
  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private AuthorityModuleService authorityModuleService;
  private String filterChainDefinitions;
  
  public Ini.Section getObject()
    throws BeansException
  {
    Ini ini = new Ini();
    
    ini.load(this.filterChainDefinitions);
    Ini.Section section = ini.getSection("");
    
    List<AuthorityModule> permissions = this.authorityModuleService.findAll();
    for (AuthorityModule permission : permissions) {
      if ((StringUtils.isNotEmpty(permission.getActionUrl())) && (StringUtils.isNotEmpty(permission.getPermission()))) {
        section.put(permission.getActionUrl(), permission.getPermission() + ",csf");
      }
    }
    section.put("/**", "csf");
    return section;
  }
  
  public void setFilterChainDefinitions(String filterChainDefinitions)
  {
    this.filterChainDefinitions = filterChainDefinitions;
  }
  
  public Class<?> getObjectType()
  {
    return getClass();
  }
  
  public boolean isSingleton()
  {
    return false;
  }
}
