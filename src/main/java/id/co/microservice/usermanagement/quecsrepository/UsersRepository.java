package id.co.microservice.usermanagement.quecsrepository;

import java.sql.Timestamp;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.microservice.usermanagement.quecsentity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	
	@Query(
			nativeQuery = true,
			value = "select id, username, password, email, firstname, lastname, createdate as createDate, "
				  + "createby as createBy, updatedate as updateDate, updateby as updateBy "
				  + "from users where username = :username"
		)
	Users getUsersByUsername(@Param("username") String username);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(
			nativeQuery = true,
			value = "insert into users (id, username, password, email, firstname, lastname, createdate, createby) "
					+ "values (:id, :username, :password, :email, :firstname, :lastname, :createdate, :createby)"
	)
	void registerUser(@Param("id") UUID id, @Param("username") String username, @Param("password") String password,
			@Param("email") String email, @Param("firstname") String firstname, @Param("lastname") String lastname, 
			@Param("createdate") Timestamp createdate, @Param("createby") UUID createby);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(
			nativeQuery = true,
			value = "update users set password = :password, updatedate = :updatedate, "
					+ "updateby = :updateby "
					+ "where id = :userid"
	)
	void updatePassword(@Param("password") String password, @Param("updatedate") Timestamp updatedate, @Param("updateby") UUID updateby,
			@Param("userid") UUID userid);
	

}
