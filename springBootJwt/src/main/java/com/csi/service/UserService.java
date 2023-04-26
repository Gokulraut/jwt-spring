package com.csi.service;

import com.csi.dao.UserDaoImpl;
import com.csi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDaoImpl userDaoImpl;

    public User saveData(User user){
        return userDaoImpl.saveData(user);
    }
    public Optional<User> getDataById(int userId){
        return userDaoImpl.getDataById(userId);
    }
    public User updateData(User user){
        return userDaoImpl.updateData(user);
    }
    public List<User> getAllData(){
        return userDaoImpl.getAllData();
    }
/*    public User findbyName(String userName){
        return userDaoImpl.findbyName(userName);
    }*/
    public void deleteById(int userId){
        userDaoImpl.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userDaoImpl.findbyName(username);


    return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), new ArrayList<>());
    }
}
