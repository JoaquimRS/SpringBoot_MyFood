package com.myfood.springboot_myfood.domain.clients.service;

import com.myfood.springboot_myfood.domain.clients.dto.ClientDto;
import com.myfood.springboot_myfood.domain.clients.entity.ClientEntity;
import com.myfood.springboot_myfood.domain.clients.repository.ClientRepository;
import com.myfood.springboot_myfood.domain.payload.request.LoginRequest;
import com.myfood.springboot_myfood.domain.payload.request.SignUpRequest;
import com.myfood.springboot_myfood.exception.AppException;
import com.myfood.springboot_myfood.exception.Error;
import com.myfood.springboot_myfood.plugins.IdGenerator;
import com.myfood.springboot_myfood.security.AuthClientDetails;
import com.myfood.springboot_myfood.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public ClientDto convertEntityToDto(ClientEntity cEntity) {
        return ClientDto.builder()
                .nombre(cEntity.getNombre())
                .email(cEntity.getEmail())
                .telefono(cEntity.getTelefono())
                .token(jwtUtils.encode(cEntity.getEmail()))
                .build();
    }

    @Override
    public ClientDto registration(final SignUpRequest data) {
        clientRepository.findByNombreOrEmail(data.getNombre(), data.getEmail())
                .stream()
                .findAny()
                .ifPresent(entity -> {
                    throw new AppException(Error.DUPLICATED_USER);
                });

        ClientEntity clientEntity = ClientEntity.builder()
                .nombre(data.getNombre())
                .email(data.getEmail())
                .contraseña(passwordEncoder.encode(data.getContraseña()))
                .id_cliente(IdGenerator.generateWithLength(10))
                .telefono(data.getTelefono())
                .build();

        clientRepository.save(clientEntity);
        return convertEntityToDto(clientEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public ClientDto login(LoginRequest credentials) {
        ClientEntity cEntity = clientRepository.findByEmail(credentials.getEmail())
                .filter(client ->
                        passwordEncoder.matches(
                                credentials.getContraseña(),
                                client.getContraseña()))
                .orElseThrow(() ->
                        new AppException(Error.LOGIN_INFO_INVALID));

        return convertEntityToDto(cEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public ClientDto currentUser(AuthClientDetails authClientDetails) {
        ClientEntity clientEntity = clientRepository.
                findById(authClientDetails.getId_cliente())
                .orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));

        return convertEntityToDto(clientEntity);
    }


}
