package pe.edu.cibertec.patitas_backendT2.dto;

import java.util.Date;

public record LogoutResponseDTO(Boolean resultado, Date fecha, String mensajeError) {
}
