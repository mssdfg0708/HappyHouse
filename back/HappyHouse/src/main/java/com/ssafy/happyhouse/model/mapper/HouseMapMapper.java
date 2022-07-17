package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.house.FavoriteAptRequest;
import com.ssafy.happyhouse.model.house.HouseInfoDto;
import com.ssafy.happyhouse.model.house.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	long getDongCode(Map<String, String> map);
	List<HouseInfoDto> getHouseList(long dongCode);
	int addFavoriteApt(FavoriteAptRequest request);
	int deleteFavoriteApt(FavoriteAptRequest request);
	HouseInfoDto getFavoriteApt(Long aptCode);
	List<Long> getAptCodeList(String email);
}
