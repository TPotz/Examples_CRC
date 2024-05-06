
package com.example.web.demo;
import com.example.web.demo.adapteri.DateAdapter;
import com.example.web.demo.adapteri.DoubleAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.Objects;


/**
 * <p>Java class for itemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="itemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="broj_tecajnice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="drzava" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sifra_valute" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="valuta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="jedinica">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="kupovni_tecaj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="srednji_tecaj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prodajni_tecaj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemType", propOrder = {
    "brojTecajnice",
    "datum",
    "drzava",
        "drzavaIso",
    "sifraValute",
    "valuta",
    "jedinica",
    "kupovniTecaj",
    "srednjiTecaj",
    "prodajniTecaj","id"
})
public class ItemType {

    @XmlElement(name = "broj_tecajnice", required = true)
    public String brojTecajnice;
    @XmlElements({@XmlElement(name = "datum"),@XmlElement(name = "datum_primjene")})
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date datum;
    @XmlElement(required = true)
    public String drzava;
    @XmlElement(name= "drzava_iso")
    public String drzavaIso;
    @XmlElement(name = "sifra_valute", required = true)
    public String sifraValute;
    @XmlElement(required = true)
    public String valuta;
    @XmlElement(required = true)
    public String jedinica;
    @XmlElement(name = "kupovni_tecaj", required = true)
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    public Double kupovniTecaj;
    @XmlElement(name = "srednji_tecaj", required = true)
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    public Double srednjiTecaj;
    @XmlElement(name = "prodajni_tecaj", required = true)
    @XmlJavaTypeAdapter(DoubleAdapter.class)
    public Double prodajniTecaj;
    public Integer id;

    public ItemType() {
    }

    public String getDrzavaIso() {
        return drzavaIso;
    }

    /**
     * Gets the value of the brojTecajnice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */

    public String getBrojTecajnice() {
        return brojTecajnice;
    }

    /**
     * Sets the value of the brojTecajnice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrojTecajnice(String value) {
        this.brojTecajnice = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatum(Date value) {
        this.datum = value;
    }

    /**
     * Gets the value of the drzava property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrzava() {
        return drzava;
    }

    /**
     * Sets the value of the drzava property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrzava(String value) {
        this.drzava = value;
    }

    /**
     * Gets the value of the sifraValute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraValute() {
        return sifraValute;
    }

    /**
     * Sets the value of the sifraValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraValute(String value) {
        this.sifraValute = value;
    }

    /**
     * Gets the value of the valuta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValuta() {
        return valuta;
    }

    /**
     * Sets the value of the valuta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValuta(String value) {
        this.valuta = value;
    }

    /**
     * Gets the value of the jedinica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJedinica() {
        return jedinica;
    }

    /**
     * Sets the value of the jedinica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJedinica(String value) {
        this.jedinica = value;
    }

    /**
     * Gets the value of the kupovniTecaj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Double getKupovniTecaj() {
        return kupovniTecaj;
    }

    /**
     * Sets the value of the kupovniTecaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKupovniTecaj(Double value) {
        this.kupovniTecaj = value;
    }

    /**
     * Gets the value of the srednjiTecaj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Double getSrednjiTecaj() {
        return srednjiTecaj;
    }

    /**
     * Sets the value of the srednjiTecaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrednjiTecaj(Double value) {
        this.srednjiTecaj = value;
    }

    /**
     * Gets the value of the prodajniTecaj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Double getProdajniTecaj() {
        return prodajniTecaj;
    }

    /**
     * Sets the value of the prodajniTecaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProdajniTecaj(Double value) {
        this.prodajniTecaj = value;
    }

    public void setDrzavaIso(String drzavaIso) {
        this.drzavaIso = drzavaIso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*@Override
            public String toString() {
                String iso = "";
                if (!Objects.equals(drzavaIso, null))
                {iso = ", drzavaIso=" + drzavaIso;}//ako postoji vrijednost drzavaIso onda ga ispisi

                return "Tečaj{" +
                        "brojTecajnice='" + brojTecajnice + '\'' +
                        ", datum='" + Constants.HR_DATE.format(datum) + '\'' +//prikaži date u string obliku, na temelju HR_DATE patterna
                        ", drzava='" + drzava + '\'' + iso +//ako nema iso onda će ostati prazan
                        ", sifraValute='" + sifraValute + '\'' +
                        ", valuta='" + valuta + '\'' +
                        ", jedinica='" + jedinica + '\'' +
                        ", kupovniTecaj='" + kupovniTecaj + '\'' +
                        ", srednjiTecaj='" + srednjiTecaj + '\'' +
                        ", prodajniTecaj='" + prodajniTecaj + '\'' +
                        '}'+"\n";
            }*/
    @Override
    public String toString() {
        String iso = "";
        if (!Objects.equals(drzavaIso, null))
        {iso = ", drzavaIso=" + drzavaIso;}//ako postoji vrijednost drzavaIso onda ga ispisi

        return "Tečaj{" +
                "brojTecajnice='" + brojTecajnice +
                ", datum='" + Constants.HR_DATE.format(datum) + //prikaži date u string obliku, na temelju HR_DATE patterna
                ", drzava='" + drzava +  iso +//ako nema iso onda će ostati prazan
                ", sifraValute='" + sifraValute +
                ", valuta='" + valuta +
                ", jedinica='" + jedinica +
                ", kupovniTecaj='" + kupovniTecaj +
                ", srednjiTecaj='" + srednjiTecaj +
                ", prodajniTecaj='" + prodajniTecaj +
                '}';
    }

}
