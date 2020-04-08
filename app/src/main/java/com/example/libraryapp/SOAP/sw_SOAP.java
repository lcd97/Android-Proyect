package com.example.libraryapp.SOAP;

import com.example.libraryapp.MainActivity;
import com.example.libraryapp.bean.Book;
import com.example.libraryapp.bean.Category;
import com.example.libraryapp.bean.Customer;
import com.example.libraryapp.bean.Login;
import com.example.libraryapp.bean.Register;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.Date;
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

    public static Register reegister(String Email, String Pass, String permiso, String Codigo, String Nombres, String Apellidos){
        final String METHOD_NAME = "AccountSecurity"; //METODO A EJECUTAR
        final String SOAP_ACTION = "http://manoamigalibrary.somee.com/AccountSecurity"; //CONCAT NAMESPACE + METHOD NAME

        Register register = new Register();

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("Email", Email);
        request.addProperty("Pass", Pass);
        request.addProperty("permiso", permiso);
        request.addProperty("Codigo", Codigo);
        request.addProperty("Nombres", Nombres);
        request.addProperty("Apellidos", Apellidos);

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
                register.setCodigo(result.getProperty("Codigo").toString());
                register.setEmail(result.getProperty("Email").toString());
                register.setMensaje(result.getProperty("Mensaje").toString());
                register.setRegister(Boolean.parseBoolean( result.getProperty("Register").toString()));
            }
            else{
                register.setMensaje("No se puedo acceder al servidor. Intentelo de nuevo");
                register.setRegister(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return register;

    }

     public static ArrayList<Book> bookArrayList(){
        ArrayList<Book> books = new ArrayList<Book>();

        final String METHOD_NAME = "BookList"; //METODO A EJECUTAR
        final String SOAP_ACTION = "http://manoamigalibrary.somee.com/BookList"; //CONCAT NAMESPACE + METHOD NAME

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

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


            //esto lo adapte de como lo tenias vos :v


            SoapObject tempData =(SoapObject) result.getProperty("BookWS");

            if (result != null) {
                //Get the first property and change the label text
                for (int currentBook = 0; currentBook < result.getPropertyCount(); currentBook++) {

                    SoapObject temp =(SoapObject) result.getProperty(currentBook);

                    Book b = new Book();

                    b.setId(Integer.parseInt(temp.getProperty(0).toString()));
                    b.setCodigo(temp.getProperty("Codigo").toString());
                    b.setTitulo(temp.getProperty("Titulo").toString());
                    b.setISBN(temp.getProperty("ISBN").toString());
                    b.setAutor(temp.getProperty("Autor").toString());
                    b.setPortada(temp.getProperty("Portada").toString().getBytes());
                    //b.setAdquisicion(Date.valueOf(temp.getProperty("Adquisicion").toString()));
                    b.setDescripcion(temp.getProperty("Descripcion").toString());
                    b.setNumeroCopia(Integer.parseInt(temp.getProperty("MateriaId").toString()));

                    books.add(b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    public static ArrayList<Category> CategoryArrayList(){
        ArrayList<Category> categories = new ArrayList<Category>();

        final String METHOD_NAME = "BookCategory"; //METODO A EJECUTAR
        final String SOAP_ACTION = "http://manoamigalibrary.somee.com/BookCategory"; //CONCAT NAMESPACE + METHOD NAME

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

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

            SoapObject tempData =(SoapObject) result.getProperty("CategoryWS");

            if (result != null) {
                //Get the first property and change the label text
                for (int currentCategory = 0; currentCategory < result.getPropertyCount(); currentCategory++) {

                    SoapObject temp =(SoapObject) result.getProperty(currentCategory);

                    Category b = new Category();

                    b.setId(Integer.parseInt(temp.getProperty(0).toString()));
                    b.setCodigo(temp.getProperty("Codigo").toString());
                    b.setDescripcion(temp.getProperty("Descripcion").toString());

                    categories.add(b);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    public static Customer CustomerData(String Email){
        Customer customer = new Customer();
        final String METHOD_NAME = "CustomerData"; //METODO A EJECUTAR
        final String SOAP_ACTION = "http://manoamigalibrary.somee.com/CustomerData"; //CONCAT NAMESPACE + METHOD NAME

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("email", Email);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.dotNet = true;

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            //this is the actual part that will call the webservice
            androidHttpTransport.call(SOAP_ACTION, envelope);
            // Get the SoapResult from the envelope body.

            SoapObject resultHead = (SoapObject) envelope.bodyIn;

            for (int i = 0; i < resultHead.getPropertyCount(); i++) {

                SoapObject result = (SoapObject) resultHead.getProperty(i);

                //Get the first property and change the label text
                customer.setId(Integer.parseInt(result.getProperty("Id").toString()));
                customer.setCodigo(result.getProperty("Codigo").toString());
                customer.setNames(result.getProperty("Nombres").toString());
                customer.setLastName(result.getProperty("Apellidos").toString());
                customer.setEmail(result.getProperty("Email").toString());
                customer.setFoto(result.getProperty("Foto").toString().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customer;
    }

    public static boolean PasswordChange(String Email, String oldPass, String newPass){
        boolean band = false;

        final String METHOD_NAME = "PasswordChange"; //METODO A EJECUTAR
        final String SOAP_ACTION = "http://manoamigalibrary.somee.com/PasswordChange"; //CONCAT NAMESPACE + METHOD NAME


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("Email", Email);
        request.addProperty("OldPassword", oldPass);
        request.addProperty("NewPassword", newPass);

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

            if(Boolean.parseBoolean(result.getProperty("band").toString())){
                band = true;
            }
            else
                band = false;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return band;
    }

}//FIN DE LA CLASE