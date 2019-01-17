package com.fyj.weixin.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


// springdata已经帮我们实现了基本的增删改查
// CRUD --> Create（增）, Read（查）, Update（改）, Delete（删）
public interface APIKEYRepository extends PagingAndSortingRepository<APIKEYEntity, Long> {

	@Query(value = "select APPID,APIKEY,SECRETKEY,USEDTIME from APIKEYTBL where USEDTIME = (select min(b.USEDTIME) from APIKEYTBL b)", nativeQuery = true)
	public List<APIKEYEntity> getNextApiKey();
	
	@Modifying
	@Query(value = "UPDATE APIKEYTBL SET USEDTIME = current_timestamp WHERE APPID =:#{#data.appid} and APIKEY=:#{#data.apikey} and SECRETKEY=:#{#data.secretkey}", nativeQuery = true)
	public int updateApiKeyTime(@Param("data") APIKEYEntity apiData);
}