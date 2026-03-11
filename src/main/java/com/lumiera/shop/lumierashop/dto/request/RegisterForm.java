package com.lumiera.shop.lumierashop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterForm {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 6, max = 12, message = "아이디는 최소 6 최대 12자 사이여야 합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]+$", message = "아이디는 영소문자와 숫자 조합이어야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 최소 8 최대 20자 사이여야 합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*])[a-z\\d!@#$%^&*]+$",
            message = "비밀번호는 영소문자, 숫자, 특수문자(!@#$%^&*)의 조합이어야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String confirmPassword;

    public boolean isPasswordMatched() {
        return password != null && password.equals(confirmPassword);
    }
}
