package uni.eszterhazy.keretrendszer.exceptions;

public class RosszErtekeles extends Throwable {
    public RosszErtekeles(String ertekeles) {
        super(String.valueOf(ertekeles));
    }
}
