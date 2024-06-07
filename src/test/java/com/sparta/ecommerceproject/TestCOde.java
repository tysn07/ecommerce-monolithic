package com.sparta.ecommerceproject;

import com.sparta.ecommerceproject.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestCOde {


    @Test
    @DisplayName("기본 테스트1")
    void Test1(){
        User user = User.builder().email("dqw").password("dwdew").username("dwqw").build();
        user.changePassword("newpassword");
        Assertions.assertThat(user.getPassword()).isEqualTo("newpassword");
    }
}
