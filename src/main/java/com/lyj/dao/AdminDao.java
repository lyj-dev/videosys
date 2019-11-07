package com.lyj.dao;

import com.lyj.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {
    Admin login(Admin admin);
}
