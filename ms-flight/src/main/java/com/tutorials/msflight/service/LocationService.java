package com.tutorials.msflight.service;

import com.tutorials.msflight.entity.Location;
import java.util.UUID;

public interface LocationService {

    Location locationById(UUID id);
}
