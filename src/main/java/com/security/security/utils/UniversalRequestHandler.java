package com.security.security.utils;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class UniversalRequestHandler {

    private Map<String, Object> data = new HashMap<>();

    public UniversalRequestHandler() {
        this.data = new HashMap<>();
    }

    public Integer getIntegerValue(String key){
        if (this.data==null)
            return null;

        Object o = this.data.get(key);
        if (o==null) {
            return null;
        }

        if (o instanceof Integer){
            return (Integer) o;
        }else if(o instanceof String) {
            try {
                return Integer.parseInt((String) o);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }


    public Short getShortValue(String key){
        if (this.data==null)
            return null;

        Object o = this.data.get(key);
        if (o==null) {
            return null;
        }

        if (o instanceof Short){
            return (Short) o;
        }else if(o instanceof String) {
            try {
                return Short.parseShort((String) o);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public Byte getByteValue(String key){
        if (this.data==null)
            return null;

        Object o = this.data.get(key);
        if (o==null) {
            return null;
        }

        if (o instanceof Byte){
            return (Byte) o;
        }else if(o instanceof String) {
            try {
                return Byte.parseByte((String) o);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public Long getLongValue(String key){
        if (this.data==null)
            return null;

        Object o = this.data.get(key);
        if (o==null) {
            return null;
        }

        if (o instanceof Integer){
            return (Long) o;
        }else if(o instanceof String) {
            try {
                return Long.parseLong((String) o);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public Float getFloatValue(String key){
        if (this.data==null)
            return null;

        Object o = this.data.get(key);
        if (o==null) {
            return null;
        }

        if (o instanceof Float){
            return (Float) o;
        }else if(o instanceof String) {
            try {
                return Float.parseFloat((String) o);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public Double getDoubleValue(String key){
        if (this.data==null)
            return null;

        Object o = this.data.get(key);
        if (o==null) {
            return null;
        }

        if (o instanceof Integer){
            return (Double) o;
        }else if(o instanceof String) {
            try {
                return Double.parseDouble((String) o);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public String getStringValue(String key){
        if (this.data==null || this.data.get(key)==null)
            return null;
        return (String) this.data.get(key);
    }

    public <T> List<T> getListValue(String key, Class<T> classType){
        ObjectMapper objectMapper = new ObjectMapper();
        if (this.data==null || this.data.get(key)==null)
            return null;

        Object o = this.data.get(key);
        List<T> typedList = new ArrayList<>();
        try {
            String s = objectMapper.writeValueAsString((o));
            typedList= objectMapper.readValue(s, new TypeReference<List<T>>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return typedList;
    }




}
