package uni.eszterhazy.keretrendszer.exceptions;

public class CimNemLehetNull extends Throwable {
    public CimNemLehetNull(String cim) {
        super(cim);
    }
}
