package pe.edu.cibertec.patitas_backendT2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.patitas_backendT2.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backendT2.dto.LoginResponseDTO;
import pe.edu.cibertec.patitas_backendT2.dto.LogoutRequestDTO;
import pe.edu.cibertec.patitas_backendT2.dto.LogoutResponseDTO;
import pe.edu.cibertec.patitas_backendT2.service.AutenticacionService;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;


@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    AutenticacionService autenticacionService;


    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO){


        try {


            Thread.sleep(Duration.ofSeconds(4));

            String[] datosUsuario=autenticacionService.validarUsuarios(loginRequestDTO);

            System.out.println("Respuesta Backend: " + Arrays.toString(datosUsuario));

            if(datosUsuario == null){

                return new LoginResponseDTO("01","Error: Usuarios no encontrado","","");


            }


            return new LoginResponseDTO("00","",datosUsuario[0],datosUsuario[1]);


        } catch (Exception e) {


            System.out.println(e.getMessage());

            return new LoginResponseDTO("99","Error:Ocurrio un Problema","","");



        }



    }










    @PostMapping("/logout")
    public LogoutResponseDTO logout(@RequestBody LogoutRequestDTO logoutRequestDTO) {

        try {

            Thread.sleep(Duration.ofSeconds(3));

            Date fechaLogout = autenticacionService.cerrarSesionUsuarios(logoutRequestDTO);
            System.out.println("Respuesta backend: " + fechaLogout);

            if (fechaLogout == null) {
                return new LogoutResponseDTO(false, null, "Error No se pudo registrar ");
            }
            return new LogoutResponseDTO(true, fechaLogout, "");

        } catch(Exception e) {

            System.out.println(e.getMessage());
            return new LogoutResponseDTO(false, null, "Error Ocurrió un problema");

        }

    }







}
