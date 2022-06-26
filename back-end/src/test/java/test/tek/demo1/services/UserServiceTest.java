package test.tek.demo1.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import test.tek.demo1.db.entity.User;
import test.tek.demo1.db.repo.UserRepository;
import test.tek.demo1.dto.request.UserCreds;
import test.tek.demo1.dto.response.UserValidityDto;
import test.tek.demo1.exceptions.InvalidUserException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup(){}

    @DisplayName("UserServiceTest - test isValidUser with user available")
    @Test
    public void givenValidUserCreds_whenValidUserCheck_thenReturnValidUserValidObject(){
        given(userRepository.findById(any()))
                .willReturn(Optional.of(new User()));

        UserValidityDto userValidityDto = userService.isValidUser(new UserCreds("user123", "pass123"));

        assertThat(userValidityDto).isNotNull();
        assertThat(userValidityDto.getUserId()).isEqualTo("user123");
        assertThat(userValidityDto.getIsValidUser()).isTrue();
    }

    @DisplayName("UserServiceTest - test isValidUser with user not in db ")
    @Test
    public void givenInvalidUserCreds_whenValidUserCheck_thenThrowInvalidUserException(){
        given(userRepository.findById(any()))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.isValidUser(new UserCreds("invaliduser", "empty")))
                .as("InvalidUserException when user is not in db")
                .isInstanceOf(InvalidUserException.class);
    }
}
