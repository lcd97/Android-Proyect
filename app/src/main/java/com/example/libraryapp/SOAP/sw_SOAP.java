package com.example.libraryapp.SOAP;

import com.example.libraryapp.MainActivity;
import com.example.libraryapp.bean.Book;
import com.example.libraryapp.bean.Login;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class sw_SOAP {

    private static final String NAMESPACE = "http://manoamigalibrary.somee.com/"; //ESPACIO DE NOMBRE UTILIZADO EN EL WS
    private static final String URL = "http://manoamigalibrary.somee.com/librarysw.asmx"; //URL DONDE SE ENCUENTRA EL SERVICE

    public static Login login(String email, String password) {
        final String METHOD_NAME = "Login"; //METODO A EJECUTAR
        final String SOAP_ACTION = "http://manoamigalibrary.somee.com/Login"; //CONCAT NAMESPACE + METHOD NAME

        Login login = new Login();

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("username", email);
        request.addProperty("contrasenia", password);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            //this is the actual part that will call the webservice
            androidHttpTransport.call(SOAP_ACTION, envelope);
            // Get the SoapResult from the envelope body.

            SoapObject resultHead = (SoapObject) envelope.bodyIn;
            SoapObject result = (SoapObject) resultHead.getProperty(0);

            if (result != null) {
                //Get the first property and change the label text
                login.setUserId(result.getProperty("UserId").toString());
                login.setUserName(result.getProperty("UserName").toString());
                login.setEmail(result.getProperty("Email").toString());
                login.setRol(result.getProperty("Rol").toString());
                login.setMensaje(result.getProperty("Mensaje").toString());
                login.setLogged(Boolean.parseBoolean(result.getProperty("IsLogged").toString()));
            }
            else{
                login.setMensaje("No se puedo acceder al servidor. Intentelo de nuevo");
                login.setLogged(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return login;
    }
}//FIN DE LA CLASE