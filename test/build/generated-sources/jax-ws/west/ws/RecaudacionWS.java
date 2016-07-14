
package west.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recaudacionWS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recaudacionWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idIniciativa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recaudacionWS", propOrder = {
    "idIniciativa"
})
public class RecaudacionWS {

    protected String idIniciativa;

    /**
     * Obtiene el valor de la propiedad idIniciativa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdIniciativa() {
        return idIniciativa;
    }

    /**
     * Define el valor de la propiedad idIniciativa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdIniciativa(String value) {
        this.idIniciativa = value;
    }

}
