package com.tutorials.msuser.model;

import java.util.Objects;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequestModel {
    @NotBlank
    String username;

    @NotBlank
    String password;

    @NotBlank
    String fullName;

    @AssertFalse(message = "Username must be valid Email address")
    public boolean isUsernameInvalid() {
        return !Objects.isNull(getUsername()) && !getUsername().matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}");
    }

    @AssertFalse(message = "Password must contain at least 5 characters")
    public boolean isPasswordInvalid() {
        return !Objects.isNull(getPassword()) && getPassword().length() < 5;
    }

    @AssertFalse(message = "Full name must be valid")
    public boolean isFullNameInvalid() {
        return !Objects.isNull(getFullName()) && getFullName().split(" ").length < 2;
    }
}
