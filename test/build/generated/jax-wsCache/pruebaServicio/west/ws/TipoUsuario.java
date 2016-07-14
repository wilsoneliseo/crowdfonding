
package west.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoUsuario.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoUsuario">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ADMINISTRADOR"/>
 *     &lt;enumeration value="USUARIO"/>
 *     &lt;enumeration value="DESCONOCIDO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoUsuario")
@XmlEnum
public enum TipoUsuario {

    ADMINISTRADOR,
    USUARIO,
    DESCONOCIDO;

    public String value() {
        return name();
    }

    public static TipoUsuario fromValue(String v) {
        return valueOf(v);
    }

}
