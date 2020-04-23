package com.telusko.dao;

import com.telusko.dao.IDaoInterface;
import com.telusko.dao.MysqlDatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.net.UnknownHostException;
import java.sql.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component("daoImpl")
public class DaoImplementation<T, U> implements IDaoInterface<T, U> {

    @Autowired
    MysqlDatabaseOperation<T> mysqlDatabaseOperation;

    @Override
    public void create(T t, U u, LinkedHashMap<String, String> data, Model model) throws SQLException, ClassNotFoundException {

        if (u instanceof MysqlDatabaseOperation) {
            mysqlDatabaseOperation.insertIntoDatabase(t, data, model);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }

    }

    @Override
    public LinkedHashMap<String, String> retrieve(T t, U u, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException {
        LinkedHashMap<String, String> viewData;
        if (u instanceof MysqlDatabaseOperation) {
            viewData = mysqlDatabaseOperation.retrieveFromDatabase(t, data);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
        return viewData;
    }

    @Override
    public int update(T t, U u, LinkedHashMap<String, String> data, String columnName) throws SQLException, ClassNotFoundException, UnknownHostException {
        int status;
        if (u instanceof MysqlDatabaseOperation) {
            status = mysqlDatabaseOperation.updateInDatabase(t, data, columnName);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
        return status;
    }

    @Override
    public void delete(T t, U u, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException {
        if (u instanceof MysqlDatabaseOperation) {
            mysqlDatabaseOperation.deleteFromDatabase(t, data);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
    }

    @Override
    public List<LinkedHashMap<String, String>> retrieveAll(T t, U u) throws SQLException, ClassNotFoundException {
        List<LinkedHashMap<String, String>> data;
        if (u instanceof MysqlDatabaseOperation) {
            data = mysqlDatabaseOperation.retrieveAll(t);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
        return data;
    }

    @Override
    public <P, E> boolean isIdPresent(P p, E e, LinkedHashMap<String, String> data) throws SQLException, ClassNotFoundException, UnknownHostException {
        if (e instanceof MysqlDatabaseOperation) {
            return mysqlDatabaseOperation.checkIdMysql(p, data);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
    }

    @Override
    public String viewMapping(T t, U u, LinkedHashMap<String, String> data, String columnName) throws SQLException, ClassNotFoundException {
        String id;
        if (u instanceof MysqlDatabaseOperation) {
            id = mysqlDatabaseOperation.viewMappingInfo(t, data, columnName);
        } else {
            throw new UnsupportedOperationException("Invalid Operation");
        }
        return id;
    }

}
