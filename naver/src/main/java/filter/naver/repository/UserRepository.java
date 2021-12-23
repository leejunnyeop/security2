package filter.naver.repository;

import filter.naver.userEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository라는 어노테이션 없어도 Ioc 됨, 상속됬어
public interface UserRepository extends JpaRepository<User, Long> {


    //select u form u as user where nsername =? 1
    public User findByUsername(String username); // Query methods
}
