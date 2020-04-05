package com.example.libraryapp.SOAP;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public final class Login {

    private static  final  String NAME_SPACE = "http://manoamigalibrary.somee.com/"; //ESPACIO DE NOMBRE UTILIZADO EN EL WS
    private static  final String URL = "http://manoamigalibrary.somee.com/librarysw.asmx/"; //URL DONDE SE ENCUENTRA EL SERVICE
    private static  final  String SERVICE = "librarysw"; //EN EL WSDL ES EL QUE SALE EN EL CAMPO NAME

    public static boolean login() throws IOException, XmlPullParserException {
        //DECLARACION DE LA BANDERA
        boolean band = false;

        final String METHOD = "Login"; //NOMBRE DEL METODO

        //PREPARAR EL REQUEST SOLICITANDO LO QUE VAN A SER LOS ARGUMENTOS - SOAP ACTION
        SoapObject request = new SoapObject(NAME_SPACE, METHOD);

        //ARGUMENTOS
        PropertyInfo pi1 = new PropertyInfo();
        pi1.setName("value"); //ESTE CAMPO SALE CUANDO SE EJECUTA EL METODO
        //pi1.setValue(valor);
        request.addProperty(pi1);

        SoapSerializationEnvelope envolve = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        //EMPAQUETACION PARA XML
        envolve.setOutputSoapObject(request);
        HttpTransportSE transport = new HttpTransportSE(URL.concat(SERVICE)); //CANCATENAN LA URL CON EL METODO A CONSUMIR
        //transport.debug = true; //PARA HACER UN TIPO DE DEBUG
        //CONSUME
        transport.call(NAME_SPACE.concat(METHOD), envolve);
        SoapObject result; //EN ESTA LINEA YA REALIZO EL GET

        try {
            result = (SoapObject) envolve.bodyIn;
        }
        catch(Exception e)
        {
            result = (SoapObject) envolve.getResponse();
        }

        return band;
    }
}