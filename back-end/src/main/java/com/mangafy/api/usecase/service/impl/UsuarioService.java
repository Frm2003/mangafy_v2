package com.mangafy.api.usecase.service.impl;

import com.mangafy.api.application.repository.AutorRepository;
import com.mangafy.api.application.repository.LeitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LeitorRepository leitorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return autorRepository.findByEmail(username)
                .or(() -> leitorRepository.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
