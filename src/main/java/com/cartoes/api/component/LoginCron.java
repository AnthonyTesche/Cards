package com.cartoes.api.component;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.cartoes.api.entities.Usuario;
import com.cartoes.api.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class LoginCron {

    @Autowired
    private UsuarioRepository userRep;
    
    private static final String Tzone = "America/Sao_Paulo";

    @Scheduled(cron = "* */24 * * * ?", zone = Tzone)
    public void verificaTrintaDias() { 
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate today = LocalDate.parse(f.format(new Date()));
        List<Usuario> users = userRep.findAll();
        for (Usuario u : users) {
            if(u.getLogged() != null){
                long betDays = ChronoUnit.DAYS.between(LocalDate.parse(f.format(u.getLogged())),today);
                if(betDays >= 30){
                    u.setAtivo(false);
                    userRep.save(u);
                }
                System.out.println(betDays);
            }
        }
    }
}
