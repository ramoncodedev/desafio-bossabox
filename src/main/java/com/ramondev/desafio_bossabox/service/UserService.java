package com.ramondev.desafio_bossabox.service;

import com.ramondev.desafio_bossabox.Exceptions.ConflictExceptions;
import com.ramondev.desafio_bossabox.Exceptions.ResourceNotFoundExceptions;
import com.ramondev.desafio_bossabox.entity.User;
import com.ramondev.desafio_bossabox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user){

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        existEmail(user.getEmail());
        return userRepository.save(user);
    }

    public void existEmail(String email){
        if (verificarEmail(email)){
            throw new ConflictExceptions("Esse email " + email + "já esta em uso.");
        }
    }

    public boolean verificarEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public List<User> findList(){
        List<User> users = userRepository.findAll();

        if (users.isEmpty()){
            throw new ResourceNotFoundExceptions("Nenhum usuário foi cadastrado");
        }

        return users;
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExceptions("não existe nenhum user " + id + " com esse id"));
    }

    public User findByPut(Long id, User user){
        User existUser = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExceptions("não existe nenhum user " + id + " com esse id"));

        if (existUser.getEmail().equals(user.getEmail())){
            existEmail(user.getEmail());
        }

        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        existUser.setPassword(user.getPassword());

        return existUser;
    }

    public void deleteById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExceptions("não existe nenhum user " + id + " com esse id"));

        userRepository.deleteById(id);
    }


}


