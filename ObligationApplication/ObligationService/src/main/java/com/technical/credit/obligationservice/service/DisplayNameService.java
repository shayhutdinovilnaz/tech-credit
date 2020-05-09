package com.technical.credit.obligationservice.service;

import lombok.NonNull;

public interface DisplayNameService {
    @NonNull String createDisplayName(String fName, String lName, String personData);
}
