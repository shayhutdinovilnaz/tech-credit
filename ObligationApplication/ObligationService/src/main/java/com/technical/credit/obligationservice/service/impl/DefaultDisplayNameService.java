package com.technical.credit.obligationservice.service.impl;

import com.technical.credit.obligationservice.service.DisplayNameService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class DefaultDisplayNameService implements DisplayNameService {

    @Override
    public @NonNull String createDisplayName(String fName, String lName, String pattern) {
        return pattern.replace("|l|", lName).replace("|f|", fName);
    }
}
