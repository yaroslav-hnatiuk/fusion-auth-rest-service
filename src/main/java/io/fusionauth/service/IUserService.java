package io.fusionauth.service;

import io.fusionauth.dto.CredentialsDto;
import io.fusionauth.dto.ResponseDto;

public interface IUserService {
  ResponseDto login(CredentialsDto credentials);
}
