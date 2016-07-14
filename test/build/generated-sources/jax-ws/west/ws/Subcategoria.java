
package west.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para subcategoria complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="subcategoria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoria" type="{http://ws.west/}categoria" minOccurs="0"/>
 *         &lt;element name="idsubcategoria" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreSub" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subcategoria", propOrder = {
    "categoria",
    "idsubcategoria",
    "nombreSub"
})
public class Subcategoria {

    protected Categoria categoria;
    protected int idsubcategoria;
    protected String nombreSub;

    /**
     * Obtiene el valor de la propiedad categoria.
     * 
     * @return
     *     possible object is
     *     {@link Categoria }
     *     
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Define el valor de la propiedad categoria.
     * 
     * @param value
     *     allowed object is
     *     {@link Categoria }
     *     
     */
    public void setCategoria(Categoria value) {
        this.categoria = value;
    }

    /**
     * Obtiene el valor de la propiedad idsubcategoria.
     * 
     */
    public int getIdsubcategoria() {
        return idsubcategoria;
    }

    /**
     * Define el valor de la propiedad idsubcategoria.
     * 
     */
    public void setIdsubcategoria(int value) {
        this.idsubcategoria = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreSub.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreSub() {
        return nombreSub;
    }

    /**
     * Define el valor de la propiedad nombreSub.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreSub(String value) {
        this.nombreSub = value;
    }

}
