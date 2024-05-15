package com.nju.architecture.zhuyuan.domain;

import com.nju.architecture.zhuyuan.modules.ums.model.UmsUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * SpringSecurity需要的用户详情
 * Created by macro on 2018/4/26.
 */
public class UmsUserDetails implements UserDetails {
    private final UmsUser umsUser;

    public UmsUserDetails(UmsUser umsUser) {
        this.umsUser = umsUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return umsUser.getPassword();
    }

    @Override
    public String getUsername() {
        return umsUser.getUsername();
    }

    public Long getId() {
        return umsUser.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
