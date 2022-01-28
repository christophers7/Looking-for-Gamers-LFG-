package com.revature.p2_lfg.repository.DAO.interfaces;

public interface DaoRepo {
    public void createNewUser();

    public void getAllUsers();

    public void getUserByID();

    public void deleteUserByID();

    public void setUsernameByID();

    public void setPasswordbyID();
}
