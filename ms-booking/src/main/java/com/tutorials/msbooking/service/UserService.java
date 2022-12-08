package com.tutorials.msbooking.service;

import com.tutorials.msbooking.entity.User;
import com.tutorials.msbooking.model.BookingRqModel;
import com.tutorials.msbooking.model.BookingRsModel;
import java.util.UUID;

public interface UserService {
    User userByUsername(String username);
}
