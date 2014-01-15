/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.byteslounge.websockets;

import java.text.MessageFormat;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 *
 * @author tamajit
 */
public class CustomConfigurator extends ServerEndpointConfig.Configurator{
//     HttpSession httpSession;
//
//    // modifyHandshake() is called before getEndpointInstance()!
//    @Override
//    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response)
//    {
//        httpSession = (HttpSession) request.getHttpSession();
//        super.modifyHandshake(sec, request, response);
//    }
//
//    @Override
//    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException
//    {
//        T endpoint = super.getEndpointInstance(endpointClass);
//
//        if (endpoint instanceof WebSocketTest)
//            ((WebSocketTest) endpoint).setHttpSession(httpSession); // <-- The injection point
//        else
//            throw new InstantiationException(
//                    MessageFormat.format("Expected instanceof \"{0}\". Got instanceof \"{1}\".",
//                    WebSocketTest.class, endpoint.getClass()));
//
//        return endpoint;
//    }
        @Override
    public void modifyHandshake(ServerEndpointConfig config, 
                                HandshakeRequest request, 
                                HandshakeResponse response)
    {
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        config.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }
}
