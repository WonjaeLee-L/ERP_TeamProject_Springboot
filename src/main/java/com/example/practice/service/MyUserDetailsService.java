package com.example.practice.service;

import com.example.practice.repository.IF_LoginDao;
import com.example.practice.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final IF_LoginDao logindao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String erpId) throws UsernameNotFoundException {
        try {
            MemberVO member = logindao.selectOne(erpId);
            String passwordEncoding = passwordEncoder.encode(member.getErpPass());
            return User.builder().username(member.getErpId()).password(passwordEncoding).roles(member.getErpRole()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
