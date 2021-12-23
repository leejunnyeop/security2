package filter.naver.Controller;

import filter.naver.qureDto.UserDto;
import filter.naver.repository.UserRepository;
import filter.naver.userEntity.User;
import org.apache.tomcat.util.buf.B2CConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //View 리런하겠다
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/")
    public String index(){
        // 머스테치 사용시 src/main/resources
        // 뷰리졸버 ?
        return "index";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    @GetMapping("/manager")
    public String manger(){
        return "manger";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @PostMapping("/join")
    public String join(User user){

        UserDto userDto = new UserDto();

        user.setRole("ROLE_USER");
        userDto.setPassword(user.getPassword());
        String rawPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
        user.setPassword(rawPassword);
        userRepository.save(user);
        return "redirect:/loginForm";
    }

    @PostMapping("/joinProc")
    public @ResponseBody
    String joinProc(){
        return "회원 가입 완료됨!";
    }



}
