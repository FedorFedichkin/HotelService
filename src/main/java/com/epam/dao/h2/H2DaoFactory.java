package com.epam.dao.h2;

import com.epam.connection_pool.ConnectionPool;
import com.epam.dao.interfaces.*;

public class H2DaoFactory implements AbstractDaoFactory {
    private final UserDao userDao;
    private final OrderDao orderDao;
    private final RoomDao roomDao;
    private final ConnectionPool connectionPool;

    public H2DaoFactory(ConnectionPool connectionPool) {
        this.userDao = new H2UserDao(connectionPool);
        this.orderDao = new H2OrderDao(connectionPool);
        this.roomDao = new H2RoomDao(connectionPool);
        this.connectionPool = connectionPool;
    }

    @Override
    public UserDao createUserDAO() {
        return userDao;
    }

    @Override
    public OrderDao createOrderDAO() {
        return orderDao;
    }

    @Override
    public RoomDao createRoomDAO() {
        return roomDao;
    }

    @Override
    //TODO implementation??
    public void close() throws Exception {

    }
}
