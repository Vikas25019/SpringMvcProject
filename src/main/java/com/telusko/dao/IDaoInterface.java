package com.telusko.dao;

import org.springframework.ui.Model;

import java.net.UnknownHostException;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.List;

public interface IDaoInterface<T, U> {
    int create(T t, U u, LinkedHashMap<String, String> data, Model model) throws SQLException, ClassNotFoundException, UnknownHostException;

    LinkedHashMap<String, String> retrieve(T t, U u, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException;

    int update(T t, U u, LinkedHashMap<String, String> data ,String columnName) throws SQLException, ClassNotFoundException, UnknownHostException;

    void delete(T t, U u ,LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException;

    <P,E> boolean isIdPresent(P p, E e, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException;

    List<LinkedHashMap<String,String>> retrieveAll(T t, U u) throws SQLException, ClassNotFoundException;

    String viewMapping(T t,U u ,LinkedHashMap<String, String> data ,String columnName) throws SQLException, ClassNotFoundException;
}
