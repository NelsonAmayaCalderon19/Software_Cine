/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelVO;

/**
 *
 * @author USUARIO
 */
public class AsientoVO {
    
    private String letra;
    private String numero;
    private EspectadorVO ocupado;

    public String getLetra() {
        return letra;
    }

    public String getNumero() {
        return numero;
    }

    public EspectadorVO getOcupado() {
        return ocupado;
    }

    public void setOcupado(EspectadorVO ocupado) {
        this.ocupado = ocupado;
    }

    public AsientoVO(String letra, String numero, EspectadorVO ocupado) {
        this.letra = letra;
        this.numero = numero;
        this.ocupado = ocupado;
    }
}
