package com.artemsilantev.core.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

public class JaxbUtils {

  private JaxbUtils() {
  }

  public static <E> E deserialize(String textToParse, Class<E> clazz) throws JAXBException {
    var targetJaxb = JAXBContext.newInstance(clazz);
    var streamSource = new StreamSource(new StringReader(textToParse));
    var unmarshaller = targetJaxb.createUnmarshaller();
    JAXBElement<E> jaxbElement = unmarshaller.unmarshal(streamSource, clazz);
    return jaxbElement.getValue();
  }

  public static <E> String serialize(E entity, String qName, Class<E> clazz) throws JAXBException {
    var targetJaxb = JAXBContext.newInstance(clazz);
    var jaxbElement = new JAXBElement<E>(new QName(qName), clazz, entity);
    var marshaller = targetJaxb.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
    var stringWriter = new StringWriter();
    marshaller.marshal(jaxbElement, stringWriter);
    return stringWriter.toString();
  }

}
