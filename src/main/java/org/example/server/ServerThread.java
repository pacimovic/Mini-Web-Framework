package org.example.server;

import org.example.annotations.Param;
import org.example.api.request.Header;
import org.example.api.request.Helper;
import org.example.api.request.Request;
import org.example.api.request.enums.MethodInfo;
import org.example.api.request.exceptions.RequestNotValidException;
import org.example.api.response.JsonResponse;
import org.example.api.response.Response;
import org.example.main.MainClass;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerThread implements Runnable{

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket){
        this.socket = socket;

        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {

            Request request = this.generateRequest();
            if(request == null) {
                in.close();
                out.close();
                socket.close();
                return;
            }

            //Ovde implementiramo logiku za pozivanje odgovarajuce metode
            String[] location = request.getLocation().split("\\?");
            String route = request.getMethod() + "@" + location[0];
            MainClass mainClass = MainClass.getInstance();
            System.out.println(route);
            if(mainClass.getRouteMap().containsKey(route)){
                boolean flag = true;
                Method method = mainClass.getRouteMap().get(route);


                List<String> requestParameters = new ArrayList<>();

                //ovde prodji kroz parametre metode i njihove anotacije
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    if(parameters[i].isAnnotationPresent(Param.class)){
                        Param param = parameters[i].getAnnotation(Param.class);
                        String paramName = param.value();

                        //proveriti da li u requestu imamo parametre sa vrednosti paramName
                        if(request.getParameters().containsKey(paramName)){
                            requestParameters.add(request.getParameter(paramName));
                        }
                        else{
                            //error da nam nedostaje odgovarajuci parametar
                            System.err.println("Missing parameter in request!");
                            flag = false;
                        }
                    }
                    else{
                        //error da ne postoji anotacija na parametru
                        System.err.println("Missing annotation!");
                        flag = false;
                    }
                }

                //uzmemo vec gotovu instancu klase ove metode
                Object obj = MainClass.getInstance().getMethodMap().get(method);
                if(flag) method.invoke(obj, requestParameters.toArray(new String[0]));

                System.out.println(mainClass.getRouteMap().get(route).getName());
            }
            else{
                //error da ne postoji odgovarajuca ruta u kontroleru
                System.err.println("There is no specific route in controller!");
            }



            // Response example
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("route_location", request.getLocation());
            responseMap.put("route_method", request.getMethod().toString());
            responseMap.put("parameters", request.getParameters());
            Response response = new JsonResponse(responseMap);

            out.println(response.render());

            in.close();
            out.close();
            socket.close();

        } catch (IOException | RequestNotValidException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Request generateRequest() throws IOException, RequestNotValidException {
        String command = in.readLine();
        if(command == null) {
            return null;
        }

        String[] actionRow = command.split(" ");
        MethodInfo method = MethodInfo.valueOf(actionRow[0]);
        String route = actionRow[1];
        Header header = new Header();
        HashMap<String, String> parameters = Helper.getParametersFromRoute(route);

        do {
            command = in.readLine();
            String[] headerRow = command.split(": ");
            if(headerRow.length == 2) {
                header.add(headerRow[0], headerRow[1]);
            }

        } while(!command.trim().equals(""));

        if(method.equals(MethodInfo.POST)) {
            int contentLength = Integer.parseInt(header.get("Content-Length"));
            char[] buff = new char[contentLength];
            in.read(buff, 0, contentLength);
            String parametersString = new String(buff);

            HashMap<String, String> postParameters = Helper.getParametersFromString(parametersString);
            for (String parameterName : postParameters.keySet()) {
                parameters.put(parameterName, postParameters.get(parameterName));
            }
        }

        Request request = new Request(method, route, header, parameters);

        return request;
    }
}
