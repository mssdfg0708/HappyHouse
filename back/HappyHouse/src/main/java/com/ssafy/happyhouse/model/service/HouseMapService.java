package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.house.FavoriteAptRequest;
import com.ssafy.happyhouse.model.house.HouseInfoDto;
import com.ssafy.happyhouse.model.house.SidoGugunCodeDto;

public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> userAddress(String address);
	int addFavoriteApt(FavoriteAptRequest request);
	int deleteFavoriteApt(FavoriteAptRequest request);
	List<HouseInfoDto> getFavoriteAptList(String email);
}
