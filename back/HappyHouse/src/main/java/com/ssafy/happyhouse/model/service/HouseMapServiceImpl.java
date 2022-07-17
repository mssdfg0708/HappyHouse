package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.house.FavoriteAptRequest;
import com.ssafy.happyhouse.model.house.HouseInfoDto;
import com.ssafy.happyhouse.model.house.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;
import com.ssafy.happyhouse.model.mapper.UserMapper;
import com.ssafy.happyhouse.model.user.ModifyUserResponse;
import com.ssafy.happyhouse.model.user.User;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		List<HouseInfoDto> data = houseMapMapper.getDongInGugun(gugun);
		for (HouseInfoDto house: data) {
			System.out.println(house);
		}
		return data;
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> userAddress(String address) {
		String[] data = address.split(" ");
		String sido = data[0];
		System.out.println(address);
		if (sido.equals("서울")) {
			sido = "서울특별시";
		}
		if (sido.equals("전남")) {
			sido = "전라남도";
		}
		if (sido.equals("전북")) {
			sido = "전라북도";
		}
		if (sido.equals("경남")) {
			sido = "경상남도";
		}
		if (sido.equals("경북")) {
			sido = "경상북도";
		}
		if (sido.equals("충남")) {
			sido = "충청남도";
		}
		if (sido.equals("충북")) {
			sido = "충청북도";
		}
		if (sido.equals("경기")) {
			sido = "경기도";
		}
		if (sido.equals("강원")) {
			sido = "강원도";
		}
		if (sido.equals("광주")) {
			sido = "광주광역시";
		}
		if (sido.equals("대구")) {
			sido = "대구광역시";
		}
		if (sido.equals("울산")) {
			sido = "울산광역시";
		}
		if (sido.equals("부산")) {
			sido = "부산광역시";
		}
		if (sido.equals("대전")) {
			sido = "대전광역시";
		}
		if (sido.equals("인천")) {
			sido = "인천광역시";
		}
		
		String gugun = null;
		String dong = null;
		if (sido.equals("세종특별자치시")) {
			System.out.println("세종!");
			gugun = data[0];
			dong = data[1];
		}
		else {
			gugun = data[1];
			dong = data[2];
		}
		
		if (dong.charAt(dong.length()-1) == '구') {
			gugun += " " + dong;
			dong = data[3];
		}
		
		System.out.println(sido);
		System.out.println(gugun);
		System.out.println(dong);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sidoName", sido);
		map.put("gugunName", gugun);
		map.put("dongName", dong);

		long dongCode = houseMapMapper.getDongCode(map);
		List<HouseInfoDto> result = houseMapMapper.getHouseList(dongCode);
		System.out.println("result" + result);
		return result;
	}

	@Override
	public int addFavoriteApt(FavoriteAptRequest request) {
		int result = houseMapMapper.addFavoriteApt(request);
		return result;
		
	}

	@Override
	public int deleteFavoriteApt(FavoriteAptRequest request) {
		int result = houseMapMapper.deleteFavoriteApt(request);
		return result;
	}

	@Override
	public List<HouseInfoDto> getFavoriteAptList(String email) {
		List<Long> aptCodeList = houseMapMapper.getAptCodeList(email);
		
		List<HouseInfoDto> result = new ArrayList<>();
		for (Long aptCode: aptCodeList) {
			result.add(houseMapMapper.getFavoriteApt(aptCode));
		}
		return result;
	}

}
