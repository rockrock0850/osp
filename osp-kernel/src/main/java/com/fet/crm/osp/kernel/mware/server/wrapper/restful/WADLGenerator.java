/**
 * Copyright (c) 2017 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.kernel.mware.server.wrapper.restful;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;
import org.jvnet.ws.wadl.Application;
import org.jvnet.ws.wadl.Doc;
import org.jvnet.ws.wadl.Param;
import org.jvnet.ws.wadl.ParamStyle;
import org.jvnet.ws.wadl.Representation;
import org.jvnet.ws.wadl.Request;
import org.jvnet.ws.wadl.Resource;
import org.jvnet.ws.wadl.Resources;
import org.jvnet.ws.wadl.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * OSP RESTful Web Services WADL generator. <br>
 * The WADL document is XML description of HTTP-based web services. <br>
 * 
 * view the WADL document --> http://localhost[:port]/rest/
 * 
 * @author RichardHuang
 */
@Controller
public class WADLGenerator {
	
	String xsNamespace="http://www.w3.org/2001/XMLSchema";
	String tnsNamespace = "http://rest.wrapper.server.mware.kernel.osp.crm.fet.com/";
	
    @Autowired(required = false)
    private RequestMappingHandlerMapping handlerMapping;
    
    @Autowired(required = false)
    private WebApplicationContext webApplicationContext;
 
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {"application/xml"}) 
    public @ResponseBody Application generateWadl(HttpServletRequest request) {
        Application result = new Application();
        Doc doc = new Doc();
        doc.setTitle("OSP RESTful Web Services WADL");
        result.getDoc().add(doc);
        Resources wadResources = new Resources();
        wadResources.setBase(getBaseUrl(request));
          
        Map<RequestMappingInfo, HandlerMethod> handletMethods = handlerMapping.getHandlerMethods();
        
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handletMethods.entrySet()) {
            HandlerMethod handlerMethod = entry.getValue();
            Object object = handlerMethod.getBean();
            Object bean = webApplicationContext.getBean(object.toString());
             
            boolean isRestContoller = bean.getClass().isAnnotationPresent(RestController.class);
            
            if (!isRestContoller) {
                continue;
            }
            
            RequestMappingInfo mappingInfo = entry.getKey();
              
            Set<String> pattern =  mappingInfo.getPatternsCondition().getPatterns();
            Set<RequestMethod> httpMethods =  mappingInfo.getMethodsCondition().getMethods();
            ProducesRequestCondition producesRequestCondition = mappingInfo.getProducesCondition();
            Set<MediaType> mediaTypes = producesRequestCondition.getProducibleMediaTypes();
            Resource wadlResource = null;
            
            for (RequestMethod httpMethod : httpMethods) {
                org.jvnet.ws.wadl.Method wadlMethod = new org.jvnet.ws.wadl.Method();
      
                for (String uri : pattern) {
                    wadlResource = createOrFind(uri,  wadResources); 
                    wadlResource.setPath(uri);      
                }
                  
                wadlMethod.setName(httpMethod.name());
                Method javaMethod = handlerMethod.getMethod();
                wadlMethod.setId(javaMethod.getName());
                Doc wadlDocMethod = new Doc();
                wadlDocMethod.setTitle(javaMethod.getDeclaringClass().getSimpleName() + "." + javaMethod.getName());
                wadlMethod.getDoc().add(wadlDocMethod);
                  
                // Request
                Request wadlRequest = new Request();
                  
                Annotation[][] annotations = javaMethod.getParameterAnnotations();
                Class<?>[] paramTypes = javaMethod.getParameterTypes();
                int i = 0;
                
                for (Annotation[] annotation : annotations) {
                    Class<?> paramType = paramTypes[i];
                    i++;
                    
                    for (Annotation annotation2 : annotation) {
                        if (annotation2 instanceof RequestParam) {
                            RequestParam param2 = (RequestParam) annotation2;
                            QName qName = convertJavaToXMLType(paramType);
                            Param wadlParam = new Param();
                            wadlParam.setName(param2.value());
                            wadlParam.setStyle(ParamStyle.QUERY);
                            wadlParam.setRequired(param2.required());
                            String defaultValue = cleanDefault(param2.defaultValue());
                            
                            if (!defaultValue.equals("")) {
                                wadlParam.setDefault(defaultValue);
                            }
                            
                            wadlParam.setType(qName);
                            wadlRequest.getParam().add(wadlParam);
                            
                        } if (annotation2 instanceof RequestBody) {
                            RequestBody param2 = (RequestBody) annotation2;
                            QName qName = convertJavaToXMLType(paramType);
                            Param wadlParam = new Param();
                            wadlParam.setName(paramType.getName());
                            wadlParam.setStyle(ParamStyle.QUERY);
                            wadlParam.setRequired(param2.required());
                            wadlParam.setType(qName);
                            wadlRequest.getParam().add(wadlParam);
                            
                        } else if (annotation2 instanceof PathVariable) {
                            PathVariable param2 = (PathVariable) annotation2;
                            QName qName = convertJavaToXMLType(paramType);
                            Param waldParam = new Param();
                            waldParam.setName(param2.value());
                            waldParam.setStyle(ParamStyle.TEMPLATE);
                            waldParam.setRequired(true);
                            wadlRequest.getParam().add(waldParam);
                            waldParam.setType(qName);
                        }
                    }
                }
                
                if (!wadlRequest.getParam().isEmpty()) {
                    wadlMethod.setRequest(wadlRequest);
                }
                  
                // Response
                if (!mediaTypes.isEmpty()) {
                    Response wadlResponse = new Response();
                    ResponseStatus status = handlerMethod.getMethodAnnotation(ResponseStatus.class);
                    
                    if (status == null) {
                        wadlResponse.getStatus().add((long)(HttpStatus.OK.value()));
                        
                    } else {
                        HttpStatus httpcode = status.value();
                        wadlResponse.getStatus().add((long)httpcode.value());
                    }
 
                    for (MediaType mediaType : mediaTypes) {
                        Representation wadlRepresentation = new Representation();
                        wadlRepresentation.setMediaType(mediaType.toString());
                        wadlResponse.getRepresentation().add(wadlRepresentation);
                    }
                    
                    wadlMethod.getResponse().add(wadlResponse);
                }
                  
                wadlResource.getMethodOrResource().add(wadlMethod);
            }
        }
        
        result.getResources().add(wadResources);
          
        return result;
    }
    
    private QName convertJavaToXMLType(Class<?> type) {
        QName qName = null;
        String className = type.getSimpleName();
        
        try {
            if (type.newInstance() instanceof String) {
                qName = new QName(xsNamespace, "string", "xs");
                
            } else if (type.newInstance() instanceof Integer) {
                qName = new QName(xsNamespace, "int", "xs");
                
            } else {
                qName = new QName(tnsNamespace, className, "tns");
            }
            
            return qName;
            
        } catch (Exception e) { }
        
        return new QName(tnsNamespace, "object", "tns");
    }
    
    private Resource createOrFind(String uri, Resources wadResources) {
          List<Resource> current = wadResources.getResource();
          
          for (Resource resource:current) {
              if (resource.getPath().equalsIgnoreCase(uri)) {
                  return resource;
              }
          }
          
          Resource wadlResource = new  Resource();
          current.add(wadlResource);
          return wadlResource;
    }
    
    private String getBaseUrl(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        requestUri = StringUtils.substringBeforeLast(requestUri, "/");
        
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + requestUri;
    }
      
    private String cleanDefault(String value) {
        value = value.replaceAll("\t", "");
        value = value.replaceAll("\n", "");
        
        return value;
    }
    
}
