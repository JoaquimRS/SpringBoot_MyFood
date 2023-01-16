package com.myfood.springboot_myfood.domain.clients.service;

import ch.qos.logback.core.net.server.Client;
import com.myfood.springboot_myfood.domain.clients.dto.ClientDto;
import com.myfood.springboot_myfood.domain.clients.entity.ClientEntity;
import com.myfood.springboot_myfood.domain.clients.repository.ClientRepository;
import com.myfood.springboot_myfood.domain.payload.request.LoginRequest;
import com.myfood.springboot_myfood.domain.payload.request.SignUpRequest;
import com.myfood.springboot_myfood.domain.payload.request.UpdateRequest;
import com.myfood.springboot_myfood.domain.payload.response.JWTResponse;
import com.myfood.springboot_myfood.exception.AppException;
import com.myfood.springboot_myfood.exception.Error;
import com.myfood.springboot_myfood.plugins.IdGenerator;
import com.myfood.springboot_myfood.security.AuthClientDetails;
import com.myfood.springboot_myfood.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;

import java.util.Base64;

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
                .id_cliente(cEntity.getId_cliente())
                .nombre(cEntity.getNombre())
                .email(cEntity.getEmail())
                .telefono(cEntity.getTelefono())
                .avatar(cEntity.getAvatar())
                .contraseña(cEntity.getContraseña())
                .build();
    }

    public JWTResponse responseToken(ClientEntity entity) {
        return JWTResponse.builder()
                .token(jwtUtils.encode(entity.getId_cliente())) //! Check if email or ID
                .type("Bearer")
                .email(entity.getEmail())
                .nombre(entity.getNombre())
                .avatar(entity.getAvatar())
                .build();
    }

    @Override
    public JWTResponse registration(final SignUpRequest data) {
        clientRepository.findByNombreOrEmail(data.getNombre(), data.getEmail())
                .stream()
                .findAny()
                .ifPresent(entity -> {
                    throw new AppException(Error.DUPLICATED_USER);
                });

        ClientEntity clientEntity = ClientEntity.builder()
                .id_cliente(IdGenerator.generateWithLength(10))
                .nombre(data.getNombre())
                .email(data.getEmail())
                .contraseña(passwordEncoder.encode(data.getContraseña()))
                .telefono(data.getTelefono())
                .avatar("https://api.multiavatar.com/" + new String(Base64.getEncoder().encode(data.getNombre().getBytes())) + ".png")
                .build();

        clientRepository.save(clientEntity);
        return responseToken(clientEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public JWTResponse login(LoginRequest credentials) {
        ClientEntity cEntity = clientRepository.findByEmail(credentials.getEmail())
                .filter(client ->
                        passwordEncoder.matches(
                                credentials.getContraseña(),
                                client.getContraseña()))
                .orElseThrow(() ->
                        new AppException(Error.LOGIN_INFO_INVALID));

        return responseToken(cEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public ClientDto currentUser(AuthClientDetails client) {
        ClientEntity clientEntity = clientRepository.
                findById(client.getId_cliente())
                .orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));

        return convertEntityToDto(clientEntity);
    }

    @Transactional
    @Override
    public ClientDto update(UpdateRequest newData, final AuthClientDetails clientDetails) {
        ClientEntity cEntity = clientRepository
                .findById(clientDetails.getId_cliente())
                .orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));

        if (newData.getNombre() != null) {
            cEntity.setNombre(newData.getNombre());
        }

        if (newData.getTelefono() != null) {
            cEntity.setTelefono(newData.getTelefono());
        }

        if (newData.getContraseña() != null) {
            cEntity.setContraseña(passwordEncoder.encode(newData.getContraseña()));
        }

        clientRepository.save(cEntity);
        return convertEntityToDto(cEntity);
    }
}
