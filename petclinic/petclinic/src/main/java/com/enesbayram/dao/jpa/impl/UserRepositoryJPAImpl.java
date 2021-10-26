package com.enesbayram.dao.jpa.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enesbayram.dao.IUserRepository;
import com.enesbayram.exception.UserNotFoundException;
import com.enesbayram.model.User;
import com.enesbayram.model.Vet;

@Repository("userRepository")
@Transactional(rollbackFor = Exception.class)
public class UserRepositoryJPAImpl implements IUserRepository {

	@PersistenceContext // enjekte ediyoruz.
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<User> findAll() {
		return entityManager.createQuery("from User", User.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> findByLastname(String lastName) {
		return entityManager.createQuery("from User where lastName=:lastName", User.class)
				.setParameter("lastName", lastName).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public User findById(Long userId) throws UserNotFoundException {
		return entityManager.createQuery("from User where id=:id", User.class)
				.setParameter("id", Integer.parseInt(userId.toString())).getSingleResult();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void create(User user) {

		try {
			entityManager.persist(user);
			sendMail();
		} catch (Exception e) {
			System.out.println("Hata : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void sendMail() throws Exception {
		String fromEmail = "blogsitemlog@gmail.com";
		String password = "blogsitemlog99";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(RecipientType.TO, InternetAddress.parse("enesbayram166@gmail.com"));
			message.setSubject("SORU");
			message.setText("User başarılı bir şekilde eklendi.");
			Transport.send(message);
			System.out.println("Mail sent successfully");
		} catch (Exception e) {
			System.out.println("it happened an error, while sent a mail : " + e.getMessage());
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public User update(User user) {
		// olmayabilir bakacağız buna.
		return entityManager.merge(user);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(Long userId) throws UserNotFoundException {
		User user = entityManager.find(User.class, Integer.parseInt(userId.toString()));
		entityManager.remove(user);
	}

	@Override
	public List<Vet> getVetList() {
		return entityManager.createQuery("from Vet", Vet.class).getResultList();
	}

	@Override
	public Vet getVetById(Long id) {
		return entityManager.find(Vet.class, id);
	}

}
