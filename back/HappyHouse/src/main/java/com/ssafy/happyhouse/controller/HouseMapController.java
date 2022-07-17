package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.house.FavoriteAptListRequest;
import com.ssafy.happyhouse.model.house.FavoriteAptRequest;
import com.ssafy.happyhouse.model.house.HouseInfoDto;
import com.ssafy.happyhouse.model.house.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;
import com.ssafy.happyhouse.model.service.UserService;

@RestController
@CrossOrigin("*")
public class HouseMapController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;
	@Autowired
	private UserService userService;
	
	@GetMapping("map/useraddress/{address}")
	public List<HouseInfoDto> userAddress (@PathVariable("address") String address) {
		List<HouseInfoDto> response = haHouseMapService.userAddress(address);
		return response;
	}
	
	@GetMapping("map/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@GetMapping("map/gugun/{sido}")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@PathVariable("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@GetMapping("map/dong/{gugun}")
	public ResponseEntity<List<HouseInfoDto>> dong(@PathVariable("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@GetMapping("map/apt/{dong}")
	public ResponseEntity<List<HouseInfoDto>> apt(@PathVariable("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@PostMapping("map/favorite")
	public int addFavoriteApt(@RequestBody @Validated FavoriteAptRequest request) {
		int result = haHouseMapService.addFavoriteApt(request);
		return result;
	}
	
	@DeleteMapping("map/favorite")
	public int deleteFavoriteApt(@RequestBody @Validated FavoriteAptRequest request) {
		int result = haHouseMapService.deleteFavoriteApt(request);
		return result;
	}
	
	@GetMapping("map/favorite/{email}/{refreshToken}")
	public ResponseEntity<List<HouseInfoDto>> getFavoriteAptList
	(@PathVariable("email") String email, @PathVariable("refreshToken") String refreshToken) {
		
		List<HouseInfoDto> result = new ArrayList<>();
		if (!refreshToken.equals(userService.getRefreshToken(email))) {
			System.out.println("UNAUTH");
			return new ResponseEntity<List<HouseInfoDto>>(result, HttpStatus.UNAUTHORIZED);
		}
		
		result = haHouseMapService.getFavoriteAptList(email);
		return new ResponseEntity<List<HouseInfoDto>>(result, HttpStatus.OK);
	}
	
}
