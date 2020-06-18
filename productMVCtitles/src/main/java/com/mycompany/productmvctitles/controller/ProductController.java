/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.productmvctitles.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mycompany.productmvctitles.entities.Contact;
import com.mycompany.productmvctitles.entities.Product;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author linhtn
 */
@Controller
public class ProductController {

    private String rootUrl = "http://localhost:1122/";
    private int timeOut = 60000;
    static Logger logger2 = Logger.getLogger(ProductController.class);
    
    @RequestMapping(value = "/redirectCreateProduct", method = RequestMethod.GET)
    public String redirectCreateProduct(Model m) {
        m.addAttribute("command", new Product());
        return "createProduct";
    }

    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String getListProduct(ModelMap model) {
        String data = "cannot get products";
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> hashMap = new ArrayList<HashMap<String, Object>>();

        try {
            data = this.getAPI(this.rootUrl + "api/products", String.class, this.timeOut);
            hashMap = objectMapper.readValue(data, List.class);
        } catch (JsonProcessingException ex) {
            logger2.error(ex.getMessage(), ex);
        } catch (URISyntaxException ex) {
            logger2.error(ex.getMessage(), ex);
        }

        model.addAttribute("listProduct", hashMap);
        return "product_page";
    }

    @RequestMapping(value = "/productList/find_by_name", method = RequestMethod.GET)
    public String searchByName(@RequestParam String productName, @RequestParam String pageIndex, ModelMap model) {
        String data = "cannot get products";
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> listHashMap = new ArrayList<HashMap<String, Object>>();

        String url = this.rootUrl + "api/products/_search_by_name?productName=" + productName + "&pageIndex=" + pageIndex + "&pageSize=" + "3";
        try {
            data = this.getAPI(url, String.class, this.timeOut);
            HashMap<String, Object> a = objectMapper.readValue(data, HashMap.class);
            listHashMap = (List<HashMap<String, Object>>) a.get("data");
        } catch (JsonProcessingException ex) {
            logger2.error(ex.getMessage(), ex);
        } catch (URISyntaxException ex) {
            logger2.error(ex.getMessage(), ex);
        }

        model.addAttribute("listProduct", listHashMap);
        return "productList";

    }

    @RequestMapping(value = "/create_product", method = RequestMethod.POST)
    public String createProduct(ModelMap model, Product product) {
        Boolean data = false;
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> listHashMap = new ArrayList<HashMap<String, Object>>();
        String url = this.rootUrl + "api/add_product";
        try {
           data =  this.restPost(url, Boolean.class, this.timeOut, product);
        }catch (URISyntaxException ex) {
            logger2.error(ex.getMessage(), ex);
        }
       
        model.addAttribute("status", data);
        return "redirect:productList";

    }

    @RequestMapping(value = "/delete_product", method = RequestMethod.POST)
    public String deleteProduct(@RequestParam int productId, ModelMap model) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, Object>> listHashMap = new ArrayList<HashMap<String, Object>>();

        String url = this.rootUrl + "api/product?id=" + productId;
        Object status = this.restDelete(url, Boolean.class, this.timeOut);
        model.addAttribute("status", status.toString());
        return "redirect:productList";

    }

    public static <T extends Object> T getAPI(String url, Class<T> responseType, int timeOut) throws URISyntaxException, RestClientException {
        try {
            URI uri = new URI(url);
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            simpleClientHttpRequestFactory.setConnectTimeout(timeOut);
            simpleClientHttpRequestFactory.setReadTimeout(timeOut);
            RestTemplate rest = new RestTemplate(simpleClientHttpRequestFactory);
            logger2.info("Request To:" + url);

            T result = result = rest.getForObject(uri, responseType);
            logger2.info("Response From:" + url + ", Timeout=" + timeOut);
            return result;
        } catch (Exception e) {
            logger2.info("Exception to GET DATA, EX=" + e.getMessage());
            return null;
        }

    }

    public static <T extends Object> T restPost(String url, Class<T> responseType, int timeOut, Object postObject) throws URISyntaxException, RestClientException {
        try {
            Gson gson = new Gson();
            URI uri = new URI(url);
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            simpleClientHttpRequestFactory.setConnectTimeout(timeOut);
            simpleClientHttpRequestFactory.setReadTimeout(timeOut);
            RestTemplate rest = new RestTemplate(simpleClientHttpRequestFactory);
            logger2.info("Request To:" + url + ", Timeout=" + timeOut + "\nreqData:" + gson.toJson(postObject));
            T result = rest.postForObject(uri, postObject, responseType);
            logger2.info("Response From:" + url + ", Timeout=" + timeOut + "\noutData:" + gson.toJson(result, responseType));
            return result;
        } catch (Exception exx) {
            logger2.fatal("Exception Request To:" + url + ", Timeout=" + timeOut + ", Exception:", exx);
            return null;
        }
    }

    public static <T extends Object> T restDelete(String url, Class<T> responseType, int timeOut) {

        try {
            URI uri = new URI(url);
            SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            simpleClientHttpRequestFactory.setConnectTimeout(timeOut);
            simpleClientHttpRequestFactory.setReadTimeout(timeOut);
            RestTemplate rest = new RestTemplate(simpleClientHttpRequestFactory);
            rest.getMessageConverters()
                    .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            T result = (T) rest.exchange(uri, HttpMethod.DELETE, null, responseType);
            return result;
        } catch (URISyntaxException ex) {
            logger2.fatal("Exception :" + ex.getMessage());
        }
        return null;
    }
}
