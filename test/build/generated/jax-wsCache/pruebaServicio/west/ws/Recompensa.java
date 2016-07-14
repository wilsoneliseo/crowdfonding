
package west.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recompensa complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recompensa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idrecompensa" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreIni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="p_minimo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recompensa", propOrder = {
    "cantidad",
    "descripcion",
    "idrecompensa",
    "nombre",
    "nombreIni",
    "pMinimo"
})
public class Recompensa {

    protected int cantidad;
    protected String descripcion;
    protected int idrecompensa;
    protected String nombre;
    protected String nombreIni;
    @XmlElement(name = "p_minimo")
    protected double pMinimo;

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad idrecompensa.
     * 
     */
    public int getIdrecompensa() {
        return idrecompensa;
    }

    /**
     * Define el valor de la propiedad idrecompensa.
     * 
     */
    public void setIdrecompensa(int value) {
        this.idrecompensa = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreIni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreIni() {
        return nombreIni;
    }

    /**
     * Define el valor de la propiedad nombreIni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreIni(String value) {
        this.nombreIni = value;
    }

    /**
     * Obtiene el valor de la propiedad pMinimo.
     * 
     */
    public double getPMinimo() {
        return pMinimo;
    }

    /**
     * Define el valor de la propiedad pMinimo.
     * 
     */
    public void setPMinimo(double value) {
        this.pMinimo = value;
    }

}
