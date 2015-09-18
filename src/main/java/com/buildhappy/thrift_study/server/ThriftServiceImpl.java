package com.buildhappy.thrift_study.server;

import com.buildhappy.thrift_study.thriftGen.ThriftService;
import com.buildhappy.thrift_study.thriftGen.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThriftServiceImpl implements ThriftService.Iface {
    public void addUser(User user) throws org.apache.thrift.TException {
        System.out.println(user.userId + "  " + user.username + "  " + user.password);
    }

    public User queryUser(int id) throws org.apache.thrift.TException {
        System.out.println(id);
        User user = new User();
        user.userId = 00;
        user.username = "FFF";
        user.password = "NNN";
        return user;
    }

    public List<User> queryUserList() throws org.apache.thrift.TException {
        User user = new User();
        user.userId = 00;
        user.username = "FFF";
        user.password = "NNN";
        User user1 = new User();
        user1.userId = 0;
        user1.username = "FFF1";
        user1.password = "NNN1";
        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user1);
        return list;
    }

    public Map<String, String> queryUserNamePass() throws org.apache.thrift.TException {
        User user = new User();
        user.userId = 00;
        user.username = "FFF";
        user.password = "NNN";
        Map<String, String> map = new HashMap<String, String>();
        map.put("password", user.password);
        map.put("useranme", user.username);
        return map;
    }

    public Map<Integer, User> queryUserMap() throws org.apache.thrift.TException {
        User user = new User();
        user.userId = 00;
        user.username = "FFF";
        user.password = "NNN";
        User user1 = new User();
        user1.userId = 0;
        user1.username = "FFF1";
        user1.password = "NNN1";
        Map<Integer, User> map = new HashMap<Integer, User>();
        map.put(user.userId, user);
        map.put(user.userId, user1);
        return map;
    }
}