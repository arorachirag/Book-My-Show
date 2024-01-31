package com.BookMyShow.Book.My.Show.Service;


import com.BookMyShow.Book.My.Show.DTO.request.RegularUserSignUpDTO;
import com.BookMyShow.Book.My.Show.Models.ApplicationUser;
import com.BookMyShow.Book.My.Show.Repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularUserService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    public ApplicationUser signUp(RegularUserSignUpDTO regularUserSignUpDTO){
        ApplicationUser user = new ApplicationUser();
        user.setId(regularUserSignUpDTO.getId());
        user.setName(regularUserSignUpDTO.getName());
        user.setEmail(regularUserSignUpDTO.getEmail());
        user.setPassword(regularUserSignUpDTO.getPassword());
        user.setMobileNo(regularUserSignUpDTO.getMobileNo());
        user.setAge(regularUserSignUpDTO.getAge());
        user.setType(regularUserSignUpDTO.getType().toString());

        applicationUserRepository.save(user);
        return user;
    }

    public ApplicationUser getUserByEmail(String email){
        return applicationUserRepository.findUserByEmail(email);
    }

}
