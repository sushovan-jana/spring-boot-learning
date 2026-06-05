package com.kodnest.app.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.kodnest.app.entities.Otp;
import com.kodnest.app.entities.Users;
import com.kodnest.app.repositories.OtpRepo;
import com.kodnest.app.repositories.UserRepo;

@Service
public class UserService {
	UserRepo userRepo;
	JavaMailSender mailSender;
	OtpRepo otpRepo;

//	Constructor Injection
	public UserService(UserRepo userRepo, JavaMailSender mailSender, OtpRepo otpRepo) {
		super();
		this.userRepo = userRepo;
		this.mailSender = mailSender;
		this.otpRepo = otpRepo;
	}

	public boolean userSignUp(Users user) {
		Users savedUser = userRepo.save(user);
		if (savedUser != null)
			return true;
		else
			return false;
	}

	public boolean userSignIn(String username, String password) {
		Users user = userRepo.findByUsername(username);
		if (user != null) {
//			Generate 6 digits otp
//			new Random().nextInt(999999);
			int otp = new Random().nextInt(100000, 999999);
			System.out.println(otp);

//			Create otp entity object and store it in DB using OtpRepo
			Otp newOtp = new Otp(otp, LocalDateTime.now(), user);
			otpRepo.save(newOtp);

			String useremail = user.getEmial();

//			Send it to user's mail
			SimpleMailMessage message = new SimpleMailMessage();
			message.setText(useremail);
			message.setSubject("KODNEST OTP:");
			message.setText("YOUR LOGIN OTP FOR KODNEST APPLICATION IS: " + otp);
			mailSender.send(message);
			if (user.getPassword().equals(password))
				return true;
		}
		return false;
	}

	public Users verifyOtp(int otp) {
		Otp ref = otpRepo.findByOtpvalue(otp);
		if (ref != null && otp == ref.getOtpvalue()) {
			Users user = userRepo.findById(ref.getUser().getId()).orElse(null);
			LocalDateTime otpTime = ref.getCreatedat();
			LocalDateTime currentTime = LocalDateTime.now();

			if (ChronoUnit.MINUTES.between(otpTime, currentTime) < 1) {
				return user;
			} else {
				otpRepo.delete(ref);
				return null;
			}	
		} else return null;
	}
}