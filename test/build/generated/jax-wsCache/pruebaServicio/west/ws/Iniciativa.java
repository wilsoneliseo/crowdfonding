
package west.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para iniciativa complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="iniciativa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idiniciativa" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="metaeconomica" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="metatiempo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreIni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subcategoria" type="{http://ws.west/}subcategoria" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://ws.west/}usuario" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "iniciativa", propOrder = {
    "estado",
    "idiniciativa",
    "metaeconomica",
    "metatiempo",
    "nombreIni",
    "subcategoria",
    "usuario"
})
public class Iniciativa {

    protected int estado;
    protected int idiniciativa;
    protected Double metaeconomica;
    protected String metatiempo;
    protected String nombreIni;
    protected Subcategoria subcategoria;
    protected Usuario usuario;

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     */
    public void setEstado(int value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad idiniciativa.
     * 
     */
    public int getIdiniciativa() {
        return idiniciativa;
    }

    /**
     * Define el valor de la propiedad idiniciativa.
     * 
     */
    public void setIdiniciativa(int value) {
        this.idiniciativa = value;
    }

    /**
     * Obtiene el valor de la propiedad metaeconomica.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMetaeconomica() {
        return metaeconomica;
    }

    /**
     * Define el valor de la propiedad metaeconomica.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMetaeconomica(Double value) {
        this.metaeconomica = value;
    }

    /**
     * Obtiene el valor de la propiedad metatiempo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetatiempo() {
        return metatiempo;
    }

    /**
     * Define el valor de la propiedad metatiempo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetatiempo(String value) {
        this.metatiempo = value;
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
     * Obtiene el valor de la propiedad subcategoria.
     * 
     * @return
     *     possible object is
     *     {@link Subcategoria }
     *     
     */
    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    /**
     * Define el valor de la propiedad subcategoria.
     * 
     * @param value
     *     allowed object is
     *     {@link Subcategoria }
     *     
     */
    public void setSubcategoria(Subcategoria value) {
        this.subcategoria = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link Usuario }
     *     
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link Usuario }
     *     
     */
    public void setUsuario(Usuario value) {
        this.usuario = value;
    }

}
