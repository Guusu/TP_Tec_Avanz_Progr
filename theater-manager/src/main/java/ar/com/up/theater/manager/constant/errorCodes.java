package ar.com.up.theater.manager.constant;

public enum errorCodes {
    SHOW_CREATE_ERROR("SH00","Ocurrio un error al crear el espectaculo."),
    INVALID_HALL_ERROR("SH01","Sala invalida."),
    INVALID_SHOW_TYPE_ERROR ("SH02","Tipo de Show invalido."),
    INVALID_ARTIST_ERROR ("SH03","Artista invalida."),
    INVALID_SHOW_DATA_ERROR ("SH04","Datos del espectaculo incorrectos."),
    INVALID_DATE_ERROR ("SH05","No Puede crearse un Espectaculo en ese horario."),
    INVALID_SHOW_NAME("SH06","Ya Existe otro espectaculo con ese nombre."),
    SHOW_NOT_EXISTS("SH07","No existe el Show mencionado"),
    TICKET_BUY_ERROR("TB01","Error al intentar procesar la transaccion");

    private String code;
    private String message;

    errorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
