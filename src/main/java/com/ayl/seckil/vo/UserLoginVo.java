package com.ayl.seckil.vo;

import com.ayl.seckil.validator.IsPhone;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author AYL    2018/9/3 0:29
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UserLoginVo {
    @NotNull(message = "密码不能为空")
    @NotBlank(message = "请输入密码")
    private String formPassword;

    @IsPhone
    private String phone;
}
