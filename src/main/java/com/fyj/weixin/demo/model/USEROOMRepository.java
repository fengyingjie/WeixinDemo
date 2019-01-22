package com.fyj.weixin.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


// springdata已经帮我们实现了基本的增删改查
// CRUD --> Create（增）, Read（查）, Update（改）, Delete（删）
public interface USEROOMRepository extends PagingAndSortingRepository<LoginEntity, Long> {

	@Query(value = " SELECT\r\n" + 
			" loginTBL.Name as Name,\r\n" + 
			" loginTBL.ID as ID,\r\n" +
			" loginTBL.ROOMID as ROOMID,\r\n" +
			" loginTBL.LoginTime as LoginTime\r\n" +
			"FROM loginTBL left join ROOMTBL on (ROOMTBL.ROOMID = loginTBL.ROOMID)\r\n" + 
			"WHERE\r\n" + 
			"     ROOMTBL.ROOMNAME = :#{#roomName} AND to_timestamp(loginTBL.LoginTime,'YYYYMMDDHH24MISS') < current_timestamp", nativeQuery = true)
	public List<LoginEntity> findUserinRoom(@Param("roomName") String roomName);
}