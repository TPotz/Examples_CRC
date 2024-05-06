
package com.example.web.demo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tin.paket package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TECAJNALISTA_QNAME = new QName("", "TECAJNA_LISTA");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tin.paket
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TECAJNALISTAType }
     * 
     */
    public TECAJNALISTAType createTECAJNALISTAType() {
        return new TECAJNALISTAType();
    }

    /**
     * Create an instance of {@link ItemType }
     * 
     */
    public ItemType createItemType() {
        return new ItemType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TECAJNALISTAType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "TECAJNA_LISTA")
    public JAXBElement<TECAJNALISTAType> createTECAJNALISTA(TECAJNALISTAType value) {
        return new JAXBElement<TECAJNALISTAType>(_TECAJNALISTA_QNAME, TECAJNALISTAType.class, null, value);
    }

}
