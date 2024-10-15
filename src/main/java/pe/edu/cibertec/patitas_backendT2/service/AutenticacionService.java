package pe.edu.cibertec.patitas_backendT2.service;

import pe.edu.cibertec.patitas_backendT2.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backendT2.dto.LogoutRequestDTO;


import java.io.IOException;
import java.util.Date;

public interface AutenticacionService {


    String[] validarUsuarios(LoginRequestDTO loginRequestDTO) throws IOException;

     Date cerrarSesionUsuarios(LogoutRequestDTO logoutRequestDTO) throws IOException;

}
