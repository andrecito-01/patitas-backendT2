package pe.edu.cibertec.patitas_backendT2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import org.springframework.stereotype.Service;
import pe.edu.cibertec.patitas_backendT2.dto.LoginRequestDTO;
import pe.edu.cibertec.patitas_backendT2.dto.LogoutRequestDTO;
import pe.edu.cibertec.patitas_backendT2.service.AutenticacionService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

@Service
public class AutenticacionServiceimpl implements AutenticacionService {

    @Autowired
    ResourceLoader resourceLoader;



    @Override
    public String[] validarUsuarios(LoginRequestDTO loginRequestDTO) throws IOException  {



        String[] datosUsuario=null;
        Resource resource= resourceLoader.getResource("classpath:usuario.txt");


        try(BufferedReader br =new BufferedReader(new FileReader(resource.getFile()))){



            String linea;
            while((linea = br.readLine()) != null){
                String[] datos=linea.split(";");
                if(loginRequestDTO.tipoDocumento().equals(datos[0]) && loginRequestDTO.numeroDocumento().equals(datos[1])
                && loginRequestDTO.password().equals(datos[2])){



                    datosUsuario =new String[2];
                    datosUsuario[0]=datos[3];
                    datosUsuario[1]=datos[4];


                }
            }

        }catch (IOException e){
            datosUsuario=null;
            throw new IOException(e);
        }






        return datosUsuario;
    }




    @Override
    public Date cerrarSesionUsuarios(LogoutRequestDTO logoutRequestDTO) throws IOException {

        Date fechaLogout = null;
        Resource resource = resourceLoader.getResource("classpath:auditorias.txt");
        Path rutaArchivo = Paths.get(resource.getURI());

        try (BufferedWriter bw = Files.newBufferedWriter(rutaArchivo, StandardOpenOption.APPEND)) {

           //inicializamos fecha
            fechaLogout = new Date();


            StringBuilder sb = new StringBuilder();
            sb.append(logoutRequestDTO.tipoDocumento());
            sb.append(";");
            sb.append(logoutRequestDTO.numeroDocumento());
            sb.append(";");
            sb.append(fechaLogout);


            bw.write(sb.toString());
            bw.newLine();
            System.out.println(sb.toString());

        } catch (IOException e) {

            fechaLogout = null;
            throw new IOException(e);

        }

        return fechaLogout;

    }

}
